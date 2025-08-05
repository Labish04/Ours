package happytravell.controller;

import happytravell.popup.DriverPopup;
import happytravell.dao.DriverDao;
import happytravell.model.DriverData;
import happytravell.view.AdminDriverDetailsView;
import happytravell.UI.AdminDriverDetailsCardPanel;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Controller for Driver Details functionality
 * Handles the interaction between view, model and data access layer
 */
public class DriverDetailsController {
    
    private AdminDriverDetailsView view;
    private DriverDao driverDao;
    private List<DriverData> driversList;
    private int currentAdminId;
    
    public DriverDetailsController(AdminDriverDetailsView view) {
        this.view = view;
        this.driverDao = new DriverDao();
        this.view.BackNavigation(new BackNav());
        this.view.DashboardNavigation(new DashboardNav(view.getDashboardlabel()));
        this.view.BookingDetailsNavigation(new BookingDetailsNav(view.getBookingDetailslabel()));
        this.view.BusTicketsNavigation(new BusTicketsNav(view.getBusTicketslabel()));
        this.view.VehiclesDetailsNavigation(new VehiclesNav(view.getVehiclesDetailslabel()));
        this.view.RouteDetailsNavigation(new RouteDetailsNav(view.getRouteDetailslabel()));
        this.view.ProfileNavigation(new ProfileNav(view.getProfilelabel()));
        this.view.LogOutNavigation(new LogOutNav(view.getLogOutlabel()));
        
        initializeController();
    }
     public void open(){
    this.view.setVisible(true);
    } 
    public void close(){
    this.view.dispose();
    }
    
    /**
     * Initialize controller and set up event listeners
     */
    private void initializeController() {
        loadDriversData();
        setupEventListeners();
    }
    
    /**
     * Set up event listeners for UI components
     */
    private void setupEventListeners() {
        // Add driver button listener
        view.getAddDriverButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDriverDialog();
            }
        });
        
