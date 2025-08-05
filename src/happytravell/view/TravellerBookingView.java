/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;


import happytravell.model.BookingData;
import happytravell.model.GuideData;
import happytravell.model.VehiclesData;
import happytravell.popup.BookingDetailsPopup;
import happytravell.popup.GuideSelectionPopup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class TravellerBookingView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public TravellerBookingView() {
        initComponents();
        JFormattedTextField num = ((JSpinner.DefaultEditor) numberSpinner.getEditor()).getTextField();
        num.setBackground(new Color(248,219,164));
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
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/car.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(carIcon.getWidth(), carIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        carIcon.setIcon(scaledIcon);
    }
    public void scaleImage11(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/jeep .png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(jeepIcon.getWidth(), jeepIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jeepIcon.setIcon(scaledIcon);
    }
    public void scaleImage12(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/taxi.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(taxiIcon.getWidth(), taxiIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        taxiIcon.setIcon(scaledIcon);
    }
    public void scaleImage13(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/guide.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(guideIcon.getWidth(), guideIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        guideIcon.setIcon(scaledIcon);
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
        pickupAddressTextField = new javax.swing.JTextField();
        dropAddressTextField = new javax.swing.JTextField();
        departureSpinner = new javax.swing.JSpinner();
        returnSpinner = new javax.swing.JSpinner();
        numberSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        driverNameComboBox = new javax.swing.JComboBox<>();
        paymentMethodComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        bookingDetailsButton = new javax.swing.JButton();
        bookButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        carIcon = new javax.swing.JLabel();
        carButton = new javax.swing.JButton();
        jeepIcon = new javax.swing.JLabel();
        jeepButton = new javax.swing.JButton();
        taxiIcon = new javax.swing.JLabel();
        taxiButton = new javax.swing.JButton();
        guideIcon = new javax.swing.JLabel();
        guideButton = new javax.swing.JButton();
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

        pickupAddressTextField.setBackground(new java.awt.Color(248, 219, 164));
        pickupAddressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        placePanel.add(pickupAddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 250, 25));

        dropAddressTextField.setBackground(new java.awt.Color(248, 219, 164));
        dropAddressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dropAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropAddressTextFieldActionPerformed(evt);
            }
        });
        placePanel.add(dropAddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 250, 25));

        departureSpinner.setModel(new javax.swing.SpinnerDateModel());
        departureSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        departureSpinner.setFocusCycleRoot(true);
        placePanel.add(departureSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 250, 25));

        returnSpinner.setModel(new javax.swing.SpinnerDateModel());
        returnSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        returnSpinner.setFocusCycleRoot(true);
        placePanel.add(returnSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 25));

        numberSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        numberSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        numberSpinner.setFocusCycleRoot(true);
        placePanel.add(numberSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 80, 30));

        jLabel8.setText("Number of Passenger");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        placePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 250, 30));

        driverNameComboBox.setBackground(new java.awt.Color(248, 219, 164));
        driverNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Driver name" }));
        driverNameComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        driverNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driverNameComboBoxActionPerformed(evt);
            }
        });
        placePanel.add(driverNameComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 250, 25));

        paymentMethodComboBox.setBackground(new java.awt.Color(248, 219, 164));
        paymentMethodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Payment Method", "Cash ", "E-sewa", " " }));
        paymentMethodComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        paymentMethodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMethodComboBoxActionPerformed(evt);
            }
        });
        placePanel.add(paymentMethodComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 250, 25));
        placePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 10, 20));

        bookingDetailsButton.setBackground(new java.awt.Color(171, 106, 32));
        bookingDetailsButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        bookingDetailsButton.setText("Booking Details");
        bookingDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingDetailsButtonActionPerformed(evt);
            }
        });
        placePanel.add(bookingDetailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 160, -1));

        bookButton.setBackground(new java.awt.Color(171, 106, 32));
        bookButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bookButton.setText(" Book");
        placePanel.add(bookButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, 80, -1));

        jScrollPane1.setBackground(new java.awt.Color(252, 186, 107));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTabbedPane1.setBackground(new java.awt.Color(252, 186, 107));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("Car", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("Jeep", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("Taxi", jPanel5);

        jScrollPane1.setViewportView(jTabbedPane1);

        placePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 330, 250));

        jLabel3.setText("Pickup Address");
        placePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, -1));

        jLabel4.setText("Drop Address");
        placePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 80, -1));

        jLabel5.setText("Departure Date & Time");
        placePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, -1));

        jLabel7.setText("Return Date & Time");
        placePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, -1));

        TravelerDetailsPanel.add(placePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 680, 430));

        jLabel2.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel2.setText("Choose Service");
        TravelerDetailsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 30));

        carIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/car_1.png"))); // NOI18N
        TravelerDetailsPanel.add(carIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 40, 30));

        carButton.setBackground(new java.awt.Color(171, 106, 32));
        carButton.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        carButton.setText("              Car");
        carButton.setBorder(null);
        carButton.setFocusable(false);
        carButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(carButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 120, 35));

        jeepIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/jeep  (1).png"))); // NOI18N
        TravelerDetailsPanel.add(jeepIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 30, 27));

        jeepButton.setBackground(new java.awt.Color(171, 106, 32));
        jeepButton.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jeepButton.setText("            Jeep");
        jeepButton.setBorder(null);
        jeepButton.setHideActionText(true);
        jeepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jeepButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(jeepButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 120, 35));

        taxiIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/taxi (1).png"))); // NOI18N
        TravelerDetailsPanel.add(taxiIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 43, 30, 25));

        taxiButton.setBackground(new java.awt.Color(171, 106, 32));
        taxiButton.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        taxiButton.setText("       Taxi");
        taxiButton.setBorder(null);
        taxiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxiButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(taxiButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 120, 35));

        guideIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/guide (3).png"))); // NOI18N
        TravelerDetailsPanel.add(guideIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 42, 30, 25));

        guideButton.setBackground(new java.awt.Color(171, 106, 32));
        guideButton.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        guideButton.setText("           Guide");
        guideButton.setBorder(null);
        guideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guideButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(guideButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 120, 35));

        getContentPane().add(TravelerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 700, 550));

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
        jLabel1.setText("Booking");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 80, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 700, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carButtonActionPerformed

    private void jeepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jeepButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jeepButtonActionPerformed

    private void taxiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxiButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxiButtonActionPerformed

    private void guideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guideButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guideButtonActionPerformed

    private void driverNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverNameComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_driverNameComboBoxActionPerformed

    private void paymentMethodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentMethodComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentMethodComboBoxActionPerformed

    private void bookingDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingDetailsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingDetailsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TravellerBookingView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JButton bookButton;
    private javax.swing.JButton bookingDetailsButton;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JButton carButton;
    private javax.swing.JLabel carIcon;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JSpinner departureSpinner;
    private javax.swing.JComboBox<String> driverNameComboBox;
    private javax.swing.JTextField dropAddressTextField;
    private javax.swing.JButton guideButton;
    private javax.swing.JLabel guideIcon;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jeepButton;
    private javax.swing.JLabel jeepIcon;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JSpinner numberSpinner;
    private javax.swing.JComboBox<String> paymentMethodComboBox;
    private javax.swing.JTextField pickupAddressTextField;
    private javax.swing.JPanel placePanel;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JSpinner returnSpinner;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel routeLabel;
    private javax.swing.JButton taxiButton;
    private javax.swing.JLabel taxiIcon;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

    
    public void GuideNavigation(ActionListener listener){
        guideButton.addActionListener(listener);
    }

    public void addCarButtonListener(ActionListener listener) {
        carButton.addActionListener(listener);
    }

    public void addJeepButtonListener(ActionListener listener) {
        jeepButton.addActionListener(listener);
    }

    public void addTaxiButtonListener(ActionListener listener) {
        taxiButton.addActionListener(listener);
    }

    public void addGuideButtonListener(ActionListener listener) {
        guideButton.addActionListener(listener);
    }

    public void addBookButtonListener(ActionListener listener) {
        bookButton.addActionListener(listener);
    }

