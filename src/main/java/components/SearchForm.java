package components;

import com.toedter.calendar.JDateChooser;
import components.labels.PageTitle;

import javax.swing.*;
import java.awt.*;

public class SearchForm extends JPanel {
    private JTextField searchField = new JTextField(20); // Text field for search
    private JDateChooser entryDateChooser = new JDateChooser(); // Date chooser for entry date
    private JDateChooser exitDateChooser = new JDateChooser(); // Date chooser for exit date
    private PageTitle pageTitle = new PageTitle("Control de Alquileres");

    public SearchForm() {
        // Use BoxLayout for a simple vertical layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false); // Make the background transparent
        // Título
        this.add(pageTitle, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre título y formulario

        // Panel for date filters
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setOpaque(false); // Transparent background

        // Entry date components
        JLabel entryDateLabel = new JLabel("Fecha de Entrada: ");
        entryDateLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        entryDateLabel.setForeground(Color.WHITE);

        entryDateChooser.setDateFormatString("dd/MM/yyyy");
        entryDateChooser.setPreferredSize(new Dimension(130, 25));

        // Exit date components
        JLabel exitDateLabel = new JLabel("Fecha de Salida: ");
        exitDateLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        exitDateLabel.setForeground(Color.WHITE);

        exitDateChooser.setDateFormatString("dd/MM/yyyy");
        exitDateChooser.setPreferredSize(new Dimension(130, 25));

        // Add components for date filters
        datePanel.add(entryDateLabel);
        datePanel.add(entryDateChooser);
        datePanel.add(exitDateLabel);
        datePanel.add(exitDateChooser);

        // Panel for search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Buscar por Nombre: ");
        searchLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        searchLabel.setForeground(Color.WHITE);

        searchField.setFont(new Font("Work Sans", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 25));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        // Add panels to the main form
        add(datePanel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Small space between panels
        add(searchPanel);
    }


    private JLabel createSectionTitle() {
        JLabel sectionTitle = new JLabel("Control de Alquileres");
        sectionTitle.setFont(new Font("Work Sans", Font.BOLD, 28));
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        return sectionTitle;
    }

    public String getSearchText() {
        return searchField.getText().toLowerCase();
    }

    public java.util.Date getEntryDate() {
        return entryDateChooser.getDate();
    }

    public java.util.Date getExitDate() {
        return exitDateChooser.getDate();
    }

    public void addSearchListener(javax.swing.event.DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }

    public void addDateChangeListener(java.beans.PropertyChangeListener listener) {
        entryDateChooser.getDateEditor().addPropertyChangeListener(listener);
        exitDateChooser.getDateEditor().addPropertyChangeListener(listener);
    }

    public JPanel GetSearchForm() {
        return this;
    }
}
