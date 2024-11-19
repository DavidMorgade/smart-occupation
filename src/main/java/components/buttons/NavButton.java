package components.buttons;

import javax.swing.*;
import java.awt.*;

public class NavButton extends JButton {

    public NavButton (String text) {
        super(text);
        this.setFont(new Font("Work Sans", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(100, 30)); // Uniform button size
    }
}
