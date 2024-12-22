package workpanels;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import workpanels.generateInventory.addCustomerData;
public class addcustomer extends JPanel implements ActionListener {
    static int custid = 1100;

    JLabel l1, l2, l3, l4, l5, l6;
    JTextField nameField, contactField, addressField;
    JButton addCustomerBtn;

    public addcustomer() {
        // Set up the main panel
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1050, 800));

        // Header Panel
        l1 = new JLabel("Add Customer Details");
        l1.setFont(new Font("Arial", Font.BOLD, 24));
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setPreferredSize(new Dimension(1050, 50));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.LIGHT_GRAY);

        LocalDate currentDate = LocalDate.now();
        l5 = new JLabel("Customer ID :      " + custid);
        l5.setBounds(220, 50, 200, 30);
        l5.setFont(new Font("Arial", Font.PLAIN, 16));

        l6 = new JLabel("Date :      " + currentDate);
        l6.setBounds(560, 50, 200, 30);
        l6.setFont(new Font("Arial", Font.PLAIN, 16));

        l2 = new JLabel("Enter Customer Name:");
        l2.setBounds(220, 100, 200, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 16));

        nameField = new JTextField();
        nameField.setBounds(420, 100, 300, 30);

        l3 = new JLabel("Enter Customer Contact:");
        l3.setBounds(220, 150, 200, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 16));

        contactField = new JTextField();
        contactField.setBounds(420, 150, 300, 30);

        // Apply DigitDocumentFilter to contactField
        PlainDocument contactDoc = (PlainDocument) contactField.getDocument();
        contactDoc.setDocumentFilter(new DigitDocumentFilter(10));

        l4 = new JLabel("Enter Customer Address:");
        l4.setBounds(220, 200, 200, 30);
        l4.setFont(new Font("Arial", Font.PLAIN, 16));

        addressField = new JTextField();
        addressField.setBounds(420, 200, 300, 30);

        addCustomerBtn = new JButton("Add Customer");
        addCustomerBtn.setBounds(400, 270, 150, 40);

        addCustomerBtn.addActionListener(this);

        // Add components to form panel
        formPanel.add(l2);
        formPanel.add(nameField);
        formPanel.add(l3);
        formPanel.add(contactField);
        formPanel.add(l4);
        formPanel.add(l5);
        formPanel.add(l6);
        formPanel.add(addressField);
        formPanel.add(addCustomerBtn);

        // Add header and form panels to main panel
        add(l1, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomerBtn) {
            // Collect input data
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();
            String address = addressField.getText().trim();
            LocalDate currentDate = LocalDate.now();

            // Validate input
            if (name.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Contact are mandatory fields.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Send data to generateInventory
            generateBill.addCustomerData(custid, currentDate, name, contact, address);

            // Increment custid for the next customer
            custid++;
            l5.setText("Customer ID :      " + custid); // Update the label to reflect the new ID

            // Optionally, reset the form fields
            nameField.setText("");
            contactField.setText("");
            addressField.setText("");
            JOptionPane.showMessageDialog(this, "Customer details added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Custom DocumentFilter to allow only digits and restrict length.
     */
    static class DigitDocumentFilter extends DocumentFilter {
        private final int maxLength;

        public DigitDocumentFilter(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (isValidInput(fb.getDocument().getLength() + string.length(), string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (isValidInput(fb.getDocument().getLength() - length + text.length(), text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isValidInput(int newLength, String text) {
            return newLength <= maxLength && text.matches("\\d*");
        }
    }
}
