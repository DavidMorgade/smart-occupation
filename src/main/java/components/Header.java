package components;

import javax.swing.*;
import java.awt.*;

public class Header {
    JPanel titlePanel = new Title().getTitle();
    JPanel navPanel;
    JPanel header = new JPanel(new GridBagLayout()); // GridBaglayout para alinear elementos

    public Header(JPanel mainPanel) {
        this.navPanel = new Navigation(mainPanel).getNavigation();
        // Header styling
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xe7eff3)));
        header.setBackground(new Color(0x121C22));
        header.setPreferredSize(new Dimension(0, 60));

        // Add title panel
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.weightx = 1.0;
        gbcTitle.anchor = GridBagConstraints.WEST; // Alinear a la izq
        gbcTitle.fill = GridBagConstraints.VERTICAL;
        header.add(titlePanel, gbcTitle);

        // Add navigation panel
        GridBagConstraints gbcNav = new GridBagConstraints();
        gbcNav.gridx = 1;
        gbcNav.gridy = 0;
        gbcNav.weightx = 1.0;
        gbcNav.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        gbcNav.fill = GridBagConstraints.VERTICAL;
        header.add(navPanel, gbcNav);

        titlePanel.setOpaque(false);
        navPanel.setOpaque(false);
    }

    public JPanel getHeader() {
        return header;
    }
}
