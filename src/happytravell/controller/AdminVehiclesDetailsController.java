/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.VehiclesDao;
import happytravell.model.VehiclesData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminDriverDetailsView;
import happytravell.view.AdminGuideDetailsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class AdminVehiclesDetailsController {
    private int currentAdminId;
    private AdminVehiclesDetailsView vehiclesDetailsView;
    private VehiclesDao vehiclesDao;
    private JTable vehiclesTable;
    private DefaultTableModel tableModel;
    
    public AdminVehiclesDetailsController(AdminVehiclesDetailsView adminVehiclesDetailsView, int adminId ) {
        this.vehiclesDetailsView = adminVehiclesDetailsView;
        this.currentAdminId = adminId;
        this.vehiclesDao = new VehiclesDao();
        
        // Setup Add Vehicles button action
        this.vehiclesDetailsView.getAddVeheclesButton().addActionListener(new AddVehiclesAction());
        
        // Setup navigation
        this.vehiclesDetailsView.DashboardNavigation(new DashboardNav(adminVehiclesDetailsView.getDashboardlabel()));
        this.vehiclesDetailsView.BookingDetailsNavigation(new BookingDetailsNav(adminVehiclesDetailsView.getBookingDetailslabel()));
        this.vehiclesDetailsView.BusTicketsNavigation(new BusTicketsNav(adminVehiclesDetailsView.getBusTicketslabel()));
        this.vehiclesDetailsView.RouteDetailsNavigation(new RouteDetailsNav(adminVehiclesDetailsView.getRouteDetailslabel()));
        this.vehiclesDetailsView.ProfileNavigation(new ProfileNav(adminVehiclesDetailsView.getProfilelabel()));
        this.vehiclesDetailsView.LogOutNavigation(new LogOutNav(adminVehiclesDetailsView.getLogOutlabel()));
        this.vehiclesDetailsView.GuideNavigation(new GuideNav());
        this.vehiclesDetailsView.DriverNavigation(new DriverNav () );
    
        loadAndDisplayVehicles();
}
   
    // Add Vehicles Action Listener
    class AddVehiclesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddVehicleDialog();
        }
    }
    
    /**
     * Opens the Add Vehicle dialog popup
     */
   private void openAddVehicleDialog() {
        JDialog addVehicleDialog = new JDialog(vehiclesDetailsView, "Add New Vehicle", true);
        addVehicleDialog.setSize(450, 500); // Increased height for price field
        addVehicleDialog.setLocationRelativeTo(vehiclesDetailsView);
        addVehicleDialog.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        final byte[][] selectedImageBytes = {null};
        
        // Vehicle Type
        gbc.gridx = 0; gbc.gridy = 0;
        addVehicleDialog.add(new JLabel("Vehicle Type:"), gbc);
        
        gbc.gridx = 1;
        String[] vehicleTypes = {"Bus", "Car", "Jeep", "Taxi"};
        JComboBox<String> vehicleTypeCombo = new JComboBox<>(vehicleTypes);
        vehicleTypeCombo.setPreferredSize(new Dimension(200, 25));
        addVehicleDialog.add(vehicleTypeCombo, gbc);
        
        // Vehicle Number
        gbc.gridx = 0; gbc.gridy = 1;
        addVehicleDialog.add(new JLabel("Vehicle Number:"), gbc);
        
        gbc.gridx = 1;
        JTextField vehicleNumberField = new JTextField(15);
        addVehicleDialog.add(vehicleNumberField, gbc);
        
        // Number of Seats
        gbc.gridx = 0; gbc.gridy = 2;
        addVehicleDialog.add(new JLabel("Number of Seats:"), gbc);
        
        gbc.gridx = 1;
        JTextField seatsField = new JTextField(15);
        addVehicleDialog.add(seatsField, gbc);
        
        // Vehicle Name
        gbc.gridx = 0; gbc.gridy = 3;
        addVehicleDialog.add(new JLabel("Vehicle Name:"), gbc);
        
        gbc.gridx = 1;
        JTextField vehicleNameField = new JTextField(15);
        addVehicleDialog.add(vehicleNameField, gbc);
        
        // Vehicle Color
        gbc.gridx = 0; gbc.gridy = 4;
        addVehicleDialog.add(new JLabel("Vehicle Color:"), gbc);
        
        gbc.gridx = 1;
        JTextField vehicleColorField = new JTextField(15);
        addVehicleDialog.add(vehicleColorField, gbc);
        
        // Travel Agency
        gbc.gridx = 0; gbc.gridy = 5;
        addVehicleDialog.add(new JLabel("Travel Agency:"), gbc);
        
        gbc.gridx = 1;
        JTextField travelAgencyField = new JTextField(15);
        travelAgencyField.setText("Happy Travels");
        addVehicleDialog.add(travelAgencyField, gbc);
        
        // Price
        gbc.gridx = 0; gbc.gridy = 6;
        addVehicleDialog.add(new JLabel("Price (Rs):"), gbc);
        
        gbc.gridx = 1;
        JTextField priceField = new JTextField(15);
        addVehicleDialog.add(priceField, gbc);
        
        // Vehicle Image Section
        gbc.gridx = 0; gbc.gridy = 7;
        addVehicleDialog.add(new JLabel("Vehicle Image:"), gbc);
        
        gbc.gridx = 1;
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        JButton selectImageButton = new JButton("Select Image");
        selectImageButton.setBackground(new Color(70, 130, 180));
        selectImageButton.setForeground(Color.WHITE);
        selectImageButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
        selectImageButton.setPreferredSize(new Dimension(100, 25));
        
        JLabel imageStatusLabel = new JLabel("No image selected");
        imageStatusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        imageStatusLabel.setForeground(Color.GRAY);
        
        JLabel imagePreviewLabel = new JLabel();
        imagePreviewLabel.setPreferredSize(new Dimension(80, 60));
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagePreviewLabel.setVisible(false);
        
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Vehicle Image");
                
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif", "bmp");
                fileChooser.setFileFilter(imageFilter);
                
                int result = fileChooser.showOpenDialog(addVehicleDialog);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    
                    try {
                        selectedImageBytes[0] = readImageToByteArray(selectedFile);
                        imageStatusLabel.setText(selectedFile.getName());
                        imageStatusLabel.setForeground(new Color(0, 120, 0));
                        
                        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image image = imageIcon.getImage().getScaledInstance(78, 58, Image.SCALE_SMOOTH);
                        imagePreviewLabel.setIcon(new ImageIcon(image));
                        imagePreviewLabel.setVisible(true);
                        addVehicleDialog.pack();
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(addVehicleDialog,
                            "Error reading image file: " + ex.getMessage(),
                            "Image Error",
                            JOptionPane.ERROR_MESSAGE);
                        selectedImageBytes[0] = null;
                        imageStatusLabel.setText("Error loading image");
                        imageStatusLabel.setForeground(Color.RED);
                        imagePreviewLabel.setVisible(false);
                    }
                }
            }
        });
        
        imagePanel.add(selectImageButton);
        imagePanel.add(Box.createHorizontalStrut(10));
        imagePanel.add(imageStatusLabel);
        addVehicleDialog.add(imagePanel, gbc);
        
        // Image preview
        gbc.gridx = 1; gbc.gridy = 8;
        addVehicleDialog.add(imagePreviewLabel, gbc);
        
        // Buttons Panel
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(200, 143, 75));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Replace the addButton ActionListener in your openAddVehicleDialog() method
// with this updated version that properly handles the price:

addButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateVehicleInput(vehicleNumberField.getText(), seatsField.getText(), 
                               vehicleNameField.getText(), vehicleColorField.getText(), 
                               travelAgencyField.getText(), priceField.getText())) {
            
            try {
                VehiclesData newVehicle = new VehiclesData(
                    vehicleTypeCombo.getSelectedItem().toString(),
                    vehicleNumberField.getText().trim(),
                    Integer.parseInt(seatsField.getText().trim()),
                    vehicleNameField.getText().trim(),
                    vehicleColorField.getText().trim(),
                    travelAgencyField.getText().trim()
                );
                
                // Set the price - convert string to BigDecimal
                BigDecimal price = new BigDecimal(priceField.getText().trim());
                newVehicle.setPrice(price);
                
                if (selectedImageBytes[0] != null) {
                    newVehicle.setVehicleImage(selectedImageBytes[0]);
                }
                
                if (vehiclesDao.isVehicleNumberExists(vehicleNumberField.getText().trim())) {
                    JOptionPane.showMessageDialog(addVehicleDialog, 
                        "Vehicle number already exists!", 
                        "Duplicate Vehicle Number", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (vehiclesDao.addVehicle(newVehicle)) {
                    JOptionPane.showMessageDialog(addVehicleDialog, 
                        "Vehicle added successfully!", 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    addVehicleDialog.dispose();
                    loadAndDisplayVehicles();
                } else {
                    JOptionPane.showMessageDialog(addVehicleDialog, 
                        "Failed to add vehicle. Please try again.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addVehicleDialog, 
                    "Please enter valid numbers for seats and price.", 
                    "Invalid Input", 
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
});

// Also, make sure you have the BigDecimal import at the top of your file:
// import java.math.BigDecimal;
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicleDialog.dispose();
            }
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        addVehicleDialog.add(buttonPanel, gbc);
        
        addVehicleDialog.setVisible(true);
    }
    

/**
 * Reads an image file and converts it to byte array
 */
 private byte[] readImageToByteArray(File imageFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(imageFile);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            
            return baos.toByteArray();
        }
    }

/**
 * Validates the input fields for adding a new vehicle
 */
private boolean validateVehicleInput(String vehicleNumber, String seats, 
                                       String vehicleName, String vehicleColor, 
                                       String travelAgency, String price) {
        
        if (vehicleNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Vehicle number cannot be empty!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (seats.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Number of seats cannot be empty!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            int seatCount = Integer.parseInt(seats.trim());
            if (seatCount <= 0) {
                JOptionPane.showMessageDialog(vehiclesDetailsView, 
                    "Number of seats must be greater than 0!", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Please enter a valid number for seats!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (vehicleName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Vehicle name cannot be empty!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (vehicleColor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Vehicle color cannot be empty!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (travelAgency.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Travel agency cannot be empty!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (price.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Price cannot be empty!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            double priceValue = Double.parseDouble(price.trim());
            if (priceValue < 0) {
                JOptionPane.showMessageDialog(vehiclesDetailsView, 
                    "Price must be a positive number!", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vehiclesDetailsView, 
                "Please enter a valid price!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }


    public void open(){
        this.vehiclesDetailsView.setVisible(true);
    } 
    
    public void close(){
        this.vehiclesDetailsView.dispose();
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
    
    // Booking Details Navigation
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
    
    // Route Details Navigation
    class RouteDetailsNav implements MouseListener{
        private JLabel routeDetailsLabel;      
        
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView, currentAdminId);
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
    
    // Bus Tickets Navigation  
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
    
    
    
    // Profile Navigation
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
    
    // LogOut Navigation
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
    
    class GuideNav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminGuideDetailsView GuideView = new AdminGuideDetailsView();
            GuideController guideController = new GuideController(GuideView, currentAdminId);
            guideController.open();
            close();
        }
    }
    
    class DriverNav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminDriverDetailsView view = new AdminDriverDetailsView();
            DriverDetailsController driverController = new DriverDetailsController(view);
            driverController.open();
            close();
        }
    }
    
    private void loadAndDisplayVehicles() {
    List<VehiclesData> vehicles = vehiclesDao.getAllVehicles();
    vehiclesDetailsView.displayVehicleCards(vehicles);
}
    
}