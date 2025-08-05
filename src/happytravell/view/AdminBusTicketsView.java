/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import happytravell.UI.AdminBookingDetailsCardPanel;
import happytravell.UI.AdminBusTicketsCardPanel;
import happytravell.controller.AdminBookingDetailsController;
import happytravell.controller.AdminBusTicketsController;
import happytravell.dao.BusTicketsDao;
import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.model.BusTicketsData;
import happytravell.model.TravellerData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class AdminBusTicketsView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public AdminBusTicketsView() {
        initComponents();
        scaleImage1();
        scaleImage2();
        scaleImage3();
        scaleImage4();
        scaleImage5();
        scaleImage6();
        scaleImage7();
        scaleImage8();
        

    }
    
    public void scaleImage1(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/dashboard.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(dashboardIcon.getWidth(), dashboardIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        dashboardIcon.setIcon(scaledIcon);
    }
    public void scaleImage2(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/booking.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(bookingIcon.getWidth(), bookingIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bookingIcon.setIcon(scaledIcon);
    }
    public void scaleImage3(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/directions.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(routeIcon.getWidth(), routeIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        routeIcon.setIcon(scaledIcon);
    }
    public void scaleImage4(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/tickets.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(busTicketIcon.getWidth(), busTicketIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        busTicketIcon.setIcon(scaledIcon);
    }
    public void scaleImage5(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/front-of-bus.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(vehiclesIcon.getWidth(), vehiclesIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        vehiclesIcon.setIcon(scaledIcon);
    }
    public void scaleImage6(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/user.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(profileIcon.getWidth(), profileIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        profileIcon.setIcon(scaledIcon);
    }
    public void scaleImage7(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/logout.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(logoutIcon.getWidth(), logoutIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoutIcon.setIcon(scaledIcon);
    }
    public void scaleImage8(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/happy.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(logoIcon.getWidth(), logoIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoIcon.setIcon(scaledIcon);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        TravelerDetailsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        DashDetailsPanel = new javax.swing.JPanel();
        logoutIcon = new javax.swing.JLabel();
        profileIcon = new javax.swing.JLabel();
        vehiclesIcon = new javax.swing.JLabel();
        busTicketIcon = new javax.swing.JLabel();
        happyTravelLabel = new javax.swing.JLabel();
        routeIcon = new javax.swing.JLabel();
        bookingIcon = new javax.swing.JLabel();
        dashboardIcon = new javax.swing.JLabel();
        logoIcon = new javax.swing.JLabel();
        dashboardLabel = new javax.swing.JLabel();
        bookingDetailsLabel = new javax.swing.JLabel();
        routeDetailsLabel = new javax.swing.JLabel();
        busTicketsLabel = new javax.swing.JLabel();
        vehiclesDetailsLabel = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();
        logOutLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TravelerDetailsPanel.setBackground(new java.awt.Color(255, 242, 227));
        TravelerDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Constantia", 1, 18)); // NOI18N
        jLabel1.setText("Bus Tickets");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 150, 50));

        TravelerDetailsPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 50));

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel2.setBackground(new java.awt.Color(255, 242, 227));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        scrollPane.setViewportView(jPanel2);

        TravelerDetailsPanel.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, 700, 550));

        getContentPane().add(TravelerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 700, 600));

        DashDetailsPanel.setBackground(new java.awt.Color(241, 215, 184));
        DashDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        DashDetailsPanel.add(logoutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 30, 30));
        logoutIcon.getAccessibleContext().setAccessibleName("");

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        profileIcon.setText("jLabel1");
        DashDetailsPanel.add(profileIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 30, 30));

        vehiclesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/front-of-bus.png"))); // NOI18N
        DashDetailsPanel.add(vehiclesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 30, 30));

        busTicketIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tickets.png"))); // NOI18N
        DashDetailsPanel.add(busTicketIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 30, 30));

        happyTravelLabel.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        happyTravelLabel.setText("Happy Travels");
        DashDetailsPanel.add(happyTravelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, -1));

        routeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/directions.png"))); // NOI18N
        DashDetailsPanel.add(routeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 30, 30));

        bookingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/booking.png"))); // NOI18N
        DashDetailsPanel.add(bookingIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 30, 30));
        bookingIcon.getAccessibleContext().setAccessibleName("");

        dashboardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard.png"))); // NOI18N
        dashboardIcon.setText("jLabel1");
        DashDetailsPanel.add(dashboardIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 30, 30));

        logoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/happy.png"))); // NOI18N
        logoIcon.setFocusCycleRoot(true);
        DashDetailsPanel.add(logoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, -20, 180, 120));
        logoIcon.getAccessibleContext().setAccessibleName("");

        dashboardLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        dashboardLabel.setText("  Dashboard");
        DashDetailsPanel.add(dashboardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 100, 20));

        bookingDetailsLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        bookingDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bookingDetailsLabel.setText("  Booking Details");
        DashDetailsPanel.add(bookingDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 120, 20));

        routeDetailsLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        routeDetailsLabel.setText("  Route Details");
        DashDetailsPanel.add(routeDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 130, 20));

        busTicketsLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        busTicketsLabel.setText("Bus Tickets");
        DashDetailsPanel.add(busTicketsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 110, 20));

        vehiclesDetailsLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        vehiclesDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        vehiclesDetailsLabel.setText("  Vehicles Details");
        DashDetailsPanel.add(vehiclesDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 390, 120, 20));

        profileLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        profileLabel.setText("Profile");
        DashDetailsPanel.add(profileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 90, 20));

        logOutLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        logOutLabel.setText("LogOut");
        DashDetailsPanel.add(logOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 80, 20));

        getContentPane().add(DashDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminBusTicketsView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JLabel bookingDetailsLabel;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel routeDetailsLabel;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

   
//    navigation
    public void DashboardNavigation(MouseListener listener){
        dashboardLabel.addMouseListener(listener);
    }
    public JLabel getDashboardlabel(){
        return dashboardLabel;
    }
    public void RouteDetailsNavigation(MouseListener listener){
        routeDetailsLabel.addMouseListener(listener);
    }
    public JLabel getRouteDetailslabel(){
        return routeDetailsLabel;
    }
    
    public void BookingDetailsNavigation(MouseListener listener){
        bookingDetailsLabel.addMouseListener(listener);
    }
    public JLabel getBookingDetailslabel(){
        return bookingDetailsLabel;
    }
    public void VehiclesDetailsNavigation(MouseListener listener){
        vehiclesDetailsLabel.addMouseListener(listener);
    }
    public JLabel getVehiclesDetailslabel(){
        return vehiclesDetailsLabel;
    }
    public void ProfileNavigation(MouseListener listener){
        profileLabel.addMouseListener(listener);
    }
    public JLabel getProfilelabel(){
        return profileLabel;
    }
    public void LogOutNavigation(MouseListener listener){
        logOutLabel.addMouseListener(listener);
    }
    public JLabel getLogOutlabel(){
        return logOutLabel;
    } 
    
  
//     Add these methods to the class
public void displayBusTickets(List<BusTicketsData> tickets) {
    JPanel contentPanel = (JPanel) scrollPane.getViewport().getView();
    contentPanel.removeAll();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    
    for (BusTicketsData ticket : tickets) {
        AdminBusTicketsCardPanel card = new AdminBusTicketsCardPanel(ticket);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(card);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between cards
    }
    
    contentPanel.revalidate();
    contentPanel.repaint();
}
    
    
    
    

    
    
    
    
    
    
    
public JPanel getContentPanel() {
    return jPanel2;
}
    
    
}
