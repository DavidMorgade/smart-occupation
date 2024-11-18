package components;

import components.buttons.StylishButton;
import mocks.Client;
import mocks.House;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Content {
    JPanel contentPanel = new JPanel();
    JLabel sectionTitle = this.createSectionTitle();

    public Content() {
        // Use GridBagLayout for better control over component positioning
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(0x121C22)); // Equivalent to `bg-slate-50`
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40)); // No top padding

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0); // Small gap between components

        // Title section
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(sectionTitle, gbc);

        // Add Search Form section
        SearchForm searchForm = new SearchForm();
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(searchForm, gbc);

        // Add a small gap after the search form
        gbc.gridy = 2;
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // Add rental data below the search form
        this.createRandomData(gbc);
    }

    public JPanel GetContent() {
        return contentPanel;
    }

    private JLabel createSectionTitle() {
        JLabel sectionTitle = new JLabel("Control de Alquileres");
        sectionTitle.setFont(new Font("Work Sans", Font.BOLD, 28)); // Slightly larger font
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Adjust spacing around the title
        return sectionTitle;
    }

    private void createRandomData(GridBagConstraints gbc) {
        for (int i = 0; i < 5; i++) {
            Client client = new Client("Daniel", "daniel_@gmail.com", "123456789", "12345678A", 123456789);
            House house = new House(client, 1, new Date(), new Date(), 1, "Calle 1", 100, 3, 2, 1000);

            JPanel card = createRentalItem(house, i % 2 == 0);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

            // Adding each card to contentPanel
            gbc.gridx = 0;
            gbc.gridy = 3 + i;  // Add a new row for each rental item
            contentPanel.add(card, gbc);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        }
    }

    private JPanel createRentalItem(House house, boolean isAlternate) {
        JPanel rentalPanel = new JPanel(new BorderLayout());
        rentalPanel.setBackground(isAlternate ? new Color(0x1A2A33) : new Color(0x121C22));
        rentalPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x37474F), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setBackground(rentalPanel.getBackground());

        infoPanel.add(this.dataLabel("Nombre huesped : " + house.getClient().getFullName()));
        infoPanel.add(this.dataLabel("Ubicación Vivienda: " + house.getLocation()));
        infoPanel.add(this.dataLabel("Precio: $" + house.getMensualPrice()));
        infoPanel.add(this.dataLabel("Fecha de entrada: " + house.getEntryDate().toString()));

        StylishButton detailsButton = new StylishButton("Ver detalles");
        infoPanel.add(detailsButton);

        rentalPanel.add(infoPanel, BorderLayout.CENTER);

        return rentalPanel;
    }

    private JLabel dataLabel(String data) {
        JLabel label = new JLabel(data);
        label.setFont(new Font("Work Sans", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        return label;
    }
}
