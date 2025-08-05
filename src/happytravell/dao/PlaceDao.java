/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.PlaceData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class PlaceDao {
    MysqlConnection mySql = new MysqlConnection();

    public boolean addPlaces(PlaceData placeData) {
        // Create table if it doesn't exist
        String createPlacesTable = """
                                   CREATE TABLE IF NOT EXISTS places (
                                   place_ID INT AUTO_INCREMENT PRIMARY KEY,
                                   place_name VARCHAR(100) NOT NULL,
                                   description  VARCHAR(100) NOT NULL,
                                   place_image MEDIUMBLOB
                                   );
                                   """;
        String sql = "INSERT INTO places (place_name, description, place_image) VALUES (?, ?, ?)";                                          
    
        Connection conn = mySql.openConnection();
        try {
            // Create table first
            try (PreparedStatement createStmt = conn.prepareStatement(createPlacesTable)) {
                createStmt.executeUpdate();
            }
            
            // Insert data
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, placeData.getPlaceName());
                pstmt.setString(2, placeData.getDescription());
                pstmt.setBytes(3, placeData.getPlaceImage());
                
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding place: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Get all places
    public List<PlaceData> getAllPlaces() {
        List<PlaceData> places = new ArrayList<>();
        String sql = "SELECT * FROM places";
        
        Connection conn = mySql.openConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                PlaceData item = new PlaceData(
                    rs.getInt("place_ID"),
                    rs.getString("place_name"),
                    rs.getString("description"),
                    rs.getBytes("place_image")
                );
                places.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySql.closeConnection(conn);
        }
        return places;
    }

    // Add new places
    public boolean addPlace (PlaceData placeData) {
        String sql = "INSERT INTO places (place_name, description, place_image) VALUES (?, ?, ?)";
        
        Connection conn = mySql.openConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, placeData.getPlaceName());
            pstmt.setString(2, placeData.getDescription());
            pstmt.setBytes(3, placeData.getPlaceImage());
            
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Update place - FIXED: Column name and missing placeId parameter
    public boolean updatePlace(PlaceData placeData) {
        String sql = "UPDATE places SET place_name = ?, description = ?, place_image = ? WHERE place_ID = ?";
        
        Connection conn = mySql.openConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, placeData.getPlaceName());
            pstmt.setString(2, placeData.getDescription());
            pstmt.setBytes(3, placeData.getPlaceImage());
            pstmt.setInt(4, placeData.getPlaceId()); // FIXED: Added missing placeId parameter
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating place: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }
    
    // Delete place - FIXED: Table name and connection management
    public boolean deletePlace(int placeId) {
        String sql = "DELETE FROM places WHERE place_ID = ?";  // FIXED: Changed 'place' to 'places'
        
        Connection conn = mySql.openConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, placeId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting place: "+ e.getMessage());
            e.printStackTrace();
            return false;
            
        } finally {
            mySql.closeConnection(conn);
        }
    }
    
    
    
}