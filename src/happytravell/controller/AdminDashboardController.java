/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import happytravell.view.AdminPlacesView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminReviewsDetailsView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author Acer
 */
public class AdminDashboardController {
    private AdmindashboardView admindashboardView = new AdmindashboardView();
    private int currentAdminId;
    public AdminDashboardController(AdmindashboardView admindashboardView, int adminId){
        this.currentAdminId = adminId;
        this.admindashboardView = admindashboardView;
        this.admindashboardView.PlacesNavigation(new PlacesNav());
        this.admindashboardView.ReviewsNavigation(new ReviewsNav());
        this.admindashboardView.BookingDetailsNavigation(new AdminDashboardController.BookingDetailsNav(admindashboardView.getBookingDetailslabel()));
        this.admindashboardView.BusTicketsNavigation(new AdminDashboardController.BusTicketsNav(admindashboardView.getBusTicketslabel()));
        this.admindashboardView.RouteDetailsNavigation(new AdminDashboardController.RouteDetailsNav(admindashboardView.getRouteDetailslabel()));
        this.admindashboardView.VehiclesDetailsNavigation(new AdminDashboardController.VehiclesDetailsNav(admindashboardView.getVehiclesDetailslabel()));
        this.admindashboardView.ProfileNavigation(new AdminDashboardController.ProfileNav(admindashboardView.getProfilelabel()));
        this.admindashboardView.LogOutNavigation(new AdminDashboardController.LogOutNav(admindashboardView.getLogOutlabel()));
        this.admindashboardView.DashboardNavigation(new AdminDashboardController.DashboardNav(admindashboardView.getDashboardlabel()));
    }   
    public void open(){
        this.admindashboardView.setVisible(true);
    } 
    public void close(){
        this.admindashboardView.dispose();
    } 
    
//    Dashboard Navigation
    class DashboardNav implements MouseListener{
        
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label){
            this.dashboardLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView adminDashboardView = new AdmindashboardView();
            AdminDashboardController adminController = new AdminDashboardController(adminDashboardView, currentAdminId);
            adminController.open();
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
                admindashboardView.dispose();

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

    
//  place Navigation
    class PlacesNav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminPlacesView placesView = new AdminPlacesView();
            AdminPlacesController placesController = new AdminPlacesController(placesView, currentAdminId);
            placesController.open();
            close();
        }
    }   

 
    
//  Review Navigation
    class ReviewsNav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                AdminReviewsDetailsView reviewView = new AdminReviewsDetailsView();
                AdminReviewsDetailsController reviewsController = new AdminReviewsDetailsController(reviewView);
                reviewsController.open();
        }
    }  



}
