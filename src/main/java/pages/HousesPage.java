package pages;

import components.HousesDetailsDialog;
import components.SearchForm;
import components.buttons.StylishButton;
import db.DatabaseManager;
import mocks.Client;
import mocks.House;
import reports.ReportGeneratorHouses;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static db.DatabaseManager.getClientFromHouse;

public class HousesPage extends JPanel {
    private JPanel rentalListPanel = new JPanel(); // Panel para las cards
    private List<House> houseList = new ArrayList<>(); // Lista de viviendas
    private SearchForm searchForm = new SearchForm(); // Formulario de búsqueda

    public HousesPage() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x121C22)); // Fondo oscuro
        this.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));


        // Añadir formulario de búsqueda
        this.add(searchForm, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre formulario y lista

        // Panel para las cards
        rentalListPanel.setLayout(new BoxLayout(rentalListPanel, BoxLayout.Y_AXIS));
        rentalListPanel.setOpaque(false); // Fondo transparente
        this.add(rentalListPanel);

        // Crea datos iniciales
        getHousesFromDB();

        // Configura los filtros dinámicos
        setupFilters();

        // Button for reports
        StylishButton reportButton = new StylishButton("Generar Reporte");
        reportButton.addActionListener(e -> {
            // Generate the report
            ReportGeneratorHouses.generateReport();
        });

        // Add the report button to the bottom of the page
        this.add(reportButton, BorderLayout.SOUTH);
    }

    private JPanel createRentalItem(House house, boolean isAlternate) {
        JPanel rentalPanel = new JPanel(new BorderLayout()); // BorderLayout para cards de ancho completo
        rentalPanel.setBackground(isAlternate ? new Color(0x1A2A33) : new Color(0x121C22));
        rentalPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x37474F), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Dejo un tamaño fijo para las cards
        rentalPanel.setPreferredSize(new Dimension(2000, 150));
        rentalPanel.setMaximumSize(new Dimension(2000, 150));
        // Información de la vivienda
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setBackground(rentalPanel.getBackground());
        infoPanel.add(dataLabel("Nombre huésped: " + house.getClient().getFullName()));
        infoPanel.add(dataLabel("Ubicación Vivienda: " + house.getLocation()));
        infoPanel.add(dataLabel("Precio: $" + house.getMensualPrice()));
        infoPanel.add(dataLabel("Fecha entrada: " + house.getEntryDate().toString()));
        infoPanel.add(dataLabel("Fecha salida: " + house.getExitDate().toString()));
        StylishButton detailsButton = new StylishButton("Ver Detalles");
        infoPanel.add(detailsButton);

        detailsButton.addActionListener(e -> {
            HousesDetailsDialog dialog = new HousesDetailsDialog((JFrame) SwingUtilities.getWindowAncestor(this), house);
            dialog.setVisible(true);
        });

        rentalPanel.add(infoPanel, BorderLayout.CENTER);
        return rentalPanel;
    }

    private JLabel dataLabel(String data) {
        JLabel label = new JLabel(data);
        label.setFont(new Font("Work Sans", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private void getHousesFromDB() {

        System.out.println("\nViviendas en la base de datos:a");
        try (ResultSet houses = DatabaseManager.getAllHouses()) {
            while (houses != null && houses.next()) {
                Client client = getClientFromHouse(houses.getInt("id"));
                House house = new House(
                        client,
                        houses.getInt("exp_number"),
                        new java.util.Date(), // Convertir fechas correctamente
                        new java.util.Date(),
                        houses.getInt("id"),
                        houses.getString("location"),
                        houses.getInt("meters"),
                        houses.getInt("rooms"),
                        houses.getInt("bathrooms"),
                        houses.getInt("mensual_price")
                );
                houseList.add(house);
                System.out.println("Ubicación: " + house.getLocation() + ", Precio Mensual: $" + house.getMensualPrice() + "id: " + house.getHouseId());
            }
        } catch (SQLException e) {
            System.out.println("Error al leer viviendas: " + e.getMessage());
        }
        // Renderiza inicialmente todas las viviendas
        renderContent(houseList);
    }

    private void renderContent(List<House> filteredHouses) {
        rentalListPanel.removeAll(); // Limpia las cards actuales

        int index = 0;
        for (House house : filteredHouses) {
            JPanel card = createRentalItem(house, index % 2 == 0);
            rentalListPanel.add(card);
            rentalListPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre cards
            index++;
        }

        rentalListPanel.revalidate();
        rentalListPanel.repaint();
    }

    private void setupFilters() {
        // Listener para el campo de búsqueda
        searchForm.addSearchListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterContent();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterContent();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterContent();
            }
        });

        // Listener para los seleccionadores de fecha
        searchForm.addDateChangeListener(evt -> filterContent());
    }

    private void filterContent() {
        String searchText = searchForm.getSearchText();
        Date entryDate = searchForm.getEntryDate();
        Date exitDate = searchForm.getExitDate();

        List<House> filteredHouses = new ArrayList<>();
        for (House house : houseList) {
            boolean matchesSearch = searchText.isEmpty() || house.getClient().getFullName().toLowerCase().contains(searchText);
            boolean matchesEntryDate = (entryDate == null || !house.getEntryDate().before(entryDate));
            boolean matchesExitDate = (exitDate == null || !house.getExitDate().after(exitDate));

            if (matchesSearch && matchesEntryDate && matchesExitDate) {
                filteredHouses.add(house);
            }
        }

        renderContent(filteredHouses);
    }
}
