package happytravell.UI;

import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Card panel component for displaying traveler route details in Admin Route Details View
 * Shows traveler photo, name, and route information from bookings
 * 
 * @author Acer
 */
public class AdminRouteDetailsCardPanel extends JPanel {
    
    private static final Color CARD_BACKGROUND = new Color(255, 255, 255);
    private static final Color BORDER_COLOR = new Color(200, 200, 200);
    private static final Color HEADER_BACKGROUND = new Color(241, 215, 184);
    private static final Color TEXT_PRIMARY = new Color(51, 51, 51);
    private static final Color TEXT_SECONDARY = new Color(102, 102, 102);
    
    private static final int CARD_WIDTH = 320;
    private static final int CARD_HEIGHT = 400;
    private static final int PHOTO_SIZE = 80;
    private static final int MAP_HEIGHT = 150;
    
    private BookingData bookingData;
    private TravellerData travellerData;
    
    // UI Components
    private JLabel photoLabel;
    private JLabel nameLabel;
    private JLabel routeNameLabel;
    private JLabel pickupLabel;
    private JLabel destinationLabel;
    private JPanel mapPanel;
    
    /**
     * Constructor for creating a route card panel
     * @param booking BookingData containing route information
     * @param traveller TravellerData containing traveler information
     */
    public AdminRouteDetailsCardPanel(BookingData booking, TravellerData traveller) {
        this.bookingData = booking;
        this.travellerData = traveller;
        
        initializePanel();
        setupLayout();
        populateData();
    }
    
    /**
     * Initialize the panel properties
     */
    private void initializePanel() {
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setMaximumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setMinimumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setBackground(CARD_BACKGROUND);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        setLayout(new BorderLayout(0, 10));
    }
    
