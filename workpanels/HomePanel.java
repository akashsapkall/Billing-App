package workpanels;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    JLabel l1, l2, l3;

    public HomePanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        setPreferredSize(new Dimension(1050, 800));

        l1 = new JLabel("Welcome To A-Mart");
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the label horizontally at the center

        l2 = new JLabel("*New Arrivals And Offers");
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);

        l3 = new JLabel("*Grand Upcoming Events");
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add spacing between labels
        add(Box.createVerticalGlue()); // Add flexible vertical space at the top
        add(l1);
        add(Box.createRigidArea(new Dimension(0, 30))); // Add fixed spacing
        add(l2);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(l3);
        add(Box.createVerticalGlue()); // Add flexible vertical space at the bottom
    }
}
