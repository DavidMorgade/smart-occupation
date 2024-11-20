package components;

import components.labels.PageTitle;

import javax.swing.*;
import java.awt.*;

public class SimpleSearchForm extends JPanel {
    private JTextField searchField = new JTextField(20); // Campo de texto para búsqueda
    private final PageTitle pageTitle = new PageTitle("Control de Clientes");

    public SimpleSearchForm() {
        // Configuración de diseño
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false); // Fondo transparente
        // Add the title
        this.add(pageTitle, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Space between the search form and the list
        // center the title
        pageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiqueta para el campo de búsqueda
        JLabel searchLabel = new JLabel("Buscar Cliente: ");
        searchLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        searchLabel.setForeground(Color.WHITE);
        // añadir espacio entre la etiqueta y el campo de texto

        // Configuración del campo de texto
        searchField.setFont(new Font("Work Sans", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 25));
        searchField.setToolTipText("Escriba el nombre del cliente para buscar...");

        // Añadir componentes al panel
        add(searchLabel, BorderLayout.CENTER);
        add(searchField, BorderLayout.NORTH);
    }

    // Método para obtener el texto ingresado en el campo de búsqueda
    public String getSearchText() {
        return searchField.getText().toLowerCase().trim();
    }

    // Método para agregar un listener al campo de búsqueda
    public void addSearchListener(javax.swing.event.DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }
}
