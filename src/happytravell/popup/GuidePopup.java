/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.popup;

import happytravell.dao.GuideDao;
import happytravell.model.GuideData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Acer
 */
public class GuidePopup extends JDialog{
    
    private GuideData guide;
    private GuideDao guideDao;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField emailField;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private boolean isUpdated = false;
    private boolean isDeleted = false;
    
    public GuidePopup(Frame parent, GuideData guide) {
        super(parent, "Edit Guide Details", true);
        this.guide = guide;
        this.guideDao = new GuideDao();
        
        initializeComponents();
        setupLayout();
        setupEventListeners();
        populateFields();
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }
    private void initializeComponents() {
        // Create input fields
        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        
        // Style the text fields
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        phoneField.setFont(fieldFont);
        addressField.setFont(fieldFont);
        emailField.setFont(fieldFont);
        
        // Create buttons
        saveButton = new JButton("Save Changes");
        deleteButton = new JButton("Delete Guide");
        cancelButton = new JButton("Cancel");
        
        // Style buttons
        saveButton.setBackground(new Color(76, 175, 80));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        cancelButton.setBackground(new Color(158, 158, 158));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 204, 150));
        
        // Title
        JLabel titleLabel = new JLabel("Edit Guide Information");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(239, 204, 150));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        
        // Phone field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        
        // Address field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);
        
        // Email field
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(239, 204, 150));
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        
        // Add components to main panel
        mainPanel.add(titleLabel);
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEventListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGuide();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGuide();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void populateFields() {
        nameField.setText(guide.getGuideName());
        phoneField.setText(guide.getPhoneNumber());
        addressField.setText(guide.getAddress());
        emailField.setText(guide.getEmail());
    }
    
    private void saveGuide() {
        // Validate input
        if (!validateInput()) {
            return;
        }
        
        // Update guide object
        guide.setGuideName(nameField.getText().trim());
        guide.setPhoneNumber(phoneField.getText().trim());
        guide.setAddress(addressField.getText().trim());
        guide.setEmail(emailField.getText().trim());
        
        // Save to database
        if (guideDao.updateGuide(guide)) {
            isUpdated = true;
            JOptionPane.showMessageDialog(this, 
                "Guide information updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to update guide information. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteGuide() {
        int option = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this guide?\n" +
            "This action cannot be undone.", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (option == JOptionPane.YES_OPTION) {
            if (guideDao.deleteGuide(guide.getGuideId())) {
                isDeleted = true;
                JOptionPane.showMessageDialog(this, 
                    "Guide deleted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to delete guide. Please try again.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean validateInput() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter guide name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return false;
        }
        
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter phone number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneField.requestFocus();
            return false;
        }
        
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            addressField.requestFocus();
            return false;
        }
        
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        
        // Validate email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        
        // Check if email already exists (excluding current guide)
        if (guideDao.emailExists(email, guide.getGuideId())) {
            JOptionPane.showMessageDialog(this, "This email is already registered with another guide.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        
        // Validate phone number format (basic validation)
        if (!phone.matches("^[+]?[0-9]{10,}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number (at least 10 digits).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    public boolean isUpdated() {
        return isUpdated;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }
    
    public GuideData getUpdatedGuide() {
        return guide;
    }
}

