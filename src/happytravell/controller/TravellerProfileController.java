/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.AdminDao;
import happytravell.dao.TravellerDao;
import happytravell.model.TravellerData;
import happytravell.view.AdminProfileAccountManagementView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
import happytravell.view.TravellerProfileAccountManagementView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;
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
public class TravellerProfileController {
    private TravellerProfileView profileView;
    private int currentTravellerId;
    private TravellerDao travellerDao = new TravellerDao();
    
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String email = "";
    private String phoneNumber = "";
    private String address = "";
    
    public TravellerProfileController(TravellerProfileView travellerProfileView, int travellerId) {
        this.profileView = travellerProfileView;
        this.currentTravellerId = travellerId;
        
        // Set up navigation
        this.profileView.NextNavigation(new NextNav());
        this.profileView.DashboardNavigation(new DashboardNav(profileView.getDashboardlabel()));
        this.profileView.BookingNavigation(new BookingNav(profileView.getBookinglabel()));
        this.profileView.RouteNavigation(new RouteNav(profileView.getRoutelabel()));
        this.profileView.BusTicketsNavigation(new BusTicketsNav(profileView.getBusTicketslabel()));
        this.profileView.VehiclesDetailsNavigation(new VehiclesDetailsNav(profileView.getVehiclesDetailslabel()));
        this.profileView.LogOutNavigation(new LogOutNav(profileView.getLogOutlabel()));
        this.profileView.ProfileNavigation(new ProfileNav(profileView.getProfilelabel()));
        
        // Set up profile picture upload
        this.profileView.uploadProfilePicture(new UploadProfileImage(profileView.getUploadProfile()));
        
        // Set up update button action
        this.profileView.setUpdateProfileButtonAction(e -> handleUpdateProfile());
        
        loadTravellerData();
        loadExistingProfilePicture();     
    }

    private void loadTravellerData() {
        if (currentTravellerId != -1) {
            try {
                TravellerData traveller = travellerDao.getTravellerById(currentTravellerId);
                if (traveller != null) {
                    firstName = traveller.getFirstName() != null ? traveller.getFirstName() : "";
                    lastName = traveller.getLastName() != null ? traveller.getLastName() : "";
                    username = traveller.getUsername() != null ? traveller.getUsername() : "";
                    phoneNumber = traveller.getPhoneNumber() != null ? traveller.getPhoneNumber() : "";
                    email = traveller.getEmail() != null ? traveller.getEmail() : "";
                    address = traveller.getAddress() != null ? traveller.getAddress() : "";
                    
                    profileView.getFirstNameTextField().setText(firstName);
                    profileView.getLastNameTextField().setText(lastName);
                    profileView.getUsernameTextField().setText(username);
                    profileView.getPhoneNumberTextField().setText(phoneNumber);
                    profileView.getEmailTextField().setText(email);
                    profileView.getAddressTextField().setText(address);
                    profileView.geTravellerName().setText(firstName + " "+lastName );
                    
//                    // Set traveller name in the view
//                    profileView.getravellerNameLabel().setText(firstName + " " + lastName);
                } else {
                    showErrorAndRedirect("Traveller account not found. Please contact system administrator.");
                }
            } catch (Exception e) {
                showErrorAndRedirect("Database error: " + e.getMessage());
            }
        } else {
            showErrorAndRedirect("Invalid session. Please login again.");
        }
    }

    private void showErrorAndRedirect(String message) {
        JOptionPane.showMessageDialog(profileView, message, "Error", JOptionPane.ERROR_MESSAGE);
        redirectToLogin();
    }

    private void redirectToLogin() {
        profileView.dispose();
        LoginPageView loginView = new LoginPageView();
        LoginController loginController = new LoginController(loginView);
        loginController.open();
    }
    
