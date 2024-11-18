package components.buttons;

import javax.swing.*;
import java.awt.*;

public class StylishButton extends JButton {

    public StylishButton(String text) {
        super(text);
        this.setFont(new Font("Work Sans", Font.BOLD, 14)); // Smaller font size
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(0x0D47A1)); // Darker blue color
        this.setFocusPainted(false); // Remove focus outline
        this.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16)); // Smaller padding
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Rounded corners and border
        this.setBorder(BorderFactory.createLineBorder(new Color(0x0B3D91), 2)); // Slightly darker border
        this.setContentAreaFilled(false); // Disable default painting
        this.setOpaque(true);

        // Hover effects
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(new Color(0x0B3D91)); // Slightly darker shade on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(new Color(0x0D47A1)); // Original darker blue color
            }
        });
    }

    // Paint component for rounded corners
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // More rounded corners
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20); // Match rounded corners
        g2.dispose();
    }


}

