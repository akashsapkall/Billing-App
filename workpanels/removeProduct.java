package workpanels;

// import ProductInfo.Productnames;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class removeProduct extends JPanel implements ActionListener {
    JLabel l1, l2;
    JButton addremoveProductBtn;
    DefaultTableModel tableModel; // Table model for better control
    JTable table;
    // generateBill billPanel;
    addproduct aproduct;
    public removeProduct() {
        // this.billPanel = billPanel;
        this.aproduct=null;
        // Set up the main panel
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1050, 800));

        // Header Label
        l1 = new JLabel("Remove Product");
        l1.setFont(new Font("Arial", Font.BOLD, 24));
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setPreferredSize(new Dimension(1050, 50));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.LIGHT_GRAY);

        addremoveProductBtn = new JButton("Remove Product");
        addremoveProductBtn.setBounds(250, 50, 150, 40);
        addremoveProductBtn.addActionListener(this);

        l2 = new JLabel("Select Product To Remove:");
        l2.setBounds(250, 220, 200, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add components to the form panel
        formPanel.add(l2);
        formPanel.add(addremoveProductBtn);

        // Table Panel
        String[] columns = { "Product Name", "Quantity (Kg/Unit)" };
        tableModel = new DefaultTableModel(columns, 0); // Initialize with columns, no rows
        table = new JTable(tableModel);

        // Configure table and scroll pane
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(600, 300)); // Medium size for the table

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.LIGHT_GRAY);
        tablePanel.setPreferredSize(new Dimension(1050, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the table
        tablePanel.add(tableScrollPane, gbc);

        // Add components to the main panel
        add(l1, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.SOUTH);
    }
    public void setAddProduct(addproduct aproduct) {
        this.aproduct = aproduct;
    }
    public void addDataToTable(String productName, double quantity) {
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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addremoveProductBtn) {
            // Get the selected row index
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) { // Check if a row is selected
                // Remove the selected row from the table model
                String productName = (String) tableModel.getValueAt(selectedRow, 0);
                double quantity = (double) tableModel.getValueAt(selectedRow, 1);

                // Update the generateBill panel if needed
                // billPanel.removeProductData(productName, quantity);
                aproduct.removeProductData(productName, quantity);
                // Remove the row from the table model
                tableModel.removeRow(selectedRow);

                // Notify the user
                JOptionPane.showMessageDialog(
                        this,
                        "Product removed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Notify the user to select a row
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a product to remove.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void clearTable() {
        // DefaultTableModel model = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Sets the number of rows to 0, effectively clearing the table.
    }
}