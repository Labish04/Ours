package happytravell.UI;

import happytravell.dao.BusTicketsDao;
import happytravell.model.BusTicketsData;
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
import java.sql.Timestamp;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class AdminBusTicketsCardPanel extends PanelShadow {
    private BusTicketsData ticketData;
    
    // UI Components
    private JLabel busIconLabel;
    private JLabel nameLabel;
    private JLabel pickupLabel;
    private JLabel dropLabel;
    private JLabel priceLabel;
    private JLabel seatLabel;
    private JPanel mainContentPanel;
    
    // Colors matching the Happy Travels theme
    private static final Color CARD_BACKGROUND = new Color(239, 204, 150); // Warm beige
    private static final Color TEXT_PRIMARY = new Color(80, 50, 30);       // Dark brown
    private static final Color TEXT_SECONDARY = new Color(100, 70, 50);    // Medium brown
    private static final Color SEAT_COLOR = new Color(70, 130, 180);       // Steel blue
    
    // Callback interface for refreshing parent container
    public interface RefreshCallback {
        void onRefresh();
    }
    
    private RefreshCallback refreshCallback;
    
    public AdminBusTicketsCardPanel(BusTicketsData ticket) {
        this.ticketData = ticket;
        initializeComponents();
        setupLayout();
        populateData();
        setupEventListeners();
    }
    
    public AdminBusTicketsCardPanel(BusTicketsData ticket, RefreshCallback callback) {
        this.ticketData = ticket;
        this.refreshCallback = callback;
        initializeComponents();
        setupLayout();
        populateData();
        setupEventListeners();
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
        
        // Initialize bus icon label
        busIconLabel = new JLabel();
        busIconLabel.setPreferredSize(new Dimension(60, 60));
        busIconLabel.setOpaque(false);
        busIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        busIconLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Set bus icon
        try {
            ImageIcon busIcon = new ImageIcon(getClass().getResource("/image/front-of-bus.png"));
            Image scaledImage = busIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            busIconLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            busIconLabel.setText("🚌");
            busIconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        }
        
        // Initialize text labels
        nameLabel = new JLabel("Passenger: ");
        nameLabel.setFont(new Font("Candara", Font.BOLD, 16));
        nameLabel.setForeground(TEXT_PRIMARY);
        
        pickupLabel = new JLabel("Pickup: ");
        pickupLabel.setFont(new Font("Candara", Font.PLAIN, 13));
        pickupLabel.setForeground(TEXT_SECONDARY);
        
        dropLabel = new JLabel("Drop: ");
        dropLabel.setFont(new Font("Candara", Font.PLAIN, 13));
        dropLabel.setForeground(TEXT_SECONDARY);
        
        // Initialize price label
        priceLabel = new JLabel("Price: ");
        priceLabel.setFont(new Font("Candara", Font.BOLD, 14));
        priceLabel.setForeground(TEXT_PRIMARY);
    
        
        // Initialize seat label
        seatLabel = new JLabel();
        seatLabel.setFont(new Font("Candara", Font.BOLD, 14));
        seatLabel.setForeground(Color.WHITE);
        seatLabel.setOpaque(true);
        seatLabel.setBackground(SEAT_COLOR);
        seatLabel.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
        seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBackground(CARD_BACKGROUND);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Bus icon on the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 15);
        mainContentPanel.add(busIconLabel, gbc);
        
        // Passenger name label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 8, 100); // Right margin for seat
        mainContentPanel.add(nameLabel, gbc);
        
        // Pickup label
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 6, 100);
        mainContentPanel.add(pickupLabel, gbc);
        
        // Drop label
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 6, 100);
        mainContentPanel.add(dropLabel, gbc);
        
        // Date label
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 100);
        mainContentPanel.add(priceLabel, gbc);
        
        // Seat label (top right)
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainContentPanel.add(seatLabel, gbc);
        
        add(mainContentPanel, BorderLayout.CENTER);
    }
    
    public void populateData() {
    if (ticketData != null) {
        // Populate passenger name - use getName() instead of phone number
        String name = ticketData.getName() != null ? ticketData.getName() : "Unknown Passenger";
        nameLabel.setText("Passenger: " + name);
        
        // Populate pickup address - use getPickupAddress() instead of phone number
        String pickup = ticketData.getPickupAddress() != null ? 
            shortenAddress(ticketData.getPickupAddress()) : "Not specified";
        pickupLabel.setText("Pickup: " + pickup);
        
        // Populate drop address
        String drop = ticketData.getDropAddress() != null ? 
            shortenAddress(ticketData.getDropAddress()) : "Not specified";
        dropLabel.setText("Drop: " + drop);
        
        // Populate travel date
         String price = String.format("Price: $%.2f", ticketData.getTicketPrice());
            ticketData.getTicketPrice();
         priceLabel.setText(price);
        
        // Populate seat number
        String seat = ticketData.getSeatNumber() != null ? 
            "Seat: " + ticketData.getSeatNumber() : "Seat: N/A";
        seatLabel.setText(seat);
    }
}
    
    
    
    
    private String shortenAddress(String address) {
        if (address.length() > 30) {
            return address.substring(0, 27) + "...";
        }
        return address;
    }
    
    private void setupEventListeners() {
        // Create mouse adapter for the main panel
        MouseAdapter mouseHandler = new MouseAdapter() {
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
                showTicketDetailsPopup();
            }
        };
        
        // Add mouse listener to both the main panel and content panel
        addMouseListener(mouseHandler);
        mainContentPanel.addMouseListener(mouseHandler);
        
        // Also add to all child components to ensure clicks are captured
        busIconLabel.addMouseListener(mouseHandler);
        nameLabel.addMouseListener(mouseHandler);
        pickupLabel.addMouseListener(mouseHandler);
        dropLabel.addMouseListener(mouseHandler);
        priceLabel.addMouseListener(mouseHandler);
        seatLabel.addMouseListener(mouseHandler);
    }
    
    private void showTicketDetailsPopup() {
        if (ticketData == null) return;
        
        // Create a formatted message with ticket details
        StringBuilder details = new StringBuilder();
        details.append("<html><body style='width: 300px; padding: 10px;'>");
        details.append("<h2 style='color: #50321E; margin-bottom: 15px;'>Ticket Details</h2>");
        
        // Add all ticket information with proper formatting
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Passenger Name:</b> ")
               .append(ticketData.getName() != null ? ticketData.getName() : "N/A").append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Phone Number:</b> ")
               .append(ticketData.getPhoneNumber() != null ? ticketData.getPhoneNumber() : "N/A").append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Bus Number:</b> ")
               .append(ticketData.getBusNumber() != null ? ticketData.getBusNumber() : "N/A").append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Seat Number:</b> ")
               .append(ticketData.getSeatNumber() != null ? ticketData.getSeatNumber() : "N/A").append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Pickup Address:</b> ")
               .append(ticketData.getPickupAddress() != null ? ticketData.getPickupAddress() : "N/A").append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Drop Address:</b> ")
               .append(ticketData.getDropAddress() != null ? ticketData.getDropAddress() : "N/A").append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Ticket Price:</b> $")
               .append(String.format("%.2f", ticketData.getTicketPrice())).append("</div>");
        
        details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Departure Time:</b> ")
               .append(ticketData.getDepartureDateTime() != null ? formatTimestamp(ticketData.getDepartureDateTime()) : "N/A").append("</div>");
        
        if (ticketData.getReturnDateTime() != null) {
            details.append("<div style='margin-bottom: 10px;'><b style='color: #50321E;'>Return Time:</b> ")
                   .append(formatTimestamp(ticketData.getReturnDateTime())).append("</div>");
        }
        
        details.append("</body></html>");
        
        // Create custom buttons for the dialog
        Object[] options = {"Delete Ticket", "Close"};
        
        int choice = JOptionPane.showOptionDialog(
            SwingUtilities.getWindowAncestor(this), // Get the parent window
            details.toString(), 
            "Ticket Details - ID: " + ticketData.getId(), 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[1] // Default to "Close"
        );
        
        // Handle the delete action
        if (choice == 0) { // "Delete Ticket" was clicked
            int confirm = JOptionPane.showConfirmDialog(
                SwingUtilities.getWindowAncestor(this),
                "Are you sure you want to delete this ticket?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Call the DAO to delete the ticket
                BusTicketsDao busTicketsDao = new BusTicketsDao();
                boolean deleted = busTicketsDao.deleteTicket(ticketData.getId());
                
                if (deleted) {
                    JOptionPane.showMessageDialog(
                        SwingUtilities.getWindowAncestor(this),
                        "Ticket deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    // Use callback to refresh the parent container
                    if (refreshCallback != null) {
                        refreshCallback.onRefresh();
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        SwingUtilities.getWindowAncestor(this),
                        "Failed to delete ticket!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }
    
    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) return "N/A";
        return timestamp.toString();
    }
    
    // Getters for accessing the data
    public BusTicketsData getTicketData() {
        return ticketData;
    }
    
    // Method to refresh the card data
    public void refreshData() {
        populateData();
        repaint();
    }
}