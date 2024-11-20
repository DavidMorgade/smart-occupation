import components.*;
import pages.LoginPage;

import javax.swing.*;
import java.awt.*;

public class SmartOccupation {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmartOccupation::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        boolean isLogged = false;
        JFrame frame = new JFrame("Smart Occupation");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        // Main layout container
        MainPanel mainPanel = new MainPanel();
        Header header = new Header(mainPanel);

        LoginPage loginPage = new LoginPage(mainPanel, header, isLogged);
        if (!isLogged) {
            header.setVisible(false);
        }
        frame.add(header, BorderLayout.NORTH);
        frame.add(mainPanel);


        mainPanel.add(loginPage, BorderLayout.CENTER);

        frame.setVisible(true);


    }

}
