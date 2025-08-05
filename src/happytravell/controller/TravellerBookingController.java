/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.popup.BookingDetailsPopup;
import happytravell.dao.BookingDao;
import happytravell.dao.GuideDao;
import happytravell.model.BookingData;
import happytravell.model.GuideData;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Acer
 */
public class TravellerBookingController {
    private TravellerBookingView BookingView;
    private int currentTravellerId;
    private BookingDao bookingDao;
    private GuideDao guideDao;
    private String selectedVehicleType = "";
    
    public TravellerBookingController(TravellerBookingView travellerBookingView, int travellerId) {
        this.currentTravellerId = travellerId;
        this.BookingView = travellerBookingView;
        this.bookingDao = new BookingDao();
        this.guideDao = new GuideDao(); 
        
        // Initialize navigation listeners
        initializeNavigation();
        
        // Initialize booking functionality
        initializeBookingFunctionality();
        
        // Load available vehicles and drivers
        loadAvailableOptions();
    }
    
    private void initializeNavigation() {
        this.BookingView.DashboardNavigation(new TravellerBookingController.DashboardNav(BookingView.getDashboardLabel()));
        this.BookingView.RouteDetailsNavigation(new TravellerBookingController.RouteDetailsNav(BookingView.getRouteDetailsLabel()));
        this.BookingView.BusDetailsNavigation(new TravellerBookingController.BusDetailsNav(BookingView.getBusDetailsLabel()));
        this.BookingView.VehiclesDetailsNavigation(new TravellerBookingController.VehiclesDetailsNav(BookingView.getVehiclesDetailsLabel()));
        this.BookingView.ProfileNavigation(new TravellerBookingController.ProfileNav(BookingView.getProfileLabel()));
        this.BookingView.LogOutNavigation(new TravellerBookingController.LogOutNav(BookingView.getLogOutLabel()));
    }
    
    private void initializeBookingFunctionality() {
        // Add listeners for vehicle type selection buttons
        BookingView.addCarButtonListener(new VehicleTypeListener("Car"));
        BookingView.addJeepButtonListener(new VehicleTypeListener("Jeep"));
        BookingView.addTaxiButtonListener(new VehicleTypeListener("Taxi"));
        BookingView.addGuideButtonListener(new VehicleTypeListener("Guide"));
        
        // Add listener for book button
        BookingView.addBookButtonListener(new BookButtonListener());
        
        // Add listener for booking details button - FIXED METHOD NAME
            
        // This line in your controller:
        BookingView.addBookingDetailsListener(new BookingDetailsListener());
        
    }
    
    private void loadAvailableOptions() {
        // Load available drivers
        List<BookingData.DriverInfo> availableDrivers = bookingDao.getAvailableDrivers();
        // Clear existing items and add new ones
        BookingView.getDriverNameComboBox().removeAllItems();
        BookingView.getDriverNameComboBox().addItem("Select Driver");
        for (BookingData.DriverInfo driver : availableDrivers) {
            BookingView.getDriverNameComboBox().addItem(driver);
        }
    }
    
//    private void loadVehiclesByType(String vehicleType) {
//        List<BookingData.VehicleInfo> availableVehicles = bookingDao.getAvailableVehiclesByType(vehicleType);
//        // Clear existing items and add new ones
//        BookingView.getVehiclesNumberComboBox().removeAllItems();
//        BookingView.getVehiclesNumberComboBox().addItem("Select Vehicle");
//        for (BookingData.VehicleInfo vehicle : availableVehicles) {
//            BookingView.getVehiclesNumberComboBox().addItem(vehicle);
//        }
//    }
    
    public void open(){
        this.BookingView.setVisible(true);
    } 
    
    public void close(){
        this.BookingView.dispose();
    }
    
    class VehicleTypeListener implements ActionListener {
        private String vehicleType;
        
