/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import happytravell.UI.AdminBookingDetailsCardPanel;
import happytravell.controller.AdminBookingDetailsController;
import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class TravellerProfileView extends javax.swing.JFrame {
    File selectedProfileFile;
    /**
     * Creates new form AdmindashboardView
     */
    public TravellerProfileView() {
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
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/addImage.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(profileAddIcon.getWidth(), profileAddIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        profileAddIcon.setIcon(scaledIcon);
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
        ProfilePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        travellerProfile = new javax.swing.JLabel();
        travellerNameLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        usernamelabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        phoneNumberLabel = new javax.swing.JLabel();
        phoneNumberTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        updateProfileButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        profileAddIcon = new javax.swing.JLabel();
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

        ProfilePanel.setBackground(new java.awt.Color(248, 206, 157));
        ProfilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Constantia", 1, 18)); // NOI18N
        jLabel1.setText("Profile");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 150, 50));

        ProfilePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 50));

        jPanel3.setBackground(new java.awt.Color(251, 245, 205));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        travellerProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(travellerProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 90, 80));

        travellerNameLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        travellerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        travellerNameLabel.setText("Traveller Name");
        jPanel3.add(travellerNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 170, 20));

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        firstNameLabel.setText("First Name");
        jPanel3.add(firstNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 70, 20));

        firstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(firstNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 150, 30));

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lastNameLabel.setText("Last Name");
        jPanel3.add(lastNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 70, 20));

        lastNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(lastNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 150, 30));

        usernamelabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernamelabel.setText("Username");
        jPanel3.add(usernamelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 80, 20));

        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(usernameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 162, 150, 30));

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNumberLabel.setText("Phone Number");
        jPanel3.add(phoneNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, 20));

        phoneNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(phoneNumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 162, 150, 30));

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailLabel.setText("Email");
        jPanel3.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 40, 20));

        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 232, 150, 30));

        addressLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addressLabel.setText("Address");
        jPanel3.add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 70, 20));

        addressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(addressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 233, 150, 30));

        updateProfileButton.setBackground(new java.awt.Color(241, 171, 89));
        updateProfileButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        updateProfileButton.setText("Update Profile");
        updateProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileButtonActionPerformed(evt);
            }
        });
        jPanel3.add(updateProfileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 150, 30));

        nextButton.setBackground(new java.awt.Color(241, 171, 89));
        nextButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nextButton.setText("Next");
        jPanel3.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 297, 80, 30));

        profileAddIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/addImage.png"))); // NOI18N
        jPanel3.add(profileAddIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 135, 25, 25));

        ProfilePanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 640, 380));

        getContentPane().add(ProfilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 700, 600));

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
        bookingLabel.setText("  Booking ");
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

    private void firstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTextFieldActionPerformed

    private void lastNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameTextFieldActionPerformed

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void phoneNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTextFieldActionPerformed

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void addressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextFieldActionPerformed

    private void updateProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProfileButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TravellerProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
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
                new TravellerProfileView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JLabel profileAddIcon;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel routeLabel;
    private javax.swing.JLabel travellerNameLabel;
    private javax.swing.JLabel travellerProfile;
    private javax.swing.JButton updateProfileButton;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JLabel usernamelabel;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

   

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
    
    public JTextField getFirstNameTextField() {
        return firstNameTextField;
    }
    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }
    public JTextField getAddressTextField() {
        return addressTextField;
    }
    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }
    public JTextField getEmailTextField() {
        return emailTextField;
    }
    
    public void setUpdateProfileButtonAction(ActionListener listener) {
        updateProfileButton.addActionListener(listener);
    }
    
    public void uploadProfilePicture(MouseListener listener){
        profileAddIcon.addMouseListener(listener);
    }
    
    public JLabel getUploadProfile(){
        return profileAddIcon;
    }
    
    public void selectedProfileFile(File file){
        this.selectedProfileFile = file;
    }
    public void setDefaultProfileImage() {
        try {
            ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/path/to/default-profile.png"));

            if (defaultIcon.getIconWidth() == -1) {
                travellerProfile.setText("No Image");
                travellerProfile.setIcon(null);
            } else {
                Image scaledImage = defaultIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                travellerProfile.setIcon(new ImageIcon(scaledImage));
                travellerProfile.setText("");
            }
        } catch (Exception e) {
            travellerProfile.setText("No Image");
            travellerProfile.setIcon(null);
        }
    }
    public void displayProfileImage(byte[] imageData) {
        try {
            if (imageData != null && imageData.length > 0) {
                ImageIcon originalIcon = new ImageIcon(imageData);
                Image scaledImage = originalIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                travellerProfile.setIcon(scaledIcon);
                travellerProfile.setText("");
            } else {
                setDefaultProfileImage();
            }
        } catch (Exception e) {
            setDefaultProfileImage();
        }
    }

    public JLabel geTravellerName(){
        return travellerNameLabel;
    }
 
    public void NextNavigation(ActionListener listener){
        nextButton.addActionListener(listener);
    }
    
}