/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

//import happytravell.view.PlacesView;



import happytravell.dao.PlaceDao;
import happytravell.model.PlaceData;
import happytravell.popup.AdminPlacePopup;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminPlacesView;
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
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 *
 * @author Acer
 */
public class AdminPlacesController {
     private AdminPlacesView placesView;
    private PlaceDao placeDao;
    private int currentAdminId;
    
    public AdminPlacesController(AdminPlacesView view, int adminId) {
        this.placesView = view;
        this.placeDao = new PlaceDao();
        this.currentAdminId = adminId;
        this.placesView.setbackButtonAction(new BackButtonListener());
        this.placesView.BookingDetailsNavigation(new AdminPlacesController.BookingDetailsNav(view.getBookingDetailslabel()));
        this.placesView.BusTicketsNavigation(new AdminPlacesController.BusTicketsNav(view.getBusTicketslabel()));
        this.placesView.RouteDetailsNavigation(new AdminPlacesController.RouteDetailsNav(view.getRouteDetailslabel()));
        this.placesView.VehiclesDetailsNavigation(new AdminPlacesController.VehiclesDetailsNav(view.getVehiclesDetailslabel()));
        this.placesView.ProfileNavigation(new AdminPlacesController.ProfileNav(view.getProfilelabel()));
        this.placesView.LogOutNavigation(new AdminPlacesController.LogOutNav(view.getLogOutlabel()));
        this.placesView.DashboardNavigation(new AdminPlacesController.DashboardNav(view.getDashboardlabel()));
        
        initializePlaceManagement();
        loadPlaces();
    }
    