    /**
     * Setup the layout and create UI components
     */
    private void setupLayout() {
        // Header Panel (Traveler Info)
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Route Details Panel
        JPanel routePanel = createRouteDetailsPanel();
        add(routePanel, BorderLayout.CENTER);
        
        // Map Panel
        mapPanel = createMapPanel();
        add(mapPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Create the header panel with traveler photo and name
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(HEADER_BACKGROUND);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Photo label
        photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(PHOTO_SIZE, PHOTO_SIZE));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setVerticalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 2));
        photoLabel.setOpaque(true);
        photoLabel.setBackground(Color.WHITE);
        
        // Name label
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Candara", Font.BOLD, 16));
        nameLabel.setForeground(TEXT_PRIMARY);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        headerPanel.add(photoLabel, BorderLayout.WEST);
        headerPanel.add(nameLabel, BorderLayout.CENTER);
        
        return headerPanel;
    }
    
    /**
     * Create the route details panel
     */
    private JPanel createRouteDetailsPanel() {
        JPanel routePanel = new JPanel();
        routePanel.setLayout(new BoxLayout(routePanel, BoxLayout.Y_AXIS));
        routePanel.setBackground(CARD_BACKGROUND);
        routePanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        
        // Route Name
        routeNameLabel = createInfoLabel("Route:", "", Font.BOLD, 14);
        routePanel.add(routeNameLabel);
        routePanel.add(Box.createVerticalStrut(8));
        
        // Pickup Location
        pickupLabel = createInfoLabel("Pickup:", "", Font.PLAIN, 12);
        routePanel.add(pickupLabel);
        routePanel.add(Box.createVerticalStrut(5));
        
        // Destination
        destinationLabel = createInfoLabel("Destination:", "", Font.PLAIN, 12);
        routePanel.add(destinationLabel);
        
        return routePanel;
    }
    
    /**
     * Create a formatted info label
     */
    private JLabel createInfoLabel(String title, String value, int fontStyle, int fontSize) {
        JLabel label = new JLabel();
        label.setFont(new Font("Candara", fontStyle, fontSize));
        label.setForeground(fontStyle == Font.BOLD ? TEXT_PRIMARY : TEXT_SECONDARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Set initial text
        updateLabelText(label, title, value);
        
        return label;
    }
    
    /**
     * Update label text with title and value
     */
    private void updateLabelText(JLabel label, String title, String value) {
        if (value == null || value.trim().isEmpty()) {
            value = "Not specified";
        }
        
        // Truncate long text
        if (value.length() > 35) {
            value = value.substring(0, 32) + "...";
        }
        
        label.setText("<html><b>" + title + "</b> " + value + "</html>");
    }
    
    /**
     * Create the map panel placeholder
     */
    private JPanel createMapPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(CARD_WIDTH - 20, MAP_HEIGHT));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR), 
            "Route Map",
            0, 0, new Font("Candara", Font.BOLD, 12), TEXT_SECONDARY
        ));
        
        JLabel mapLabel = new JLabel("Loading map...", SwingConstants.CENTER);
        mapLabel.setFont(new Font("Candara", Font.ITALIC, 11));
        mapLabel.setForeground(TEXT_SECONDARY);
        
        panel.add(mapLabel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Populate the panel with actual data
     */
    private void populateData() {
        if (travellerData != null) {
            populateTravellerInfo();
        }
        
        if (bookingData != null) {
            populateRouteInfo();
            loadRouteMap();
        }
    }
    
    /**
     * Populate traveler information
     */
    private void populateTravellerInfo() {
        // Set traveler name
        String fullName = buildFullName();
        nameLabel.setText("<html><div style='text-align: left;'>" + fullName + "</div></html>");
        
        // Set traveler photo
        setTravellerPhoto();
    }
    
    /**
     * Build full name from traveler data
     */
    private String buildFullName() {
        StringBuilder nameBuilder = new StringBuilder();
        
        if (travellerData.getFirstName() != null && !travellerData.getFirstName().trim().isEmpty()) {
            nameBuilder.append(travellerData.getFirstName().trim());
        }
        
        if (travellerData.getLastName() != null && !travellerData.getLastName().trim().isEmpty()) {
            if (nameBuilder.length() > 0) {
                nameBuilder.append(" ");
            }
            nameBuilder.append(travellerData.getLastName().trim());
        }
        
        return nameBuilder.length() > 0 ? nameBuilder.toString() : "Unknown Traveler";
    }
    
    /**
     * Set traveler photo from byte array
     */
    private void setTravellerPhoto() {
        try {
            byte[] photoBytes = travellerData.getProfilePicture();
            
            if (photoBytes != null && photoBytes.length > 0) {
                ByteArrayInputStream bis = new ByteArrayInputStream(photoBytes);
                BufferedImage originalImage = ImageIO.read(bis);
                
                if (originalImage != null) {
                    // Scale image to fit the photo label
                    Image scaledImage = originalImage.getScaledInstance(
                        PHOTO_SIZE - 4, PHOTO_SIZE - 4, Image.SCALE_SMOOTH);
                    photoLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    setDefaultPhoto();
                }
            } else {
                setDefaultPhoto();
            }
        } catch (IOException e) {
            System.err.println("Error loading traveler photo: " + e.getMessage());
            setDefaultPhoto();
        }
    }
    
    /**
     * Set default photo when no image is available
     */
    private void setDefaultPhoto() {
        photoLabel.setIcon(null);
        photoLabel.setText("No Photo");
        photoLabel.setFont(new Font("Candara", Font.ITALIC, 10));
        photoLabel.setForeground(TEXT_SECONDARY);
    }
    
    /**
     * Populate route information from booking data
     */
    private void populateRouteInfo() {
        // Route Name: Pickup - Destination
        String pickup = bookingData.getPickupAddress();
        String destination = bookingData.getDropAddress();
        String routeName = buildRouteName(pickup, destination);
        updateLabelText(routeNameLabel, "Route:", routeName);
        
        // Pickup Location
        updateLabelText(pickupLabel, "Pickup:", pickup);
        
        // Destination
        updateLabelText(destinationLabel, "Destination:", destination);
    }
    
    /**
     * Build route name from pickup and destination
     */
    private String buildRouteName(String pickup, String destination) {
        if (pickup == null) pickup = "Unknown";
        if (destination == null) destination = "Unknown";
        
        return pickup + " → " + destination;
    }
    
    /**
     * Load route map (placeholder implementation)
     * In a real implementation, you would integrate with a mapping service
     */
    private void loadRouteMap() {
        SwingUtilities.invokeLater(() -> {
            String pickup = bookingData.getPickupAddress();
            String destination = bookingData.getDropAddress();
            
            if (pickup != null && destination != null && 
                !pickup.trim().isEmpty() && !destination.trim().isEmpty()) {
                
                // Create a simple route representation
                createSimpleRouteDisplay(pickup, destination);
            } else {
                showNoRouteMessage();
            }
        });
    }
    
    /**
     * Create a simple text-based route display
     */
    private void createSimpleRouteDisplay(String pickup, String destination) {
        mapPanel.removeAll();
        
        JPanel routeDisplay = new JPanel();
        routeDisplay.setLayout(new BoxLayout(routeDisplay, BoxLayout.Y_AXIS));
        routeDisplay.setBackground(new Color(250, 250, 250));
        
        // Start point
        JLabel startLabel = new JLabel("🚩 " + truncateText(pickup, 25));
        startLabel.setFont(new Font("Candara", Font.PLAIN, 11));
        startLabel.setForeground(new Color(0, 150, 0));
        startLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Arrow/Path
        JLabel pathLabel = new JLabel("⬇");
        pathLabel.setFont(new Font("Candara", Font.BOLD, 16));
        pathLabel.setForeground(TEXT_SECONDARY);
        pathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // End point
        JLabel endLabel = new JLabel("📍 " + truncateText(destination, 25));
        endLabel.setFont(new Font("Candara", Font.PLAIN, 11));
        endLabel.setForeground(new Color(200, 0, 0));
        endLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Distance/Info (placeholder)
        JLabel infoLabel = new JLabel("Route Path");
        infoLabel.setFont(new Font("Candara", Font.ITALIC, 10));
        infoLabel.setForeground(TEXT_SECONDARY);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        routeDisplay.add(Box.createVerticalStrut(10));
        routeDisplay.add(startLabel);
        routeDisplay.add(Box.createVerticalStrut(5));
        routeDisplay.add(pathLabel);
        routeDisplay.add(Box.createVerticalStrut(5));
        routeDisplay.add(endLabel);
        routeDisplay.add(Box.createVerticalStrut(5));
        routeDisplay.add(infoLabel);
        routeDisplay.add(Box.createVerticalStrut(10));
        
        mapPanel.add(routeDisplay, BorderLayout.CENTER);
        mapPanel.revalidate();
        mapPanel.repaint();
    }
    
    /**
     * Show message when no route data is available
     */
    private void showNoRouteMessage() {
        mapPanel.removeAll();
        
        JLabel noRouteLabel = new JLabel("No route data available");
        noRouteLabel.setFont(new Font("Candara", Font.ITALIC, 11));
        noRouteLabel.setForeground(TEXT_SECONDARY);
        noRouteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        mapPanel.add(noRouteLabel, BorderLayout.CENTER);
        mapPanel.revalidate();
        mapPanel.repaint();
    }
    
    /**
     * Utility method to truncate text
     */
    private String truncateText(String text, int maxLength) {
        if (text == null) return "N/A";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Get booking data associated with this card
     */
    public BookingData getBookingData() {
        return bookingData;
    }
    
    /**
     * Get traveller data associated with this card
     */
    public TravellerData getTravellerData() {
        return travellerData;
    }
    
    /**
     * Refresh the card data
     */
    public void refreshCard() {
        populateData();
    }
}