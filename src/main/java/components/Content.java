package components;

import mocks.Client;
import mocks.House;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Content {
    JPanel contentPanel = new JPanel();
    JLabel sectionTitle = this.createSectionTitle();

    public Content() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0x121C22)); // Equivalent to `bg-slate-50`
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the title
        contentPanel.add(sectionTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space below the title

        this.createRandomData();
    }

    public JPanel GetContent() {
        return contentPanel;
    }

    private JLabel createSectionTitle() {
        JLabel sectionTitle = new JLabel("Control de Alquileres");
        sectionTitle.setFont(new Font("Work Sans", Font.BOLD, 28)); // Slightly larger font
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Add spacing around the title
        return sectionTitle;
    }

    private JPanel createRentalItem(House house, boolean isAlternate) {
        JPanel rentalPanel = new JPanel(new BorderLayout()); // BorderLayout for full-width alignment
        rentalPanel.setBackground(isAlternate ? new Color(0x1A2A33) : new Color(0x121C22));
        rentalPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x37474F), 2), // Card border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Internal padding
        ));

        // Inner panel for data using GridLayout
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns with spacing
        infoPanel.setBackground(rentalPanel.getBackground());

        // Add data labels to the grid
        infoPanel.add(this.dataLabel("Name:"));
        infoPanel.add(this.dataLabel(house.getClient().getFullName()));

        infoPanel.add(this.dataLabel("Email:"));
        infoPanel.add(this.dataLabel(house.getClient().getEmail()));

        infoPanel.add(this.dataLabel("Phone:"));
        infoPanel.add(this.dataLabel(house.getClient().getPhone()));

        infoPanel.add(this.dataLabel("DNI:"));
        infoPanel.add(this.dataLabel(house.getClient().getDni()));

        infoPanel.add(this.dataLabel("Card Number:"));
        infoPanel.add(this.dataLabel(String.valueOf(house.getClient().getCardNumber())));

        infoPanel.add(this.dataLabel("Location:"));
        infoPanel.add(this.dataLabel(house.getLocation()));

        infoPanel.add(this.dataLabel("Price:"));
        infoPanel.add(this.dataLabel("$" + house.getMensualPrice()));

        infoPanel.add(this.dataLabel("Entry Date:"));
        infoPanel.add(this.dataLabel(house.getEntryDate().toString()));

        infoPanel.add(this.dataLabel("Exit Date:"));
        infoPanel.add(this.dataLabel(house.getExitDate().toString()));

        // Add the info panel to the center of the rental panel
        rentalPanel.add(infoPanel, BorderLayout.CENTER);

        // Add mouse events for interactivity
        rentalPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Details:\n" + house.toString());
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                rentalPanel.setBackground(new Color(0x2C3E50)); // Highlight on hover
                infoPanel.setBackground(new Color(0x2C3E50));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                rentalPanel.setBackground(isAlternate ? new Color(0x1A2A33) : new Color(0x121C22)); // Revert on exit
                infoPanel.setBackground(rentalPanel.getBackground());
            }
        });

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
            Client client = new Client("Daniel", "daniel_@gmail.com", "123456789", "12345678A", 123456789);
            House house = new House(client, 1, new Date(), new Date(), 1, "Calle 1", 100, 3, 2, 1000);

            JPanel card = createRentalItem(house, i % 2 == 0);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150)); // Full width with
            contentPanel.add(card);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }
}