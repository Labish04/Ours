/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.PasswordResetDao;
import happytravell.view.CodeVerificationView;
import happytravell.view.ResetPasswordView;
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.controller.LoginController;
import happytravell.controller.SMTPSMailSender;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class CodeVerificationController {

    private CodeVerificationView codeView;
    private String currentEmail;
    private PasswordResetDao passwordResetDao;
    
    public CodeVerificationController(CodeVerificationView codeView, String currentEmail) {
        this.codeView = codeView;
        this.currentEmail = currentEmail;
        this.passwordResetDao = new PasswordResetDao();
        
        setupEventListeners();
    }
    
    private void setupEventListeners() {
        codeView.addSubmitListener(new SubmitListener());
        codeView.addBackListener(new BackListener());
        codeView.addResendListener(new ResendCodeListener());
    }
    
    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredCode = codeView.getCode();
            
            if (enteredCode == null || enteredCode.trim().isEmpty()) {
                JOptionPane.showMessageDialog(codeView, "Please enter the verification code", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                if (passwordResetDao.verifyCode(currentEmail, enteredCode)) {
                    // Code is valid, navigate to reset password view
                    codeView.dispose();
                    ResetPasswordView resetPasswordView = new ResetPasswordView();
                    ResetPasswordController resetPasswordController = new ResetPasswordController(resetPasswordView, currentEmail);
                    resetPasswordController.open();
                } else {
                    JOptionPane.showMessageDialog(codeView, "Invalid or expired verification code. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(codeView, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            navigateToForgetPassword();
        }
    }
    
    private class ResendCodeListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            resendVerificationCode();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        
        @Override
        public void mouseReleased(MouseEvent e) {}
        
        @Override
        public void mouseEntered(MouseEvent e) {
            codeView.setResendLabelHover(true);
            codeView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            codeView.setResendLabelHover(false);
            codeView.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    private void resendVerificationCode() {
        try {
            String newVerificationCode = generateVerificationCode();
            passwordResetDao.storeVerificationCode(currentEmail, newVerificationCode);
            
            boolean emailSent = SMTPSMailSender.sendVerificationCode(currentEmail, newVerificationCode);
            
            if (emailSent) {
                JOptionPane.showMessageDialog(codeView, "A new verification code has been sent to " + currentEmail, "Code Resent", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(codeView, "Failed to send verification email. Please try again later.", "Email Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(codeView, "An error occurred while resending code: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
    
    private void navigateToForgetPassword() {
        try {
            codeView.dispose();
            ForgetPasswordView forgetPasswordView = new ForgetPasswordView();
            ForgetPasswordController forgetPasswordController = new ForgetPasswordController(forgetPasswordView);
            forgetPasswordController.open();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error navigating to forget password: " + ex.getMessage(), "Navigation Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public void open() {
        codeView.setVisible(true);
    } 
    
    public void close() {
        codeView.dispose();
    }
}