        public VehicleTypeListener(String vehicleType) {
            this.vehicleType = vehicleType;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedVehicleType = vehicleType;

            if ("Guide".equals(vehicleType)) {
                try {
                    List<GuideData> availableGuides = guideDao.getAllGuides();
                    if (availableGuides.isEmpty()) {
                        JOptionPane.showMessageDialog(BookingView, 
                            "No guides available at this time.", 
                            "No Guides", 
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        GuideData selectedGuide = BookingView.showGuideSelectionPopup(availableGuides);
                        if (selectedGuide != null) {
                            // Store the selected guide
                            BookingView.setSelectedGuide(selectedGuide);

                            // Show confirmation to user
                            JOptionPane.showMessageDialog(BookingView,
                                "Guide selected: " + selectedGuide.getGuideName(),
                                "Guide Confirmation",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BookingView,
                        "Error loading guides: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Existing vehicle type handling code...
                List<BookingData.VehicleInfo> cars = bookingDao.getAvailableVehiclesByType("Car");
                List<BookingData.VehicleInfo> jeeps = bookingDao.getAvailableVehiclesByType("Jeep");
                List<BookingData.VehicleInfo> taxis = bookingDao.getAvailableVehiclesByType("Taxi");

                BookingView.populateCarTab(cars);
                BookingView.populateJeepTab(jeeps);
                BookingView.populateTaxiTab(taxis);

                // Select the appropriate tab based on vehicle type
                switch (vehicleType) {
                    case "Car":
                        BookingView.getTabbedPane().setSelectedIndex(0);
                        break;
                    case "Jeep":
                        BookingView.getTabbedPane().setSelectedIndex(1);
                        break;
                    case "Taxi":
                        BookingView.getTabbedPane().setSelectedIndex(2);
                        break;
                }

                // Load vehicles for the combo box
//                loadVehiclesByType(vehicleType);
            }
        }
    }
    
    // Book Button Listener
    class BookButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (validateBookingForm()) {
                BookingData booking = createBookingFromForm();
                
                if (bookingDao.insertBooking(booking)) {
                    JOptionPane.showMessageDialog(BookingView, 
                        "Booking placed successfully!\nBooking ID: " + booking.getBookingId() + 
                        "\nStatus: Pending\nYou will be contacted soon for confirmation.", 
                        "Booking Successful", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Clear the form after successful booking
                    clearBookingForm();
                } else {
                    JOptionPane.showMessageDialog(BookingView, 
                        "Failed to place booking. Please try again.", 
                        "Booking Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        private boolean validateBookingForm() {
            // Check if vehicle type is selected
            if (selectedVehicleType.isEmpty()) {
                JOptionPane.showMessageDialog(BookingView, 
                    "Please select a vehicle type first.", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Check pickup address
            if (BookingView.getPickUpAddressTextField().getText().trim().isEmpty() || 
                BookingView.getPickUpAddressTextField().getText().equals("Pickup Address")) {
                JOptionPane.showMessageDialog(BookingView, 
                    "Please enter pickup address.", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Check drop address
            if (BookingView.getDropAddressTextField().getText().trim().isEmpty() || 
                BookingView.getDropAddressTextField().getText().equals("Drop Address")) {
                JOptionPane.showMessageDialog(BookingView, 
                    "Please enter drop address.", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Check passenger count
            if ((Integer) BookingView.getPassengerNumberSpinner().getValue() <= 0) {
                JOptionPane.showMessageDialog(BookingView, 
                    "Please select number of passengers.", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Check payment method
            if (BookingView.getPaymentMethodComboBox().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(BookingView, 
                    "Please select a payment method.", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            return true;
        }
        
        private BookingData createBookingFromForm() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String pickupAddress = BookingView.getPickUpAddressTextField().getText().trim();
            String dropAddress = BookingView.getDropAddressTextField().getText().trim();
            
            // Get departure date/time
            Date departureDate = (Date) BookingView.getDepartureDateTimeSpinner().getValue();
            String departureDateTimeStr = sdf.format(departureDate);
            
            // Get return date/time
            Date returnDate = (Date) BookingView.getReturnDateTimeSpinner().getValue();
            String returnDateTimeStr = sdf.format(returnDate);
            
            int passengerCount = (Integer) BookingView.getPassengerNumberSpinner().getValue();
            String paymentMethod = (String) BookingView.getPaymentMethodComboBox().getSelectedItem();
            
            return new BookingData(
                currentTravellerId,
                pickupAddress,
                dropAddress,
                departureDateTimeStr,
                returnDateTimeStr,
                passengerCount,
                selectedVehicleType,
                paymentMethod
            );
        }
        
        private void clearBookingForm() {
            BookingView.getPickUpAddressTextField().setText("Pickup Address");
            BookingView.getDropAddressTextField().setText("Drop Address");
            BookingView.getPassengerNumberSpinner().setValue(0);
            BookingView.getPaymentMethodComboBox().setSelectedIndex(0);
            BookingView.getDriverNameComboBox().setSelectedIndex(0);
            selectedVehicleType = "";
        }
    }
    
    // FIXED Booking Details Listener
    class BookingDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<BookingData> bookings = bookingDao.getBookingsByTravellerId(currentTravellerId);
            
            if (bookings.isEmpty()) {
                JOptionPane.showMessageDialog(BookingView, 
                    "No bookings found for your account.", 
                    "Booking Details", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show selection dialog if multiple bookings
                if (bookings.size() > 1) {
                    BookingData selected = (BookingData) JOptionPane.showInputDialog(
                        BookingView,
                        "Select a booking to view details:",
                        "Your Bookings",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        bookings.toArray(),
                        bookings.get(0));
                    
                    if (selected != null) {
                        showBookingDetails(selected);
                    }
                } else {
                    // Show details for the single booking
                    showBookingDetails(bookings.get(0));
                }
            }
        }
        
        private void showBookingDetails(BookingData booking) {
            // FIXED: Use the correct constructor without Frame parameter
            BookingDetailsPopup popup = new BookingDetailsPopup(BookingView, booking);
            
            popup.addCancelButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(
                        popup,
                        "Are you sure you want to cancel this booking?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (bookingDao.deleteBooking(booking.getBookingId())) {
                            JOptionPane.showMessageDialog(
                                popup,
                                "Booking has been cancelled successfully.",
                                "Cancellation Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                            popup.dispose();
                        } else {
                            JOptionPane.showMessageDialog(
                                popup,
                                "Failed to cancel booking. Please try again.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
            
            popup.addPrintButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                        popup,
                        "Print functionality will be implemented soon.",
                        "Print Receipt",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            });
            
            popup.addCloseButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popup.dispose();
                }
            });
            
            // FIXED: Use setVisible instead of showDialog
            popup.setVisible(true);
        }
    }
    
    //    Dashboard Navigation
    class DashboardNav implements MouseListener {
        private JLabel dashboardLabel;
        
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
    
    // Route Details Navigation
    class RouteDetailsNav implements MouseListener {
        private JLabel routeDetailsLabel;      
        
        public RouteDetailsNav(JLabel label) {
            this.routeDetailsLabel = label;
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
            routeDetailsLabel.setForeground(Color.WHITE);
            routeDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.BLACK);
            routeDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // Bus Ticket Navigation  
    class BusDetailsNav implements MouseListener {
        private JLabel busDetailsLabel;
        
        public BusDetailsNav(JLabel label) {
            this.busDetailsLabel = label;
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
            busDetailsLabel.setForeground(Color.WHITE);
            busDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            busDetailsLabel.setForeground(Color.BLACK);
            busDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // Vehicles Details Navigation
    class VehiclesDetailsNav implements MouseListener {
        private JLabel vehiclesDetailsLabel;
        
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
    
    // Profile Navigation
    class ProfileNav implements MouseListener {
        private JLabel profileLabel;
        
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
    
    // LogOut Navigation
    class LogOutNav implements MouseListener {
        private JLabel logOutLabel;
        
        public LogOutNav(JLabel label) {
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                BookingView.dispose();

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