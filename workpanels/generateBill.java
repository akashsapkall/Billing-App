package workpanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileOutputStream;
import java.time.LocalDate;
import ProductInfo.productwithhsn;
import ProductInfo.ProductGSTandPrice;

public class generateBill extends JPanel {
    static JLabel custid, custdate, custname, custcontact, custaddr;
    JTable productTable;
    DefaultTableModel tableModel;
    JLabel totalLabel;

    public generateBill() {
        // Set panel properties
        setPreferredSize(new Dimension(1050, 800));
        setLayout(null); // Use absolute layout for precise positioning
        setBackground(Color.LIGHT_GRAY);

        // Header Label
        JLabel header = new JLabel("System Generated Bill");
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setBounds(0, 20, 1050, 40); // Center-aligned header

        // Customer Details Labels
        custid = new JLabel();
        custid.setBounds(80, 100, 300, 30);
        custid.setFont(new Font("Arial", Font.PLAIN, 16));

        custdate = new JLabel();
        custdate.setBounds(750, 100, 300, 30);
        custdate.setFont(new Font("Arial", Font.PLAIN, 16));

        custname = new JLabel();
        custname.setBounds(80, 140, 400, 30);
        custname.setFont(new Font("Arial", Font.PLAIN, 16));

        custcontact = new JLabel();
        custcontact.setBounds(650, 140, 300, 30);
        custcontact.setFont(new Font("Arial", Font.PLAIN, 16));

        custaddr = new JLabel();
        custaddr.setBounds(80, 180, 500, 30);
        custaddr.setFont(new Font("Arial", Font.PLAIN, 16));

        // Table for Product Details
        String[] columnNames = { "Sr No", "Product HSN", "Product Name", "Quantity", "Price/Rate", "GST", "Amount" };
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 240, 950, 300);

        // Total Label
        totalLabel = new JLabel("Total Amount: 0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBounds(750, 560, 250, 30);

        // Add components to the panel
        add(header);
        add(custid);
        add(custdate);
        add(custname);
        add(custcontact);
        add(custaddr);
        add(scrollPane);
        add(totalLabel);
    }

    public static void addCustomerData(int custId, LocalDate date, String name, String contact, String address) {
        custid.setText(String.format("Customer ID: %d", custId));
        custdate.setText(String.format("Date: %s", date));
        custname.setText(String.format("Customer Name: %s", name));
        custcontact.setText(String.format("Customer Contact: %s", contact));
        custaddr.setText(String.format("Customer Address: %s", address));
    }

    public void addProductData(String productName, double quantity) {
        boolean productExists = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 2).equals(productName)) {
                // Update the quantity
                double existingQuantity = Double.parseDouble(tableModel.getValueAt(i, 3).toString());
                tableModel.setValueAt(existingQuantity + quantity, i, 3);
                productExists = true;
                break;
            }
        }

        // If the product does not exist, add a new row
        if (!productExists) {
            int hsnCode = productwithhsn.pruductHSNDetails(productName);
            if (hsnCode == 0) {
                JOptionPane.showMessageDialog(this, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price = ProductGSTandPrice.pruductHsnAndPriceDetails(hsnCode);
            double gst = ProductGSTandPrice.pruductHsnAndGSTDetails(hsnCode);
            double amount = quantity * price * (1 + gst / 100);
            amount = Math.round(amount * 100.0) / 100.0;

            Object[] rowData = {
                    tableModel.getRowCount() + 1,
                    hsnCode,
                    productName,
                    quantity,
                    price,
                    gst,
                    amount
            };
            tableModel.addRow(rowData);
        }
        // Update total amount
        updateTotalAmount();
    }
    public void removeProductData(String productName, double quantity) {
        boolean productFound = false;

        // Iterate over the rows to find the matching product
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String rowProductName = (String) tableModel.getValueAt(i, 2); // Product Name column
            double rowQuantity = (double) tableModel.getValueAt(i, 3); // Quantity column

            if (rowProductName.equals(productName) && rowQuantity == quantity) {
                // Remove the row from the table model
                tableModel.removeRow(i);

                // Recalculate the serial numbers
                for (int j = i; j < tableModel.getRowCount(); j++) {
                    tableModel.setValueAt(j + 1, j, 0); // Update serial numbers
                }

                // Update the total amount after removal
                updateTotalAmount();

                // Notify the user
                JOptionPane.showMessageDialog(
                        this,
                        "Product removed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                productFound = true;
                break;
            }
        }

        // If no matching product is found, show an error message
        if (!productFound) {
            JOptionPane.showMessageDialog(
                    this,
                    "Product not found in the table with the specified quantity.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateTotalAmount() {
        double total = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += (double) tableModel.getValueAt(i, 6);
        }
        totalLabel.setText(String.format("Total Amount: %.2f", total));
    }
    public void clearTable() {
        tableModel.setRowCount(0); 
    }
}
