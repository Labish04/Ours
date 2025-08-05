/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.VehiclesDao;
import happytravell.model.VehiclesData;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Acer
 */
public class TravellerVehiclesDetailsController {
    private TravellerVehiclesDetailsView vehiclesDetailsView;
    private int currentTravellerId;
    private VehiclesDao vehiclesDao;
    private JPanel vehiclesPanel;
    private JScrollPane scrollPane;
    private List<VehiclesData> currentVehiclesList;
    private int currentVehicleIndex = 0;

    public TravellerVehiclesDetailsController(TravellerVehiclesDetailsView vehiclesDetailsView, int travellerId) {
        this.currentTravellerId = travellerId;
        this.vehiclesDetailsView = vehiclesDetailsView;
        this.vehiclesDao = new VehiclesDao();

        // Attach all the navigation listeners
        this.vehiclesDetailsView.DashboardNavigation(new DashboardNav(vehiclesDetailsView.getDashboardlabel()));
        this.vehiclesDetailsView.BookingNavigation(new BookingNav(vehiclesDetailsView.getBookinglabel()));
        this.vehiclesDetailsView.RouteNavigation(new RouteNav(vehiclesDetailsView.getRoutelabel()));
        this.vehiclesDetailsView.BusTicketsNavigation(new BusTicketsNav(vehiclesDetailsView.getBusTicketslabel()));
        this.vehiclesDetailsView.VehiclesDetailsNavigation(new VehiclesDetailsNav(vehiclesDetailsView.getVehiclesDetailslabel()));
        this.vehiclesDetailsView.ProfileNavigation(new ProfileNav(vehiclesDetailsView.getProfilelabel()));
        this.vehiclesDetailsView.LogOutNavigation(new LogOutNav(vehiclesDetailsView.getLogOutlabel()));
        
        // Add vehicle type selection listener
        setupVehicleTypeSelection();
        
        // Clear fields initially
        clearVehicleDetails();
    }

    /**
     * Setup the vehicle type combo box selection listener
     */
    private void setupVehicleTypeSelection() {
        vehiclesDetailsView.getVehicleTypeComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) vehiclesDetailsView.getVehicleTypeComboBox().getSelectedItem();
                if (selectedType != null && !selectedType.equals("Select Vehicles")) {
                    loadVehiclesByType(selectedType);
                } else {
                    clearVehicleDetails();
                }
            }
        });
    }
    
    /**
     * Load vehicles by selected type
     */
    private void loadVehiclesByType(String vehicleType) {
        try {
            currentVehiclesList = vehiclesDao.getVehiclesByType(vehicleType);
            currentVehicleIndex = 0;
            
            if (currentVehiclesList != null && !currentVehiclesList.isEmpty()) {
                displayVehicleDetails(currentVehiclesList.get(0));
                
                // Add click listener to vehicle photo for navigation if multiple vehicles
                if (currentVehiclesList.size() > 1) {
                    addNavigationToVehiclePhoto();
                }
            } else {
                clearVehicleDetails();
                JOptionPane.showMessageDialog(vehiclesDetailsView, 
                    "No vehicles found for the selected type.", 
                    "No Vehicles", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Error loading vehicles: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            clearVehicleDetails();
        }
    }
    
    /**
     * Display vehicle details in the form fields
     */
    private void displayVehicleDetails(VehiclesData vehicle) {
        if (vehicle != null) {
            // Fill text fields
            vehiclesDetailsView.getTravelAgencyTextField().setText(vehicle.getTravelAgency());
            vehiclesDetailsView.getVehiclesNumberTextField().setText(vehicle.getVehicleNumber());
            vehiclesDetailsView.getVehiclesTypeTextField().setText(vehicle.getVehicleName());
            vehiclesDetailsView.getNumberOfSeatsTextField1().setText(String.valueOf(vehicle.getNumberOfSeats()));
            vehiclesDetailsView.getNumberOfSeatsTextField().setText(vehicle.getVehicleColor());
            
            // Display vehicle image
            displayVehicleImage(vehicle.getVehicleImage());
            
            // Update vehicle photo label to show navigation info if multiple vehicles
            if (currentVehiclesList.size() > 1) {
                vehiclesDetailsView.getVehiclePhotoLabel().setText(
                    "<html><center>Vehicle " + (currentVehicleIndex + 1) + " of " + 
                    currentVehiclesList.size() + "<br>Click to view next</center></html>");
            } else {
                vehiclesDetailsView.getVehiclePhotoLabel().setText("");
            }
        }
    }
    
    /**
     * Display vehicle image
     */
    private void displayVehicleImage(byte[] imageBytes) {
        if (imageBytes != null && imageBytes.length > 0) {
            try {
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                Image image = imageIcon.getImage();
                
                // Scale image to fit the label
                JLabel photoLabel = vehiclesDetailsView.getVehiclePhotoLabel();
                int width = photoLabel.getWidth() > 0 ? photoLabel.getWidth() : 430;
                int height = photoLabel.getHeight() > 0 ? photoLabel.getHeight() : 210;
                
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
                photoLabel.setIcon(scaledIcon);
                photoLabel.setText(""); // Clear any text when image is displayed
            } catch (Exception e) {
                vehiclesDetailsView.getVehiclePhotoLabel().setIcon(null);
                vehiclesDetailsView.getVehiclePhotoLabel().setText("Error loading image");
            }
        } else {
            vehiclesDetailsView.getVehiclePhotoLabel().setIcon(null);
            vehiclesDetailsView.getVehiclePhotoLabel().setText("No image available");
        }
    }
    
    /**
     * Add navigation functionality to vehicle photo for multiple vehicles
     */
    private void addNavigationToVehiclePhoto() {
        // Remove existing mouse listeners
        for (MouseListener ml : vehiclesDetailsView.getVehiclePhotoLabel().getMouseListeners()) {
            vehiclesDetailsView.getVehiclePhotoLabel().removeMouseListener(ml);
        }
        
        // Add new mouse listener for navigation
        vehiclesDetailsView.getVehiclePhotoLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentVehiclesList != null && currentVehiclesList.size() > 1) {
                    currentVehicleIndex = (currentVehicleIndex + 1) % currentVehiclesList.size();
                    displayVehicleDetails(currentVehiclesList.get(currentVehicleIndex));
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (currentVehiclesList != null && currentVehiclesList.size() > 1) {
                    vehiclesDetailsView.getVehiclePhotoLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                vehiclesDetailsView.getVehiclePhotoLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    
    /**
     * Clear all vehicle detail fields
     */
    private void clearVehicleDetails() {
        vehiclesDetailsView.getTravelAgencyTextField().setText("");
        vehiclesDetailsView.getVehiclesNumberTextField().setText("");
        vehiclesDetailsView.getVehiclesTypeTextField().setText("");
        vehiclesDetailsView.getNumberOfSeatsTextField1().setText("");
        vehiclesDetailsView.getNumberOfSeatsTextField().setText("");
        vehiclesDetailsView.getVehiclePhotoLabel().setIcon(null);
        vehiclesDetailsView.getVehiclePhotoLabel().setText("Select a vehicle type to view details");
        
        currentVehiclesList = null;
        currentVehicleIndex = 0;
    }

    public void open() {
        this.vehiclesDetailsView.setVisible(true);
    }

    public void close() {
        this.vehiclesDetailsView.dispose();
    }

    //    Dashboard Navigation
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

    //    Booking  Navigation
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
            // Already on this page, do nothing
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

    //    LogOut Navigation
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
                vehiclesDetailsView.dispose();

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