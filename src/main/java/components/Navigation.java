package components;

import javax.swing.*;
import java.awt.*;

public class Navigation {
    JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Adjust horizontal spacing
    JLabel avatarLabel = this.createAvatar();

    public Navigation() {
        navPanel.setOpaque(false); // Match header background
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
            navButton.setPreferredSize(new Dimension(100, 30)); // Uniform button size
            navPanel.add(navButton);
        }
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
