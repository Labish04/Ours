/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.AdminData;
import happytravell.model.LoginRequest;
import happytravell.model.ResetPasswordRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */
public class AdminDao {

    private static final String ADMIN_TABLE = "admin";

    MysqlConnection mysql = new MysqlConnection();

    // SQL queries
    private static final String CREATE_ADMIN_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + ADMIN_TABLE + "("
        + "admin_ID INT AUTO_INCREMENT PRIMARY KEY,"
        + "first_name VARCHAR(100) NOT NULL,"
        + "last_name VARCHAR(100) NOT NULL,"
        + "username VARCHAR(100) NOT NULL,"
        + "phone_number VARCHAR(15) NOT NULL,"
        + "address VARCHAR(100) NOT NULL,"
        + "email VARCHAR(100) UNIQUE NOT NULL,"
        + "password VARCHAR(100) NOT NULL,"
        + "profile_picture MEDIUMBLOB"
        + ")";

    private static final String INSERT_ADMIN = 
        "INSERT INTO " + ADMIN_TABLE + " (first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String LOGIN_QUERY = 
        "SELECT admin_ID, first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture "
        + "FROM " + ADMIN_TABLE + " WHERE email=? AND password=?";

    private static final String CHECK_EMAIL_QUERY = 
        "SELECT 1 FROM " + ADMIN_TABLE + " WHERE email=?";

    private static final String RESET_PASSWORD_QUERY = 
        "UPDATE " + ADMIN_TABLE + " SET password=? WHERE email=?";

    private static final String GET_ADMIN_BY_ID = 
        "SELECT admin_ID, first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture "
        + "FROM " + ADMIN_TABLE + " WHERE admin_ID=?";

    private static final String UPDATE_ADMIN_PROFILE = 
        "UPDATE " + ADMIN_TABLE + " SET first_name = ?, last_name = ?, username = ?, " +
        "phone_number = ?, email = ?, address = ? WHERE admin_ID = ?";

    private static final String UPDATE_PROFILE_PICTURE = 
        "UPDATE " + ADMIN_TABLE + " SET profile_picture = ? WHERE admin_ID = ?";

    private static final String GET_PROFILE_PICTURE = 
        "SELECT profile_picture FROM " + ADMIN_TABLE + " WHERE admin_ID = ?";

    private static final String CHECK_ADMIN_COUNT = 
        "SELECT COUNT(*) FROM " + ADMIN_TABLE;

    private static final String INSERT_DEFAULT_ADMIN = 
        "INSERT INTO " + ADMIN_TABLE + " (first_name, last_name, username, phone_number, "
        + "address, email, password) VALUES ('Admin', 'User', 'admin', '1234567890', "
        + "'Default Address', 'admin@example.com', 'admin123')";

    // Account management queries
    private static final String UPDATE_PASSWORD = 
        "UPDATE " + ADMIN_TABLE + " SET password = ? WHERE admin_ID = ?";

    private static final String DELETE_ADMIN = 
        "DELETE FROM " + ADMIN_TABLE + " WHERE admin_ID = ?";

    // Create and insert admin data
    public boolean register(AdminData admin) {
        Connection conn = null;
        PreparedStatement createTableStmt = null;
        PreparedStatement insertStmt = null;

        try {
            conn = mysql.openConnection();
            
            // Create table if not exists
            createTableStmt = conn.prepareStatement(CREATE_ADMIN_TABLE);
            createTableStmt.executeUpdate();
            
            // Insert admin data
            insertStmt = conn.prepareStatement(INSERT_ADMIN);
            insertStmt.setString(1, admin.getFirstName());
            insertStmt.setString(2, admin.getLastName());
            insertStmt.setString(3, admin.getUsername());
            insertStmt.setString(4, admin.getPhoneNumber());
            insertStmt.setString(5, admin.getAddress());
            insertStmt.setString(6, admin.getEmail());
            insertStmt.setString(7, admin.getPassword());
            insertStmt.setBytes(8, admin.getProfilePicture());
            
            return insertStmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(createTableStmt, insertStmt);
            mysql.closeConnection(conn);
        }
    }

    // Login functionality
    public AdminData adminLogin(LoginRequest loginRequest) {
        Connection conn = null;
        PreparedStatement createTableStmt = null;
        PreparedStatement checkAdminStmt = null;
        PreparedStatement insertDefaultStmt = null;
        ResultSet rs = null;

        try {
            conn = mysql.openConnection();
            
            // Create table if not exists
            createTableStmt = conn.prepareStatement(CREATE_ADMIN_TABLE);
            createTableStmt.executeUpdate();
            
            // Check if admin user exists, if not, create default one
            checkAdminStmt = conn.prepareStatement(CHECK_ADMIN_COUNT);
            rs = checkAdminStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) == 0) {
                insertDefaultStmt = conn.prepareStatement(INSERT_DEFAULT_ADMIN);
                insertDefaultStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, createTableStmt, checkAdminStmt, insertDefaultStmt);
            mysql.closeConnection(conn);
        }

        // --- New block for login ---
        PreparedStatement loginStmt = null;
        rs = null; // Reset result set
        try {
            conn = mysql.openConnection(); // Re-open connection
            
            // Perform login
            loginStmt = conn.prepareStatement(LOGIN_QUERY);
            loginStmt.setString(1, loginRequest.getEmail());
            loginStmt.setString(2, loginRequest.getPassword());
            
            rs = loginStmt.executeQuery();
            
            if (rs.next()) {
                AdminData admin = new AdminData();
                admin.setId(rs.getInt("admin_ID"));
                admin.setFirstName(rs.getString("first_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setUsername(rs.getString("username"));
                admin.setPhoneNumber(rs.getString("phone_number"));
                admin.setAddress(rs.getString("address"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setProfilePicture(rs.getBytes("profile_picture"));
                
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, loginStmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }

    public boolean checkEmail(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(CHECK_EMAIL_QUERY);
            stmt.setString(1, email);
            
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
    }

    public boolean resetPassword(ResetPasswordRequest resetRequest) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(RESET_PASSWORD_QUERY);
            stmt.setString(1, resetRequest.getPassword());
            stmt.setString(2, resetRequest.getEmail());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    public AdminData getAdminById(int adminId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_ADMIN_BY_ID);
            stmt.setInt(1, adminId);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                AdminData admin = new AdminData();
                admin.setId(rs.getInt("admin_ID"));
                admin.setFirstName(rs.getString("first_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setUsername(rs.getString("username"));
                admin.setPhoneNumber(rs.getString("phone_number"));
                admin.setAddress(rs.getString("address"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setProfilePicture(rs.getBytes("profile_picture"));
                
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }

    public boolean updateAdminProfile(int adminId, String firstName, String lastName, 
                                    String username, String phoneNumber, String email, 
                                    String address) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_ADMIN_PROFILE);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, email);
            stmt.setString(6, address);
            stmt.setInt(7, adminId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    public boolean updateProfilePicture(int adminId, byte[] profilePicture) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_PROFILE_PICTURE);
            stmt.setBytes(1, profilePicture);
            stmt.setInt(2, adminId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    public byte[] getProfilePicture(int adminId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_PROFILE_PICTURE);
            stmt.setInt(1, adminId);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBytes("profile_picture");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        return null;
    }

    public boolean updatePassword(int adminId, String newPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_PASSWORD);
            stmt.setString(1, newPassword);
            stmt.setInt(2, adminId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    public boolean deleteAdmin(int adminId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(DELETE_ADMIN);
            stmt.setInt(1, adminId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}