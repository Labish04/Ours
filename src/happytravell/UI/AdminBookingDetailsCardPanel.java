/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.UI;

import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Acer
 */
public class AdminBookingDetailsCardPanel extends PanelShadow {
    private BookingData bookingData;
    private TravellerData travellerData;
    
    // UI Components
    private JLabel profileImageLabel;
    private JLabel nameLabel;
    private JLabel pickupAddressLabel;
    private JLabel dateTimeLabel;
    private JLabel statusLabel;
    private JPanel mainContentPanel;
    
    // Colors matching the Happy Travels theme
    private static final Color CARD_BACKGROUND = new Color(239, 204, 150); // Warm beige
    private static final Color TEXT_PRIMARY = new Color(80, 50, 30);       // Dark brown
    private static final Color TEXT_SECONDARY = new Color(100, 70, 50);    // Medium brown
    private static final Color STATUS_ACTIVE = new Color(76, 175, 80);     // Green
    private static final Color STATUS_PENDING = new Color(255, 193, 7);    // Amber
    private static final Color STATUS_CANCELLED = new Color(244, 67, 54);  // Red
    
    public AdminBookingDetailsCardPanel(BookingData booking) {
        this.bookingData = booking;
        initializeComponents();
        setupLayout();
        populateData();
        setupEventListeners();
    }
    
    public void setTravellerData(TravellerData travellerData) {
        this.travellerData = travellerData;
        populateData(); // Refresh the data when traveller is set
        loadProfileImage(); // Load the profile image
    }
    
    private void initializeComponents() {
        // Set up the shadow panel properties
        setBackground(CARD_BACKGROUND);
        setRoundTopLeft(15);
        setRoundTopRight(15);
        setRoundBottomLeft(15);
        setRoundBottomRight(15);
        setShadowSize(8);
        setShadowOpacity(0.3f);
        setShadowColor(new Color(0, 0, 0, 80));
        
        // Set card dimensions
        setPreferredSize(new Dimension(660, 140));
        setMaximumSize(new Dimension(660, 140));
        setMinimumSize(new Dimension(660, 140));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Initialize profile image label
        profileImageLabel = new JLabel();
        profileImageLabel.setPreferredSize(new Dimension(60, 60)); // Slightly larger for better visibility
        profileImageLabel.setBackground(Color.WHITE);
        profileImageLabel.setOpaque(true);
        profileImageLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Set default profile icon
        setDefaultProfileIcon();
        
        // Initialize text labels
        nameLabel = new JLabel("Traveller Name: ");
        nameLabel.setFont(new Font("Candara", Font.BOLD, 16));
        nameLabel.setForeground(TEXT_PRIMARY);
        
        pickupAddressLabel = new JLabel("Pickup Address: ");
        pickupAddressLabel.setFont(new Font("Candara", Font.PLAIN, 13));
        pickupAddressLabel.setForeground(TEXT_SECONDARY);
        
        dateTimeLabel = new JLabel("Departure Time: ");
        dateTimeLabel.setFont(new Font("Candara", Font.PLAIN, 13));
        dateTimeLabel.setForeground(TEXT_SECONDARY);
        
        // Initialize status label
        statusLabel = new JLabel("Status: Pending");
        statusLabel.setFont(new Font("Candara", Font.BOLD, 11));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setOpaque(true);
        statusLabel.setBackground(STATUS_PENDING);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void setDefaultProfileIcon() {
        try {
            ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/image/user.png"));
            if (defaultIcon.getIconWidth() > 0) {
                // Scale the image to fit the label size
                Image scaledImage = defaultIcon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
                profileImageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                profileImageLabel.setText("👤");
                profileImageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
            }
        } catch (Exception e) {
            profileImageLabel.setText("👤");
            profileImageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
            profileImageLabel.setForeground(new Color(120, 120, 120));
        }
    }
    
    private void loadProfileImage() {
        if (travellerData != null && travellerData.getProfilePicture() != null) {
            try {
                byte[] imageData = travellerData.getProfilePicture();
                if (imageData != null && imageData.length > 0) {
                    // Convert byte array to BufferedImage
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    java.awt.image.BufferedImage bufferedImage = ImageIO.read(bis);
                    
                    if (bufferedImage != null) {
                        // Scale the image to fit the label (56x56 to account for border)
                        Image scaledImage = bufferedImage.getScaledInstance(56, 56, Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(scaledImage);
                        profileImageLabel.setIcon(imageIcon);
                        profileImageLabel.setText(""); // Clear any text
                        System.out.println("Profile image loaded successfully for traveller: " + getTravellerName());
                    } else {
                        System.out.println("Could not read image data for traveller: " + getTravellerName());
                        setDefaultProfileIcon();
                    }
                } else {
                    System.out.println("No image data available for traveller: " + getTravellerName());
                    setDefaultProfileIcon();
                }
            } catch (Exception e) {
                System.out.println("Error loading profile image for traveller: " + getTravellerName() + " - " + e.getMessage());
                e.printStackTrace();
                setDefaultProfileIcon();
            }
        } else {
            System.out.println("No traveller data or profile picture available");
            setDefaultProfileIcon();
        }
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBackground(CARD_BACKGROUND);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Profile image on the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 15);
        mainContentPanel.add(profileImageLabel, gbc);
        
        // Traveller name label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 8, 100); // Right margin for status
        mainContentPanel.add(nameLabel, gbc);
        
        // Pickup address label
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 6, 100);
        mainContentPanel.add(pickupAddressLabel, gbc);
        
        // Date and time label
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 100);
        mainContentPanel.add(dateTimeLabel, gbc);
        
