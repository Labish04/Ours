/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPSMailSender {
    private static final String host = "smtp.gmail.com";
    private static final String port = "587"; 
    private static final String email = "bbhawana131@gmail.com";
    private static final String password = "peqp gcsq kubd rpej";
    
    // Send Email Method
    public static boolean sendMail(String recipient, String subject, String body) {
        // Set up properties for the email session
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);  
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");  // Enable STARTTLS
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Forces TLSv1.2
        
        // Create a session with the properties
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
        
        try {
            // Create the message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
            
            // Send the message
            Transport.send(message);
            System.out.println("Mail sent successfully! " + body);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }
    
    // Method specifically for sending verification codes (as used in ForgetPasswordController)
    public static boolean sendVerificationCode(String recipient, String verificationCode) {
        String subject = "Password Reset Verification Code";
        String body = "Your verification code is: " + verificationCode + 
                     "\n\nThis code will expire in 10 minutes." +
                     "\n\nIf you didn't request this, please ignore this email.";
        
        return sendMail(recipient, subject, body);
    }
}