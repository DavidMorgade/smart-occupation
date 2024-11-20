package pages;

import components.SimpleSearchForm;
import components.buttons.StylishButton;
import db.DatabaseManager;
import mocks.Client;

import javax.swing.*;
import java.awt.*;
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

        // Create initial data
        createRandomClients();
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
        infoPanel.add(detailsButton);

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

    private void createRandomClients() {
        for (int i = 0; i < 5; i++) {
            Client client = new Client("Cliente " + i, "email" + i + "@gmail.com", "123456789", "DNI" + i, 1234567812 + i);
            clientList.add(client);
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
