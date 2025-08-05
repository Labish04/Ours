/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.PlaceDao;
import happytravell.dao.ReviewDao;
import happytravell.model.PlaceData;
import happytravell.model.ReviewData;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;
import happytravell.view.TravelerReviewsView;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Acer
 */
public class TravelerReviewsController {
    private TravelerReviewsView reviewView;
    private ReviewDao reviewDao;
    private JPanel reviewsContainer;
    private int currentTravellerId;
    private PlaceDao placeDao;
    
    public TravelerReviewsController(TravelerReviewsView travelerReviewsView, int travellerId) {
        this.reviewView = travelerReviewsView;
        this.reviewDao = new ReviewDao();
        this.currentTravellerId = travellerId;
        this.placeDao = new PlaceDao();
        this.reviewsContainer = (JPanel) reviewView.getScrollPane().getViewport().getView();
        
        setupNavigation();
        setupBackButton();
        initializePlaceManagement();
        loadPlaces(); 
    }
    
    private void setupNavigation() {
        reviewView.DashboardNavigation(new DashboardNav(reviewView.getDashboardlabel()));
        reviewView.RouteDetailsNavigation(new RouteDetailsNav(reviewView.getRoutelabel()));
        reviewView.BookingNavigation(new BookingNav(reviewView.getBookinglabel()));
        reviewView.BusTicketsNavigation(new BusTicketsNav(reviewView.getBusTicketslabel()));
        reviewView.VehiclesDetailsNavigation(new VehiclesDetailsNav(reviewView.getVehiclesDetailslabel()));
        reviewView.ProfileNavigation(new ProfileNav(reviewView.getProfilelabel()));
        reviewView.LogOutNavigation(new LogOutNav(reviewView.getLogOutlabel()));
        this.reviewView.BackNavigation(new BackNav());
    }
    
