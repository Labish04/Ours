package happytravell.controller;

import happytravell.dao.BusTicketsDao;
import happytravell.model.BusTicketsData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AdminBusTicketsController {
    private AdminBusTicketsView ticketsView;
    private int currentAdminId;
    private BusTicketsDao busTicketsDao;
    
    public AdminBusTicketsController(AdminBusTicketsView adminBusTicketsView, int adminId) {
        this.ticketsView = adminBusTicketsView;
        this.currentAdminId = adminId;
        this.busTicketsDao = new BusTicketsDao(); 
        
        // Set up navigation
        setupNavigation();
        loadBusTickets();
    }
    
    private void setupNavigation() {
        ticketsView.DashboardNavigation(new NavListener(ticketsView.getDashboardlabel(), 
            () -> navigateTo(new AdmindashboardView(), AdminDashboardController.class)));
        
        ticketsView.BookingDetailsNavigation(new NavListener(ticketsView.getBookingDetailslabel(), 
            () -> navigateTo(new AdminBookingDetailsView(), AdminBookingDetailsController.class)));
        
        ticketsView.RouteDetailsNavigation(new NavListener(ticketsView.getRouteDetailslabel(), 
            () -> navigateTo(new AdminRouteDetailsView(), AdminRouteDetailsController.class)));
        
        ticketsView.VehiclesDetailsNavigation(new NavListener(ticketsView.getVehiclesDetailslabel(), 
            () -> navigateTo(new AdminVehiclesDetailsView(), AdminVehiclesDetailsController.class)));
        
        ticketsView.ProfileNavigation(new NavListener(ticketsView.getProfilelabel(), 
            () -> navigateTo(new AdminProfileView(), AdminProfileController.class)));
        
        ticketsView.LogOutNavigation(new NavListener(ticketsView.getLogOutlabel(), this::handleLogout));
    }
    
    private void navigateTo(JFrame view, Class<?> controllerClass) {
        try {
            Constructor<?> constructor = controllerClass.getConstructor(view.getClass(), int.class);
            Object controller = constructor.newInstance(view, currentAdminId);
            Method openMethod = controllerClass.getMethod("open");
            openMethod.invoke(controller);
            close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ticketsView, "Error navigating: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleLogout() {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            close();
            LoginPageView loginView = new LoginPageView();
            new LoginController(loginView).open();
        }
    }
    
    public void open() {
        this.ticketsView.setVisible(true);
    } 
    
    public void close() {
        this.ticketsView.dispose();
    }
    
    public void loadBusTickets() {
        try {
            List<BusTicketsData> tickets = busTicketsDao.getAllBusTickets();
            if (tickets == null || tickets.isEmpty()) {
                JOptionPane.showMessageDialog(ticketsView, "No bus tickets found", 
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ticketsView.displayBusTickets(tickets);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ticketsView, 
                "Error loading bus tickets: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Reusable navigation listener class
    class NavListener implements MouseListener {
        private JLabel label;
        private Runnable action;
        private Color hoverColor;
        
        public NavListener(JLabel label, Runnable action) {
            this(label, action, label.getText().equals("Log Out") ? Color.WHITE : Color.RED);
        }
        
        public NavListener(JLabel label, Runnable action, Color hoverColor) {
            this.label = label;
            this.action = action;
            this.hoverColor = hoverColor;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            action.run();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        
        @Override
        public void mouseReleased(MouseEvent e) {}
        
        @Override
        public void mouseEntered(MouseEvent e) {
            label.setForeground(hoverColor);
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            label.setForeground(Color.BLACK);
            label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}