package components;

import javax.swing.*;
import java.awt.*;

public class Navigation {
    JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel avatarLabel = this.createAvatar();

    public Navigation() {
        navPanel.setOpaque(false);
        this.AddLinks();
        navPanel.add(avatarLabel);
    }

    private void AddLinks() {
        String[] navItems = {"Dashboard", "Calendar", "Listings", "Inquiries", "Templates", "Reports"};
        for (String navItem : navItems) {
            JButton navButton = new JButton(navItem);
            navButton.setFont(new Font("Work Sans", Font.PLAIN, 14));
            navButton.setForeground(Color.WHITE);
            navButton.setContentAreaFilled(false);
            navButton.setBorderPainted(false);
            navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            navPanel.add(navButton);
        }
    }

    private JLabel createAvatar() {
        return new JLabel(new ImageIcon("avatar.png"));
    }

    public JPanel getNavigation() {
        return navPanel;
    }
}
