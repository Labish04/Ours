/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;
//
//import image.AdminProfileAccountManagementController;
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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Acer
 */
public class AdminProfileController {
    private AdminProfileView profileView = new AdminProfileView();
    private int currentAdminId;
    private AdminDao adminDao = new AdminDao();
    
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String email = "";
    private String phoneNumber = "";
    private String address = "";
    
    
    public AdminProfileController(AdminProfileView adminProfileView, int adminId) {
        this.profileView = adminProfileView;
        this.currentAdminId = adminId;
        this.profileView.NextNavigation(new NextNav());
        this.profileView.DashboardNavigation(new AdminProfileController.DashboardNav(adminProfileView.getDashboardlabel()));
        this.profileView.BookingDetailsNavigation(new AdminProfileController.BookingDetailsNav(adminProfileView.getBookingDetailslabel()));
        this.profileView.BusTicketsNavigation(new AdminProfileController.BusTicketsNav(adminProfileView.getBusTicketslabel()));
        this.profileView.RouteDetailsNavigation(new AdminProfileController.RouteDetailsNav(adminProfileView.getRouteDetailslabel()));
        this.profileView.VehiclesDetailsNavigation(new AdminProfileController.VehiclesDetailsNav(adminProfileView.getVehiclesDetailslabel()));
        this.profileView.LogOutNavigation(new AdminProfileController.LogOutNav(adminProfileView.getLogOutlabel()));
        this.profileView.uploadProfilePicture(new UploadProfilePicture(profileView.getUploadProfile()));
        this.profileView.setUpdateProfileButtonAction(e -> handleUpdateProfile());
        
        loadAdminData();
        loadExistingProfilePicture();     
    }
    private void loadAdminData() {
    if (currentAdminId != -1) {
        try {
            AdminData admin = adminDao.getAdminById(currentAdminId);
            if (admin != null) {
                firstName = admin.getFirstName() != null ? admin.getFirstName() : "";
                lastName = admin.getLastName() != null ? admin.getLastName() : "";
                username = admin.getUsername() != null ? admin.getUsername() : "";
                phoneNumber = admin.getPhoneNumber() != null ? admin.getPhoneNumber() : "";
                email = admin.getEmail() != null ? admin.getEmail() : "";
                address = admin.getAddress() != null ? admin.getAddress() : "";
                
                profileView.getFirstNameTextField().setText(firstName);
                profileView.getLastNameTextField().setText(lastName);
                profileView.getUsernameTextField().setText(username);
                profileView.getPhoneNumberTextField().setText(phoneNumber);
                profileView.getEmailTextField().setText(email);
                profileView.getAddressTextField().setText(address);
                profileView.getAdminName().setText(firstName + " "+lastName );

            } else {
                JOptionPane.showMessageDialog(profileView, 
                    "Admin account not found. Please contact system administrator.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                // Redirect to login page
                redirectToLogin();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(profileView, 
                "Database error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            // Redirect to login page
            redirectToLogin();
        }
    } else {
        JOptionPane.showMessageDialog(profileView, 
            "Invalid session. Please login again.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        // Redirect to login page
        redirectToLogin();
    }
}

private void redirectToLogin() {
    profileView.dispose();
    LoginPageView loginView = new LoginPageView();
    LoginController loginController = new LoginController(loginView);
    loginController.open();
}
    
   private void handleUpdateProfile() {
    if (currentAdminId == -1) {
        JOptionPane.showMessageDialog(profileView, 
            "Error: Admin ID not set. Please login again.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        redirectToLogin();
        return;
    }
    String changedFirstName = profileView.getFirstNameTextField().getText().trim();
    String changedLastName = profileView.getLastNameTextField().getText().trim();
    String changedUsername = profileView.getUsernameTextField().getText().trim();
    String changedPhoneNumber = profileView.getPhoneNumberTextField().getText().trim();
    String changedEmail = profileView.getEmailTextField().getText().trim();
    String changedAddress = profileView.getAddressTextField().getText().trim();
    
    // Validate required fields
    if (changedFirstName.isEmpty() || changedLastName.isEmpty() || changedUsername.isEmpty() || 
        changedPhoneNumber.isEmpty() || changedEmail.isEmpty()) {
        JOptionPane.showMessageDialog(profileView, 
            "Please fill in all required fields (First Name, Last Name, Username, Phone, Email)", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
         AdminDao adminDao = new AdminDao();
        boolean success = adminDao.updateAdminProfile(currentAdminId, 
                                            changedFirstName, 
                                            changedLastName, 
                                            changedUsername, 
                                            changedPhoneNumber, 
                                            changedEmail, 
                                            changedAddress);

        if (success) {
            firstName = changedFirstName;
            lastName = changedLastName;
            username = changedUsername;
            phoneNumber = changedPhoneNumber;
            email = changedEmail;
            address = changedAddress;
            profileView.getAdminName().setText(firstName + " "+lastName );

            JOptionPane.showMessageDialog(profileView, 
                "Profile updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(profileView, 
                "Failed to update profile. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
    }

    public void setCurrentAdminId(int adminId) {
        this.currentAdminId = adminId;
        loadExistingProfilePicture();
    }
    
    private void loadExistingProfilePicture() {
        if (currentAdminId != -1) {
            try {
                byte[] existingProfilePicture = adminDao.getProfilePicture(currentAdminId);
                if (existingProfilePicture != null && existingProfilePicture.length > 0) {
                    displayProfileImageInView(existingProfilePicture);
                } else {
                    profileView.setDefaultProfileImage();
                }
            } catch (Exception e) {
            }
        }
    }
    
    
    private void displayProfileImageInView(byte[] imageData) {
        try {
            if (imageData != null && imageData.length > 0) {
                ImageIcon originalIcon = new ImageIcon(imageData);
                
                int labelWidth = 130;
                int labelHeight = 130;
                
                Image scaledImage = originalIcon.getImage().getScaledInstance(
                    labelWidth, labelHeight, Image.SCALE_SMOOTH);
                
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
                profileView.displayProfileImage(imageData);
               
            }
        } catch (Exception e) {
        }
    }
    
   
    
    public void open(){
    this.profileView.setVisible(true);
    } 
    public void close(){
    this.profileView.dispose();
    }
    
     //Profile Picture
    class UploadProfilePicture implements MouseListener{
        
        private JLabel insertProfileIcon;
        
        public UploadProfilePicture(JLabel label){
            this.insertProfileIcon = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (currentAdminId == -1) {
                JOptionPane.showMessageDialog(profileView, 
                    "Error: Owner ID not set. Please login again.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JFileChooser fileChooser = new JFileChooser();
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image files (*.jpg, *.jpeg, *.png, *.gif, *.bmp)", 
                "jpg", "jpeg", "png", "gif", "bmp");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            
            int result = fileChooser.showOpenDialog(profileView);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                
                if (file != null && file.exists() && file.isFile()) {
                    try {
                        long fileSize = file.length();
                        if (fileSize > 5 * 1024 * 1024) {
                            JOptionPane.showMessageDialog(profileView, 
                                "File size too large. Please select an image smaller than 5MB.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        byte[] imageData = Files.readAllBytes(file.toPath());
                        
                        
                        AdminDao adminDao = new AdminDao();
                        boolean success = adminDao.updateProfilePicture(currentAdminId, imageData);
                        
                        if (success) {
                            displayImageInView(imageData);
                            profileView.selectedProfileFile(file);
                            
                            JOptionPane.showMessageDialog(profileView, 
                                "Profile picture updated successfully!", 
                                "Success", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(profileView, 
                                "Failed to save profile picture to database.", 
                                "Database Error", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(profileView, 
                            "Error reading file: " + ex.getMessage(), 
                            "File Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(profileView, 
                        "Invalid file selected or file does not exist.", 
                        "File Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        private void displayImageInView(byte[] imageData) {
            try {
                if (imageData != null && imageData.length > 0) {
                    ImageIcon originalIcon = new ImageIcon(imageData);
                    
                    int labelWidth = 130;
                    int labelHeight = 130;
                    
                    Image scaledImage = originalIcon.getImage().getScaledInstance(
                        labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    
                    profileView.displayProfileImage(imageData);
                    
                } 
            } catch (Exception e) {
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            insertProfileIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            insertProfileIcon.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    
    
    

    
    //    Dashboard Navigation
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
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView,currentAdminId);
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
    class VehiclesDetailsNav implements MouseListener{
        
        private JLabel vehiclesDetailsLabel;
        public VehiclesDetailsNav(JLabel label){
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
    
    // Traveller Navigation
    class NextNav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                AdminProfileAccountManagementView accountView = new AdminProfileAccountManagementView();
                AdminProfileAccountManagementController accountController = new AdminProfileAccountManagementController(accountView,currentAdminId);
                accountController.open();
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
                profileView.dispose();

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