    private void setupBackButton() {
        reviewView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TravellerdashboardView dashboardView = new TravellerdashboardView();
                TravellerDashboardController dashboardController = new TravellerDashboardController(dashboardView, currentTravellerId);
                dashboardController.open();
                close();
            }
        });
    }
    
    
    public void open() {
        this.reviewView.setVisible(true);
    }
    
    public void close() {
        this.reviewView.dispose();
    }
    
    // Navigation classes following the TravellerDashboardController pattern
    class DashboardNav implements MouseListener {
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label) {
            this.dashboardLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerdashboardView dashboardView = new TravellerdashboardView();
            TravellerDashboardController dashboardController = new TravellerDashboardController(dashboardView, currentTravellerId);
            dashboardController.open();
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
    
    
    
    class RouteDetailsNav implements MouseListener {
        private JLabel routeLabel;
        
        public RouteDetailsNav(JLabel label) {
            this.routeLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerRouteView travellerRouteView = new TravellerRouteView();
            TravellerRouteController travellerRoute = new TravellerRouteController(travellerRouteView, currentTravellerId);
            travellerRoute.open();
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
        private JLabel busTicketsLabel;
        
        public BusTicketsNav(JLabel label) {
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBusTicketsView travellerBusTicketsView = new TravellerBusTicketsView();
            TravellerBusTicketController travellerBusTicket = new TravellerBusTicketController(travellerBusTicketsView, currentTravellerId);
            travellerBusTicket.open();
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
    
    class VehiclesDetailsNav implements MouseListener {
        private JLabel vehiclesDetailsLabel;
        
        public VehiclesDetailsNav(JLabel label) {
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerVehiclesDetailsView travellerVehiclesDetailsView = new TravellerVehiclesDetailsView();
            TravellerVehiclesDetailsController travellerVehiclesDetails = new TravellerVehiclesDetailsController(travellerVehiclesDetailsView, currentTravellerId);
            travellerVehiclesDetails.open();
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
        private JLabel profileLabel;
        
        public ProfileNav(JLabel label) {
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerProfileView travellerProfileView = new TravellerProfileView();
            TravellerProfileController travellerProfile = new TravellerProfileController(travellerProfileView, currentTravellerId);
            travellerProfile.open();
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
        private JLabel logOutLabel;
        
        public LogOutNav(JLabel label) {
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                reviewView.dispose();
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
            TravellerdashboardView travellerDashboardView = new TravellerdashboardView();
            TravellerDashboardController travellerController = new TravellerDashboardController(travellerDashboardView, currentTravellerId);
            travellerController.open();
            close();
        }
        
    }
    
    private JPanel createPlaceCard(PlaceData place) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(222, 184, 135));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(205, 133, 63), 2), 
            BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        card.setPreferredSize(new Dimension(280, 420));
        card.setMaximumSize(new Dimension(280, 420));
        card.setMinimumSize(new Dimension(280, 420));
        
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
            imageLabel.setIcon(createCircularImage(scaledIcon, 250));
        } else {
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
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        // Average Rating Panel
        double avgRating = reviewDao.getAverageRating(place.getPlaceName());
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        ratingPanel.setBackground(new Color(222, 184, 135));
        ratingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        int fullStars = (int) avgRating;
        for (int i = 0; i < 5; i++) {
            JLabel starLabel = new JLabel(i < fullStars ? "★" : "☆");
            starLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            starLabel.setForeground(i < fullStars ? new Color(255, 193, 7) : Color.GRAY);
            ratingPanel.add(starLabel);
        }
        
        // Add rating text
        JLabel ratingText = new JLabel(String.format("(%.1f)", avgRating));
        ratingText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ratingText.setForeground(new Color(100, 100, 100));
        ratingPanel.add(ratingText);
        
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
        
        // Review Button
        JButton reviewButton = new JButton("Add Review");
        reviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        reviewButton.setBackground(new Color(139, 69, 19));
        reviewButton.setForeground(Color.WHITE);
        reviewButton.setFocusPainted(false);
        reviewButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        reviewButton.setMaximumSize(new Dimension(150, 30));
        
        reviewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                reviewButton.setBackground(new Color(160, 82, 45));
                reviewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                reviewButton.setBackground(new Color(139, 69, 19));
            }
        });
        
        reviewButton.addActionListener(e -> openReviewDialog(place));
        
        // Add components to content panel
        contentPanel.add(imagePanel);
        contentPanel.add(nameLabel);
        contentPanel.add(ratingPanel);
        contentPanel.add(descArea);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(reviewButton);
        
        card.add(contentPanel, BorderLayout.CENTER);
        
        // Hover effects
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(160, 82, 45), 3), 
                    BorderFactory.createEmptyBorder(14, 14, 14, 14)));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(205, 133, 63), 2), 
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            }
        });
        
        return card;
    }
    
     private ImageIcon createCircularImage(ImageIcon icon, int size) {
        Image img = icon.getImage();
        java.awt.image.BufferedImage circularImage = new java.awt.image.BufferedImage(
            size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
        g2d.drawImage(img, 0, 0, size, size, null);
        g2d.dispose();

        return new ImageIcon(circularImage);
    }

// Placeholder for review dialog method
private void openReviewDialog(PlaceData place) {
        JDialog dialog = new JDialog(reviewView, "Add Review for " + place.getPlaceName(), true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(reviewView);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);

        // Place information
        JLabel placeLabel = new JLabel(place.getPlaceName());
        placeLabel.setFont(new Font("Constantia", Font.BOLD, 16));
        placeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(placeLabel);
        
        // Rating Selection
        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new BoxLayout(ratingPanel, BoxLayout.Y_AXIS));
        ratingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingPanel.setBackground(Color.WHITE);
        
        JLabel ratingLabel = new JLabel("Your Rating:");
        ratingLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingPanel.add(ratingLabel);
        ratingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JPanel starPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        starPanel.setBackground(Color.WHITE);
        JButton[] starButtons = new JButton[5];
        int[] userRating = {0};

        for (int i = 0; i < 5; i++) {
            starButtons[i] = new JButton("☆");
            starButtons[i].setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
            starButtons[i].setForeground(Color.GRAY);
            starButtons[i].setBorderPainted(false);
            starButtons[i].setContentAreaFilled(false);
            starButtons[i].setFocusPainted(false);
            int index = i;
            starButtons[i].addActionListener(e -> {
                userRating[0] = index + 1;
                for (int j = 0; j < 5; j++) {
                    if (j <= index) {
                        starButtons[j].setText("★");
                        starButtons[j].setForeground(new Color(255, 193, 7));
                    } else {
                        starButtons[j].setText("☆");
                        starButtons[j].setForeground(Color.GRAY);
                    }
                }
            });
            starPanel.add(starButtons[i]);
        }
        ratingPanel.add(starPanel);
        ratingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Review Text
        JLabel commentLabel = new JLabel("Your Review:");
        commentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        commentLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        JTextArea reviewText = new JTextArea(5, 30);
        reviewText.setLineWrap(true);
        reviewText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reviewText);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Submit Button
        JButton submitButton = new JButton("Submit Review");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setBackground(new Color(76, 175, 80));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setMaximumSize(new Dimension(150, 35));
        submitButton.addActionListener(e -> {
            if (userRating[0] == 0) {
                JOptionPane.showMessageDialog(dialog, "Please select a rating", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ReviewData review = new ReviewData();
            review.setPlaceName(place.getPlaceName());
            review.setTravellerId(currentTravellerId);
            review.setRating(userRating[0]);
            review.setReviewText(reviewText.getText());
            review.setStatus("PENDING");
            
            if (reviewDao.addReview(review)) {
                JOptionPane.showMessageDialog(dialog, 
                    "Review submitted for approval", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                loadPlaces(); // Refresh to update average rating
            } else {
                JOptionPane.showMessageDialog(dialog, 
                    "Failed to submit review", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Button hover effects
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(56, 142, 60));
                submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(76, 175, 80));
            }
        });

        // Add components to panel
        panel.add(ratingPanel);
        panel.add(commentLabel);
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(submitButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void initializePlaceManagement() {
            reviewView.getSearchField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchPlaces();
                }
            });

            reviewView.getSearchField().addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (reviewView.getSearchField().getText().equals("Search")) {
                        reviewView.getSearchField().setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (reviewView.getSearchField().getText().isEmpty()) {
                        reviewView.getSearchField().setText("Search");
                        loadPlaces();
                    }
                }
            });
    }

    // Enhanced loadPlaces method for TravellerPlacesController
    private void loadPlaces() {
        try {
            List<PlaceData> places = placeDao.getAllPlaces();
            JPanel placesPanel = reviewView.getPlacesPanel();
            placesPanel.removeAll();
            placesPanel.setLayout(new GridBagLayout());
            placesPanel.setBackground(new Color(255, 242, 227));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            if (places.isEmpty()) {
                JLabel noPlacesLabel = new JLabel("No places available.");
                noPlacesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                placesPanel.add(noPlacesLabel, gbc);
            } else {
                int row = 0;
                int col = 0;
                
                for (PlaceData place : places) {
                    gbc.gridx = col;
                    gbc.gridy = row;
                    placesPanel.add(createPlaceCard(place), gbc);
                    
                    col++;
                    if (col >= 2) { // 2 columns
                        col = 0;
                        row++;
                    }
                }
            }
            placesPanel.revalidate();
            placesPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(reviewView, "Error loading places", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    private void searchPlaces() {
        String searchText = reviewView.getSearchField().getText();
        if (searchText.equals("Search") || searchText.isEmpty()) {
            loadPlaces();
            return;
        }
        
        try {
            List<PlaceData> allPlaces = placeDao.getAllPlaces();
            JPanel placesPanel = reviewView.getPlacesPanel();
            placesPanel.removeAll();
            placesPanel.setLayout(new GridBagLayout());
            placesPanel.setBackground(new Color(255, 242, 227));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
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
            JOptionPane.showMessageDialog(reviewView, "Error searching places: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}