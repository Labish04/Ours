package happytravell.controller;

import happytravell.UI.AdminRouteDetailsCardPanel;
import happytravell.dao.BookingDao;
import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Enhanced AdminRouteDetailsController with card panel integration
 * Displays traveler route information in card format
 * 
 * @author Acer
 */
public class AdminRouteDetailsController {
    private AdminRouteDetailsView routeView;
    private int currentAdminId;
    private BookingDao bookingDao;
    private TravellerDao travellerDao;
    
    // Card management
    private JPanel cardContainer;
    private Map<Integer, AdminRouteDetailsCardPanel> cardPanelMap;
    
    public AdminRouteDetailsController(AdminRouteDetailsView adminRouteDetailsView, int adminId) {
        this.routeView = adminRouteDetailsView;
        this.currentAdminId = adminId;
        this.bookingDao = new BookingDao();
        this.travellerDao = new TravellerDao();
        this.cardPanelMap = new HashMap<>();
        
        initializeNavigation();
        initializeCardContainer();
        loadTravelerRouteData();
    }
    
    /**
     * Initialize navigation listeners
     */
    private void initializeNavigation() {
        this.routeView.DashboardNavigation(new DashboardNav(routeView.getDashboardlabel()));
        this.routeView.BookingDetailsNavigation(new BookingDetailsNav(routeView.getBookingDetailslabel()));
        this.routeView.BusTicketsNavigation(new BusTicketsNav(routeView.getBusTicketsLabel()));
        this.routeView.VehiclesDetailsNavigation(new VehiclesDetailsNav(routeView.getVehiclesDetailslabel()));
        this.routeView.ProfileNavigation(new ProfileNav(routeView.getProfilelabel()));
        this.routeView.LogOutNavigation(new LogOutNav(routeView.getLogOutlabel()));
    }
    
    /**
     * Initialize the card container panel
     */
    private void initializeCardContainer() {
        // Get the container panel from the view
        cardContainer = routeView.getContainerPanel();
        cardContainer.setLayout(new GridBagLayout());
        cardContainer.setBackground(new Color(255, 242, 227)); // Match the view background
        
        // Clear any existing components
        cardContainer.removeAll();
    }
    
    /**
     * Load traveler route data and create card panels
     */
    private void loadTravelerRouteData() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Show loading message
                showLoadingMessage();
                
                // Get all bookings with route information
                List<BookingData> allBookings = bookingDao.getAllBookings();
                
                if (allBookings == null || allBookings.isEmpty()) {
                    showNoDataMessage();
                    return;
                }
                
                // Clear existing cards
                cardContainer.removeAll();
                cardPanelMap.clear();
                
                // Create cards for each unique traveler route
                createRouteCards(allBookings);
                
