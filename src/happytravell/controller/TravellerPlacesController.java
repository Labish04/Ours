/*
 * Enhanced TravellerPlacesController with read-only place details view
 */
package happytravell.controller;

import happytravell.dao.PlaceDao;
import happytravell.model.PlaceData;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerPlacesView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TravellerPlacesController {
    private TravellerPlacesView placeView;
    private int currentTravellerId;
    private PlaceDao placeDao;
    
    public TravellerPlacesController(TravellerPlacesView placeView, int travellerId) {
        this.currentTravellerId = travellerId;
        this.placeView = placeView;
        this.placeDao = new PlaceDao();
        
        // Attach all the navigation listeners
        this.placeView.DashboardNavigation(new DashboardNav(placeView.getDashboardlabel()));
        this.placeView.BookingNavigation(new BookingNav(placeView.getBookinglabel()));
        this.placeView.RouteNavigation(new RouteNav(placeView.getRoutelabel()));
        this.placeView.BusTicketsNavigation(new BusTicketsNav(placeView.getBusTicketslabel()));
        this.placeView.VehiclesDetailsNavigation(new VehiclesDetailsNav(placeView.getVehiclesDetailslabel()));
        this.placeView.ProfileNavigation(new ProfileNav(placeView.getProfilelabel()));
        this.placeView.LogOutNavigation(new LogOutNav(placeView.getLogOutlabel()));
         
        // Load places when controller is initialized
        loadPlaces(); 
        initializePlaceManagement();
    }
    private void initializePlaceManagement() {
        placeView.getSearchField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPlaces();
            }
        });
        
        placeView.getSearchField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (placeView.getSearchField().getText().equals("Search")) {
                    placeView.getSearchField().setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (placeView.getSearchField().getText().isEmpty()) {
                    placeView.getSearchField().setText("Search");
                    loadPlaces();
                }
            }
        });
    }

    // Enhanced loadPlaces method for TravellerPlacesController
    private void loadPlaces() {
        try {
            List<PlaceData> places = placeDao.getAllPlaces();
            JPanel placesPanel = placeView.getPlacesPanel();
            placesPanel.removeAll();
            placesPanel.setLayout(new GridBagLayout());
            placesPanel.setBackground(new Color(255, 242, 227));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            if (places.isEmpty()) {
                JLabel noPlacesLabel = new JLabel("No places available to explore.");
                noPlacesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                noPlacesLabel.setForeground(Color.GRAY);
                noPlacesLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
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
            JOptionPane.showMessageDialog(placeView, "Error loading places: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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

        @Override
        public void mouseClicked(MouseEvent e) {
            
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

    private void showPlaceDetails(PlaceData place) {
        JDialog dialog = new JDialog();
        dialog.setTitle(place.getPlaceName());
        dialog.setSize(400, 500);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(placeView);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Image
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        if (place.getPlaceImage() != null) {
            ImageIcon icon = new ImageIcon(place.getPlaceImage());
            Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setText("No Image Available");
            imageLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            imageLabel.setForeground(Color.GRAY);
        }
        
        // Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        
        // Place name
        JLabel nameLabel = new JLabel(place.getPlaceName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Description
        JTextArea descArea = new JTextArea(place.getDescription());
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descArea.setBackground(Color.WHITE);
        descArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(descArea);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        // Add components to details panel
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(scrollPane);
        
        // Add components to main panel
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(detailsPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(closeButton);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    
   

    // Enhanced search method for better grid layout
    private void searchPlaces() {
        String searchText = placeView.getSearchField().getText();
        if (searchText.equals("Search") || searchText.isEmpty()) {
            loadPlaces();
            return;
        }
        
        try {
            List<PlaceData> allPlaces = placeDao.getAllPlaces();
            JPanel placesPanel = placeView.getPlacesPanel();
            placesPanel.removeAll();
            placesPanel.setLayout(new GridBagLayout());
            placesPanel.setBackground(new Color(255, 242, 227));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            
            boolean found = false;
            int col = 0;
            int row = 0;
            
            for (PlaceData place : allPlaces) {
                if (place.getPlaceName().toLowerCase().contains(searchText.toLowerCase()) ||
                    place.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
                    
                    gbc.gridx = col;
                    gbc.gridy = row;
                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.CENTER;
                    
                    placesPanel.add(createPlaceCard(place), gbc);
                    
                    col++;
                    if (col >= 3) {
                        col = 0;
                        row++;
                    }
                    found = true;
                }
            }
            
            if (!found) {
                JLabel noResultsLabel = new JLabel("No places found matching '" + searchText + "'");
                noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                noResultsLabel.setForeground(Color.GRAY);
                noResultsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 3;
                placesPanel.add(noResultsLabel, gbc);
            }
            
            placesPanel.revalidate();
            placesPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(placeView, "Error searching places: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        TravellerdashboardView dashboardView = new TravellerdashboardView();
        TravellerDashboardController dashboardController = new TravellerDashboardController(dashboardView, currentTravellerId);
        dashboardController.open();
        close();
    }

    public void open() {
        this.placeView.setVisible(true);
    }

    public void close() {
        this.placeView.dispose();
    }

    // Navigation classes
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

    class BusTicketsNav implements MouseListener {
        private final JLabel busTicketsLabel;

        public BusTicketsNav(JLabel label) {
            this.busTicketsLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Already on this page, do nothing or refresh
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
                placeView.dispose();

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