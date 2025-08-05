package happytravell.popup;

import happytravell.model.DriverData;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Dialog for editing/adding driver details
 * Provides form fields for driver information with save, delete, and cancel options
 */
public class DriverPopup extends JDialog {
    
    private DriverData driverData;
    private boolean isNewDriver;
    private ActionListener actionListener;
    
    // Form components
    private JTextField nameField;
    private JTextField licenseField;
    private JTextField phoneField;
    private JTextField addressField;
    private JComboBox<String> statusComboBox;
    
    // Buttons
    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;
    
    // Status options
    private final String[] STATUS_OPTIONS = {"AVAILABLE", "BUSY", "INACTIVE"};
    
    /**
     * Constructor
     * @param parent Parent frame
     * @param modal Whether dialog is modal
     * @param driver DriverData object to edit
     * @param isNew Whether this is a new driver (true) or editing existing (false)
     */
    public DriverPopup(Frame parent, boolean modal, DriverData driver, boolean isNew) {
        super(parent, modal);
        this.driverData = driver;
        this.isNewDriver = isNew;
        
        initializeComponents();
        setupLayout();
        populateFields();
        setupEventHandlers();
        
        setTitle(isNew ? "Add New Driver" : "Edit Driver Details");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }
    
    /**
     * Initialize UI components
     */
    private void initializeComponents() {
        // Text fields
        nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        licenseField = new JTextField(20);
        licenseField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        phoneField = new JTextField(20);
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        addressField = new JTextField(20);
        addressField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
       
        statusComboBox = new JComboBox<>(STATUS_OPTIONS);
        statusComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Buttons
        saveButton = new JButton(isNewDriver ? "Add Driver" : "Save Changes");
        saveButton.setBackground(new Color(34, 139, 34));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setPreferredSize(new Dimension(120, 35));
        
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteButton.setPreferredSize(new Dimension(100, 35));
        deleteButton.setVisible(!isNewDriver); // Hide delete button for new drivers
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(128, 128, 128));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setPreferredSize(new Dimension(120, 35));
    }
    
    /**
     * Setup dialog layout
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 204, 150));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(239, 204, 150));
        JLabel titleLabel = new JLabel(isNewDriver ? "Add New Driver" : "Edit Driver Details");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(239, 204, 150));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Driver Name :"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        
        // License field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("License Number :"), gbc);
        gbc.gridx = 1;
        formPanel.add(licenseField, gbc);
        
        // Phone field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Phone Number :"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        
        // Address field
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(createLabel("Address :"), gbc);
        gbc.gridx = 1;

        formPanel.add(addressField, gbc);
        
        // Status field
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(createLabel("Status:"), gbc);
        gbc.gridx = 1;
        formPanel.add(statusComboBox, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(239, 204, 150));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        if (!isNewDriver) {
            buttonPanel.add(deleteButton);
        }
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Required fields note
        JPanel notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notePanel.setBackground(Color.WHITE);
        JLabel noteLabel = new JLabel("* Required fields");
        noteLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        noteLabel.setForeground(new Color(150, 150, 150));
        notePanel.add(noteLabel);
        
        add(notePanel, BorderLayout.SOUTH);
    }
    
    /**
     * Create styled label
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(51, 51, 51));
        label.setPreferredSize(new Dimension(140, 25));
        return label;
    }
    
    /**
     * Populate form fields with driver data
     */
    private void populateFields() {
        if (driverData != null) {
            nameField.setText(driverData.getName() != null ? driverData.getName() : "");
            licenseField.setText(driverData.getLicenseNumber() != null ? driverData.getLicenseNumber() : "");
            phoneField.setText(driverData.getPhone() != null ? driverData.getPhone() : "");
            addressField.setText(driverData.getAddress() != null ? driverData.getAddress() : "");
            
            String status = driverData.getStatus() != null ? driverData.getStatus() : "AVAILABLE";
            statusComboBox.setSelectedItem(status);
        }
    }
    
    /**
     * Setup event handlers for buttons
     */
    private void setupEventHandlers() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    updateDriverData();
                    if (actionListener != null) {
                        ActionEvent saveEvent = new ActionEvent(DriverPopup.this, 
                            ActionEvent.ACTION_PERFORMED, "SAVE");
                        actionListener.actionPerformed(saveEvent);
                    }
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null) {
                    ActionEvent deleteEvent = new ActionEvent(DriverPopup.this, 
                        ActionEvent.ACTION_PERFORMED, "DELETE");
                    actionListener.actionPerformed(deleteEvent);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null) {
                    ActionEvent cancelEvent = new ActionEvent(DriverPopup.this, 
                        ActionEvent.ACTION_PERFORMED, "CANCEL");
                    actionListener.actionPerformed(cancelEvent);
                }
            }
        });
    }
    
    /**
     * Validate form fields
     */
    private boolean validateForm() {
        StringBuilder errors = new StringBuilder();
        
        if (nameField.getText().trim().isEmpty()) {
            errors.append("• Driver name is required\n");
        }
        
        if (licenseField.getText().trim().isEmpty()) {
            errors.append("• License number is required\n");
        }
        
        if (phoneField.getText().trim().isEmpty()) {
            errors.append("• Phone number is required\n");
        }
        
        // Validate phone number format (basic validation)
        String phone = phoneField.getText().trim();
        if (!phone.isEmpty() && !phone.matches("^[+]?[0-9\\s\\-\\(\\)]+$")) {
            errors.append("• Phone number format is invalid\n");
        }
        
        if (errors.length() > 0) {
            showValidationError(errors.toString());
            return false;
        }
        
        return true;
    }
    
    
    private void showValidationError(String message) {
        JOptionPane.showMessageDialog(this, 
            "Please fix the following errors:\n\n" + message, 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Update driver data object with form values
     */
    private void updateDriverData() {
        driverData.setName(nameField.getText().trim());
        driverData.setLicenseNumber(licenseField.getText().trim());
        driverData.setPhone(phoneField.getText().trim());
        driverData.setAddress(addressField.getText().trim());
        driverData.setStatus((String) statusComboBox.getSelectedItem());
    }
    
    /**
     * Set action listener for button events
     */
    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }
    
    /**
     * Get the driver data object
     */
    public DriverData getDriverData() {
        return driverData;
    }
    
    /**
     * Set focus to the first field
     */
    public void focusFirstField() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                nameField.requestFocusInWindow();
            }
        });
    }
    
    /**
     * Enable or disable form fields
     */
    public void setFieldsEnabled(boolean enabled) {
        nameField.setEnabled(enabled);
        licenseField.setEnabled(enabled);
        phoneField.setEnabled(enabled);
        addressField.setEnabled(enabled);
        statusComboBox.setEnabled(enabled);
        saveButton.setEnabled(enabled);
        deleteButton.setEnabled(enabled && !isNewDriver);
    }
    
    /**
     * Clear all form fields
     */
    public void clearFields() {
        nameField.setText("");
        licenseField.setText("");
        phoneField.setText("");
        addressField.setText("");
        statusComboBox.setSelectedIndex(0);
    }
    
    /**
     * Override setVisible to focus first field when dialog is shown
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            focusFirstField();
        }
    }
}