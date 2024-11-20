package components.labels;

import javax.swing.*;
import java.awt.*;

public class PageTitle extends JLabel {
    public PageTitle(String title) {
        super(title);
        this.setFont(new Font("Work Sans", Font.BOLD, 28));
        this.setForeground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

    }
}
