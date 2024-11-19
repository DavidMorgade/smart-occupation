package pages;

import components.SearchForm;
import components.buttons.StylishButton;
import mocks.Client;
import mocks.House;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HousesPage {
    private JPanel contentPanel = new JPanel(); // Panel principal
    private JPanel rentalListPanel = new JPanel(); // Panel para las cards
    private List<House> houseList = new ArrayList<>(); // Lista de viviendas
    private SearchForm searchForm = new SearchForm(); // Formulario de búsqueda

    public HousesPage() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(0x121C22)); // Fondo oscuro
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));


        // Añadir formulario de búsqueda
        contentPanel.add(searchForm, BorderLayout.NORTH);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre formulario y lista

        // Panel para las cards
        rentalListPanel.setLayout(new BoxLayout(rentalListPanel, BoxLayout.Y_AXIS));
        rentalListPanel.setOpaque(false); // Fondo transparente
        contentPanel.add(rentalListPanel);

        // Crea datos iniciales
        createRandomData();

        // Configura los filtros dinámicos
        setupFilters();
    }

    public JPanel GetContent() {
        return contentPanel;
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
        infoPanel.add(new StylishButton("Ver Detalles"));

        rentalPanel.add(infoPanel, BorderLayout.CENTER);
        return rentalPanel;
    }

    private JLabel dataLabel(String data) {
        JLabel label = new JLabel(data);
        label.setFont(new Font("Work Sans", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private void createRandomData() {
        for (int i = 0; i < 5; i++) {
            Client client = new Client("Huésped " + i, "email" + i + "@gmail.com", "123456789", "12345678A", 123456789);
            House house = new House(client, 1, new Date(), new Date(), 1, "Calle " + i, 100, 3, 2, 1000);
            houseList.add(house);
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
