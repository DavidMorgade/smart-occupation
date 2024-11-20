package components;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    JPanel titlePanel = new Title().getTitle();
    JPanel navPanel;

    public Header(JPanel mainPanel) {
        this.setLayout(new GridBagLayout());
        this.navPanel = new Navigation(mainPanel).getNavigation();
        // Header styling
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xe7eff3)));
        this.setBackground(new Color(0x121C22));
        this.setPreferredSize(new Dimension(0, 60));

        // Add title panel
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.weightx = 1.0;
        gbcTitle.anchor = GridBagConstraints.WEST; // Alinear a la izq
        gbcTitle.fill = GridBagConstraints.VERTICAL;
        this.add(titlePanel, gbcTitle);

        // Add navigation panel
        GridBagConstraints gbcNav = new GridBagConstraints();
        gbcNav.gridx = 1;
        gbcNav.gridy = 0;
        gbcNav.weightx = 1.0;
        gbcNav.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        gbcNav.fill = GridBagConstraints.VERTICAL;
        this.add(navPanel, gbcNav);

        titlePanel.setOpaque(false);
        navPanel.setOpaque(false);
    }

}
