package pages;

import components.ClientDetailsDialog;
import components.SimpleSearchForm;
import components.buttons.StylishButton;
import db.DatabaseManager;
import mocks.Client;
import reports.ReportGenerator;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsPage extends JPanel {
    private final JPanel clientListPanel = new JPanel(); // Panel for the client cards
    private final SimpleSearchForm searchForm = new SimpleSearchForm(); // Search form
    private final List<Client> clientList = new ArrayList<>(); // List of clients

    public ClientsPage() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x121C22)); // Dark background
        this.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        //añadir el formulario de búsqueda
        this.add(searchForm, BorderLayout.NORTH);


        // Panel for the client cards
        clientListPanel.setLayout(new BoxLayout(clientListPanel, BoxLayout.Y_AXIS));
        clientListPanel.setOpaque(false); // Transparent background
        this.add(clientListPanel, BorderLayout.CENTER);

        // Button for reports
        StylishButton reportButton = new StylishButton("Generar Reporte");
        reportButton.addActionListener(e -> {
            // Generate the report
            ReportGenerator.generateReport();
        });

        // Add the report button to the bottom of the page
        this.add(reportButton, BorderLayout.SOUTH);

        // Create initial data
        retrieveClientsFromDB();
        this.setupSearchListener();
    }

    private JPanel createClientCard(Client client, boolean isAlternate) {
        JPanel clientCard = new JPanel(new BorderLayout()); // BorderLayout for full-width cards
        clientCard.setBackground(isAlternate ? new Color(0x1A2A33) : new Color(0x121C22));
        clientCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x37474F), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Fixed size for the cards
        clientCard.setPreferredSize(new Dimension(2000, 150));
        clientCard.setMaximumSize(new Dimension(2000, 150));

        // Client info
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setBackground(clientCard.getBackground());
        infoPanel.add(dataLabel("Nombre completo: " + client.getFullName()));
        infoPanel.add(dataLabel("Email: " + client.getEmail()));
        infoPanel.add(dataLabel("Teléfono: " + client.getPhone()));
        infoPanel.add(dataLabel("DNI: " + client.getDni()));
        infoPanel.add(dataLabel("Número de tarjeta: " + maskCardNumber(client.getCardNumber())));

        StylishButton detailsButton = new StylishButton("Ver Detalles");
        detailsButton.setPreferredSize(new Dimension(150, 60));
        infoPanel.add(detailsButton);

        detailsButton.addActionListener(e -> {
            // Open a dialog with the client details
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            new ClientDetailsDialog(parentFrame, client).setVisible(true);
        });

        clientCard.add(infoPanel, BorderLayout.CENTER);

        return clientCard;
    }

    private JLabel dataLabel(String data) {
        JLabel label = new JLabel(data);
        label.setFont(new Font("Work Sans", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private String maskCardNumber(long cardNumber) {
        String cardStr = String.valueOf(cardNumber);
        int length = cardStr.length();
        return "*".repeat(length - 4) + cardStr.substring(length - 4);
    }

    private void retrieveClientsFromDB() {
        try (ResultSet clients = DatabaseManager.getAllClients()) {
            while (clients != null && clients.next()) {
                clientList.add(new Client(
                        clients.getString("full_name"),
                        clients.getString("email"),
                        clients.getString("phone"),
                        clients.getString("dni"),
                        clients.getInt("card_number"),
                        clients.getInt("id"),
                        clients.getInt("house_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer clientes: " + e.getMessage());
        }

        // Render all clients initially
        renderClients(clientList);
    }

    private void renderClients(List<Client> filteredClients) {
        clientListPanel.removeAll(); // Clear the current cards

        int index = 0;
        for (Client client : filteredClients) {
            JPanel card = createClientCard(client, index % 2 == 0);
            clientListPanel.add(card);
            clientListPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between cards
            index++;
        }

        clientListPanel.revalidate();
        clientListPanel.repaint();
    }

    private void setupSearchListener() {
        searchForm.addSearchListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterClients();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterClients();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterClients();
            }
        });
    }

    private void filterClients() {
        String searchText = searchForm.getSearchText();

        List<Client> filteredClients = new ArrayList<>();
        for (Client client : clientList) {
            if (client.getFullName().toLowerCase().contains(searchText)) {
                filteredClients.add(client);
            }
        }

        renderClients(filteredClients);
    }
}
