/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.VerificationCode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Acer
 */
public class PasswordResetDao {
    
    private static final Map<String, VerificationCode> verificationCodes = new HashMap<>();
    private static final long CODE_EXPIRATION_MINUTES = 15;
    
    private final MysqlConnection mysql = new MysqlConnection();
    
    public PasswordResetDao() {
        // Create the verification_codes table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS verification_codes ("
                + "email VARCHAR(255) PRIMARY KEY,"
                + "code VARCHAR(10) NOT NULL,"
                + "expiration_time BIGINT NOT NULL"
                + ")";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(createTableQuery)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean emailExists(String email) {
        Connection conn = mysql.openConnection();
        String query = "SELECT 1 FROM (SELECT email FROM admin UNION SELECT email FROM traveller) AS users WHERE email = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public void storeVerificationCode(String email, String code) {
        long expirationTime = System.currentTimeMillis() + (15 * 60 * 1000); // 15 minutes
        String query = "REPLACE INTO verification_codes (email, code, expiration_time) VALUES (?, ?, ?)";
        
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, code);
            stmt.setLong(3, expirationTime);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean verifyCode(String email, String code) {
        String query = "SELECT code, expiration_time FROM verification_codes WHERE email = ?";
        
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedCode = rs.getString("code");
                    long expirationTime = rs.getLong("expiration_time");
                    
                    if (System.currentTimeMillis() > expirationTime) {
                        // Code has expired
                        return false;
                    }
                    
                    return storedCode.equals(code);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean resetPassword(String email, String newPassword) {
        Connection conn = mysql.openConnection();
        boolean success = false;
        
        try {
            // Try updating admin table first
            String adminQuery = "UPDATE admin SET password = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(adminQuery)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, email);
                success = stmt.executeUpdate() > 0;
            }
            
            // If not found in admin, try traveller table
            if (!success) {
                String travellerQuery = "UPDATE traveller SET password = ? WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(travellerQuery)) {
                    stmt.setString(1, newPassword);
                    stmt.setString(2, email);
                    success = stmt.executeUpdate() > 0;
                }
            }
            
            // If successful, remove the verification code
            if (success) {
                String deleteQuery = "DELETE FROM verification_codes WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
                    stmt.setString(1, email);
                    stmt.executeUpdate();
                }
            }
            
            return success;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}