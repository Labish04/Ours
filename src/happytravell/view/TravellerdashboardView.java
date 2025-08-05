/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Acer
 */
public class TravellerdashboardView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public TravellerdashboardView() {
        initComponents();
        scaleImage1();
        scaleImage2();
        scaleImage3();
        scaleImage4();
        scaleImage5();
        scaleImage6();
        scaleImage7();
        scaleImage8();
        scaleImage10();
        scaleImage11();
        scaleImage12();

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
    
    public void scaleImage10(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/pokhara.jpg"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(pokharaImage.getWidth(), pokharaImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        pokharaImage.setIcon(scaledIcon);
    }
    public void scaleImage11(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/mustang.jpg"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(mustangImage.getWidth(), mustangImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        mustangImage.setIcon(scaledIcon);
    }
    public void scaleImage12(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/kathmandu.jpg"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(kathmanduImage.getWidth(), kathmanduImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        kathmanduImage.setIcon(scaledIcon);
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
        placePanel = new javax.swing.JPanel();
        pokharaPanelButton = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        pokharaImage = new javax.swing.JLabel();
        mustangPanelButton = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        mustangImage = new javax.swing.JLabel();
        kathmanduPanelButton = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        kathmanduImage = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        placesButton = new javax.swing.JButton();
        reviewsButton = new javax.swing.JButton();
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
        bookingLabel = new javax.swing.JLabel();
        routeLabel = new javax.swing.JLabel();
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

        placePanel.setBackground(new java.awt.Color(200, 143, 75));
        placePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pokharaPanelButton.setBackground(new java.awt.Color(222, 183, 154));
        pokharaPanelButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pokharaPanelButton.setPreferredSize(new java.awt.Dimension(640, 360));
        pokharaPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel19.setText("Pokhara");
        pokharaPanelButton.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 70, -1));

        jLabel23.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel23.setText("Pokhara is a city on Phewa ");
        pokharaPanelButton.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 170, -1));

        jLabel24.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel24.setText("Lake, in central Nepal.");
        pokharaPanelButton.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 139, -1));

        pokharaImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pokhara.jpg"))); // NOI18N
        pokharaPanelButton.add(pokharaImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 190, 180));

        placePanel.add(pokharaPanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 300));

        mustangPanelButton.setBackground(new java.awt.Color(222, 183, 154));
        mustangPanelButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mustangPanelButton.setPreferredSize(new java.awt.Dimension(640, 360));
        mustangPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel11.setText("Mustang");
        mustangPanelButton.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel26.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel26.setText("The district is home to ");
        mustangPanelButton.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel29.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel29.setText("Muktinath Temple.");
        mustangPanelButton.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        mustangImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mustang.jpg"))); // NOI18N
        mustangPanelButton.add(mustangImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 190, 190));

        placePanel.add(mustangPanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 200, 300));

        kathmanduPanelButton.setBackground(new java.awt.Color(222, 183, 154));
        kathmanduPanelButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kathmanduPanelButton.setPreferredSize(new java.awt.Dimension(640, 360));
        kathmanduPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel27.setText("Kathmandu");
        kathmanduPanelButton.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, 20));

        jLabel28.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel28.setText("Kathmandu is best known");
        kathmanduPanelButton.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel30.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel30.setText("For its historical sights.");
        kathmanduPanelButton.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        kathmanduImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/kathmandu.jpg"))); // NOI18N
        kathmanduPanelButton.add(kathmanduImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 190, 190));

        placePanel.add(kathmanduPanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 200, 300));

        jLabel17.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel17.setText("Here are few suggections for you new travel.");
        placePanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));

        TravelerDetailsPanel.add(placePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 680, 410));

        jLabel2.setFont(new java.awt.Font("Candara", 3, 16)); // NOI18N
        jLabel2.setText("Welcome to Happy Travels!");
        TravelerDetailsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jLabel3.setFont(new java.awt.Font("Candara", 3, 16)); // NOI18N
        jLabel3.setText("Lets travel the world together.");
        TravelerDetailsPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        placesButton.setBackground(new java.awt.Color(168, 116, 55));
        placesButton.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        placesButton.setText("Places");
        placesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placesButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(placesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 350, 60));

        reviewsButton.setBackground(new java.awt.Color(168, 116, 55));
        reviewsButton.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        reviewsButton.setText("Reviews");
        reviewsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewsButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(reviewsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, 300, 60));

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

        bookingLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        bookingLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bookingLabel.setText("  Booking");
        DashDetailsPanel.add(bookingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 120, 20));

        routeLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        routeLabel.setText("  Route ");
        DashDetailsPanel.add(routeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 130, 20));

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

    private void placesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placesButtonActionPerformed

    private void reviewsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TravellerdashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel kathmanduImage;
    private javax.swing.JPanel kathmanduPanelButton;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel mustangImage;
    private javax.swing.JPanel mustangPanelButton;
    private javax.swing.JPanel placePanel;
    private javax.swing.JButton placesButton;
    private javax.swing.JLabel pokharaImage;
    private javax.swing.JPanel pokharaPanelButton;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JButton reviewsButton;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel routeLabel;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

    
    public void PlacesNavigation(ActionListener listener){
        placesButton.addActionListener(listener);
    }
    
    public void ReviewsNavigation(ActionListener listener){
        reviewsButton.addActionListener(listener);
    }
    
    public void DashboardNavigation(MouseListener listener){
        dashboardLabel.addMouseListener(listener);
    }
    public JLabel getDashboardlabel(){
        return dashboardLabel;
    }
    public void BookingNavigation(MouseListener listener){
        bookingLabel.addMouseListener(listener);
    }
    public JLabel getBookinglabel(){
        return bookingLabel;
    }
    public void RouteNavigation(MouseListener listener){
        routeLabel.addMouseListener(listener);
    }
    public JLabel getRoutelabel(){
        return routeLabel;
    }
    public void BusTicketsNavigation(MouseListener listener){
        busTicketsLabel.addMouseListener(listener);
    }
    public JLabel getBusTicketslabel(){
        return busTicketsLabel;
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
}