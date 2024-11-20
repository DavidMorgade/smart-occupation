package pages;

import components.Header;
import components.buttons.StylishButton;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JLabel errorLabel = new JLabel("");
    private final JPanel mainPanel;
    private final Header header;
    private boolean isLogged;

    public LoginPage(JPanel mainPanel, Header header, boolean isLogged) {
        this.header = header;
        this.isLogged = isLogged;
        this.mainPanel = mainPanel;
        this.setLayout(new GridBagLayout()); // Center all components
        this.setBackground(new Color(0x121C22)); // Dark background

        // placeholder para los comentarios
        usernameField.setToolTipText("Ingrese su usuario");
        passwordField.setToolTipText("Ingrese su contraseña");

        // Container for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(0x1A2A33));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x37474F), 2),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        // Title
        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Work Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Username Field
        JPanel usernamePanel = createInputPanel("Usuario:", usernameField);

        // Password Field
        JPanel passwordPanel = createInputPanel("Contraseña:", passwordField);

        // Error Label
        errorLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Login Button
        StylishButton loginButton = new StylishButton("Ingresar");
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> validateForm());

        // Add components to the form panel
        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(usernamePanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passwordPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(errorLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(loginButton);

        // Add the form panel to the center of the page
        this.add(formPanel);
    }

    private JPanel createInputPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false); // Transparent background

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Work Sans", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);

        textField.setFont(new Font("Work Sans", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(0x37474F), 2));
        textField.setBackground(new Color(0x253239));
        textField.setForeground(Color.WHITE);

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    private void validateForm() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Por favor, complete todos los campos.");
        } else if (!authenticate(username, password)) {
            errorLabel.setText("Usuario o contraseña incorrectos.");
        } else {
            errorLabel.setText("");
            this.header.setVisible(true);
            this.isLogged = true;
            JOptionPane.showMessageDialog(this, "¡Bienvenido " + username + "!", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
            // Cambiar a la página principal
            mainPanel.removeAll();
            mainPanel.add(new HousesPage());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private boolean authenticate(String username, String password) {
        // Simula una autenticación básica (reemplazar con lógica real)
        return username.equals("admin") && password.equals("1234");
    }
}
