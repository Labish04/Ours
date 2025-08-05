/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.PasswordResetDao;
import happytravell.view.ResetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.controller.LoginController;
import happytravell.controller.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ResetPasswordController {

    private ResetPasswordView resetPasswordView;
    private String currentEmail;
    private PasswordResetDao passwordResetDao;
    
    public ResetPasswordController(ResetPasswordView resetPasswordView, String currentEmail) {
        this.resetPasswordView = resetPasswordView;
        this.currentEmail = currentEmail;
        this.passwordResetDao = new PasswordResetDao();
        
        setupEventListeners();
    }
    
    private void setupEventListeners() {
        resetPasswordView.addResetListener(new ResetListener());
        resetPasswordView.addBackListener(new BackListener());
    }
    
    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = resetPasswordView.getNewPassword();
            String confirmPassword = resetPasswordView.getConfirmPassword();
            
            // Input validation
            if (newPassword == null || newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(resetPasswordView, "Please enter a new password", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(resetPasswordView, "Please confirm your new password", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(resetPasswordView, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(resetPasswordView, "Password must be at least 6 characters long", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                boolean success = passwordResetDao.resetPassword(currentEmail, newPassword);
                
                if (success) {
                    JOptionPane.showMessageDialog(resetPasswordView, "Password has been reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    navigateToLogin();
                } else {
                    JOptionPane.showMessageDialog(resetPasswordView, "Failed to reset password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(resetPasswordView, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            navigateToLogin();
        }
    }
    
    private void navigateToLogin() {
        try {
            resetPasswordView.dispose();
            LoginPageView loginView = new LoginPageView();
            LoginController loginController = new LoginController(loginView);
            loginController.open();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error navigating to login: " + ex.getMessage(), "Navigation Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public void open() {
        resetPasswordView.setVisible(true);
    }
    
    public void close() {
        resetPasswordView.dispose();
    }
}