        // Status label (top right)
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainContentPanel.add(statusLabel, gbc);
        
        add(mainContentPanel, BorderLayout.CENTER);
    }
    
    public void populateData() {
    if (bookingData != null) {
        // Existing population code
        String travellerName = getTravellerName();
        nameLabel.setText("Traveller Name: " + travellerName);
        
        String pickupAddress = getPickupAddress();
        pickupAddressLabel.setText("Pickup Address: " + pickupAddress);
        
        String departureDateTime = getDepartureDateTime();
        dateTimeLabel.setText("Departure Time: " + departureDateTime);
        
        // Add status display
        String status = bookingData.getStatus();
        if (status == null || status.trim().isEmpty()) {
            status = "Pending";
        }
        statusLabel.setText("Status: " + status);
        setStatusLabelColor(status);
        
        loadProfileImage();
    }
}
    
    private void setupEventListeners() {
        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(235, 205, 170)); // Slightly darker on hover
                mainContentPanel.setBackground(new Color(235, 205, 170));
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(CARD_BACKGROUND);
                mainContentPanel.setBackground(CARD_BACKGROUND);
                repaint();
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle card click - you can add popup or navigation logic here
                System.out.println("Booking card clicked for booking ID: " + 
                                 (bookingData != null ? bookingData.getBookingId() : "N/A"));
            }
        });
    }
    
    // Helper methods to safely get data
    private String getTravellerName() {
        if (travellerData != null) {
            try {
                String firstName = "";
                String lastName = "";
                
                // Try different ways to get the name
                try {
                    firstName = travellerData.getFirstName();
                } catch (Exception e) {
                    // If getter doesn't work, try direct field access
                    try {
                        firstName = travellerData.firstName;
                    } catch (Exception ex) {
                        firstName = "";
                    }
                }
                
                try {
                    lastName = travellerData.getLastName();
                } catch (Exception e) {
                    // If getter doesn't work, try direct field access
                    try {
                        lastName = travellerData.lastName;
                    } catch (Exception ex) {
                        lastName = "";
                    }
                }
                
                String fullName = (firstName != null ? firstName : "") + 
                                 (lastName != null ? " " + lastName : "");
                return fullName.trim().isEmpty() ? "Customer" : fullName.trim();
            } catch (Exception e) {
                return "Customer";
            }
        }
        return "Customer";
    }
    
    private String getPickupAddress() {
        if (bookingData != null) {
            try {
                // Try getter method first
                String address = bookingData.getPickupAddress();
                if (address != null && !address.trim().isEmpty()) {
                    return address;
                }
            } catch (Exception e) {
                // If getter doesn't work, try direct field access
                try {
                    if (bookingData.pickupAddress != null && !bookingData.pickupAddress.trim().isEmpty()) {
                        return bookingData.pickupAddress;
                    }
                } catch (Exception ex) {
                    // Ignore
                }
            }
        }
        return "Not specified";
    }
    
    private String getDepartureDateTime() {
        if (bookingData != null) {
            try {
                String dateTime = bookingData.getDepartureDateTime();
                if (dateTime != null && !dateTime.trim().isEmpty()) {
                    return dateTime;
                }
            } catch (Exception e) {
                // Try direct field access
                try {
                    if (bookingData.departureDateTime != null && !bookingData.departureDateTime.trim().isEmpty()) {
                        return bookingData.departureDateTime;
                    }
                } catch (Exception ex) {
                    // Ignore
                }
            }
        }
        return "Not specified";
    }
    
    private void setStatusLabelColor(String status) {
        if (status == null) return;
        
        switch (status.toLowerCase()) {
            case "active":
            case "confirmed":
            case "completed":
                statusLabel.setBackground(STATUS_ACTIVE);
                statusLabel.setForeground(Color.WHITE);
                break;
            case "pending":
                statusLabel.setBackground(STATUS_PENDING);
                statusLabel.setForeground(Color.BLACK);
                break;
            case "cancelled":
                statusLabel.setBackground(STATUS_CANCELLED);
                statusLabel.setForeground(Color.WHITE);
                break;
            default:
                statusLabel.setBackground(STATUS_PENDING);
                statusLabel.setForeground(Color.BLACK);
                break;
        }
    }
    
    // Method to set profile image from external source
    public void setProfileImage(ImageIcon image) {
        if (image != null) {
            // Scale the image to fit the label size (56x56 to account for border)
            Image scaledImage = image.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
            profileImageLabel.setIcon(new ImageIcon(scaledImage));
            profileImageLabel.setText(""); // Clear any text
        }
    }
    
    // Method to set profile image from byte array
    public void setProfileImage(byte[] imageData) {
        if (imageData != null && imageData.length > 0) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                java.awt.image.BufferedImage bufferedImage = ImageIO.read(bis);
                
                if (bufferedImage != null) {
                    Image scaledImage = bufferedImage.getScaledInstance(56, 56, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(scaledImage);
                    profileImageLabel.setIcon(imageIcon);
                    profileImageLabel.setText(""); // Clear any text
                }
            } catch (Exception e) {
                System.out.println("Error setting profile image: " + e.getMessage());
                setDefaultProfileIcon();
            }
        } else {
            setDefaultProfileIcon();
        }
    }
    
    // Getters for accessing the data
    public BookingData getBookingData() {
        return bookingData;
    }
    
    public TravellerData getTravellerData() {
        return travellerData;
    }
    
    // Method to refresh the card data
    public void refreshData() {
        populateData();
        repaint();
    }
}