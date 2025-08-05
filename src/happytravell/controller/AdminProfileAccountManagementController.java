/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.controller.AdminBookingDetailsController;
import happytravell.controller.AdminBusTicketsController;
import happytravell.controller.AdminDashboardController;
import happytravell.controller.AdminProfileController;
import happytravell.controller.AdminRouteDetailsController;
import happytravell.controller.AdminVehiclesDetailsController;
import happytravell.controller.LoginController;
import happytravell.dao.AdminDao;
import happytravell.model.AdminData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileAccountManagementView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AdminProfileAccountManagementController {
    private AdminProfileAccountManagementView accountView;
    private int currentAdminId;
    private AdminDao adminDao = new AdminDao();
    
    public AdminProfileAccountManagementController(AdminProfileAccountManagementView accountView, int adminId) {
        this.accountView = accountView;
        this.currentAdminId = adminId;
        
        // Set up navigation listeners
        this.accountView.DashboardNavigation(new DashboardNav(accountView.getDashboardlabel()));
        this.accountView.BookingDetailsNavigation(new BookingDetailsNav(accountView.getBookingDetailslabel()));
        this.accountView.BusTicketsNavigation(new BusTicketsNav(accountView.getBusTicketslabel()));
        this.accountView.RouteDetailsNavigation(new RouteDetailsNav(accountView.getRouteDetailslabel()));
        this.accountView.VehiclesDetailsNavigation(new VehiclesDetailsNav(accountView.getVehiclesDetailslabel()));
        this.accountView.LogOutNavigation(new LogOutNav(accountView.getLogOutlabel()));
        
        // Set up button actions
        this.accountView.setUpdateProfileButtonAction(new BackButtonListener());
        this.accountView.setchangePasswordAction(new ChangePasswordListener());
        this.accountView.setDeleteAccoutAction(new DeleteAccountListener());
    }
    
    private void handleChangePassword() {
        if (currentAdminId == -1) {
            JOptionPane.showMessageDialog(accountView, 
                "Error: Admin ID not set. Please login again.", 
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
        AdminData admin = adminDao.getAdminById(currentAdminId);
        if (admin == null || !admin.getPassword().equals(currentPassword)) {
            JOptionPane.showMessageDialog(accountView, 
                "Current password is incorrect", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Update password in database
        boolean success = adminDao.updatePassword(currentAdminId, newPassword);
        
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
    if (currentAdminId == -1) {
        JOptionPane.showMessageDialog(accountView, 
            "Error: Admin ID not set. Please login again.", 
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
    AdminData admin = adminDao.getAdminById(currentAdminId);
    if (admin == null || !admin.getPassword().equals(password)) {
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
        boolean success = adminDao.deleteAdmin(currentAdminId);
        
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
            AdminProfileView profileView = new AdminProfileView();
            AdminProfileController profileController = new AdminProfileController(profileView, currentAdminId);
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
    
    // Navigation classes (similar to AdminProfileController)
    class DashboardNav implements MouseListener {
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label) {
            this.dashboardLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView admindashboardView = new AdmindashboardView();
            AdminDashboardController adminDashboard = new AdminDashboardController(admindashboardView, currentAdminId);
            adminDashboard.open();
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
            AdminBookingDetailsView adminBookingDetailsView = new AdminBookingDetailsView();
            AdminBookingDetailsController adminBookingDetails = new AdminBookingDetailsController(adminBookingDetailsView, currentAdminId);
            adminBookingDetails.open();
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
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController adminRouteDetails = new AdminRouteDetailsController(adminRouteDetailsView, currentAdminId);
            adminRouteDetails.open();
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
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController adminBusTickets = new AdminBusTicketsController(adminBusTicketsView, currentAdminId);
            adminBusTickets.open();
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
            AdminVehiclesDetailsView adminVehiclesDetailsView = new AdminVehiclesDetailsView();
            AdminVehiclesDetailsController adminVehiclesDetails = new AdminVehiclesDetailsController(adminVehiclesDetailsView, currentAdminId);
            adminVehiclesDetails.open();
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