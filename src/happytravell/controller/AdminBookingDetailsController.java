 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.BookingDao;
import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author Acer
 */
public class AdminBookingDetailsController {
    private AdminBookingDetailsView bookingView = new AdminBookingDetailsView();
    private int currentAdminId;
    private List<BookingData>allBooking;
    private List<BookingData>filteredBooking;
    
    public AdminBookingDetailsController(AdminBookingDetailsView adminBookingDetailsView, int adminId) {
        this.bookingView = adminBookingDetailsView;
    this.bookingView.DashboardNavigation(new AdminBookingDetailsController.DashboardNav(adminBookingDetailsView.getDashboardlabel()));
    this.bookingView.BusTicketsNavigation(new AdminBookingDetailsController.BusTicketsNav(adminBookingDetailsView.getBusTicketslabel()));
    this.bookingView.RouteDetailsNavigation(new AdminBookingDetailsController.RouteDetailsNav(adminBookingDetailsView.getRouteDetailslabel()));
    this.bookingView.VehiclesDetailsNavigation(new AdminBookingDetailsController.VehiclesDetailsNav(adminBookingDetailsView.getVehiclesDetailslabel()));
    this.bookingView.ProfileNavigation(new AdminBookingDetailsController.ProfileNav(adminBookingDetailsView.getProfilelabel()));
    this.bookingView.LogOutNavigation(new AdminBookingDetailsController.LogOutNav(adminBookingDetailsView.getLogOutlabel()));
    this.currentAdminId = adminId;
    
    this.allBooking = new ArrayList<>();
    this.filteredBooking = new ArrayList<>();
    loadBooking();
}
    public void open(){
    this.bookingView.setVisible(true);
    } 
    public void close(){
    this.bookingView.dispose();
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
    
//  Route Details Navigation
    class RouteDetailsNav implements MouseListener{
        
        private JLabel routeDetailsLabel;      
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView,currentAdminId );
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
            AdminBusTicketsController AdminBusTickets= new AdminBusTicketsController(adminBusTicketsView ,currentAdminId);
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
            AdminProfileController  AdminProfile= new  AdminProfileController(adminProfileView, currentAdminId );
            AdminProfile.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            profileLabel.setForeground(Color.RED);
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
                bookingView.dispose();

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

    

    
    private void loadBooking() {
    try {
        BookingDao bookingDao = new BookingDao(); // Use BookingDao instead of TravellerDao
        allBooking = bookingDao.getAllBookings(); // Use the correct method
        filteredBooking = new ArrayList<>(allBooking);
        displayAllBooking();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(bookingView, "Error loading bookings: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void displayAllBooking() {
    bookingView.displayBooking(allBooking, this);
}

private void displayFilteredBooking() {
    bookingView.displayBooking(filteredBooking, this);
}
    
    public void refreshBooking() {
    try {
        BookingDao bookingDao = new BookingDao();
        allBooking = bookingDao.getAllBookings(); // Refresh from database
        filteredBooking = new ArrayList<>(allBooking);
        displayAllBooking(); // This should update all cards
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(bookingView, "Error refreshing bookings: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public class BookingDetailsPopup extends JDialog {
    private BookingData bookingData;
    private TravellerData travellerData;
    private JLabel imageLabel;
    private JLabel nameValueLabel;
    private JLabel pickupAddressValueLabel;
    private JLabel dropAddressValueLabel;
    private JLabel departureDateTimeValueLabel;
    private JLabel returnDateTimeValueLabel;
    private JLabel passengerCountValueLabel;
    private JLabel vehicleNumberValueLabel;
    private JLabel driverNameValueLabel;
    private JButton confirmButton;
   
    
    public BookingDetailsPopup(BookingData booking) {
        super((JFrame) null, "Booking Details", true);
        this.bookingData = booking;
        initializeComponents();
        setupLayout();
        populateData();
        setupEventHandlers();
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initializeComponents() {
        setSize(800, 600);
        setBackground(new Color(255, 248, 235));
        
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(120, 120));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(241, 215, 184), 3));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setOpaque(true);
        
        // Initialize value labels
        nameValueLabel = createValueLabel();
        pickupAddressValueLabel = createValueLabel();
        dropAddressValueLabel = createValueLabel();
        departureDateTimeValueLabel = createValueLabel();
        returnDateTimeValueLabel = createValueLabel();
        passengerCountValueLabel = createValueLabel();
        vehicleNumberValueLabel = createValueLabel();
        driverNameValueLabel = createValueLabel();
     
        
        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(241, 215, 184));
        confirmButton.setForeground(new Color(80, 50, 30));
        confirmButton.setFont(new Font("Candara", Font.BOLD, 14));
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButton.setFocusPainted(false);
        confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
    }    
        private JLabel createValueLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Candara", Font.PLAIN, 14));
        label.setForeground(new Color(60, 40, 20));
        return label;
    }
    
    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Candara", Font.BOLD, 14));
        label.setForeground(new Color(80, 50, 30));
        return label;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Booking Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Constantia", Font.BOLD, 18));
        titleLabel.setForeground(new Color(80, 50, 30));
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        
        // Main Content Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 248, 235));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBackground(new Color(255, 248, 235));
        customerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(241, 215, 184), 2),
            "Customer Information",
            0, 0,
            new Font("Candara", Font.BOLD, 16),
            new Color(80, 50, 30)
        ));
        
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(255, 248, 235));
        imagePanel.add(imageLabel);
        
        customerPanel.add(imagePanel, BorderLayout.CENTER);
        customerPanel.setPreferredSize(new Dimension(200, 300));
        
        // Details Panel (Right side)
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(new Color(255, 248, 235));
        detailsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(241, 215, 184), 2),
            "Booking Information",
            0, 0,
            new Font("Candara", Font.BOLD, 16),
            new Color(80, 50, 30)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 10, 8, 10);
        
        // Add fields to details panel
        addField(detailsPanel, "Name:", nameValueLabel, gbc, 0);
        addField(detailsPanel, "Pickup Address:", pickupAddressValueLabel, gbc, 1);
        addField(detailsPanel, "Drop Address:", dropAddressValueLabel, gbc, 2);
        addField(detailsPanel, "Departure DateTime:", departureDateTimeValueLabel, gbc, 3);
        addField(detailsPanel, "Return DateTime:", returnDateTimeValueLabel, gbc, 4);
        addField(detailsPanel, "Passengers:", passengerCountValueLabel, gbc, 5);
        addField(detailsPanel, "Vehicle Number:", vehicleNumberValueLabel, gbc, 6);
        addField(detailsPanel, "Driver Name:", driverNameValueLabel, gbc, 7);
       
        
        mainPanel.add(customerPanel, BorderLayout.WEST);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 248, 235));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(confirmButton);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addField(JPanel parent, String labelText, JLabel valueLabel, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        parent.add(createFieldLabel(labelText), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        parent.add(valueLabel, gbc);
        gbc.fill = GridBagConstraints.NONE;
    }
    
    // Replace the populateData() method in BookingDetailsPopup class with this fixed version:

private void populateData() {
    if (bookingData != null) {
        // First, try to get traveller data if not already set
        if (travellerData == null) {
            try {
                TravellerDao travellerDao = new TravellerDao();
                travellerData = travellerDao.getTravellerById(bookingData.getTravellerId());
            } catch (Exception e) {
                System.err.println("Error loading traveller data: " + e.getMessage());
                travellerData = new TravellerData(); // Create empty fallback
            }
        }
        
        // Populate booking-related fields (these come from bookingData)
        dropAddressValueLabel.setText(bookingData.getDropAddress() != null ? 
                                       bookingData.getDropAddress() : "Not specified");
        pickupAddressValueLabel.setText(bookingData.getPickupAddress() != null ? 
                                      bookingData.getPickupAddress() : "Not specified");
        departureDateTimeValueLabel.setText(bookingData.getDepartureDateTime() != null ? 
                                    bookingData.getDepartureDateTime() : "Not specified");
        returnDateTimeValueLabel.setText(bookingData.getReturnDateTime() != null ? 
                                      bookingData.getReturnDateTime() : "Not specified");
        passengerCountValueLabel.setText(String.valueOf(bookingData.getPassengerCount()));
        vehicleNumberValueLabel.setText(bookingData.getVehicleNumber() != null ? 
                                      bookingData.getVehicleNumber() : "Not assigned");
        driverNameValueLabel.setText(bookingData.getDriverName() != null ? 
                                   bookingData.getDriverName() : "Not assigned");
        
        // Populate traveller-related fields (these come from travellerData)
        if (travellerData != null) {
            nameValueLabel.setText(travellerData.getFirstName() != null ? 
                                 travellerData.getFirstName() : "Not specified");
            
            // Set customer image
            byte[] imageData = travellerData.getProfilePicture();
            if (imageData != null && imageData.length > 0) {
                try {
                    ImageIcon originalIcon = new ImageIcon(imageData);
                    if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                        Image scaledImage = originalIcon.getImage().getScaledInstance(
                            110, 110, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        imageLabel.setIcon(scaledIcon);
                        imageLabel.setText("");
                    } else {
                        setDefaultCustomerImage();
                    }
                } catch (Exception e) {
                    System.err.println("Error loading customer image: " + e.getMessage());
                    setDefaultCustomerImage();
                }
            } else {
                setDefaultCustomerImage();
            }
        } else {
            nameValueLabel.setText("Not specified");
            setDefaultCustomerImage();
        }
    }
}
    
    private void setDefaultCustomerImage() {
        imageLabel.setText("👤");
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        imageLabel.setForeground(new Color(158, 158, 158));
        imageLabel.setIcon(null);
    }
    
    private void setupEventHandlers() {
    confirmButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Update booking status to "completed"
                bookingData.setStatus("completed");
                
                // Update status in database
                BookingDao bookingDao = new BookingDao();
                bookingDao.updateBookingStatus(bookingData.getBookingId(), "completed");
                
                // Refresh main booking list
                refreshBooking();
                
                // Update UI status label
                setStatusLabel("completed");
                
                // Show success message
                JOptionPane.showMessageDialog(BookingDetailsPopup.this,
                    "Booking status updated to completed!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BookingDetailsPopup.this,
                    "Error updating status: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    // Hover effects (keep existing)
    confirmButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            confirmButton.setBackground(new Color(230, 190, 150));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            confirmButton.setBackground(new Color(241, 215, 184));
        }
    });
}

