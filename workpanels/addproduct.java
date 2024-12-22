package workpanels;

import ProductInfo.Productnames;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class addproduct extends JPanel implements ActionListener {
    JLabel l1, l2, l3;
    JTextField quantityField;
    JButton addProductBtn;
    JComboBox<String> cb;
    DefaultTableModel tableModel; // Table model for the "addproduct" table
    JTable table;
    generateBill billPanel;
    removeProduct removeProduct;

    public addproduct(generateBill billPanel) {
        this.billPanel = billPanel;
        this.removeProduct = null;
        // Set up the main panel
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1050, 800));

        // Header Label
        l1 = new JLabel("Add Product Details");
        l1.setFont(new Font("Arial", Font.BOLD, 24));
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setPreferredSize(new Dimension(1050, 50));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.LIGHT_GRAY);

        l2 = new JLabel("Select Product:");
        l2.setBounds(260, 50, 200, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 16));

        cb = new JComboBox<>(Productnames.productnames());
        cb.setBounds(510, 50, 250, 30);

        l3 = new JLabel("Enter Quantity For Selected Product (Kg/Unit):");
        l3.setBounds(260, 100, 350, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 16));

        quantityField = new JTextField();
        quantityField.setBounds(610, 100, 150, 30);

        addProductBtn = new JButton("Add Product");
        addProductBtn.setBounds(420, 180, 150, 40);
        addProductBtn.addActionListener(this);

        // Add components to the form panel
        formPanel.add(l2);
        formPanel.add(cb);
        formPanel.add(l3);
        formPanel.add(quantityField);
        formPanel.add(addProductBtn);

        // Table Panel
        String[] columns = { "Product Name", "Quantity (Kg/Unit)" };
        tableModel = new DefaultTableModel(columns, 0); // Initialize with columns, no rows
        table = new JTable(tableModel);

        // Configure table and scroll pane
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(600, 300));

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.LIGHT_GRAY);
        tablePanel.setPreferredSize(new Dimension(1050, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        tablePanel.add(tableScrollPane, gbc);

        // Add components to the main panel
        add(l1, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.SOUTH);
    }

    public void setRemoveProduct(removeProduct rmProduct) {
        this.removeProduct = rmProduct;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addProductBtn) {
            // Get selected product name and quantity
            String productName = (String) cb.getSelectedItem();
            String quantityStr = quantityField.getText().trim();

            // Validate input
            if (productName == null || productName.isEmpty() || quantityStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter valid product details.");
                return;
            }

            // Validate quantity input (must be numeric)
            if (!isNumeric(quantityStr)) {
                JOptionPane.showMessageDialog(this, "Quantity must be a numeric value.");
                return;
            }

            double quantity = Double.parseDouble(quantityStr);

            // Check if the product is already in the table
            boolean productExists = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(productName)) {
                    // Update the quantity
                    double existingQuantity = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                    tableModel.setValueAt(existingQuantity + quantity, i, 1);
                    productExists = true;
                    break;
                }
            }

            // If the product does not exist, add a new row
            if (!productExists) {
                tableModel.addRow(new Object[] { productName, quantity });
            }
            removeProduct.addDataToTable(productName, quantity);
            billPanel.addProductData(productName, quantity);
            // removeProduct.addDataToTable(productName, quantity);

            // Clear input fields for new entry
            quantityField.setText("");
            cb.setSelectedIndex(0);
        }
    }

    // Method to remove product data from the table
    public void removeProductData(String productName, double quantity) {
        boolean productFound = false;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String rowProductName = (String) tableModel.getValueAt(i, 0);
            double rowQuantity = (double) tableModel.getValueAt(i, 1);

            if (rowProductName.equals(productName)) {
                if (rowQuantity > quantity) {
                    // Reduce the quantity
                    tableModel.setValueAt(rowQuantity - quantity, i, 1);
                } else {
                    // Remove the row if quantity becomes zero or less
                    tableModel.removeRow(i);
                }

                // Synchronize with other panels
                billPanel.removeProductData(productName, quantity);
                // removeProduct.removeDataFromTable(productName, quantity);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            JOptionPane.showMessageDialog(this, "Product not found in the table.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void clearTable() {
        // DefaultTableModel model = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Sets the number of rows to 0, effectively clearing the table.
    }  
}
