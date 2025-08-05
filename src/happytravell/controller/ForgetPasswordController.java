

package happytravell.controller;

import happytravell.dao.PasswordResetDao;
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.view.CodeVerificationView;
import happytravell.controller.LoginController;
//import happytravell.controller.CodeVerificationController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ForgetPasswordController {
    private final ForgetPasswordView view;
    private final PasswordResetDao passwordResetDao;
    private String currentEmail;
    
    public ForgetPasswordController(ForgetPasswordView view) {
        this.view = view;
        this.passwordResetDao = new PasswordResetDao();
        
        setupEventListeners();
    }
    
    private void setupEventListeners() {
        view.addResetListener(new ContinueListener());
//        view.addBackListener(new BackListener());
    }
    
    private class ContinueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentEmail = view.getEmail();
            
            // Input validation
            if (currentEmail == null || currentEmail.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your email address", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Email format validation
            if (!isValidEmail(currentEmail)) {
                JOptionPane.showMessageDialog(view, "Please enter a valid email address", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                if (!passwordResetDao.emailExists(currentEmail)) {
                    JOptionPane.showMessageDialog(view, "Email not found in our system", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String verificationCode = generateVerificationCode();
                passwordResetDao.storeVerificationCode(currentEmail, verificationCode);

                // Send the verification code via email
                boolean emailSent = SMTPSMailSender.sendVerificationCode(currentEmail, verificationCode);

                if (emailSent) {
                    JOptionPane.showMessageDialog(view, "A verification code has been sent to " + currentEmail, "Email Sent", JOptionPane.INFORMATION_MESSAGE);
                    // Close the current view and open the code verification view
                    view.dispose();
                    CodeVerificationView codeView = new CodeVerificationView();
                    new CodeVerificationController(codeView, currentEmail).open();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to send verification email. Please try again later.", "Email Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // For debugging
            }
        }
        
        private String generateVerificationCode() {
            Random random = new Random();
            return String.format("%06d", random.nextInt(1000000)); // Fixed: should be 1000000 not 999999 for 6 digits
        }
        
        private boolean isValidEmail(String email) {
            // Basic email validation
            return email.contains("@") && email.contains(".") && email.length() > 5;
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
            view.dispose();
            LoginPageView loginView = new LoginPageView();
            new LoginController(loginView).open();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error navigating to login: " + ex.getMessage(), "Navigation Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public void open() {
        view.setVisible(true);
    }
}