// Helper method to update status label
private void setStatusLabel(String status) {
    if ("completed".equalsIgnoreCase(status)) {
        confirmButton.setText("Completed");
        confirmButton.setEnabled(false);
        confirmButton.setBackground(new Color(200, 200, 200));
    }
}
    
    public void showPopup() {
        setVisible(true);
    }
    
    public void BookingData(BookingData newBookingData) {
        this.bookingData = newBookingData;
        populateData();
    }
    
    public BookingData getBookingData() {
        return bookingData;
    }

        void addCancelButtonListener(ActionListener actionListener) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        void addPrintButtonListener(ActionListener actionListener) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        void addCloseButtonListener(ActionListener actionListener) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    public static class BookingCardListener implements MouseListener {
    private final AdminBookingDetailsController controller;
    private BookingData booking;
    private TravellerData travellerData;
    
    public BookingCardListener(BookingData booking, AdminBookingDetailsController controller) {
        this.booking = booking;
        this.controller = controller;
        // Initialize traveller data
        try {
            TravellerDao travellerDao = new TravellerDao();
            this.travellerData = travellerDao.getTravellerById(booking.getTravellerId());
        } catch (Exception e) {
            this.travellerData = new TravellerData(); // fallback empty object
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        AdminBookingDetailsController.BookingDetailsPopup popup = 
            controller.new BookingDetailsPopup(booking);
        popup.travellerData = this.travellerData;
        popup.populateData();
        popup.showPopup();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JPanel card = (JPanel) e.getSource();
        card.setBorder(BorderFactory.createLineBorder(new Color(241, 215, 184), 2));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPanel card = (JPanel) e.getSource();
        card.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
}
