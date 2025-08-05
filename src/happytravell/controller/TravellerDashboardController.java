/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminReviewsDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
import happytravell.view.TravellerPlacesView;
import happytravell.view.TravellerProfileView;
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


/**
 *
 * @author Acer
 */
public class TravellerDashboardController {
    private TravellerdashboardView travellerdashboardView = new TravellerdashboardView();

    private int currentTravellerId;
    public TravellerDashboardController(TravellerdashboardView travellerdashboardView, int travellerId){
        this.travellerdashboardView = travellerdashboardView;
        this.currentTravellerId = travellerId;
        this.travellerdashboardView.PlacesNavigation(new PlacesNav());
        this.travellerdashboardView.ReviewsNavigation(new ReviewsNav());
        this.travellerdashboardView.BookingNavigation(new TravellerDashboardController.BookingNav(travellerdashboardView.getBookinglabel())); 
        this.travellerdashboardView.BusTicketsNavigation(new TravellerDashboardController.BusTicketsNav(travellerdashboardView.getBusTicketslabel()));
        this.travellerdashboardView.RouteNavigation(new TravellerDashboardController.RouteNav(travellerdashboardView.getRoutelabel()));
        this.travellerdashboardView.VehiclesDetailsNavigation(new TravellerDashboardController.VehiclesDetailsNav(travellerdashboardView.getVehiclesDetailslabel()));
        this.travellerdashboardView.ProfileNavigation(new TravellerDashboardController.ProfileNav(travellerdashboardView.getProfilelabel()));
        this.travellerdashboardView.LogOutNavigation(new TravellerDashboardController.LogOutNav(travellerdashboardView.getLogOutlabel()));
        //  Bus Ticket Navigation  
    class BusTicketsNav implements MouseListener{
        
        private JLabel busTicketsLabel;
        public BusTicketsNav(JLabel label){
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController AdminBusTickets= new AdminBusTicketsController(adminBusTicketsView, currentTravellerId);
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
            AdminVehiclesDetailsController  AdminVehiclesDetails= new  AdminVehiclesDetailsController(adminVehiclesDetailsView, currentTravellerId);
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
    }
    
    public void open(){
        this.travellerdashboardView.setVisible(true);
    } 
    public void close(){
        this.travellerdashboardView.dispose();
    }
    
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginController(new LoginPageView()).open();
            close();
        }
    }
    
    //    Booking  Navigation
    class BookingNav implements MouseListener{
        
        private JLabel bookingLabel;
        
        public BookingNav(JLabel label){
            this.bookingLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBookingView travellerBookingView = new TravellerBookingView();
            TravellerBookingController TravellerBooking= new TravellerBookingController(travellerBookingView, currentTravellerId);
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
   

    //  Route Navigation
    class RouteNav implements MouseListener {
        private final JLabel routeLabel;

        public RouteNav(JLabel label) {
            this.routeLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerRouteView travellerRouteView = new TravellerRouteView();
            TravellerRouteController TravellerRoute = new TravellerRouteController(travellerRouteView, currentTravellerId);
            TravellerRoute.open();
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
    class BusTicketsNav implements MouseListener {
        private final JLabel busTicketsLabel;

        public BusTicketsNav(JLabel label) {
            this.busTicketsLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBusTicketsView travellerBusTicketsView = new TravellerBusTicketsView();
            TravellerBusTicketController TravellerBusTicket = new TravellerBusTicketController(travellerBusTicketsView, currentTravellerId);
            TravellerBusTicket.open();
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

    //    Profile Navigation
    class ProfileNav implements MouseListener{
        
        private JLabel profileLabel;
        public ProfileNav(JLabel label){
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerProfileView travellerProfileView = new TravellerProfileView();
            TravellerProfileController  TravellerProfile= new  TravellerProfileController(travellerProfileView , currentTravellerId);
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
                travellerdashboardView.dispose();

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
            TravellerPlacesView placesView = new TravellerPlacesView();
            TravellerPlacesController placesController = new TravellerPlacesController(placesView, currentTravellerId);
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
   

    
