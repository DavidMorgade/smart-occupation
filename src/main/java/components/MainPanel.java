package components;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    private JPanel mainPanel;
    private JPanel header = new Header().getHeader();
    private JPanel contentPanel = new Content().GetContent();

   public MainPanel() {
       this.mainPanel = new JPanel();
       mainPanel.setLayout(new BorderLayout());
       mainPanel.setBackground(new Color(0x121C22));
       mainPanel.add(header, BorderLayout.NORTH);
       mainPanel.add(contentPanel, BorderLayout.CENTER);
   }

    public JPanel getMainPanel() {
         return mainPanel;
    }

}