//       
    }
    
    
    
    
    
    
    
    
    /**
     * Load drivers data from database and display in UI
     */
    public void loadDriversData() {
        try {
            driversList = driverDao.getAllDrivers();
            displayDriverCards();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Error loading drivers data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Display driver cards in the scroll panel
     */
    private void displayDriverCards() {
        JPanel contentPanel = (JPanel) view.scrollPane.getViewport().getView();
        contentPanel.removeAll();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < driversList.size(); i++) {
        DriverData driver = driversList.get(i);
        AdminDriverDetailsCardPanel cardPanel = new AdminDriverDetailsCardPanel(driver);
        
        // Add click listener to card panel
        cardPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDriverEditDialog(driver);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {
                cardPanel.setCardHovered(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                cardPanel.setCardHovered(false);
            }
        });
        
        contentPanel.add(cardPanel);
        
        
        if (i < driversList.size() - 1) {
            contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }
    }
    
    // Optional: Add some padding at the bottom
    contentPanel.add(Box.createVerticalGlue());
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    /**
     * Show dialog for adding new driver
     */
    private void showAddDriverDialog() {
        DriverData newDriver = new DriverData();
        DriverPopup dialog = new DriverPopup(view, true, newDriver, true);
        
        dialog.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("SAVE".equals(e.getActionCommand())) {
                    if (saveDriver(dialog.getDriverData())) {
                        dialog.dispose();
                        loadDriversData(); // Refresh the list
                        JOptionPane.showMessageDialog(view, 
                            "Driver added successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if ("CANCEL".equals(e.getActionCommand())) {
                    dialog.dispose();
                }
            }
        });
        
        dialog.setVisible(true);
    }
    
    /**
     * Show dialog for editing existing driver
     */
    private void showDriverEditDialog(DriverData driver) {
        DriverPopup dialog = new DriverPopup(view, true, driver, false);
        
        dialog.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                
                if ("SAVE".equals(command)) {
                    if (updateDriver(dialog.getDriverData())) {
                        dialog.dispose();
                        loadDriversData(); // Refresh the list
                        JOptionPane.showMessageDialog(view, 
                            "Driver updated successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if ("DELETE".equals(command)) {
                    int result = JOptionPane.showConfirmDialog(view,
                        "Are you sure you want to delete driver: " + driver.getName() + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                    
                    if (result == JOptionPane.YES_OPTION) {
                        if (deleteDriver(driver.getId())) {
                            dialog.dispose();
                            loadDriversData(); // Refresh the list
                            JOptionPane.showMessageDialog(view, 
                                "Driver deleted successfully!", 
                                "Success", 
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else if ("CANCEL".equals(command)) {
                    dialog.dispose();
                }
            }
        });
        
        dialog.setVisible(true);
    }
    
    /**
     * Save new driver to database
     */
    private boolean saveDriver(DriverData driver) {
        try {
            if (validateDriverData(driver)) {
                return driverDao.insertDriver(driver);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Error saving driver: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Update existing driver in database
     */
    private boolean updateDriver(DriverData driver) {
        try {
            if (validateDriverData(driver)) {
                return driverDao.updateDriver(driver);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Error updating driver: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Delete driver from database
     */
    private boolean deleteDriver(int driverId) {
        try {
            return driverDao.deleteDriver(driverId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Error deleting driver: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Validate driver data before saving
     */
    private boolean validateDriverData(DriverData driver) {
        if (driver.getName() == null || driver.getName().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Driver name is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (driver.getLicenseNumber() == null || driver.getLicenseNumber().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "License number is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (driver.getPhone() == null || driver.getPhone().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Phone number is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (driver.getStatus() == null || driver.getStatus().trim().isEmpty()) {
            driver.setStatus("AVAILABLE"); // Default status
        }
        
        return true;
    }
    
     // Dashboard Navigation
    class DashboardNav implements MouseListener{
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label){
            this.dashboardLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView admindashboardView = new AdmindashboardView();
            AdminDashboardController AdminDashboard= new AdminDashboardController(admindashboardView, currentAdminId);
            AdminDashboard.open();
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
    
    //    Booking Details Navigation
    class BookingDetailsNav implements MouseListener{
        
        private JLabel bookingDetailsLabel;
        
        public BookingDetailsNav(JLabel label){
            this.bookingDetailsLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBookingDetailsView adminBookingDetailsView = new AdminBookingDetailsView();
            AdminBookingDetailsController AdminBookingDetails= new AdminBookingDetailsController(adminBookingDetailsView, currentAdminId);
            AdminBookingDetails.open();
            close();
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.WHITE);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.BLACK);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
//  Route Details Navigation
    class RouteDetailsNav implements MouseListener{
        
        private JLabel routeDetailsLabel;      
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView , currentAdminId);
            AdminRouteDetails.open();
            close();
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
    
//  Bus Ticket Navigation  
    class BusTicketsNav implements MouseListener{
        
        private JLabel busTicketsLabel;
        public BusTicketsNav(JLabel label){
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController AdminBusTickets= new AdminBusTicketsController(adminBusTicketsView, currentAdminId);
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
    
//  Vehicles Details Navigation
    class VehiclesNav implements MouseListener{
        
        private JLabel vehiclesDetailsLabel;
        public VehiclesNav(JLabel label){
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminVehiclesDetailsView adminVehiclesDetailsView = new AdminVehiclesDetailsView();
            AdminVehiclesDetailsController  AdminVehiclesDetails= new  AdminVehiclesDetailsController(adminVehiclesDetailsView, currentAdminId);
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
    
//    Profile Navigation
    class ProfileNav implements MouseListener{
        
        private JLabel profileLabel;
        public ProfileNav(JLabel label){
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminProfileView adminProfileView = new AdminProfileView();
            AdminProfileController  AdminProfile= new  AdminProfileController(adminProfileView , currentAdminId);
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
    
//    LogOut Navigation
    class LogOutNav implements MouseListener{
        
        private JLabel logOutLabel;
        public LogOutNav(JLabel label){
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                view.dispose();

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
    
    class BackNav implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminVehiclesDetailsView vehiclesView = new AdminVehiclesDetailsView();
            AdminVehiclesDetailsController vehiclesDetails = new AdminVehiclesDetailsController(vehiclesView, currentAdminId);
            vehiclesDetails.open();
            close();
        }
    }
    
    /**
     * Refresh driver data - can be called externally
     */
    public void refreshData() {
        loadDriversData();
    }
    
    /**
     * Get current drivers list
     */
    public List<DriverData> getDriversList() {
        return driversList;
    }
}