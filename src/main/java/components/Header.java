package components;

import javax.swing.*;
import java.awt.*;


public class Header {
    JPanel titlePanel = new Title().getTitle();
    JPanel navPanel = new Navigation().getNavigation();
    JPanel header = new JPanel(new BorderLayout());

    public Header() {
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xe7eff3)));
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 60));
        header.add(titlePanel, BorderLayout.WEST);
        header.add(navPanel, BorderLayout.EAST);

    }


    public JPanel getHeader() {
        return header;
    }
}
