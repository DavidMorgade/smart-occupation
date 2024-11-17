package components;

import javax.swing.*;
import java.awt.*;

public class Title {
    JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel logoLabel = new JLabel("🔵"); // Logo de la aplicación
    JLabel titleLabel = new JLabel("SmartOcupation");

    public Title() {
        logoPanel.setOpaque(false);
        titleLabel.setFont(new Font("Work Sans", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x0d161b));
        logoPanel.add(logoLabel);
        logoPanel.add(titleLabel);
    }

    public JPanel getTitle() {
        return logoPanel;
    }
}
