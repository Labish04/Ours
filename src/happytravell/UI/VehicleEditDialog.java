/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.UI;

import happytravell.model.VehiclesData;
import happytravell.dao.VehiclesDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Acer
 */
  
public class VehicleEditDialog extends JDialog {
    private VehiclesData vehicleData;
    private AdminVehiclesDetailsCardPanel parentCard;
    private VehiclesDao vehiclesDao;
    private byte[] selectedImageData;
    
    // UI Components
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JTextField numberField;
    private JSpinner seatsSpinner;
    private JTextField colorField;
    private JTextField agencyField;
    private JCheckBox activeCheckBox;
    private JLabel imagePreviewLabel;
    private JButton selectImageButton;
    private JButton saveButton;
    private JButton cancelButton;
    
    // Vehicle types
    private final String[] VEHICLE_TYPES = {
        "Bus", "Car", "Jeep", "Taxi"
    };
    
    public VehicleEditDialog(VehiclesData vehicle, AdminVehiclesDetailsCardPanel parentCard) {
        this.vehicleData = vehicle;
        this.parentCard = parentCard;
        this.vehiclesDao = new VehiclesDao();
        this.selectedImageData = vehicle.getVehicleImage(); // Start with existing image
        
        initializeDialog();
        initializeComponents();
        setupLayout();
        populateFields();
        setupEventHandlers();
    }
    
    private void initializeDialog() {
        setTitle("Edit Vehicle Details");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        
        getContentPane().setBackground(new Color(239, 204, 150));
    }
    
