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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Acer
 */

public class VehicleDetailsPopup extends JDialog {
    private VehiclesData vehicleData;
    private AdminVehiclesDetailsCardPanel parentCard;
    private VehiclesDao vehiclesDao;
    
    // UI Components
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel numberLabel;
    private JLabel seatsLabel;
    private JLabel priceLabel;
    private JLabel colorLabel;
    private JLabel agencyLabel;
    private JLabel statusLabel;
    private JButton editButton;
    private JButton deleteButton;
    private JButton closeButton;
    
    public VehicleDetailsPopup(VehiclesData vehicle, AdminVehiclesDetailsCardPanel parentCard) {
        this.vehicleData = vehicle;
        this.parentCard = parentCard;
        this.vehiclesDao = new VehiclesDao();
        
        initializeDialog();
        initializeComponents();
        setupLayout();
        populateData();
        setupEventHandlers();
    }
    
    private void initializeDialog() {
        setTitle("Vehicle Details");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set background color
        getContentPane().setBackground(new Color(239, 204, 150));
    }
    
    private void initializeComponents() {
        // Image label
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(300, 200));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setOpaque(true);
        
        // Detail labels
        nameLabel = createDetailLabel("", 20, Font.BOLD);
        typeLabel = createDetailLabel("", 16, Font.PLAIN);
        numberLabel = createDetailLabel("", 16, Font.PLAIN);
        seatsLabel = createDetailLabel("", 16, Font.PLAIN);
        priceLabel = createDetailLabel("", 16, Font.BOLD);  
        priceLabel.setForeground(new Color(0, 100, 0)); 
        colorLabel = createDetailLabel("", 16, Font.PLAIN);
        agencyLabel = createDetailLabel("", 16, Font.PLAIN);
        statusLabel = createDetailLabel("", 16, Font.PLAIN);
        
        // Buttons
        editButton = createStyledButton("Edit Vehicle", new Color(52, 152, 219), Color.WHITE);
        deleteButton = createStyledButton("Delete Vehicle", new Color(231, 76, 60), Color.WHITE);
        closeButton = createStyledButton("Close", new Color(149, 165, 166), Color.WHITE);
    }
    
    private JLabel createDetailLabel(String text, int fontSize, int fontStyle) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", fontStyle, fontSize));
        label.setForeground(new Color(52, 73, 94));
        label.setBorder(new EmptyBorder(5, 0, 5, 0));
        return label;
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
        
        // Image panel
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(new Color(239, 204, 150));
        imagePanel.add(imageLabel);
        
        // Details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(239, 204, 150));
        detailsPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        // Add details to panel
        detailsPanel.add(createDetailRow("Vehicle Name:", nameLabel));
        detailsPanel.add(createDetailRow("Type:", typeLabel));
        detailsPanel.add(createDetailRow("Number:", numberLabel));
        detailsPanel.add(createDetailRow("Seats:", seatsLabel));
        detailsPanel.add(createDetailRow("Price:", priceLabel));
        detailsPanel.add(createDetailRow("Color:", colorLabel));
        detailsPanel.add(createDetailRow("Agency:", agencyLabel));
        detailsPanel.add(createDetailRow("Status:", statusLabel));
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBackground(new Color(239, 204, 150));
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(closeButton);
        
        // Add panels to main panel
        mainPanel.add(imagePanel, BorderLayout.NORTH);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createDetailRow(String labelText, JLabel valueLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(new Color(239, 204, 150));
        row.setBorder(new EmptyBorder(5, 0, 5, 0));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(44, 62, 80));
        label.setPreferredSize(new Dimension(120, 25));
        
        row.add(label, BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.CENTER);
        
        return row;
    }
    
    private void populateData() {
        if (vehicleData != null) {
            // Set vehicle details
            nameLabel.setText(vehicleData.getVehicleName() != null ? 
                            vehicleData.getVehicleName() : "Not specified");
            
            typeLabel.setText(vehicleData.getVehicleType() != null ? 
                             vehicleData.getVehicleType() : "Not specified");
            
            numberLabel.setText(vehicleData.getVehicleNumber() != null ? 
                               vehicleData.getVehicleNumber() : "Not available");
            
            seatsLabel.setText(String.valueOf(vehicleData.getNumberOfSeats()));
            
            
            colorLabel.setText(vehicleData.getVehicleColor() != null ? 
                              vehicleData.getVehicleColor() : "Not specified");
            
            agencyLabel.setText(vehicleData.getTravelAgency() != null ? 
                               vehicleData.getTravelAgency() : "Not specified");
            
            statusLabel.setText(vehicleData.isActive() ? "Active" : "Inactive");
            statusLabel.setForeground(vehicleData.isActive() ? 
                                    new Color(39, 174, 96) : new Color(231, 76, 60));
            
            // Set vehicle image
            byte[] imageData = vehicleData.getVehicleImage();
            if (imageData != null && imageData.length > 0) {
                try {
                    ImageIcon originalIcon = new ImageIcon(imageData);
                    if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                        Image scaledImage = originalIcon.getImage().getScaledInstance(
                            300, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        imageLabel.setIcon(scaledIcon);
                        imageLabel.setText("");
                    } else {
                        imageLabel.setText("Invalid Image");
                        imageLabel.setIcon(null);
                    }
                } catch (Exception e) {
                    System.err.println("Error loading vehicle image: " + e.getMessage());
                    imageLabel.setText("Image Error");
                    imageLabel.setIcon(null);
                }
            } else {
                imageLabel.setText("No Image Available");
                imageLabel.setIcon(null);
            }
        }
    }
    
    private void setupEventHandlers() {
        // Edit button handler
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editVehicle();
            }
        });
        
        // Delete button handler
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteVehicle();
            }
        });
        
        // Close button handler
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void editVehicle() {
        // Close current popup and open edit dialog
        dispose();
        VehicleEditDialog editDialog = new VehicleEditDialog(vehicleData, parentCard);
        editDialog.setVisible(true);
    }
    
    private void deleteVehicle() {
        int result = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this vehicle?\n" +
            "Vehicle: " + vehicleData.getVehicleName() + "\n" +
            "Number: " + vehicleData.getVehicleNumber(),
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            boolean success = vehiclesDao.deleteVehicle(vehicleData.getVehicleId());
            
            if (success) {
                JOptionPane.showMessageDialog(
                    this,
                    "Vehicle deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                // Refresh parent card or remove it from the list
                if (parentCard != null) {
                    // You might want to notify the parent container to remove this card
                    // or refresh the vehicle list
                    parentCard.setVisible(false);
                }
                
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete vehicle. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

