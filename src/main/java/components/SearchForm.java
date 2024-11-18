package components;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;

public class SearchForm extends JPanel {
    JTextField searchField = new JTextField(20); // Text field for search
    JDateChooser dateChooser = new JDateChooser(); // Date chooser for rental date

    public SearchForm() {
        // Use GridBagLayout for better control over component placement
        setLayout(new GridBagLayout());
        setOpaque(false); // Make it transparent

        // GridBagConstraints for controlling component layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5); // Reduced vertical padding around components

        // Create components
        JLabel searchLabel = new JLabel("Buscar: ");
        searchLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        searchLabel.setForeground(Color.WHITE);

        searchField.setFont(new Font("Work Sans", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 25)); // Set a smaller preferred size

        JLabel dateLabel = new JLabel("Buscar por Fecha: ");
        dateLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        dateLabel.setForeground(Color.WHITE);

        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(130, 25));

        // Add components to the panel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(searchField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(dateChooser, gbc);
    }

    public String getSearchText() {
        return searchField.getText().toLowerCase();
    }

    public java.util.Date getSelectedDate() {
        return dateChooser.getDate();
    }

    // Add listeners for text and date filtering
    public void addSearchListener(javax.swing.event.DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }

    public void addDateChangeListener(java.beans.PropertyChangeListener listener) {
        dateChooser.getDateEditor().addPropertyChangeListener(listener);
    }

    public JPanel GetSearchForm() {
        return this;
    }
}
