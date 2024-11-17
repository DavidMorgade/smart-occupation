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
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0xf0f4f8)); // Equivalent to `bg-slate-50`

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xe7eff3)));
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 60));

        // Logo and Title
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        JLabel logoLabel = new JLabel("🔵"); // Placeholder for an SVG icon
        JLabel titleLabel = new JLabel("SmartOcupation");
        titleLabel.setFont(new Font("Work Sans", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x0d161b));
        logoPanel.add(logoLabel);
        logoPanel.add(titleLabel);

        // Navigation and Action Buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setOpaque(false);
        String[] navItems = {"Dashboard", "Calendar", "Listings", "Inquiries", "Templates", "Reports"};
        for (String navItem : navItems) {
            JButton navButton = new JButton(navItem);
            navButton.setFont(new Font("Work Sans", Font.PLAIN, 14));
            navButton.setForeground(new Color(0x0d161b));
            navButton.setContentAreaFilled(false);
            navButton.setBorderPainted(false);
            navPanel.add(navButton);
        }

        // Notifications and Avatar
        JButton bellButton = createIconButton("🔔");
        JButton chatButton = createIconButton("💬");
        JButton helpButton = createIconButton("❓");
        JLabel avatarLabel = new JLabel(new ImageIcon("avatar.png")); // Replace with actual avatar image

        navPanel.add(bellButton);
        navPanel.add(chatButton);
        navPanel.add(helpButton);
        navPanel.add(avatarLabel);

        header.add(logoPanel, BorderLayout.WEST);
        header.add(navPanel, BorderLayout.EAST);

        // Content Area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel sectionTitle = new JLabel("Recent Rentals");
        sectionTitle.setFont(new Font("Work Sans", Font.BOLD, 24));
        sectionTitle.setForeground(new Color(0x0d161b));

        contentPanel.add(sectionTitle);

        // Sample Rentals
        for (int i = 1; i <= 4; i++) {
            contentPanel.add(createRentalItem("John Doe", "Jul 2 - Jul 5 · 3 nights · $1,000"));
        }

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JButton createIconButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Work Sans", Font.BOLD, 14));
        button.setForeground(new Color(0x0d161b));
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0xe7eff3), 1));
        button.setPreferredSize(new Dimension(40, 40));
        return button;
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
