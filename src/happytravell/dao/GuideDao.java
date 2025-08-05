/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.GuideData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class GuideDao {
   
    private static final String GUIDE_TABLE = "guide";
    private final MysqlConnection mysql = new MysqlConnection();
    
    // SQL Queries
    private static final String CREATE_GUIDE_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + GUIDE_TABLE + "("
        + "guide_id INT AUTO_INCREMENT PRIMARY KEY,"
        + "guide_name VARCHAR(100) NOT NULL,"
        + "phone_number VARCHAR(15) NOT NULL,"
        + "address VARCHAR(100) NOT NULL,"
        + "email VARCHAR(100) UNIQUE NOT NULL"
        + ")";
    
    private static final String INSERT_GUIDE = 
        "INSERT INTO " + GUIDE_TABLE + " (guide_name, phone_number, address, email) VALUES (?, ?, ?, ?)";
    
    private static final String GET_ALL_GUIDES = 
        "SELECT * FROM " + GUIDE_TABLE + " ORDER BY guide_name";
    
    private static final String GET_GUIDE_BY_ID = 
        "SELECT * FROM " + GUIDE_TABLE + " WHERE guide_id = ?";
    
    private static final String UPDATE_GUIDE = 
        "UPDATE " + GUIDE_TABLE + " SET guide_name = ?, phone_number = ?, address = ?, email = ? WHERE guide_id = ?";
    
    private static final String DELETE_GUIDE = 
        "DELETE FROM " + GUIDE_TABLE + " WHERE guide_id = ?";
    
    private static final String CHECK_EMAIL_EXISTS = 
        "SELECT COUNT(*) FROM " + GUIDE_TABLE + " WHERE email = ? AND guide_id != ?";
    
    private static final String CHECK_EMAIL_EXISTS_NEW = 
        "SELECT COUNT(*) FROM " + GUIDE_TABLE + " WHERE email = ?";

    /**
     * Add a new guide
     * @param guide The guide data to add
     * @return true if guide added successfully, false otherwise
     */
    public boolean addGuide(GuideData guide) {
        Connection conn = null;
        PreparedStatement createTableStmt = null;
        PreparedStatement insertStmt = null;
        
        try {
            conn = mysql.openConnection();
            
            // Create table if not exists
            createTableStmt = conn.prepareStatement(CREATE_GUIDE_TABLE);
            createTableStmt.executeUpdate();
            
            // Insert guide data
            insertStmt = conn.prepareStatement(INSERT_GUIDE);
            insertStmt.setString(1, guide.getGuideName());
            insertStmt.setString(2, guide.getPhoneNumber());
            insertStmt.setString(3, guide.getAddress());
            insertStmt.setString(4, guide.getEmail());
            
            return insertStmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(createTableStmt, insertStmt);
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Get all guides
     * @return List of all guides
     */
    public List<GuideData> getAllGuides() {
        List<GuideData> guides = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_ALL_GUIDES);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                GuideData guide = mapResultSetToGuide(rs);
                guides.add(guide);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return guides;
    }
    
    /**
     * Get guide by ID
     * @param guideId The ID of the guide to retrieve
     * @return GuideData object if found, null otherwise
     */
    public GuideData getGuideById(int guideId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_GUIDE_BY_ID);
            stmt.setInt(1, guideId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToGuide(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }
    
    /**
     * Update guide information
     * @param guide The guide data with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateGuide(GuideData guide) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_GUIDE);
            stmt.setString(1, guide.getGuideName());
            stmt.setString(2, guide.getPhoneNumber());
            stmt.setString(3, guide.getAddress());
            stmt.setString(4, guide.getEmail());
            stmt.setInt(5, guide.getGuideId());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Delete a guide
     * @param guideId The ID of the guide to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteGuide(int guideId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(DELETE_GUIDE);
            stmt.setInt(1, guideId);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Check if email exists (for validation during update)
     * @param email The email to check
     * @param excludeId The guide ID to exclude from check
     * @return true if email exists, false otherwise
     */
    public boolean emailExists(String email, int excludeId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(CHECK_EMAIL_EXISTS);
            stmt.setString(1, email);
            stmt.setInt(2, excludeId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return false;
    }
    
    /**
     * Check if email exists (for new guide)
     * @param email The email to check
     * @return true if email exists, false otherwise
     */
    public boolean emailExists(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(CHECK_EMAIL_EXISTS_NEW);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return false;
    }
    
    /**
     * Maps ResultSet to GuideData object
     * @param rs The ResultSet containing guide data
     * @return GuideData object
     * @throws SQLException if database access error occurs
     */
    private GuideData mapResultSetToGuide(ResultSet rs) throws SQLException {
        GuideData guide = new GuideData();
        guide.setGuideId(rs.getInt("guide_id"));
        guide.setGuideName(rs.getString("guide_name"));
        guide.setPhoneNumber(rs.getString("phone_number"));
        guide.setAddress(rs.getString("address"));
        guide.setEmail(rs.getString("email"));
        return guide;
    }
    
    /**
     * Closes database resources safely
     * @param resources Variable number of AutoCloseable resources
     */
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