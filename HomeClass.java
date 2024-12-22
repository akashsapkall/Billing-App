import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import workpanels.addcustomer;
import workpanels.addproduct;
import workpanels.generateBill;
import workpanels.removeProduct;
import workpanels.HomePanel;

public class HomeClass {
    public static void main(String[] args) {
        // Start the HomePage GUI
        new HomePage();
    }
}

class HomePage implements ActionListener {
    private JFrame home;
    private JPanel navigationPanel, mainPanel;
    private CardLayout cardLayout;

    private JButton addCustomerDetails, addProductDetails, removeProduct, generateBill;

    // Declare panel instances at the class level
    private addproduct aproduct;
    private removeProduct rmProduct;
    private generateBill billPanel;

    public HomePage() {
        // Create JFrame
        home = new JFrame("Inventory Management System");
        home.setExtendedState(JFrame.MAXIMIZED_BOTH);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setLayout(new BorderLayout());

        // Navigation Panel (West)
        navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.DARK_GRAY);
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        navigationPanel.setPreferredSize(new Dimension(230, 800));

        Dimension buttonSize = new Dimension(200, 40);

        // Buttons for Navigation
        addCustomerDetails = new JButton("Add Customer Details");
        addCustomerDetails.setPreferredSize(buttonSize);
        addProductDetails = new JButton("Add Product Details");
        addProductDetails.setPreferredSize(buttonSize);
        removeProduct = new JButton("Remove a Product");
        removeProduct.setPreferredSize(buttonSize);
        generateBill = new JButton("Generate Bill");
        generateBill.setPreferredSize(buttonSize);

        // Add Buttons to Navigation Panel
        navigationPanel.add(addCustomerDetails);
        navigationPanel.add(addProductDetails);
        navigationPanel.add(removeProduct);
        navigationPanel.add(generateBill);

        // Add Navigation Panel to Frame
        home.add(navigationPanel, BorderLayout.WEST);

        // Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create an instance of generateBill
        billPanel = new generateBill();
        aproduct = new addproduct(billPanel);
        rmProduct = new removeProduct();

        // Set dependencies using setter methods
        aproduct.setRemoveProduct(rmProduct);
        rmProduct.setAddProduct(aproduct);

        // Add panels to the CardLayout
        mainPanel.add(new HomePanel(), "AddHomePanel");
        mainPanel.add(new addcustomer(), "AddCustomer");
        mainPanel.add(aproduct, "AddProduct");
        mainPanel.add(rmProduct, "RemoveProduct");
        mainPanel.add(billPanel, "GenerateBill");

        // Add Main Panel to Frame
        home.add(mainPanel, BorderLayout.CENTER);

        // Default Panel
        cardLayout.show(mainPanel, "AddHomePanel");

        // Add Action Listeners for Navigation Buttons
        addCustomerDetails.addActionListener(this);
        addProductDetails.addActionListener(e -> cardLayout.show(mainPanel, "AddProduct"));
        removeProduct.addActionListener(e -> cardLayout.show(mainPanel, "RemoveProduct"));
        generateBill.addActionListener(e -> cardLayout.show(mainPanel, "GenerateBill"));

        // Make JFrame Visible
        home.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomerDetails) {
            cardLayout.show(mainPanel, "AddCustomer");
        }

        // Clear tables when navigating between panels
        if (aproduct != null) aproduct.clearTable();
        if (rmProduct != null) rmProduct.clearTable();
        if (billPanel != null) billPanel.clearTable();
    }
}