//    public void addBookingDetailsButtonListener(ActionListener listener) {
//        bookingDetailsButton.addActionListener(listener);
//    }

    public void DashboardNavigation(java.awt.event.MouseListener listener) {
        dashboardLabel.addMouseListener(listener);
    }

    public void RouteDetailsNavigation(java.awt.event.MouseListener listener) {
        routeLabel.addMouseListener(listener);
    }

    public void BusDetailsNavigation(java.awt.event.MouseListener listener) {
        busTicketsLabel.addMouseListener(listener);
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
    
    public JComboBox getDriverNameComboBox(){
        return driverNameComboBox;
    }
    
  
    
    public JComboBox getPaymentMethodComboBox(){
        return paymentMethodComboBox;
    }
   
    public JTextField getPickUpAddressTextField() {
        return pickupAddressTextField;
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
    
    public JSpinner getPassengerNumberSpinner() {
        return numberSpinner;
    }
    


// Add these methods to your TravellerBookingView class

public void populateCarTab(List<BookingData.VehicleInfo> cars) {
    jPanel3.removeAll(); // Clear existing components
    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS)); // Change layout
    
    for (BookingData.VehicleInfo car : cars) {
        JPanel vehiclePanel = createVehiclePanel(car);
        jPanel3.add(vehiclePanel);
        jPanel3.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
    }
    
    jPanel3.revalidate();
    jPanel3.repaint();
}