    private void handleUpdateProfile() {
        if (currentTravellerId == -1) {
            showErrorAndRedirect("Error: Traveller ID not set. Please login again.");
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
        
        boolean success = travellerDao.updateTravellerProfile(currentTravellerId, 
                                            changedFirstName, 
                                            changedLastName, 
                                            changedUsername, 
                                            changedPhoneNumber, 
                                            changedEmail, 
                                            changedAddress);

        if (success) {
            // Update local fields
            firstName = changedFirstName;
            lastName = changedLastName;
            username = changedUsername;
            phoneNumber = changedPhoneNumber;
            email = changedEmail;
            address = changedAddress;
            
            profileView.geTravellerName().setText(firstName +" "+lastName );


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

    private void loadExistingProfilePicture() {
        if (currentTravellerId != -1) {
            try {
                byte[] existingProfilePicture = travellerDao.getProfilePicture(currentTravellerId);
                if (existingProfilePicture != null && existingProfilePicture.length > 0) {
                    displayProfileImageInView(existingProfilePicture);
                } else {
                    profileView.setDefaultProfileImage();
                }
            } catch (Exception e) {
                // Log error if needed
            }
        }
    }
    
    private void displayProfileImageInView(byte[] imageData) {
        try {
            if (imageData != null && imageData.length > 0) {
                profileView.displayProfileImage(imageData);
            }
        } catch (Exception e) {
            // Log error if needed
        }
    }
    
    public void open() {
        this.profileView.setVisible(true);
    }
    
    public void close() {
        this.profileView.dispose();
    }
    
    // Profile Picture Upload Handler
    class UploadProfileImage implements MouseListener {
        private JLabel uploadLabel;
        
        public UploadProfileImage(JLabel label) {
            this.uploadLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (currentTravellerId == -1) {
                JOptionPane.showMessageDialog(profileView, 
                    "Error: Traveller ID not set. Please login again.", 
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
                        boolean success = travellerDao.updateProfilePicture(currentTravellerId, imageData);
                        
                        if (success) {
                            displayProfileImageInView(imageData);
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
                }
            }
        }

        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
        @Override
        public void mouseEntered(MouseEvent e) {
            uploadLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            uploadLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
            // Implement traveller dashboard navigation
            TravellerdashboardView dashboardView = new TravellerdashboardView();
            TravellerDashboardController controller = new TravellerDashboardController(dashboardView, currentTravellerId);
            controller.open();
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
            dashboardLabel.setForeground(Color.WHITE);
        }
    }
    
    class ProfileNav implements MouseListener {
        private final JLabel profileLabel;
        
        public ProfileNav(JLabel label){
            this.profileLabel = label;
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
            profileLabel.setForeground(Color.GRAY);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            profileLabel.setForeground(Color.WHITE);
        }
    }
    
    //    Booking Details Navigation
    class BookingNav implements MouseListener{
        
        private JLabel bookingLabel;
        
        public BookingNav(JLabel label){
            this.bookingLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBookingView travellerBookingView = new TravellerBookingView();
            TravellerBookingController travellerBooking = new TravellerBookingController( travellerBookingView,currentTravellerId);
            travellerBooking.open();
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
    
//  Route Details Navigation
    class RouteNav implements MouseListener{
        
        private JLabel routeLabel;      
        public RouteNav(JLabel label){
            this.routeLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerRouteView travellerRouteView = new TravellerRouteView();
            TravellerRouteController TravellerRouteDetails= new TravellerRouteController(travellerRouteView,currentTravellerId);
            TravellerRouteDetails.open();
            close();
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
    
//  Bus Ticket Navigation  
    class BusTicketsNav implements MouseListener{
        
        private JLabel busTicketsLabel;
        public BusTicketsNav(JLabel label){
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBusTicketsView travellerBusTicketsView = new TravellerBusTicketsView();
            TravellerBusTicketController TravellerBusTickets= new TravellerBusTicketController(travellerBusTicketsView,currentTravellerId);
            TravellerBusTickets.open();
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
            TravellerVehiclesDetailsView travellerVehiclesDetailsView = new TravellerVehiclesDetailsView();
            TravellerVehiclesDetailsController  TravellerVehiclesDetails= new  TravellerVehiclesDetailsController(travellerVehiclesDetailsView, currentTravellerId);
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
    
    
    // Traveller Navigation
    class NextNav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                TravellerProfileAccountManagementView accountView = new TravellerProfileAccountManagementView();
                TravellerProfileAccountManagementController accountController = new TravellerProfileAccountManagementController(accountView,currentTravellerId);
                accountController.open();
        }
    }
    
    
//    Logout Navigation
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
                profileView.dispose();
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
    

    
    

