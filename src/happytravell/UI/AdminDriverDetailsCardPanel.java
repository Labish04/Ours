package happytravell.UI;

import happytravell.model.DriverData;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Card panel for displaying driver details in a card format
 * Provides hover effects and click handling
 */
public class AdminDriverDetailsCardPanel extends JPanel {
    
    private DriverData driverData;
    private boolean isHovered = false;
    
    // UI Components
    private JLabel nameLabel;
    private JLabel licenseLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JLabel statusLabel;
    
    // Colors
    private final Color CARD_BACKGROUND = new Color(239, 204, 150);
    private final Color CARD_HOVER_BACKGROUND = new Color(245, 245, 245);
    private final Color CARD_BORDER = new Color(220, 220, 220);
    private final Color STATUS_AVAILABLE = new Color(34, 139, 34);
    private final Color STATUS_BUSY = new Color(255, 140, 0);
    private final Color STATUS_INACTIVE = new Color(220, 20, 60);
    
    /**
     * Constructor
     * @param driver DriverData object containing driver information
     */
    public AdminDriverDetailsCardPanel(DriverData driver) {
        this.driverData = driver;
        initializeComponents();
        setupLayout();
        populateData();
        setupCardEffects();
    }
    
    /**
     * Initialize UI components
     */
    private void initializeComponents() {
        setBackground(new Color(239, 204, 150));
        setPreferredSize(new Dimension(640, 120));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        setMinimumSize(new Dimension(600, 120));
        
        setBackground(CARD_BACKGROUND);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CARD_BORDER, 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Initialize labels
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(Color.decode("#000000"));
        
        licenseLabel = new JLabel();
        licenseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        licenseLabel.setForeground(Color.decode("#000000"));
        
        phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneLabel.setForeground(Color.decode("#000000"));
        
        addressLabel = new JLabel();
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressLabel.setForeground(Color.decode("#000000"));
        
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(new EmptyBorder(4, 8, 4, 8));
    }
    
    /**
     * Setup panel layout
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBackground(new Color(239, 204, 150));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Left section - Driver info
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(2, 0, 2, 0);
        
        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        leftPanel.add(nameLabel, gbc);
        
        // License number
        gbc.gridy = 1;
        leftPanel.add(licenseLabel, gbc);
        
        // Phone
        gbc.gridy = 2;
        leftPanel.add(phoneLabel, gbc);
        
        // Address
        gbc.gridy = 3;
        leftPanel.add(addressLabel, gbc);
        
        // Add left panel to main content
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(leftPanel, gbc);
        
        // Right section - Status
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.add(statusLabel);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(rightPanel, gbc);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Add click instruction at bottom
        JLabel clickHint = new JLabel("Click to edit driver details");
        clickHint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        clickHint.setForeground(Color.decode("#000000"));
        clickHint.setHorizontalAlignment(SwingConstants.RIGHT);
        add(clickHint, BorderLayout.SOUTH);
    }
    
    /**
     * Populate labels with driver data
     */
    private void populateData() {
        if (driverData != null) {
            nameLabel.setText(driverData.getName() != null ? driverData.getName() : "N/A");
            licenseLabel.setText("License: " + (driverData.getLicenseNumber() != null ? driverData.getLicenseNumber() : "N/A"));
            phoneLabel.setText("Phone: " + (driverData.getPhone() != null ? driverData.getPhone() : "N/A"));
            addressLabel.setText("Address: " + (driverData.getAddress() != null ? driverData.getAddress() : "N/A"));
            
            updateStatusLabel();
        }
    }
    
    /**
     * Update status label with appropriate color
     */
    private void updateStatusLabel() {
        String status = driverData.getStatus() != null ? driverData.getStatus() : "AVAILABLE";
        statusLabel.setText(status);
        
        switch (status.toUpperCase()) {
            case "AVAILABLE":
                statusLabel.setBackground(STATUS_AVAILABLE);
                statusLabel.setForeground(Color.WHITE);
                break;
            case "BUSY":
                statusLabel.setBackground(STATUS_BUSY);
                statusLabel.setForeground(Color.WHITE);
                break;
            case "INACTIVE":
                statusLabel.setBackground(STATUS_INACTIVE);
                statusLabel.setForeground(Color.WHITE);
                break;
            default:
                statusLabel.setBackground(new Color(128, 128, 128));
                statusLabel.setForeground(Color.WHITE);
                break;
        }
    }
    
    /**
     * Setup card hover effects
     */
    private void setupCardEffects() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCardHovered(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setCardHovered(false);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // This will be handled by the controller
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // Add pressed effect
                setBackground(new Color(235, 235, 235));
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(isHovered ? CARD_HOVER_BACKGROUND : CARD_BACKGROUND);
                repaint();
            }
        });
    }
    
    /**
     * Set card hover state
     * @param hovered true if hovered, false otherwise
     */
    public void setCardHovered(boolean hovered) {
        this.isHovered = hovered;
        if (hovered) {
            setBackground(CARD_HOVER_BACKGROUND);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                new EmptyBorder(14, 19, 14, 19)
            ));
        } else {
            setBackground(CARD_BACKGROUND);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CARD_BORDER, 1),
                new EmptyBorder(15, 20, 15, 20)
            ));
        }
        repaint();
    }
    
    /**
     * Get the driver data associated with this card
     * @return DriverData object
     */
    public DriverData getDriverData() {
        return driverData;
    }
    
    /**
     * Update the card with new driver data
     * @param driver Updated DriverData object
     */
    public void updateDriverData(DriverData driver) {
        this.driverData = driver;
        populateData();
        repaint();
    }
    
    /**
     * Override paint component for custom painting if needed
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Add subtle shadow effect
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (isHovered) {
            // Draw shadow when hovered
            g2d.setColor(new Color(0, 0, 0, 20));
            g2d.fillRoundRect(2, 2, getWidth() - 2, getHeight() - 2, 8, 8);
        }
        
        g2d.dispose();
    }
}