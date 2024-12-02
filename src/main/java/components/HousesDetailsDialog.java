package components;

import components.buttons.StylishButton;
import mocks.House;

import javax.swing.*;
import java.awt.*;

public class HousesDetailsDialog extends JDialog {

    public HousesDetailsDialog(JFrame parent, House house) {
        super(parent, "Detalles del Cliente", true); // Modal dialog

        this.setLayout(new BorderLayout());
        this.setSize(800, 600);
        this.setLocationRelativeTo(parent);

        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailsPanel.setBackground(new Color(0x1A2A33));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch horizontally

        // Add labels and values to the grid
        addLabelAndValue(detailsPanel, gbc, "Ocupada por: ", house.getClient().getFullName(), 0, 0);
        addLabelAndValue(detailsPanel, gbc, "Fecha de entrada: ", String.valueOf(house.getEntryDate()), 0, 1);
        addLabelAndValue(detailsPanel, gbc, "Fecha de salida: ", String.valueOf(house.getExitDate()), 0, 2);
        addLabelAndValue(detailsPanel, gbc, "Identificador Vivienda: ", String.valueOf(house.getHouseId()), 0, 3);
        addLabelAndValue(detailsPanel, gbc, "Ubicación: ", house.getLocation(), 0, 4);
        addLabelAndValue(detailsPanel, gbc, "Metros Cuadrados: ", String.valueOf(house.getMeters()), 0, 5);
        addLabelAndValue(detailsPanel, gbc, "Numero Habitaciones: ", String.valueOf(house.getRooms()), 0, 6);
        addLabelAndValue(detailsPanel, gbc, "Precio Mensual: ", String.valueOf(house.getMensualPrice() + "$"), 0, 7);

        this.add(detailsPanel, BorderLayout.CENTER);

        // Close button
        StylishButton closeButton = new StylishButton("Cerrar");
        closeButton.addActionListener(e -> this.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0x1A2A33));
        buttonPanel.add(closeButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addLabelAndValue(JPanel panel, GridBagConstraints gbc, String label, String value, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Work Sans", Font.BOLD, 16));
        fieldLabel.setForeground(Color.WHITE);
        panel.add(fieldLabel, gbc);

        gbc.gridx = x + 1; // Move to the next column for the value
        JLabel fieldValue = new JLabel(value);
        fieldValue.setFont(new Font("Work Sans", Font.PLAIN, 16));
        fieldValue.setForeground(Color.WHITE);
        panel.add(fieldValue, gbc);
    }

    private String maskCardNumber(long cardNumber) {
        String cardStr = String.valueOf(cardNumber);
        int length = cardStr.length();
        return "*".repeat(length - 4) + cardStr.substring(length - 4);
    }
}
