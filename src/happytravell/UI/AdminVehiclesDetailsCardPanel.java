/*
 * Enhanced Vehicle Card Panel with click functionality
 */
package happytravell.UI;

import happytravell.UI.PanelShadow;
import happytravell.model.VehiclesData;
import happytravell.dao.VehiclesDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Enhanced Vehicle Card Panel with click functionality
 * @author Acer
 */
public class AdminVehiclesDetailsCardPanel extends PanelShadow {
    private VehiclesData vehicleData;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
     private JLabel priceLabel;
    private JLabel numberLabel;
    
    private VehiclesDao vehiclesDao;
    private boolean isHovered = false;
    
    public AdminVehiclesDetailsCardPanel(VehiclesData vehicle) {
        this.vehicleData = vehicle;
        this.vehiclesDao = new VehiclesDao();
        initializeComponents();
        setupLayout();
        populateData();
        setupClickHandler();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 204, 150));
        setFocusable(true);
        setRequestFocusEnabled(true);
        setRoundTopLeft(15);
        setRoundTopRight(15);
        setRoundBottomLeft(15);
        setRoundBottomRight(15);
        setShadowSize(8);
        setShadowOpacity(0.5f);
        setShadowColor(new Color(0, 0, 0, 100));
        setPreferredSize(new Dimension(650, 150));
        setMaximumSize(new Dimension(650, 150));
        setMinimumSize(new Dimension(650, 150));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(120, 120));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 120), 1));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setOpaque(true);
        
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        nameLabel.setForeground(new Color(80, 50, 30));
        
        typeLabel = new JLabel();
        typeLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
        typeLabel.setForeground(new Color(100, 70, 50));
        
        numberLabel = new JLabel();
        numberLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
        numberLabel.setForeground(new Color(100, 70, 50));
        
       
        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 100, 0));
    }
    
    private void setupLayout() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(239, 204, 150));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(239, 204, 150));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(140, 140));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(239, 204, 150));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(typeLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(numberLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalGlue());

        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private void setupClickHandler() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showVehicleDetailsPopup();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setBackground(new Color(245, 215, 165));
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setBackground(new Color(239, 204, 150));
                repaint();
            }
        });
    }
    
    private void showVehicleDetailsPopup() {
        VehicleDetailsPopup popup = new VehicleDetailsPopup(vehicleData, this);
        popup.setVisible(true);
    }
    
    private void populateData() {
        if (vehicleData != null) {
            // Set vehicle name
            nameLabel.setText(vehicleData.getVehicleName() != null ? 
                            vehicleData.getVehicleName() : "Vehicle Name");
            
            // Set vehicle type
            typeLabel.setText("Type: " + (vehicleData.getVehicleType() != null ? 
                             vehicleData.getVehicleType() : "Not specified"));
            
            // Set vehicle number
            numberLabel.setText("Number: " + (vehicleData.getVehicleNumber() != null ? 
                               vehicleData.getVehicleNumber() : "Not available"));
            
            // Set vehicle price (formatted with Rs. and 2 decimal places)
            // Set vehicle image
            byte[] imageData = vehicleData.getVehicleImage();
            if (imageData != null && imageData.length > 0) {
                try {
                    ImageIcon originalIcon = new ImageIcon(imageData);
                    if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                        Image scaledImage = originalIcon.getImage().getScaledInstance(
                            120, 100, Image.SCALE_SMOOTH);
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
                imageLabel.setText("No Image");
                imageLabel.setIcon(null);
            }
        }
    }
    
    public VehiclesData getVehicleData() {
        return vehicleData;
    }
    
    /**
     * Update the card with new vehicle data
     * @param vehicle Updated vehicle data
     */
    public void updateVehicleData(VehiclesData vehicle) {
        this.vehicleData = vehicle;
        populateData();
        repaint();
    }
    
    /**
     * Refresh card data from database
     */
    public void refreshData() {
        if (vehicleData != null && vehicleData.getVehicleId() > 0) {
            VehiclesData updatedData = vehiclesDao.getVehicleById(vehicleData.getVehicleId());
            if (updatedData != null) {
                updateVehicleData(updatedData);
            }
        }
    }
    
    public void refreshVehicleData(VehiclesData updatedVehicleData) {
    this.vehicleData = updatedVehicleData;
    
    // Update all the labels with new data
    if (nameLabel != null) {
        nameLabel.setText(updatedVehicleData.getVehicleName() != null ? 
                                updatedVehicleData.getVehicleName() : "Unknown Vehicle");
    }
    
    if (typeLabel != null) {
        typeLabel.setText(updatedVehicleData.getVehicleType() != null ? 
                                updatedVehicleData.getVehicleType() : "Unknown Type");
    }
    
    if (numberLabel != null) {
        numberLabel.setText(updatedVehicleData.getVehicleNumber() != null ? 
                                  updatedVehicleData.getVehicleNumber() : "N/A");
    }
    
    
    
    
    // Update vehicle image
    if (imageLabel != null) {
        byte[] imageData = updatedVehicleData.getVehicleImage();
        if (imageData != null && imageData.length > 0) {
            try {
                ImageIcon originalIcon = new ImageIcon(imageData);
                if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                    // Adjust size according to your card's image label size
                    Image scaledImage = originalIcon.getImage().getScaledInstance(
                        150, 100, Image.SCALE_SMOOTH); // Adjust dimensions as needed
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    imageLabel.setIcon(scaledIcon);
                    imageLabel.setText("");
                } else {
                    imageLabel.setText("Invalid Image");
                    imageLabel.setIcon(null);
                }
            } catch (Exception e) {
                System.err.println("Error loading updated vehicle image: " + e.getMessage());
                imageLabel.setText("Image Error");
                imageLabel.setIcon(null);
            }
        } else {
            imageLabel.setText("No Image");
            imageLabel.setIcon(null);
        }
    }
    
    // Refresh the display
    revalidate();
    repaint();
}
    
}