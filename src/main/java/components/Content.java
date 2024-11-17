package components;

import javax.swing.*;
import java.awt.*;

public class Content {
    JPanel contentPanel = new JPanel();
    JLabel sectionTitle = this.CreateSectionTitle();

    public Content() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        contentPanel.add(sectionTitle);
        for (int i = 1; i <= 4; i++) {
            contentPanel.add(createRentalItem("John Doe", "Jul 2 - Jul 5 · 3 nights · $1,000"));
        }
    }

    public JPanel GetContent() {
        return contentPanel;
    }


    private JLabel CreateSectionTitle() {
        JLabel sectionTitle = new JLabel("Recent Rentals");
        sectionTitle.setFont(new Font("Work Sans", Font.BOLD, 24));
        sectionTitle.setForeground(new Color(0x0d161b));
        return sectionTitle;
    }


    private static JPanel createRentalItem(String name, String details) {
        JPanel rentalPanel = new JPanel();
        rentalPanel.setLayout(new BorderLayout());
        rentalPanel.setBackground(new Color(0xf8fafc)); // Equivalent to `bg-slate-50`
        rentalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Work Sans", Font.BOLD, 16));
        nameLabel.setForeground(new Color(0x0d161b));

        JLabel detailsLabel = new JLabel(details);
        detailsLabel.setFont(new Font("Work Sans", Font.PLAIN, 14));
        detailsLabel.setForeground(new Color(0x4c7d9a));

        rentalPanel.add(nameLabel, BorderLayout.NORTH);
        rentalPanel.add(detailsLabel, BorderLayout.SOUTH);

        return rentalPanel;
    }
}
