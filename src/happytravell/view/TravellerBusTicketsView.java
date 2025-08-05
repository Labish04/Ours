/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;


import happytravell.model.BookingData;
import happytravell.model.GuideData;
import happytravell.popup.BookingDetailsPopup;
import happytravell.popup.GuideSelectionPopup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class TravellerBusTicketsView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public TravellerBusTicketsView() {
        initComponents();
        JFormattedTextField dep = ((JSpinner.DefaultEditor) departureSpinner.getEditor()).getTextField();
        dep.setBackground(new Color(248,219,164));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) returnSpinner.getEditor()).getTextField();
        tf.setBackground(new Color(248,219,164));
        
       
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
        placePanel = new javax.swing.JPanel();
        phoneNumberTextField = new javax.swing.JTextField();
        dropAddressTextField = new javax.swing.JTextField();
        departureSpinner = new javax.swing.JSpinner();
        returnSpinner = new javax.swing.JSpinner();
        busNumberComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        myTicketsButton = new javax.swing.JButton();
        buyNowButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pickupAddressTextField1 = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        busImageLabel = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        availableSeatsTextField = new javax.swing.JTextField();
        selectedSeatsTextField = new javax.swing.JTextField();
        bookedSeatsTextField = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

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

        TravelerDetailsPanel.setBackground(new java.awt.Color(235, 189, 135));
        TravelerDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        placePanel.setBackground(new java.awt.Color(248, 219, 164));
        placePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        phoneNumberTextField.setBackground(new java.awt.Color(248, 219, 164));
        phoneNumberTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        placePanel.add(phoneNumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 250, 25));

        dropAddressTextField.setBackground(new java.awt.Color(248, 219, 164));
        dropAddressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dropAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropAddressTextFieldActionPerformed(evt);
            }
        });
        placePanel.add(dropAddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 250, 25));

        departureSpinner.setModel(new javax.swing.SpinnerDateModel());
        departureSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        departureSpinner.setFocusCycleRoot(true);
        placePanel.add(departureSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 250, 25));

        returnSpinner.setModel(new javax.swing.SpinnerDateModel());
        returnSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        returnSpinner.setFocusCycleRoot(true);
        placePanel.add(returnSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 250, 25));

        busNumberComboBox.setBackground(new java.awt.Color(248, 219, 164));
        busNumberComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bus Number", " ", " ", " " }));
        busNumberComboBox.setToolTipText("");
        busNumberComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        busNumberComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busNumberComboBoxActionPerformed(evt);
            }
        });
        placePanel.add(busNumberComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 250, 25));
        placePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 10, 20));

        myTicketsButton.setBackground(new java.awt.Color(171, 106, 32));
        myTicketsButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        myTicketsButton.setText("My Tickets");
        myTicketsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTicketsButtonActionPerformed(evt);
            }
        });
        placePanel.add(myTicketsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 160, -1));

        buyNowButton.setBackground(new java.awt.Color(171, 106, 32));
        buyNowButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buyNowButton.setText("Buy Now");
        placePanel.add(buyNowButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 100, -1));

        jLabel3.setText("Pickup Address");
        placePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, -1));

        jLabel4.setText("Drop Address");
        placePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 80, -1));

        jLabel5.setText("Departure Date & Time");
        placePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 140, 30));

        jLabel7.setText("Return Date & Time");
        placePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 120, 30));

        pickupAddressTextField1.setBackground(new java.awt.Color(248, 219, 164));
        pickupAddressTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        placePanel.add(pickupAddressTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 250, 25));

        nameTextField.setBackground(new java.awt.Color(248, 219, 164));
        nameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        placePanel.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 250, 25));

        jLabel9.setText("Name");
        placePanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, -1));

        jLabel10.setText("Phone Number");
        placePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("A2");
        placePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 51));
        jLabel11.setText("A3");
        placePanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 51));
        jLabel12.setText("A5");
        placePanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 0, 0));
        jLabel13.setText("A7");
        placePanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("A9");
        placePanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("A11");
        placePanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 0, 0));
        jLabel16.setText("A13");
        placePanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 51));
        jLabel17.setText("A4");
        placePanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 0, 0));
        jLabel18.setText("A6");
        placePanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("A8");
        placePanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 51));
        jLabel20.setText("A10");
        placePanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 30, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 0));
        jLabel21.setText("A12");
        placePanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("A14");
        placePanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 30, 20));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("A1");
        placePanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 20, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(153, 0, 0));
        jLabel24.setText("B2");
        placePanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 20, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 0, 0));
        jLabel25.setText("B1");
        placePanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 20, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 153, 51));
        jLabel26.setText("B3");
        placePanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 20, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("B5");
        placePanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 20, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 0, 0));
        jLabel28.setText("B7");
        placePanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 20, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("B9");
        placePanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 20, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("B11");
        placePanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 30, 10));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("B13");
        placePanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 30, 10));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("B14");
        placePanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 30, 20));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 0, 0));
        jLabel33.setText("B12");
        placePanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, -1, 20));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 51));
        jLabel34.setText("B10");
        placePanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, -1, 20));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setText("B8");
        placePanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 20, 20));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setText("B6");
        placePanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 20, 20));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 51));
        jLabel37.setText("B4");
        placePanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 20, 20));

        busImageLabel.setText("bus img");
        placePanel.add(busImageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 240, 140));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel39.setText("Selected");
        placePanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, 20));

        availableSeatsTextField.setBackground(new java.awt.Color(0, 0, 0));
        availableSeatsTextField.setForeground(new java.awt.Color(0, 153, 51));
        placePanel.add(availableSeatsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 20, -1));

        selectedSeatsTextField.setBackground(new java.awt.Color(0, 153, 51));
        selectedSeatsTextField.setForeground(new java.awt.Color(0, 153, 51));
        placePanel.add(selectedSeatsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 20, -1));

        bookedSeatsTextField.setBackground(new java.awt.Color(204, 0, 0));
        bookedSeatsTextField.setForeground(new java.awt.Color(0, 153, 51));
        placePanel.add(bookedSeatsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 20, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel40.setText("Booked");
        placePanel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel41.setText("Available");
        placePanel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, -1, -1));

        TravelerDetailsPanel.add(placePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 680, 450));

        jLabel2.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel2.setText("Tickets Details");
        TravelerDetailsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 30));

        getContentPane().add(TravelerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 700, 560));

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel1.setText("Bus Tickets");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 130, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 700, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void busNumberComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busNumberComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busNumberComboBoxActionPerformed

    private void myTicketsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTicketsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTicketsButtonActionPerformed

    private void dropAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropAddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropAddressTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(TravellerBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerBusTicketsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TravellerBusTicketsView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JTextField availableSeatsTextField;
    private javax.swing.JTextField bookedSeatsTextField;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busImageLabel;
    private javax.swing.JComboBox<String> busNumberComboBox;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JButton buyNowButton;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JSpinner departureSpinner;
    private javax.swing.JTextField dropAddressTextField;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JButton myTicketsButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JTextField pickupAddressTextField1;
    private javax.swing.JPanel placePanel;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JSpinner returnSpinner;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel routeLabel;
    private javax.swing.JTextField selectedSeatsTextField;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

    
    
    

    
    


    public JLabel getBusImageLabel() {
        return busImageLabel;
    }
    
    
    

    public void BuyNowButtonListener(ActionListener listener) {
        buyNowButton.addActionListener(listener);
    }
    
    public void MyTicketsListener(ActionListener listener) {
        myTicketsButton.addActionListener(listener);
    }

//    public JButton getMyTicketsButton(){
//        return myTicketsButton;
//    }


    public void DashboardNavigation(java.awt.event.MouseListener listener) {
        dashboardLabel.addMouseListener(listener);
    }

    public void RouteNavigation(java.awt.event.MouseListener listener) {
        routeLabel.addMouseListener(listener);
    }
    

    public void BookingNavigation(java.awt.event.MouseListener listener) {
        bookingLabel.addMouseListener(listener);
    }

    public void VehiclesDetailsNavigation(java.awt.event.MouseListener listener) {
        vehiclesDetailsLabel.addMouseListener(listener);
    }

    public void ProfileNavigation(java.awt.event.MouseListener listener) {
        profileLabel.addMouseListener(listener);
    }

    public void LogOutNavigation(java.awt.event.MouseListener listener) {
        logOutLabel.addMouseListener(listener);
    }

    public JLabel getDashboardLabel() {
        return dashboardLabel;
    }

    public JLabel getRouteDetailsLabel() {
        return routeLabel;
    }

    public JLabel getBusDetailsLabel() {
        return busTicketsLabel;
    }

    public JLabel getVehiclesDetailsLabel() {
        return vehiclesDetailsLabel;
    }

    public JLabel getProfileLabel() {
        return profileLabel;
    }

    public JLabel getLogOutLabel() {
        return logOutLabel;
    }
    
    
    
    public JComboBox getBusNumberComboBox(){
        return busNumberComboBox;
    }
    
    public JTextField getNameTextField() {
        return phoneNumberTextField;
    }
    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }
    
   
    public JTextField getPickupAddressTextField() {
        return phoneNumberTextField;
    }
    
    public JTextField getSelectedSeatsTextField() {
        return selectedSeatsTextField;
    }
    
    public JTextField getBookedSeatsTextField() {
        return bookedSeatsTextField;
    }
    
    public JTextField getAvailableTextField() {
        return availableSeatsTextField;
    }
    
    
    public JTextField getDropAddressTextField() {
        return dropAddressTextField;
    }
    
    public JSpinner getReturnDateTimeSpinner() {
        return returnSpinner;
    }
    
    public JSpinner getDepartureDateTimeSpinner() {
        return departureSpinner;
    }
    
    // Add these methods to your view class
    public JLabel getSeatA1() {
        return jLabel23; 
    }
    public JLabel getSeatA2() { 
        return jLabel8; 
    }
    public JLabel getSeatA3() {
        return jLabel11; 
    }
    public JLabel getSeatA4() {
        return jLabel17;
    }
    public JLabel getSeatA5() { 
        return jLabel12;
    }
    public JLabel getSeatA6() {
        return jLabel18;
    }
    public JLabel getSeatA7() {
        return jLabel13; 
    }
    public JLabel getSeatA8() { 
        return jLabel19; 
    }
    public JLabel getSeatA9() { 
        return jLabel14;
    }
    public JLabel getSeatA10() { 
        return jLabel20; 
    }
    public JLabel getSeatA11() { 
        return jLabel15; 
    }
    public JLabel getSeatA12() { 
        return jLabel21; 
    }
    public JLabel getSeatA13() { 
        return jLabel16; 
    }
    public JLabel getSeatA14() {
        return jLabel22;
    }

    public JLabel getSeatB1() { 
        return jLabel25;
    }
    public JLabel getSeatB2() {
        return jLabel24;
    }
    public JLabel getSeatB3() {
        return jLabel26;
    }
    public JLabel getSeatB4() { 
        return jLabel37; 
    }
    public JLabel getSeatB5() {
        return jLabel27;
    }
    public JLabel getSeatB6() { 
        return jLabel36;
    }
    public JLabel getSeatB7() {
        return jLabel28;
    }
    public JLabel getSeatB8() {
        return jLabel35; 
    }
    public JLabel getSeatB9() { 
        return jLabel29;
    }
    public JLabel getSeatB10() { 
        return jLabel34; 
    }
    public JLabel getSeatB11() { 
        return jLabel30; 
    }
    public JLabel getSeatB12() { 
        return jLabel33;
    }
    public JLabel getSeatB13() { 
        return jLabel31; 
    }
    public JLabel getSeatB14() {
        return jLabel32; 
    }

    
   public JButton getMyTicketsButton(){
    return myTicketsButton;
}

public void MyTicketsButtonListener(ActionListener listener) {
    myTicketsButton.addActionListener(listener);
}
//
//// Add this to your AdminBusTicketsView class to connect the button
//public void setMyTicketsButtonListener(ActionListener listener) {
//    
//}

}


    
    