                // Refresh the display
                refreshCardDisplay();
                
            } catch (Exception e) {
                System.err.println("Error loading traveler route data: " + e.getMessage());
                e.printStackTrace();
                showErrorMessage("Failed to load route data: " + e.getMessage());
            }
        });
    }
    
    /**
     * Create route cards from booking data
     */
    private void createRouteCards(List<BookingData> bookings) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        int row = 0;
        int col = 0;
        int maxCols = 2; // Number of cards per row
        
        for (BookingData booking : bookings) {
            try {
                // Get traveler data for this booking
                TravellerData traveller = travellerDao.getTravellerById(booking.getTravellerId());
                
                if (traveller != null && hasValidRouteData(booking)) {
                    // Create card panel
                    AdminRouteDetailsCardPanel cardPanel = new AdminRouteDetailsCardPanel(booking, traveller);
                    
                    // Set grid position
                    gbc.gridx = col;
                    gbc.gridy = row;
                    
                    // Add to container
                    cardContainer.add(cardPanel, gbc);
                    cardPanelMap.put(booking.getBookingId(), cardPanel);
                    
                    // Update grid position
                    col++;
                    if (col >= maxCols) {
                        col = 0;
                        row++;
                    }
                }
            } catch (Exception e) {
                System.err.println("Error creating card for booking " + booking.getBookingId() + ": " + e.getMessage());
            }
        }
        
        // Add a filler component to push cards to the top
        if (row > 0 || col > 0) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.gridwidth = maxCols;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            cardContainer.add(Box.createVerticalGlue(), gbc);
        }
    }
    
    /**
     * Check if booking has valid route data
     */
    private boolean hasValidRouteData(BookingData booking) {
        return booking != null && 
               booking.getPickupAddress() != null && !booking.getPickupAddress().trim().isEmpty() &&
               booking.getDropAddress() != null && !booking.getDropAddress().trim().isEmpty();
    }
    
    /**
     * Show loading message
     */
    private void showLoadingMessage() {
        cardContainer.removeAll();
        
        JLabel loadingLabel = new JLabel("Loading traveler routes...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Candara", Font.ITALIC, 14));
        loadingLabel.setForeground(new Color(102, 102, 102));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        cardContainer.add(loadingLabel, gbc);
        cardContainer.revalidate();
        cardContainer.repaint();
    }
    
    /**
     * Show no data message
     */
    private void showNoDataMessage() {
        cardContainer.removeAll();
        
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(new Color(255, 242, 227));
        
        JLabel noDataLabel = new JLabel("No traveler routes found");
        noDataLabel.setFont(new Font("Candara", Font.BOLD, 16));
        noDataLabel.setForeground(new Color(102, 102, 102));
        noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel suggestionLabel = new JLabel("Routes will appear here after travelers make bookings");
        suggestionLabel.setFont(new Font("Candara", Font.ITALIC, 12));
        suggestionLabel.setForeground(new Color(153, 153, 153));
        suggestionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        messagePanel.add(Box.createVerticalGlue());
        messagePanel.add(noDataLabel);
        messagePanel.add(Box.createVerticalStrut(10));
        messagePanel.add(suggestionLabel);
        messagePanel.add(Box.createVerticalGlue());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        
        cardContainer.add(messagePanel, gbc);
        cardContainer.revalidate();
        cardContainer.repaint();
    }
    
    /**
     * Show error message
     */
    private void showErrorMessage(String message) {
        cardContainer.removeAll();
        
        JLabel errorLabel = new JLabel("<html><div style='text-align: center;'>" + 
                                      "Error: " + message + "</div></html>", SwingConstants.CENTER);
        errorLabel.setFont(new Font("Candara", Font.PLAIN, 12));
        errorLabel.setForeground(Color.RED);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        cardContainer.add(errorLabel, gbc);
        cardContainer.revalidate();
        cardContainer.repaint();
    }
    
    /**
     * Refresh the card display
     */
    private void refreshCardDisplay() {
        cardContainer.revalidate();
        cardContainer.repaint();
        
        // Update the scroll pane if needed
        Container parent = cardContainer.getParent();
        while (parent != null && !(parent instanceof JScrollPane)) {
            parent = parent.getParent();
        }
        if (parent instanceof JScrollPane) {
            ((JScrollPane) parent).getViewport().revalidate();
        }
    }
    
    /**
     * Refresh all data (can be called from navigation)
     */
    public void refreshData() {
        loadTravelerRouteData();
    }
    
    public void open() {
        this.routeView.setVisible(true);
    }
    
    public void close() {
        this.routeView.dispose();
    }
    
    // Navigation classes remain the same as in your original file
    class DashboardNav implements MouseListener {
        private JLabel dashboardLabel;
        public DashboardNav(JLabel label) { this.dashboardLabel = label; }
        @Override public void mouseClicked(MouseEvent e) {
            AdmindashboardView admindashboardView = new AdmindashboardView();
            AdminDashboardController AdminDashboard = new AdminDashboardController(admindashboardView, currentAdminId);
            AdminDashboard.open();
            close();
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {
            dashboardLabel.setForeground(Color.WHITE);
            dashboardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override public void mouseExited(MouseEvent e) {
            dashboardLabel.setForeground(Color.BLACK);
            dashboardLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    class BookingDetailsNav implements MouseListener {
        private JLabel bookingDetailsLabel;
        public BookingDetailsNav(JLabel label){ this.bookingDetailsLabel = label; }
        @Override public void mouseClicked(MouseEvent e) {
            AdminBookingDetailsView adminBookingDetailsView = new AdminBookingDetailsView();
            AdminBookingDetailsController AdminBookingDetails = new AdminBookingDetailsController(adminBookingDetailsView, currentAdminId);
            AdminBookingDetails.open();
            close();
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.WHITE);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override public void mouseExited(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.BLACK);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    
    
    class RouteDetailsNav implements MouseListener {
        private JLabel routeDetailsLabel;      
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            // Already on this page
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.WHITE);
            routeDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.BLACK);
            routeDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    class BusTicketsNav implements MouseListener {
        private JLabel busTicketsLabel;
        public BusTicketsNav(JLabel label){
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController AdminBusTickets = new AdminBusTicketsController(adminBusTicketsView, currentAdminId);
            AdminBusTickets.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            busTicketsLabel.setForeground(Color.WHITE);
            busTicketsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            busTicketsLabel.setForeground(Color.BLACK);
            busTicketsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    class VehiclesDetailsNav implements MouseListener {
        private JLabel vehiclesDetailsLabel;
        public VehiclesDetailsNav(JLabel label){
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminVehiclesDetailsView adminVehiclesDetailsView = new AdminVehiclesDetailsView();
            AdminVehiclesDetailsController AdminVehiclesDetails = new AdminVehiclesDetailsController(adminVehiclesDetailsView, currentAdminId);
            AdminVehiclesDetails.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            vehiclesDetailsLabel.setForeground(Color.WHITE);
            vehiclesDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            vehiclesDetailsLabel.setForeground(Color.BLACK);
            vehiclesDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    class ProfileNav implements MouseListener {
        private JLabel profileLabel;
        public ProfileNav(JLabel label){
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminProfileView adminProfileView = new AdminProfileView();
            AdminProfileController AdminProfile = new AdminProfileController(adminProfileView, currentAdminId);
            AdminProfile.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            profileLabel.setForeground(Color.WHITE);
            profileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            profileLabel.setForeground(Color.BLACK);
            profileLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    class LogOutNav implements MouseListener {
        private JLabel logOutLabel;
        public LogOutNav(JLabel label){
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                routeView.dispose();

                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            logOutLabel.setForeground(Color.WHITE);
            logOutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            logOutLabel.setForeground(Color.BLACK);
            logOutLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
}