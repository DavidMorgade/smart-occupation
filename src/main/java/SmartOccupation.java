import components.*;

import javax.swing.*;
import java.awt.*;

public class SmartOccupation {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmartOccupation::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Galileo Design");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        // Main layout container
        JPanel mainPanel = new MainPanel().getMainPanel();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

}
