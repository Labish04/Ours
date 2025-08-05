package happytravell.popup;

import happytravell.model.BookingData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookingDetailsPopup extends JDialog {
    private BookingData booking;
    private JButton cancelButton;
    private JButton printButton;
    private JButton closeButton;

    // Constructor for single booking
    public BookingDetailsPopup(Frame parent, BookingData booking) {
        super(parent, "Booking Details", true);
        this.booking = booking;
        initialize();
    }

    // Constructor for multiple bookings list
    public BookingDetailsPopup(Frame parent, List<BookingData> bookings) {
        super(parent, "All Booking Details", true);
        initializeForList(bookings);
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setText(formatBookingDetails());
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsArea.setBackground(new Color(248, 219, 164));
        
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        cancelButton = new JButton("Cancel Booking");
        printButton = new JButton("Print Receipt");
        closeButton = new JButton("Close");
        
        // Set button colors
        cancelButton.setBackground(new Color(171, 106, 32));
        printButton.setBackground(new Color(171, 106, 32));
        closeButton.setBackground(new Color(171, 106, 32));
        
        // Add close functionality
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeForList(List<BookingData> bookings) {
        setLayout(new BorderLayout());
        setSize(600, 500);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setText(formatAllBookingsDetails(bookings));
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailsArea.setBackground(new Color(248, 219, 164));
        
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel for list view
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(171, 106, 32));
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(closeButton);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String formatBookingDetails() {
        if (booking == null) {
            return "No booking data available.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Booking Details ===\n\n");
        sb.append(String.format("%-20s: %d\n", "Booking ID", booking.getBookingId()));
        sb.append(String.format("%-20s: %s\n", "Pickup Address", 
            booking.getPickupAddress() != null ? booking.getPickupAddress() : "Not specified"));
        sb.append(String.format("%-20s: %s\n", "Drop Address", 
            booking.getDropAddress() != null ? booking.getDropAddress() : "Not specified"));
        sb.append(String.format("%-20s: %s\n", "Departure DateTime", 
            booking.getDepartureDateTime() != null ? booking.getDepartureDateTime().toString() : "Not specified"));
        sb.append(String.format("%-20s: %s\n", "Return DateTime", 
            booking.getReturnDateTime() != null ? booking.getReturnDateTime().toString() : "N/A"));
        sb.append(String.format("%-20s: %d\n", "Passenger Count", booking.getPassengerCount()));
        sb.append(String.format("%-20s: %s\n", "Vehicle Type", 
            booking.getVehicleType() != null ? booking.getVehicleType() : "Not specified"));
        sb.append(String.format("%-20s: %s\n", "Vehicle Number", 
            booking.getVehicleNumber() != null ? booking.getVehicleNumber() : "Not assigned"));
        sb.append(String.format("%-20s: %s\n", "Driver Name", 
            booking.getDriverName() != null ? booking.getDriverName() : "Not assigned"));
        sb.append(String.format("%-20s: %s\n", "Payment Method", 
            booking.getPaymentMethod() != null ? booking.getPaymentMethod() : "Not specified"));
        sb.append(String.format("%-20s: %.2f\n", "Price", booking.getTotalAmount()));
        
        return sb.toString();
    }

    private String formatAllBookingsDetails(List<BookingData> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return "No bookings found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== All Booking Details ===\n\n");
        
        for (int i = 0; i < bookings.size(); i++) {
            BookingData booking = bookings.get(i);
            sb.append(String.format("--- Booking %d ---\n", i + 1));
            sb.append(String.format("ID: %d | Pickup: %s | Drop: %s\n", 
                booking.getBookingId(),
                booking.getPickupAddress() != null ? booking.getPickupAddress() : "N/A",
                booking.getDropAddress() != null ? booking.getDropAddress() : "N/A"));
            sb.append(String.format("Vehicle: %s | Passengers: %d\n", 
                booking.getVehicleType() != null ? booking.getVehicleType() : "N/A",
                booking.getPassengerCount()));
            sb.append(String.format("Payment: %s | Price: %.2f\n", 
                booking.getPaymentMethod() != null ? booking.getPaymentMethod() : "N/A",
                booking.getTotalAmount()));
            sb.append("\n");
        }
        
        return sb.toString();
    }

    public void addCancelButtonListener(ActionListener listener) {
        if (cancelButton != null) {
            cancelButton.addActionListener(listener);
        }
    }

    public void addPrintButtonListener(ActionListener listener) {
        if (printButton != null) {
            printButton.addActionListener(listener);
        }
    }

    public void addCloseButtonListener(ActionListener listener) {
        if (closeButton != null) {
            closeButton.addActionListener(listener);
        }
    }

    public BookingData getBooking() {
        return booking;
    }

    // Static method to show booking details popup
    public static void showBookingDetails(Frame parent, List<BookingData> bookings) {
        SwingUtilities.invokeLater(() -> {
            BookingDetailsPopup popup = new BookingDetailsPopup(parent, bookings);
            popup.setVisible(true);
        });
    }

    // Static method to show single booking details
    public static void showSingleBookingDetails(Frame parent, BookingData booking) {
        SwingUtilities.invokeLater(() -> {
            BookingDetailsPopup popup = new BookingDetailsPopup(parent, booking);
            popup.setVisible(true);
        });
    }
}