    private void initializeComponents() {
        // Text fields
        nameField = createStyledTextField();
        numberField = createStyledTextField();
        colorField = createStyledTextField();
        agencyField = createStyledTextField();
        
        // Combo box for vehicle type
        typeComboBox = new JComboBox<>(VEHICLE_TYPES);
        typeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeComboBox.setPreferredSize(new Dimension(200, 30));
        
        // Spinner for seats
        seatsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        seatsSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        seatsSpinner.setPreferredSize(new Dimension(100, 30));
        
        // Checkbox for active status
        activeCheckBox = new JCheckBox("Active");
        activeCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        activeCheckBox.setBackground(new Color(248, 248, 248));
        
        // Image preview
        imagePreviewLabel = new JLabel();
        imagePreviewLabel.setPreferredSize(new Dimension(250, 150));
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        imagePreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePreviewLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePreviewLabel.setBackground(new Color(239, 204, 150));
        imagePreviewLabel.setOpaque(true);
        
        // Buttons
        selectImageButton = createStyledButton("Select Image", new Color(52, 152, 219), Color.WHITE);
        saveButton = createStyledButton("Save Changes", new Color(39, 174, 96), Color.WHITE);
        cancelButton = createStyledButton("Cancel", new Color(149, 165, 166), Color.WHITE);
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 30));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }
    
    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(130, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = button.getBackground();
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(originalColor.darker());
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
        
        return button;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(239, 204, 150));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(239, 204, 150));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Add form fields
        addFormField(formPanel, gbc, "Vehicle Name:", nameField, 0);
        addFormField(formPanel, gbc, "Vehicle Type:", typeComboBox, 1);
        addFormField(formPanel, gbc, "Vehicle Number:", numberField, 2);
        addFormField(formPanel, gbc, "Number of Seats:", seatsSpinner, 3);
        addFormField(formPanel, gbc, "Vehicle Color:", colorField, 4);
        addFormField(formPanel, gbc, "Travel Agency:", agencyField, 5);
        addFormField(formPanel, gbc, "Status:", activeCheckBox, 6);
        
        // Image section
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(239, 204, 150));
        imagePanel.setBorder(BorderFactory.createTitledBorder("Vehicle Image"));
        
        JPanel imageControls = new JPanel(new FlowLayout());
        imageControls.setBackground(new Color(239, 204, 150));
        imageControls.add(imagePreviewLabel);
        
        JPanel imageButtonPanel = new JPanel(new FlowLayout());
        imageButtonPanel.setBackground(new Color(239, 204, 150));
        imageButtonPanel.add(selectImageButton);
        
        imagePanel.add(imageControls, BorderLayout.CENTER);
        imagePanel.add(imageButtonPanel, BorderLayout.SOUTH);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBackground(new Color(239, 204, 150));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        
        // Add panels to main panel
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, 
                             JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(44, 62, 80));
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }
    
    private void populateFields() {
        if (vehicleData != null) {
            nameField.setText(vehicleData.getVehicleName() != null ? 
                            vehicleData.getVehicleName() : "");
            
            if (vehicleData.getVehicleType() != null) {
                typeComboBox.setSelectedItem(vehicleData.getVehicleType());
            }
            
            numberField.setText(vehicleData.getVehicleNumber() != null ? 
                               vehicleData.getVehicleNumber() : "");
            
            seatsSpinner.setValue(vehicleData.getNumberOfSeats());
            
            colorField.setText(vehicleData.getVehicleColor() != null ? 
                              vehicleData.getVehicleColor() : "");
            
            agencyField.setText(vehicleData.getTravelAgency() != null ? 
                               vehicleData.getTravelAgency() : "");
            
            activeCheckBox.setSelected(vehicleData.isActive());
            
            // Load existing image
            updateImagePreview();
        }
    }
    
    private void updateImagePreview() {
        if (selectedImageData != null && selectedImageData.length > 0) {
            try {
                ImageIcon originalIcon = new ImageIcon(selectedImageData);
                if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                    Image scaledImage = originalIcon.getImage().getScaledInstance(
                        250, 150, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    imagePreviewLabel.setIcon(scaledIcon);
                    imagePreviewLabel.setText("");
                } else {
                    imagePreviewLabel.setText("Invalid Image");
                    imagePreviewLabel.setIcon(null);
                }
            } catch (Exception e) {
                System.err.println("Error loading vehicle image: " + e.getMessage());
                imagePreviewLabel.setText("Image Error");
                imagePreviewLabel.setIcon(null);
            }
        } else {
            imagePreviewLabel.setText("No Image Available");
            imagePreviewLabel.setIcon(null);
        }
    }
    
    private void setupEventHandlers() {
        // Select image button handler
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        
        // Save button handler
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveVehicle();
            }
        });
        
        // Cancel button handler
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Vehicle Image");
        
        // Set file filter for images
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try {
                // Read the image file
                selectedImageData = Files.readAllBytes(selectedFile.toPath());
                
                // Update the preview
                updateImagePreview();
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error reading image file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private void saveVehicle() {
        // Validate input
        if (!validateInput()) {
            return;
        }
        
        // Update vehicle data
        vehicleData.setVehicleName(nameField.getText().trim());
        vehicleData.setVehicleType((String) typeComboBox.getSelectedItem());
        vehicleData.setVehicleNumber(numberField.getText().trim());
        vehicleData.setNumberOfSeats((Integer) seatsSpinner.getValue());
        vehicleData.setVehicleColor(colorField.getText().trim());
        vehicleData.setTravelAgency(agencyField.getText().trim());
        vehicleData.setActive(activeCheckBox.isSelected());
        vehicleData.setVehicleImage(selectedImageData);
        
        // Save to database
        boolean success = vehiclesDao.updateVehicle(vehicleData);
        
        if (success) {
            JOptionPane.showMessageDialog(
                this,
                "Vehicle updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Refresh parent card if available
            if (parentCard != null) {
                parentCard.refreshVehicleData(vehicleData);
            }
            
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Failed to update vehicle. Please try again.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private boolean validateInput() {
        // Validate vehicle name
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a vehicle name.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            nameField.requestFocus();
            return false;
        }
        
        // Validate vehicle number
        if (numberField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a vehicle number.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            numberField.requestFocus();
            return false;
        }
        
        // Validate number of seats
        int seats = (Integer) seatsSpinner.getValue();
        if (seats <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "Number of seats must be greater than 0.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            seatsSpinner.requestFocus();
            return false;
        }
        
        return true;
    }
}