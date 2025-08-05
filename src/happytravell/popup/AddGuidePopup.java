/*
 * Add Guide Form - A dedicated form for adding new guides
 */
package happytravell.popup;

import happytravell.model.GuideData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Form for adding new guides with proper validation
 * @author Acer
 */
public class AddGuidePopup extends JDialog {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField emailField;
    private JButton saveButton;
    private JButton cancelButton;
    
    private GuideData guideData;
    private boolean saved = false;
    
    public AddGuidePopup(JFrame parent) {
        super(parent, "Add New Guide", true);
        this.guideData = new GuideData();
        setBackground(new Color(239, 204, 150));
        initializeComponents();
        setupLayout();
        setupEventListeners();
        
        // Center the dialog
        setLocationRelativeTo(parent);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private void initializeComponents() {
        // Text fields
        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        
        // Buttons
        saveButton = new JButton("Save Guide");
        cancelButton = new JButton("Cancel");
        
        // Style buttons
        saveButton.setBackground(new Color(46, 125, 50));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        cancelButton.setBackground(new Color(211, 47, 47));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(239, 204, 150));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        // Title
        JLabel titleLabel = new JLabel("Add New Guide");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(titleLabel, gbc);
        
        // Reset gridwidth for form fields
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Name field
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 10);
        mainPanel.add(new JLabel("Guide Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);
        mainPanel.add(nameField, gbc);
        
        // Phone field
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 0, 5, 10);
        mainPanel.add(new JLabel("Phone Number:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);
        mainPanel.add(phoneField, gbc);
        
        // Address field
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 0, 5, 10);
        mainPanel.add(new JLabel("Address:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);
        mainPanel.add(addressField, gbc);
        
        // Email field
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 0, 5, 10);
        mainPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);
        mainPanel.add(emailField, gbc);
        
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(239, 204, 150));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEventListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateAndSaveGuide()) {
                    saved = true;
                    dispose();
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        // Add Enter key listener for save button
        getRootPane().setDefaultButton(saveButton);
    }
    
    private boolean validateAndSaveGuide() {
        // Get and trim input values
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        
        // Validation
        if (name.isEmpty()) {
            showError("Please enter guide name.");
            nameField.requestFocus();
            return false;
        }
        
        if (phone.isEmpty()) {
            showError("Please enter phone number.");
            phoneField.requestFocus();
            return false;
        }
        
        if (address.isEmpty()) {
            showError("Please enter address.");
            addressField.requestFocus();
            return false;
        }
        
        if (email.isEmpty()) {
            showError("Please enter email address.");
            emailField.requestFocus();
            return false;
        }
        
        // Email format validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            showError("Please enter a valid email address.");
            emailField.requestFocus();
            return false;
        }
        
        // Phone number validation (basic)
        if (!phone.matches("^[0-9+\\-\\s()]+$")) {
            showError("Please enter a valid phone number.");
            phoneField.requestFocus();
            return false;
        }
        
        // Set the guide data
        guideData.setGuideName(name);
        guideData.setPhoneNumber(phone);
        guideData.setAddress(address);
        guideData.setEmail(email);
        
        return true;
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    // Getters
    public GuideData getGuideData() {
        return guideData;
    }
    
    public boolean isSaved() {
        return saved;
    }
    
    // Method to clear all fields
    public void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
        nameField.requestFocus();
    }
}