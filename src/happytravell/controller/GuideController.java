/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.UI.AdminGuideDetailsCardPanel;
import happytravell.dao.GuideDao;
import happytravell.model.GuideData;
import happytravell.popup.AddGuidePopup;
import happytravell.popup.GuidePopup;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminGuideDetailsView;
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
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class GuideController {
    private AdminGuideDetailsView guideView;
    private GuideDao guideDao;
    private int currentAdminId;
    private List<GuideData> guides;
    
    public GuideController(AdminGuideDetailsView guideView, int adminId) {
        this.guideView = guideView;
        this.currentAdminId = adminId;
        this.guideDao = new GuideDao();
        this.guideView.BackNavigation(new BackNav());
        this.guideView.DashboardNavigation(new DashboardNav(guideView.getDashboardlabel()));
        this.guideView.BookingDetailsNavigation(new BookingDetailsNav(guideView.getBookingDetailslabel()));
        this.guideView.BusTicketsNavigation(new BusTicketsNav(guideView.getBusTicketslabel()));
        this.guideView.VehiclesDetailsNavigation(new VehiclesNav(guideView.getVehiclesDetailslabel()));
        this.guideView.RouteDetailsNavigation(new RouteDetailsNav(guideView.getRouteDetailslabel()));
        this.guideView.ProfileNavigation(new ProfileNav(guideView.getProfilelabel()));
        this.guideView.LogOutNavigation(new LogOutNav(guideView.getLogOutlabel()));
        
        initializeController();
    }
    public void open(){
    this.guideView.setVisible(true);
    } 
    public void close(){
    this.guideView.dispose();
    }
    
    
    
    
    private void initializeController() {
        setupEventListeners();
        loadGuides();
    }
    
    private void setupEventListeners() {
        // Add Guide button listener
        guideView.getAddGuideButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddGuideDialog();
            }
        });
        
      
        }
  

    
    private void loadGuides() {
        try {
            guides = guideDao.getAllGuides();
            displayGuides();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(guideView, 
                "Error loading guides: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayGuides() {
        guideView.getGuideContainerPanel().removeAll();
        guideView.getGuideContainerPanel().setLayout(new BoxLayout(guideView.getGuideContainerPanel(), BoxLayout.Y_AXIS));
        
        if (guides == null || guides.isEmpty()) {
            // Show message when no guides available
            javax.swing.JLabel noGuidesLabel = new javax.swing.JLabel("No guides available. Click 'Add Guide' to add new guides.");
            noGuidesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            noGuidesLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
            noGuidesLabel.setForeground(new java.awt.Color(128, 128, 128));
            guideView.getGuideContainerPanel().add(Box.createVerticalStrut(50));
            guideView.getGuideContainerPanel().add(noGuidesLabel);
        } else {
            for (GuideData guide : guides) {
                AdminGuideDetailsCardPanel card = new AdminGuideDetailsCardPanel(guide);
                
                // Set click listener for each card
                card.setClickListener(new AdminGuideDetailsCardPanel.GuideCardClickListener() {
                    @Override
                    public void onGuideCardClicked(GuideData selectedGuide) {
                        showEditGuideDialog(selectedGuide);
                    }
                });
                
                guideView.getGuideContainerPanel().add(card);
                guideView.getGuideContainerPanel().add(Box.createVerticalStrut(10));
            }
        }
        
        guideView.getGuideContainerPanel().revalidate();
        guideView.getGuideContainerPanel().repaint();
    }
    
    private void showAddGuideDialog() {
    AddGuidePopup addForm = new AddGuidePopup((JFrame) SwingUtilities.getWindowAncestor(guideView));
    addForm.setVisible(true);
    
    // Check if the form was saved
    if (addForm.isSaved()) {
        GuideData newGuide = addForm.getGuideData();
        
        // Check if email already exists before saving
        if (guideDao.emailExists(newGuide.getEmail(), 0)) {
            JOptionPane.showMessageDialog(guideView, 
                "This email is already registered with another guide.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Save to database
        if (guideDao.addGuide(newGuide)) {
            JOptionPane.showMessageDialog(guideView, 
                "Guide added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadGuides(); // Refresh the list
        } else {
            JOptionPane.showMessageDialog(guideView, 
                "Failed to add guide. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    private void showEditGuideDialog(GuideData guide) {
        GuidePopup popup = new GuidePopup(guideView, guide);
        popup.setVisible(true);
        
        // Check if guide was updated or deleted
        if (popup.isUpdated() || popup.isDeleted()) {
            loadGuides(); // Refresh the guide list
        }
    }
    
    
    
    
   
    
    public void refreshGuides() {
        loadGuides();
    }
    
    public GuideData getGuideById(int guideId) {
        return guideDao.getGuideById(guideId);
    }
    
    public List<GuideData> getAllGuides() {
        return guides;
    }
    
    
    public AdminGuideDetailsView getView() {
        return guideView;
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
                guideView.dispose();

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
  
}