    private void initializePlaceManagement() {
        // Add Place button action
        placesView.getAddPlacesButton().addActionListener(e -> addNewPlace());
        
       
        
        // Search functionality
        placesView.getSearchField().addActionListener(e -> searchPlaces());
        placesView.getSearchField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (placesView.getSearchField().getText().equals("Search")) {
                    placesView.getSearchField().setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (placesView.getSearchField().getText().isEmpty()) {
                    placesView.getSearchField().setText("Search");
                    loadPlaces();
                }
            }
        });
    }
    
    private void addNewPlace() {
        AdminPlacePopup popup = new AdminPlacePopup();
        PlaceData newPlace = popup.showDialog();
        if (newPlace != null) {
            if (placeDao.addPlace(newPlace)) {
                JOptionPane.showMessageDialog(placesView, "Place added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPlaces();
            } else {
                JOptionPane.showMessageDialog(placesView, "Failed to add place", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        private void loadPlaces() {
            try {
                List<PlaceData> places = placeDao.getAllPlaces();
                JPanel placesPanel = placesView.getPlacesPanel();
                placesPanel.removeAll();
                placesPanel.setLayout(new GridBagLayout());
            placesPanel.setBackground(new Color(255, 242, 227));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            if (places.isEmpty()) {
                JLabel noPlacesLabel = new JLabel("No places available. Click 'Add Place' to create one.");
                noPlacesLabel.setHorizontalAlignment(JLabel.CENTER);
                noPlacesLabel.setForeground(Color.GRAY);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 3;
                placesPanel.add(noPlacesLabel, gbc);
            } else {
                int col = 0;
                int row = 0;

                for (PlaceData place : places) {
                    gbc.gridx = col;
                    gbc.gridy = row;
                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.CENTER;

                    placesPanel.add(createPlaceCard(place), gbc);

                    col++;
                    if (col >= 3) { // 3 cards per row
                        col = 0;
                        row++;
                    }
                }
            }
        
            placesPanel.revalidate();
            placesPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(placesView, "Error loading places: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }}
    
    private JPanel createPlaceCard(PlaceData place) {
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());
    card.setBackground(new Color(222, 184, 135)); // Light brown background
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(205, 133, 63), 2), 
        BorderFactory.createEmptyBorder(15, 15, 15, 15)));
    
    // Set fixed size for consistent card appearance
    card.setPreferredSize(new Dimension(280, 385));
    card.setMaximumSize(new Dimension(280, 385));
    card.setMinimumSize(new Dimension(280, 385));
    
    // Main content panel
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.setBackground(new Color(222, 184, 135));
    
    // Circular Image Panel
    JPanel imagePanel = new JPanel();
    imagePanel.setBackground(new Color(222, 184, 135));
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setPreferredSize(new Dimension(250, 250));
    
    JLabel imageLabel = new JLabel();
    imageLabel.setPreferredSize(new Dimension(250, 250));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    
    if (place.getPlaceImage() != null) {
        ImageIcon icon = new ImageIcon(place.getPlaceImage());
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        
        // Create circular image
        imageLabel.setIcon(createCircularImage(scaledIcon, 250));
    } else {
        // Default circular placeholder
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.LIGHT_GRAY);
        imageLabel.setText("No Image");
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    }
    
    imagePanel.add(imageLabel, BorderLayout.CENTER);
    
    // Place name
    JLabel nameLabel = new JLabel(place.getPlaceName());
    nameLabel.setFont(new Font("Constantia", Font.BOLD, 18));
    nameLabel.setHorizontalAlignment(JLabel.CENTER);
    nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    
    // Description
    JTextArea descArea = new JTextArea(place.getDescription());
    descArea.setEditable(false);
    descArea.setLineWrap(true);
    descArea.setWrapStyleWord(true);
    descArea.setBackground(new Color(222, 184, 135));
    descArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    descArea.setRows(3);
    descArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
    descArea.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
    
    // Add components to content panel
    contentPanel.add(imagePanel);
    contentPanel.add(nameLabel);
    contentPanel.add(descArea);
    
    card.add(contentPanel, BorderLayout.CENTER);
    
    // Add click listener for edit/delete popup
    card.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            showPlaceActionDialog(place);
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        
        @Override
        public void mouseReleased(MouseEvent e) {}
        
        @Override
        public void mouseEntered(MouseEvent e) {
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 82, 45), 3), 
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 133, 63), 2), 
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        }
    });
    
    return card;
}
    
    // Helper method to create circular images
    private ImageIcon createCircularImage(ImageIcon icon, int size) {
        Image img = icon.getImage();
        java.awt.image.BufferedImage circularImage = new java.awt.image.BufferedImage(
            size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create circular clipping area
        g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
        g2d.drawImage(img, 0, 0, size, size, null);
        g2d.dispose();

        return new ImageIcon(circularImage);
    }
    
    
    // Method to show action dialog for place
private void showPlaceActionDialog(PlaceData place) {
    JDialog actionDialog = new JDialog();
    actionDialog.setTitle("Place Actions - " + place.getPlaceName());
    actionDialog.setSize(500, 600);
    actionDialog.setModal(true);
    actionDialog.setLayout(new BorderLayout());
    actionDialog.setLocationRelativeTo(placesView);
    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    mainPanel.setBackground(new Color(255, 242, 227));
    
    // Place Name Panel
    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    namePanel.setBackground(new Color(255, 242, 227));
    JLabel nameLabel = new JLabel("Place Name:");
    JTextField nameField = new JTextField(place.getPlaceName(), 20);
    namePanel.add(nameLabel);
    namePanel.add(nameField);
    
    // Description Panel
    JPanel descPanel = new JPanel(new BorderLayout());
    descPanel.setBackground(new Color(255, 242, 227));
    JLabel descLabel = new JLabel("Description:");
    JTextArea descriptionArea = new JTextArea(place.getDescription(), 5, 20);
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    JScrollPane descScroll = new JScrollPane(descriptionArea);
    descPanel.add(descLabel, BorderLayout.NORTH);
    descPanel.add(descScroll, BorderLayout.CENTER);
    
    // Image Panel
    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.setBackground(new Color(255, 242, 227));
    JLabel imageTitleLabel = new JLabel("Place Image:");
    JLabel imageLabel = new JLabel("", JLabel.CENTER);
    imageLabel.setPreferredSize(new Dimension(200, 150));
    imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    imageLabel.setBackground(Color.WHITE);
    imageLabel.setOpaque(true);
    
    // Store current image data
    final byte[][] currentImageData = {place.getPlaceImage()};
    
    if (place.getPlaceImage() != null) {
        ImageIcon icon = new ImageIcon(place.getPlaceImage());
        Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }
    
    JButton browseButton = new JButton("Change Image");
    browseButton.addActionListener(e -> {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Place Image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
                       name.endsWith(".png") || name.endsWith(".gif");
            }
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });
        
        if (fileChooser.showOpenDialog(actionDialog) == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                currentImageData[0] = java.nio.file.Files.readAllBytes(selectedFile.toPath());
                ImageIcon icon = new ImageIcon(currentImageData[0]);
                Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(actionDialog, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    imagePanel.add(imageTitleLabel, BorderLayout.NORTH);
    imagePanel.add(imageLabel, BorderLayout.CENTER);
    imagePanel.add(browseButton, BorderLayout.SOUTH);
    
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(255, 242, 227));

        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(80, 30));
        editButton.addActionListener(e -> {
            nameField.setEditable(true);
            descriptionArea.setEditable(true);
            browseButton.setEnabled(true);
            editButton.setEnabled(false);
        });

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(80, 30));
        saveButton.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty() || descriptionArea.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(actionDialog, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create updated place data
            PlaceData updatedPlace = new PlaceData(
                place.getPlaceId(),
                nameField.getText().trim(),
                descriptionArea.getText().trim(),
                currentImageData[0]
            );

            if (placeDao.updatePlace(updatedPlace)) {
                JOptionPane.showMessageDialog(actionDialog, "Place updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                actionDialog.dispose();
                loadPlaces(); // Refresh the places view
            } else {
                JOptionPane.showMessageDialog(actionDialog, "Failed to update place", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(80, 30));
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(actionDialog, 
                "Are you sure you want to delete this place?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (placeDao.deletePlace(place.getPlaceId())) {
                    JOptionPane.showMessageDialog(actionDialog, "Place deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    actionDialog.dispose();
                    loadPlaces(); // Refresh the places view
                } else {
                    JOptionPane.showMessageDialog(actionDialog, "Failed to delete place", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
    JButton closeButton = new JButton("Close");
    closeButton.setPreferredSize(new Dimension(80, 30));
    closeButton.addActionListener(e -> actionDialog.dispose());
    
    // Initially make fields non-editable
    nameField.setEditable(false);
    descriptionArea.setEditable(false);
    browseButton.setEnabled(false);
    
    buttonPanel.add(editButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(closeButton);
    
    // Add components to main panel
    mainPanel.add(namePanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(descPanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(imagePanel);
    mainPanel.add(Box.createVerticalStrut(15));
    mainPanel.add(buttonPanel);
    
    actionDialog.add(mainPanel);
    actionDialog.setVisible(true);
}
    
    
    private void searchPlaces() {
        String searchText = placesView.getSearchField().getText();
        if (searchText.equals("Search") || searchText.isEmpty()) {
            loadPlaces();
            return;
        }
        
        try {
            List<PlaceData> allPlaces = placeDao.getAllPlaces();
            JPanel placesPanel = placesView.getPlacesPanel();
            placesPanel.removeAll();
            placesPanel.setLayout(new BoxLayout(placesPanel, BoxLayout.Y_AXIS));
            
            boolean found = false;
            for (PlaceData place : allPlaces) {
                if (place.getPlaceName().toLowerCase().contains(searchText.toLowerCase())) {
                    placesPanel.add(createPlaceCard(place));
                    placesPanel.add(Box.createVerticalStrut(10));
                    found = true;
                }
            }
            
            if (!found) {
                JLabel noResultsLabel = new JLabel("No places found matching '" + searchText + "'");
                noResultsLabel.setHorizontalAlignment(JLabel.CENTER);
                noResultsLabel.setForeground(Color.GRAY);
                placesPanel.add(noResultsLabel);
            }
            
            placesPanel.revalidate();
            placesPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(placesView, "Error searching places: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void open(){
    this.placesView.setVisible(true);
    } 

    public void close(){
    this.placesView.dispose();
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
    
    // Button listeners
    class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdmindashboardView dashboardView = new AdmindashboardView();
            AdminDashboardController dashboardController = new AdminDashboardController(dashboardView, currentAdminId);
            dashboardController.open();
            close();
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
                placesView.dispose();

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
 
