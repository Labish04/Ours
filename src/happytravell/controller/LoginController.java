/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import happytravell.dao.AdminDao;
import happytravell.dao.TravellerDao;
import happytravell.model.AdminData;
import happytravell.model.LoginRequest;
import happytravell.model.TravellerData;
import happytravell.view.TravellerdashboardView;
import happytravell.view.AdmindashboardView;
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.view.SignupAsView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
/**
 *
 * @author Acer
 */
public class LoginController {
    private LoginPageView loginView = new LoginPageView();

    private boolean isPasswordVisible = false;
    public LoginController(LoginPageView view){
        this.loginView = view;
       
        this.loginView.LoginUser(new LoginUser());
        this.loginView.signUpNavigation(new SignUpNav(loginView.getSignupLabel()));
        this.loginView.ForgetPasswordNavigation(new ForgetPasswordNav(loginView.getForgetPasswordLabel()));
        
        this.loginView.TogglePasswordVisibility(new TogglePasswordVisibility());
    }
    public void open(){
        this.loginView.setVisible(true);
    }
    public void close(){
        this.loginView.dispose();
    }
    class SignUpNav implements MouseListener{
        
        private JLabel signUpLabel;
        
        public SignUpNav(JLabel label){
            this.signUpLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            SignupAsView signupAsView = new SignupAsView();
            SignupAsController signupAsController= new SignupAsController(signupAsView);
            signupAsController.open();
            close();
        } 
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            signUpLabel.setForeground(Color.BLUE);
            signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            signUpLabel.setForeground(Color.BLACK);
            signUpLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    class ForgetPasswordNav implements MouseListener{
        
        private JLabel forgetPasswordLabel;
        
        public ForgetPasswordNav(JLabel label){
            this.forgetPasswordLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            ForgetPasswordView forgetPasswordView = new ForgetPasswordView();
            ForgetPasswordController forgetPasswordController= new ForgetPasswordController(forgetPasswordView);
            forgetPasswordController.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            forgetPasswordLabel.setForeground(Color.BLUE);
            forgetPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            forgetPasswordLabel.setForeground(Color.BLACK);
            forgetPasswordLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    



   class TogglePasswordVisibility implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPasswordVisible) {
            loginView.getPasswordField().setEchoChar('•'); // or '*'
            loginView.getShowButton().setText("Show");
        } else {
            loginView.getPasswordField().setEchoChar((char) 0); // show password
            loginView.getShowButton().setText("Hide");
        }
        isPasswordVisible = !isPasswordVisible;
    }
}
    class LoginUser implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
         String email = loginView.getEmailTextField().getText();            
         String password = String.valueOf(loginView.getPasswordField().getPassword());
         if (email.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(loginView, "Fill in all the fields");
            return;
            }
         LoginRequest loginRequest = new LoginRequest(email,password); 
         Object authenticatedUser = authenticateUser(loginRequest);
            if (authenticatedUser == null) {
                JOptionPane.showMessageDialog(loginView, 
                    "Incorrect username or password. Please try again!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String userType = getUserType(authenticatedUser);
                JOptionPane.showMessageDialog(loginView, 
                    "Logged in successfully as " + userType);
                // Navigate to appropriate dashboard based on user type
                navigateToUserDashboard(authenticatedUser, userType);
            }
        }
        
        private Object authenticateUser(LoginRequest loginRequest) {
            // Try admin first
            AdminDao adminDao = new AdminDao();
            AdminData admin= adminDao.adminLogin(loginRequest);
            if (admin != null) {
                return admin;
            }
            
            
            
            // Try traveller last
            TravellerDao travellerDao = new TravellerDao();
            TravellerData traveller = travellerDao.travellerLogin(loginRequest);
            if (traveller != null) {
                return traveller;
            }
            
            // No user found
            return null;
        }
        
        private String getUserType(Object user) {
            if (user instanceof AdminData) {
                return "Admin";
            
            } else if (user instanceof TravellerData) {
                return "Traveller";
            }
            return "Unknown";
        }
        
        
        
        private void navigateToUserDashboard(Object user, String userType) {
            // Close current login view
            close();
            
            // Navigate based on user type
            switch (userType) {
                case "Admin":
                    AdminData admin = (AdminData) user;
                    // Navigate to Admin dashboard Page
                    AdmindashboardView adminDashboardView = new AdmindashboardView();
                    AdminDashboardController admindashboardController = new AdminDashboardController(adminDashboardView,admin.getId());
                    admindashboardController.open();
                    break;
                    
               
                case "Traveller":
                    TravellerData traveller = (TravellerData) user;
                    // Navigate to traveller database Page
                    TravellerdashboardView travellerDashboardView = new TravellerdashboardView();
                    TravellerDashboardController tarvellerdashboardController = new TravellerDashboardController(travellerDashboardView, traveller.getTravellerID());
                    tarvellerdashboardController.open();
                    break;
                    
                    
                    
                   
                    
                default:
                    JOptionPane.showMessageDialog(loginView, "Unknown user type");
                    break;
            
             }
        
        }
    }
    
}
