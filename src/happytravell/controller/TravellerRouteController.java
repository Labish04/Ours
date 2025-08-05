/*
 * Enhanced TravellerRouteController with booking data integration and map functionality
 */
package happytravell.controller;

import happytravell.dao.BookingDao;
import happytravell.model.BookingData;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Enhanced controller for TravellerRouteView with booking integration and map functionality
 * @author Acer
 */
public class TravellerRouteController {
    private TravellerRouteView routeView;
    private int currentTravellerId;
    private BookingDao bookingDao;
    private MapIntegration mapIntegration;

    public TravellerRouteController(TravellerRouteView routeView, int travellerId) {
        this.currentTravellerId = travellerId;
        this.routeView = routeView;
        this.bookingDao = new BookingDao();
        this.mapIntegration = new MapIntegration();

        // Attach all the navigation listeners
        initializeNavigation();
        
        // Initialize map and route functionality
        initializeRouteFeatures();
        
        // Load traveller's booking data
        loadTravellerBookingData();
    }
    
    private void initializeNavigation() {
        this.routeView.DashboardNavigation(new DashboardNav(routeView.getDashboardlabel()));
        this.routeView.BookingNavigation(new BookingNav(routeView.getBookinglabel()));
        this.routeView.RouteNavigation(new RouteNav(routeView.getRoutelabel()));
        this.routeView.BusTicketsNavigation(new BusTicketsNav(routeView.getBusTicketslabel()));
        this.routeView.VehiclesDetailsNavigation(new VehiclesDetailsNav(routeView.getVehiclesDetailslabel()));
        this.routeView.ProfileNavigation(new ProfileNav(routeView.getProfilelabel()));
        this.routeView.LogOutNavigation(new LogOutNav(routeView.getLogOutlabel()));
    }
    
    private void initializeRouteFeatures() {
        // Add search functionality
        routeView.getSearchField().addActionListener(new SearchRouteListener());
        
        // Add map integration to the map panel
        SwingUtilities.invokeLater(() -> {
            mapIntegration.initializeMapInPanel(routeView.getMapPanel());
        });
    }
    
    /**
     * Load traveller's most recent booking data and populate the route fields
     */
    private void loadTravellerBookingData() {
        try {
            List<BookingData> bookings = bookingDao.getBookingsByTravellerId(currentTravellerId);
            
            if (!bookings.isEmpty()) {
                // Get the most recent booking (bookings are ordered by departure_date_time DESC)
                BookingData recentBooking = bookings.get(0);
                
                // Populate route information
                populateRouteFields(recentBooking);
                
                // Update map with route
                updateMapWithRoute(recentBooking.getPickupAddress(), recentBooking.getDropAddress());
            } else {
                // Set default placeholder text if no bookings found
                setDefaultRouteText();
            }
        } catch (Exception e) {
            System.err.println("Error loading traveller booking data: " + e.getMessage());
            e.printStackTrace();
            setDefaultRouteText();
        }
    }
    
    /**
     * Populate route text fields with booking data
     */
    private void populateRouteFields(BookingData booking) {
        String pickupAddress = booking.getPickupAddress();
        String dropAddress = booking.getDropAddress();
        
        // Route Name: PickupAddress - DropAddress
        String routeName = pickupAddress + " - " + dropAddress;
        routeView.getRouteNameTextField().setText(routeName);
        
        // Pickup Location: Pickup Address
        routeView.getPickupLocationTextField().setText(pickupAddress);
        
        // Destination: Drop Address
        routeView.getDestinationTextField().setText(dropAddress);
        
        // Update search field placeholder
        routeView.getSearchField().setText("Search along " + routeName);
    }
    
    /**
     * Set default text when no booking data is available
     */
    private void setDefaultRouteText() {
        routeView.getRouteNameTextField().setText("No route selected");
        routeView.getPickupLocationTextField().setText("No pickup location");
        routeView.getDestinationTextField().setText("No destination");
        routeView.getSearchField().setText("Search along route");
    }
    
    /**
     * Update map with route from pickup to destination
     */
    private void updateMapWithRoute(String pickup, String destination) {
        if (pickup != null && destination != null && !pickup.trim().isEmpty() && !destination.trim().isEmpty()) {
            mapIntegration.showRoute(pickup, destination, routeView.getMapPanel());
        }
    }

    public void open() {
        this.routeView.setVisible(true);
    }

    public void close() {
        this.routeView.dispose();
    }
    
    /**
     * Search functionality for finding places along the route
     */
    class SearchRouteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchQuery = routeView.getSearchField().getText().trim();
            
            if (searchQuery.isEmpty() || searchQuery.startsWith("Search along")) {
                JOptionPane.showMessageDialog(routeView, 
                    "Please enter a search term.", 
                    "Search", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Get current route information
            String pickupLocation = routeView.getPickupLocationTextField().getText();
            String destination = routeView.getDestinationTextField().getText();
            
            if ("No pickup location".equals(pickupLocation) || "No destination".equals(destination)) {
                JOptionPane.showMessageDialog(routeView, 
                    "No route information available. Please make a booking first.", 
                    "Search", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Search for places along the route
            mapIntegration.searchAlongRoute(pickupLocation, destination, searchQuery, routeView.getMapPanel());
        }
    }

    // Navigation classes remain the same...
    class DashboardNav implements MouseListener {
        private final JLabel dashboardLabel;

        public DashboardNav(JLabel label) {
            this.dashboardLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerdashboardView travellerdashboardView = new TravellerdashboardView();
            TravellerDashboardController TravellerDashboard = new TravellerDashboardController(travellerdashboardView, currentTravellerId);
            TravellerDashboard.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            dashboardLabel.setForeground(Color.WHITE);
            dashboardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            dashboardLabel.setForeground(Color.BLACK);
            dashboardLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    class BookingNav implements MouseListener {
        private final JLabel bookingLabel;

        public BookingNav(JLabel label) {
            this.bookingLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBookingView travellerBookingView = new TravellerBookingView();
            TravellerBookingController TravellerBooking = new TravellerBookingController(travellerBookingView, currentTravellerId);
            TravellerBooking.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            bookingLabel.setForeground(Color.WHITE);
            bookingLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            bookingLabel.setForeground(Color.BLACK);
            bookingLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    class RouteNav implements MouseListener {
        private final JLabel routeLabel;

        public RouteNav(JLabel label) {
            this.routeLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Already on this page, refresh data
            loadTravellerBookingData();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            routeLabel.setForeground(Color.WHITE);
            routeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            routeLabel.setForeground(Color.BLACK);
            routeLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    class BusTicketsNav implements MouseListener {
        private final JLabel busTicketsLabel;

        public BusTicketsNav(JLabel label) {
            this.busTicketsLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Navigate to bus tickets view
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
        private final JLabel vehiclesDetailsLabel;

        public VehiclesDetailsNav(JLabel label) {
            this.vehiclesDetailsLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerVehiclesDetailsView travellerVehiclesDetailsView = new TravellerVehiclesDetailsView();
            TravellerVehiclesDetailsController TravellerVehiclesDetails = new TravellerVehiclesDetailsController(travellerVehiclesDetailsView, currentTravellerId);
            TravellerVehiclesDetails.open();
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
        private final JLabel profileLabel;

        public ProfileNav(JLabel label) {
            this.profileLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerProfileView travellerProfileView = new TravellerProfileView();
            TravellerProfileController TravellerProfile = new TravellerProfileController(travellerProfileView, currentTravellerId);
            TravellerProfile.open();
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
        private final JLabel logOutLabel;

        public LogOutNav(JLabel label) {
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