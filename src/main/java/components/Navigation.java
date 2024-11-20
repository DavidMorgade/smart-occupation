package components;

import components.buttons.NavButton;
import pages.ClientsPage;
import pages.HousesPage;

import javax.swing.*;
import java.awt.*;

public class Navigation {
    private final JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Adjust horizontal spacing
    private final JLabel avatarLabel = this.createAvatar();
    private final NavButton viviendasButton = new NavButton("Viviendas");
    private final NavButton usuariosButton = new NavButton("Usuarios");
    private final JPanel mainPanel; // Reference to the main panel
    private final ClientsPage clientsPage = new ClientsPage();
    private final HousesPage housesPage = new HousesPage();

    public Navigation(JPanel mainPanel) {
        this.mainPanel = mainPanel;

        // Set up navigation panel
        navPanel.setOpaque(false); // Match header background
        navPanel.add(viviendasButton);
        navPanel.add(usuariosButton);
        navPanel.add(avatarLabel);

        // Add action listeners to the buttons
        viviendasButton.addActionListener(e -> navigateTo(housesPage));
        usuariosButton.addActionListener(e -> navigateTo(clientsPage));
    }

    private JLabel createAvatar() {
        JLabel avatar = new JLabel(new ImageIcon("avatar.png"));
        avatar.setPreferredSize(new Dimension(40, 40)); // Ensure consistent avatar size
        return avatar;
    }

    public JPanel getNavigation() {
        return navPanel;
    }

    private void navigateTo(JComponent newContent) {
        // Clear the main panel
        mainPanel.removeAll();

        // Add the new content
        mainPanel.add(newContent);

        // Revalidate and repaint to update the UI
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
