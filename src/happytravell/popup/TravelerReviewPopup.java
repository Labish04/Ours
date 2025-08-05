package happytravell.popup;

import happytravell.model.ReviewData;
import happytravell.dao.ReviewDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Popup dialog for travelers to submit reviews and ratings (1-5 stars)
 * @author Acer
 */
public class TravelerReviewPopup extends JDialog {
    private ReviewDao reviewDao;
    private int travellerId;
    private String travellerName;
    private String travellerEmail;
    
    // UI Components
    private JTextField bookingReferenceField;
    private JTextField vehicleTypeField;
    private JTextField driverNameField;
    private JPanel starRatingPanel;
    private JLabel[] starLabels;
    private int selectedRating = 5; // Default to 5 stars
    private JTextArea reviewTextArea;
    private JButton submitButton;
    private JButton cancelButton;
    private JLabel errorLabel;
    
    public TravelerReviewPopup(int travellerId, String travellerName, String travellerEmail) {
        this.reviewDao = new ReviewDao();
        this.travellerId = travellerId;
        this.travellerName = travellerName;
        this.travellerEmail = travellerEmail;
        
        initializeDialog();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeDialog() {
        setTitle("Submit Your Review/Rating");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set background color
        getContentPane().setBackground(new Color(248, 248, 248));
    }
    
    private void initializeComponents() {
        // Booking reference
        bookingReferenceField = new JTextField(20);
        bookingReferenceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Vehicle type
        vehicleTypeField = new JTextField(20);
        vehicleTypeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Driver name
        driverNameField = new JTextField(20);
        driverNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Star rating system - Fixed with better font and hover effects
        starRatingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        starRatingPanel.setBackground(new Color(248, 248, 248));
        starLabels = new JLabel[5];
        
        for (int i = 0; i < 5; i++) {
            starLabels[i] = new JLabel("★");
            starLabels[i].setFont(new Font("Arial Unicode MS", Font.BOLD, 35)); // Better font for stars
            starLabels[i].setForeground(new Color(255, 193, 7)); // Gold color
            starLabels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            starLabels[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
            final int starIndex = i;
            
            starLabels[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedRating = starIndex + 1;
                    updateStarDisplay();
                }
                
                @Override
                public void mousePressed(MouseEvent e) {}
                
                @Override
                public void mouseReleased(MouseEvent e) {}
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Highlight stars on hover
                    for (int j = 0; j <= starIndex; j++) {
                        starLabels[j].setForeground(new Color(255, 215, 0)); // Bright gold
                        starLabels[j].setText("★");
                    }
                    for (int j = starIndex + 1; j < 5; j++) {
                        starLabels[j].setForeground(new Color(200, 200, 200)); // Gray
                        starLabels[j].setText("☆");
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    updateStarDisplay();
                }
            });
            
            starRatingPanel.add(starLabels[i]);
        }
        updateStarDisplay();
        
        reviewTextArea = new JTextArea(5, 20);
        reviewTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        JScrollPane reviewScrollPane = new JScrollPane(reviewTextArea);
        
        // Initialize buttons
        submitButton = new JButton("Submit Review");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setBackground(new Color(76, 175, 80));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        
        // Initialize error label
        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        errorLabel.setForeground(Color.RED);
    }
    
    private void updateStarDisplay() {
        for (int i = 0; i < 5; i++) {
            if (i < selectedRating) {
                starLabels[i].setText("★");
                starLabels[i].setForeground(new Color(255, 193, 7)); // Gold
                starLabels[i].setFont(new Font("Arial Unicode MS", Font.BOLD, 35));
            } else {
                starLabels[i].setText("☆");
                starLabels[i].setForeground(new Color(200, 200, 200)); // Gray
                starLabels[i].setFont(new Font("Arial Unicode MS", Font.BOLD, 35));
            }
        }
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(248, 248, 248));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Submit Your Review/Rating");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        
        // Traveller info (read-only)
        JPanel travellerInfoPanel = new JPanel(new BorderLayout(10, 0));
        travellerInfoPanel.setBackground(new Color(240, 240, 240));
        travellerInfoPanel.setBorder(BorderFactory.createTitledBorder("Your Information"));
        
        JLabel nameLabel = new JLabel("Name: " + travellerName);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel emailLabel = new JLabel("Email: " + travellerEmail);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        travellerInfoPanel.add(nameLabel, BorderLayout.NORTH);
        travellerInfoPanel.add(emailLabel, BorderLayout.SOUTH);
        
        contentPanel.add(travellerInfoPanel);
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Form fields
        contentPanel.add(createFormField("Booking Reference:", bookingReferenceField));
        contentPanel.add(Box.createVerticalStrut(15));
        
        contentPanel.add(createFormField("Vehicle Type:", vehicleTypeField));
        contentPanel.add(Box.createVerticalStrut(15));
        
        contentPanel.add(createFormField("Driver Name:", driverNameField));
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Star rating
        contentPanel.add(createFormField("Rating (1-5 stars):", starRatingPanel));
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Review text
        contentPanel.add(createFormField("Review Text:", new JScrollPane(reviewTextArea)));
        contentPanel.add(Box.createVerticalStrut(20));
        
        // Error label
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(errorLabel);
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(248, 248, 248));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
        contentPanel.add(buttonPanel);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormField(String labelText, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(248, 248, 248));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setPreferredSize(new Dimension(150, 25));
        
        panel.add(label, BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitReview();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void submitReview() {
        // Clear previous error
        errorLabel.setText("");
        
        // Validate inputs
        String reviewText = reviewTextArea.getText().trim();
        String bookingReference = bookingReferenceField.getText().trim();
        String vehicleType = vehicleTypeField.getText().trim();
        String driverName = driverNameField.getText().trim();
        
        // Validate rating is between 1-5
        if (selectedRating < 1 || selectedRating > 5) {
            showError("Rating must be between 1 and 5 stars");
            return;
        }
        
        // Validate review text
        if (reviewText.isEmpty()) {
            showError("Please enter review text");
            return;
        }
        
        if (reviewText.length() < 10) {
            showError("Review text must be at least 10 characters long");
            return;
        }
        
        // Create review object
        ReviewData review = new ReviewData();
        review.setTravellerId(travellerId);
        review.setTravellerName(travellerName);
        review.setTravellerEmail(travellerEmail);
        review.setRating(selectedRating);
        review.setReviewText(reviewText);
        review.setBookingReference(bookingReference.isEmpty() ? null : bookingReference);
        review.setVehicleType(vehicleType.isEmpty() ? null : vehicleType);
        review.setDriverName(driverName.isEmpty() ? null : driverName);
        review.setReviewType("FULL_REVIEW");
        review.setStatus("PENDING"); // Will be reviewed by admin
        
        // Save to database
        if (reviewDao.addReview(review)) {
            JOptionPane.showMessageDialog(this, 
                "Review submitted successfully! It will be reviewed by admin.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            showError("Failed to submit review. Please try again.");
        }
    }
    
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setForeground(Color.RED);
    }
}