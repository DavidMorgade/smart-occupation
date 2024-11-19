package components;

import components.buttons.NavButton;

import javax.swing.*;
import java.awt.*;

public class Navigation {
    JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Adjust horizontal spacing
    JLabel avatarLabel = this.createAvatar();
    NavButton viviendasButton= new NavButton("Viviendas");
    NavButton usuariosButton = new NavButton("Usuarios");

    public Navigation() {
        navPanel.setOpaque(false); // Match header background
        navPanel.add(viviendasButton);
        navPanel.add(usuariosButton);
        navPanel.add(avatarLabel);
    }

    private JLabel createAvatar() {
        JLabel avatar = new JLabel(new ImageIcon("avatar.png"));
        avatar.setPreferredSize(new Dimension(40, 40)); // Ensure consistent avatar size
        return avatar;
    }

    public JPanel getNavigation() {
        return navPanel;
    }
}
