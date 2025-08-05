package happytravell.controller;

import happytravell.dao.TravellerDao;
import happytravell.view.TravellerProfileAccountManagementView;
import happytravell.view.TravellerProfileView;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TravellerProfileAccountManagementController {
    private TravellerProfileAccountManagementView accountView;
    private int currentTravellerId;
    private TravellerDao travellerDao = new TravellerDao();
    
    public TravellerProfileAccountManagementController(TravellerProfileAccountManagementView accountView, int travellerId) {
        this.accountView = accountView;
        this.currentTravellerId = travellerId;
        
        // Set up navigation listeners
        this.accountView.DashboardNavigation(new DashboardNav(accountView.getDashboardlabel()));
        this.accountView.BookingNavigation(new BookingDetailsNav(accountView.getBookinglabel()));
        this.accountView.BusTicketsNavigation(new BusTicketsNav(accountView.getBusTicketslabel()));
        this.accountView.RouteNavigation(new RouteDetailsNav(accountView.getRoutelabel()));
        this.accountView.VehiclesDetailsNavigation(new VehiclesDetailsNav(accountView.getVehiclesDetailslabel()));
        this.accountView.LogOutNavigation(new LogOutNav(accountView.getLogOutlabel()));
        
        // Set up button actions
        this.accountView.setUpdateProfileButtonAction(new BackButtonListener());
        this.accountView.setchangePasswordAction(new ChangePasswordListener());
        this.accountView.setDeleteAccoutAction(new DeleteAccountListener());
    }
    
    private void handleChangePassword() {
        if (currentTravellerId == -1) {
            JOptionPane.showMessageDialog(accountView, 
                "Error: Traveller ID not set. Please login again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            redirectToLogin();
            return;
        }
        
        char[] currentPasswordChars = accountView.getCurrentPasswordField().getPassword();
        char[] newPasswordChars = accountView.getNewPasswordField().getPassword();
        char[] confirmPasswordChars = accountView.getConfirmNewPasswordField().getPassword();
        
        String currentPassword = new String(currentPasswordChars);
        String newPassword = new String(newPasswordChars);
        String confirmPassword = new String(confirmPasswordChars);
        
        // Clear password fields for security
        java.util.Arrays.fill(currentPasswordChars, '0');
        java.util.Arrays.fill(newPasswordChars, '0');
        java.util.Arrays.fill(confirmPasswordChars, '0');
        
        // Validate inputs
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(accountView, 
                "Please fill in all password fields", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(accountView, 
                "New password and confirm password do not match", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(accountView, 
                "Password must be at least 6 characters long", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verify current password
        String storedPassword = travellerDao.getTravellerById(currentTravellerId).getPassword();
        if (storedPassword == null || !storedPassword.equals(currentPassword)) {
            JOptionPane.showMessageDialog(accountView, 
                "Current password is incorrect", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Update password in database
        boolean success = travellerDao.updatePassword(currentTravellerId, newPassword);
        
        if (success) {
            JOptionPane.showMessageDialog(accountView, 
                "Password changed successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields after successful change
            accountView.getCurrentPasswordField().setText("");
            accountView.getNewPasswordField().setText("");
            accountView.getConfirmNewPasswordField().setText("");
        } else {
            JOptionPane.showMessageDialog(accountView, 
                "Failed to change password. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleDeleteAccount() {
        if (currentTravellerId == -1) {
            JOptionPane.showMessageDialog(accountView, 
                "Error: Traveller ID not set. Please login again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            redirectToLogin();
            return;
        }
        
        // First verify current password
        char[] passwordChars = accountView.getCurrentPasswordField().getPassword();
        String password = new String(passwordChars);
        java.util.Arrays.fill(passwordChars, '0'); // Clear the password from memory
        
        // Verify password before showing deletion confirmation
        String storedPassword = travellerDao.getTravellerById(currentTravellerId).getPassword();
        if (storedPassword == null || !storedPassword.equals(password)) {
            JOptionPane.showMessageDialog(accountView, 
                "Incorrect password. Account not deleted.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Now show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(accountView, 
            "Are you sure you want to permanently delete your account? This action cannot be undone.", 
            "Confirm Account Deletion", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Delete account from database
            boolean success = travellerDao.deleteTraveller(currentTravellerId);
            
            if (success) {
                JOptionPane.showMessageDialog(accountView, 
                    "Account deleted successfully. You will be redirected to the login page.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Close all views and redirect to login
                accountView.dispose();
                redirectToLogin();
            } else {
                JOptionPane.showMessageDialog(accountView, 
                    "Failed to delete account. Please try again.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void redirectToLogin() {
        if (accountView != null) {
            accountView.dispose();
        }
        LoginPageView loginView = new LoginPageView();
        LoginController loginController = new LoginController(loginView);
        loginController.open();
    }
    
    public void open() {
        this.accountView.setVisible(true);
    }
    
    public void close() {
        this.accountView.dispose();
    }
    
    // Button listeners
    class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TravellerProfileView profileView = new TravellerProfileView();
            TravellerProfileController profileController = new TravellerProfileController(profileView, currentTravellerId);
            profileController.open();
            close();
        }
    }
    
    class ChangePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleChangePassword();
        }
    }
    
    class DeleteAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleDeleteAccount();
        }
    }
    
    // Navigation classes
    class DashboardNav implements MouseListener {
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label) {
            this.dashboardLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerdashboardView dashboardView = new TravellerdashboardView();
            TravellerDashboardController dashboardController = new TravellerDashboardController(dashboardView, currentTravellerId);
            dashboardController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class BookingDetailsNav implements MouseListener {
        private JLabel bookingDetailsLabel;
        
        public BookingDetailsNav(JLabel label) {
            this.bookingDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBookingView bookingView = new TravellerBookingView();
            TravellerBookingController bookingDetailsController = new TravellerBookingController(bookingView, currentTravellerId);
            bookingDetailsController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class RouteDetailsNav implements MouseListener {
        private JLabel routeDetailsLabel;
        
        public RouteDetailsNav(JLabel label) {
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerRouteView routeDetailsView = new TravellerRouteView();
            TravellerRouteController routeDetailsController = new TravellerRouteController(routeDetailsView, currentTravellerId);
            routeDetailsController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
        
        public BusTicketsNav(JLabel label) {
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBusTicketsView busTicketsView = new TravellerBusTicketsView();
            TravellerBusTicketController busTicketsController = new TravellerBusTicketController(busTicketsView, currentTravellerId);
            busTicketsController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
        
        public VehiclesDetailsNav(JLabel label) {
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerVehiclesDetailsView vehiclesDetailsView = new TravellerVehiclesDetailsView();
            TravellerVehiclesDetailsController vehiclesDetailsController = new TravellerVehiclesDetailsController(vehiclesDetailsView, currentTravellerId);
            vehiclesDetailsController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class LogOutNav implements MouseListener {
        private JLabel logOutLabel;
        
        public LogOutNav(JLabel label) {
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                accountView.dispose();
                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
            }
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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