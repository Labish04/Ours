package happytravell.controller;

import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.view.CancelRescheduleView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CancelRescheduleController {
    
    private CancelRescheduleView view;
    private TravellerDao dao;
    private int bookingId;

    public CancelRescheduleController(CancelRescheduleView view, int bookingId) {
        this.view = view;
        this.bookingId = bookingId;
        this.dao = new TravellerDao();
        
        loadBookingDetails();
        
        this.view.addRescheduleListener(new RescheduleListener());
        this.view.addCancelListener(new CancelListener());
    }
    
    public void open() {
        view.setVisible(true);
    }
    
    public void close() {
        view.dispose();
    }
    
    private void loadBookingDetails() {
        BookingData booking = dao.getBookingById(bookingId);
        if (booking != null) {
            view.setPickupAddress(booking.getPickupAddress());
            view.setDropAddress(booking.getDropAddress());
            // Note: Date conversion might be needed here depending on your model/view
            // view.setDepartureDateTime(...);
            // view.setReturnDateTime(...);
            view.setNumberOfPassengers(booking.getPassengerCount());
            view.setVehicleNumber(booking.getVehicleNumber());
            view.setDriverName(booking.getDriverName());
            view.setPaymentMethod(booking.getPaymentMethod());
        } else {
            JOptionPane.showMessageDialog(view, "Error: Could not load booking details.", "Error", JOptionPane.ERROR_MESSAGE);
            close();
        }
    }
    
    class RescheduleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BookingData bookingToUpdate = new BookingData();
            bookingToUpdate.setBookingId(bookingId);
            bookingToUpdate.setPickupAddress(view.getPickupAddress());
            bookingToUpdate.setDropAddress(view.getDropAddress());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            bookingToUpdate.setDepartureDateTime(sdf.format(view.getDepartureDateTime()));
            bookingToUpdate.setReturnDateTime(sdf.format(view.getReturnDateTime()));
            
            bookingToUpdate.setPassengerCount(view.getNumberOfPassengers());
            bookingToUpdate.setVehicleNumber(view.getSelectedVehicleNumber());
            bookingToUpdate.setDriverName(view.getSelectedDriverName());
            bookingToUpdate.setPaymentMethod(view.getSelectedPaymentMethod());

            if (dao.updateBooking(bookingToUpdate)) {
                JOptionPane.showMessageDialog(view, "Trip has been successfully rescheduled!", "Reschedule Success", JOptionPane.INFORMATION_MESSAGE);
                close();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to reschedule the trip.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmation = JOptionPane.showConfirmDialog(
                view, 
                "Are you sure you want to cancel this trip?", 
                "Confirm Cancellation", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirmation == JOptionPane.YES_OPTION) {
                if (dao.deleteBooking(bookingId)) {
                    JOptionPane.showMessageDialog(view, "Trip has been successfully cancelled.", "Cancellation Success", JOptionPane.INFORMATION_MESSAGE);
                    close();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to cancel the trip.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
} 