public void populateJeepTab(List<BookingData.VehicleInfo> jeeps) {
    jPanel4.removeAll(); // Clear existing components
    jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.Y_AXIS)); // Change layout
    
    for (BookingData.VehicleInfo jeep : jeeps) {
        JPanel vehiclePanel = createVehiclePanel(jeep);
        jPanel4.add(vehiclePanel);
        jPanel4.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
    }
    
    jPanel4.revalidate();
    jPanel4.repaint();
}

public void populateTaxiTab(List<BookingData.VehicleInfo> taxis) {
    jPanel5.removeAll(); // Clear existing components
    jPanel5.setLayout(new BoxLayout(jPanel5, BoxLayout.Y_AXIS)); // Change layout
    
    for (BookingData.VehicleInfo taxi : taxis) {
        JPanel vehiclePanel = createVehiclePanel(taxi);
        jPanel5.add(vehiclePanel);
        jPanel5.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
    }
    
    jPanel5.revalidate();
    jPanel5.repaint();
}

private JPanel createVehiclePanel(BookingData.VehicleInfo vehicle) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(10, 10));
    panel.setBackground(new Color(248, 219, 164));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setPreferredSize(new Dimension(300, 8));

    // Left panel for image
    JPanel leftPanel = new JPanel();
    leftPanel.setBackground(new Color(248, 219, 164));
    leftPanel.setPreferredSize(new Dimension(100, 100));
    
    JLabel imageLabel = new JLabel();
    if (vehicle.getVehicleImage() != null) {
        ImageIcon icon = new ImageIcon(vehicle.getVehicleImage());
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    } else {
        imageLabel.setIcon(new ImageIcon(getClass().getResource("/image/default_vehicle.png")));
    }
    leftPanel.add(imageLabel);
    panel.add(leftPanel, BorderLayout.WEST);

// Right panel alternative using BoxLayout
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setBackground(new Color(248, 219, 164));

    JLabel numberLabel = new JLabel("Number: " + vehicle.getVehicleNumber());
    numberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    numberLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // No vertical padding

    JLabel seatsLabel = new JLabel("Seats: " + vehicle.getNumberOfSeats());
    seatsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    seatsLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

    JLabel agencyLabel = new JLabel("Agency: " + vehicle.getTravelAgency());
    agencyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    agencyLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

    rightPanel.add(numberLabel);
    rightPanel.add(seatsLabel);
    rightPanel.add(agencyLabel);
    
    panel.add(rightPanel, BorderLayout.CENTER);

    return panel;
}

    public JTabbedPane getTabbedPane() {
    return jTabbedPane1;
    
} 
    

    
    
// Add this with your other variable declarations at the top of the class
private GuideData selectedGuide;

// Add these getter and setter methods
public GuideData getSelectedGuide() {
    return selectedGuide;
}

public void setSelectedGuide(GuideData guide) {
    this.selectedGuide = guide;
}
    
    


//  showGuideSelectionPopup method with this:
public GuideData showGuideSelectionPopup(List<GuideData> guides) {
    GuideSelectionPopup popup = new GuideSelectionPopup(this, guides);
    return popup.showDialog();
}


private void guideButtonAcctionPerformed(java.awt.event.ActionEvent evt) {                                           
    
}


 


public void addBookingDetailsListener(ActionListener listener) {
    bookingDetailsButton.addActionListener(listener);
}

public void showBookingDetailsPopup(BookingData booking) {
    BookingDetailsPopup popup = new BookingDetailsPopup(this, booking);
    popup.setVisible(true);
}

// Alternative method for multiple bookings:
public void showBookingDetailsPopup(List<BookingData> bookings) {
    BookingDetailsPopup popup = new BookingDetailsPopup(this, bookings);
    popup.setVisible(true);
}

}


    
    

