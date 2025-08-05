/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import java.sql.*;
import happytravell.database.MysqlConnection;
import happytravell.model.VehiclesData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class VehiclesDao {
 
    MysqlConnection mySql = new MysqlConnection();

    public boolean addVehicle(VehiclesData vehicle) {
        Connection conn = mySql.openConnection();
        String sql = "INSERT INTO vehicle (vehicle_type, vehicle_number, number_of_seats, " +
                    "vehicle_name, vehicle_color, travel_agency, vehicles_image, price, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getVehicleNumber());
            stmt.setInt(3, vehicle.getNumberOfSeats());
            stmt.setString(4, vehicle.getVehicleName());
            stmt.setString(5, vehicle.getVehicleColor());
            stmt.setString(6, vehicle.getTravelAgency());
            
            // Handle image data
            if (vehicle.getVehicleImage() != null) {
                stmt.setBytes(7, vehicle.getVehicleImage());
            } else {
                stmt.setNull(7, Types.BLOB);
            }
            
            stmt.setBigDecimal(8, vehicle.getPrice());
            stmt.setBoolean(9, vehicle.isActive());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Get the generated vehicle ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        vehicle.setVehicleId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
    
    /**
     * Update an existing vehicle
     * @param vehicle Vehicle object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateVehicle(VehiclesData vehicle){
        Connection conn = mySql.openConnection();
        String sql = "UPDATE vehicle SET vehicle_type = ?, vehicle_number = ?, number_of_seats = ?, " +
                    "vehicle_name = ?, vehicle_color = ?, travel_agency = ?, vehicles_image = ?, price = ?, is_active = ? WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getVehicleNumber());
            stmt.setInt(3, vehicle.getNumberOfSeats());
            stmt.setString(4, vehicle.getVehicleName());
            stmt.setString(5, vehicle.getVehicleColor());
            stmt.setString(6, vehicle.getTravelAgency());
            
            // Handle image data
            if (vehicle.getVehicleImage() != null) {
                stmt.setBytes(7, vehicle.getVehicleImage());
            } else {
                stmt.setNull(7, Types.BLOB);
            }
            
            stmt.setBigDecimal(8, vehicle.getPrice());
            stmt.setBoolean(9, vehicle.isActive());
            stmt.setInt(10, vehicle.getVehicleId());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
    
    
    public boolean deleteVehicle(int vehicleId) {
        Connection conn = mySql.openConnection();
        String sql = "UPDATE vehicle SET is_active = false WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
    
    
    public VehiclesData getVehicleById(int vehicleId) {
        Connection conn = mySql.openConnection();
        String sql = "SELECT * FROM vehicle WHERE vehicle_id = ? AND is_active = true";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractVehicleFromResultSet(rs);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting vehicle by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return null;
    }
    
    
    
    public List<VehiclesData> getAllVehicles() {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE is_active = true ORDER BY vehicle_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all vehicles: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return vehicles;
    }
    
    
    public List<VehiclesData> getVehiclesByType(String vehicleType) {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE vehicle_type = ? AND is_active = true ORDER BY vehicle_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleType);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(extractVehicleFromResultSet(rs));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting vehicle by type: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return vehicles;
    }
    
    /**
     * Check if vehicle number already exists
     * @param vehicleNumber Vehicle number to check
     * @return true if exists, false otherwise
     */
    public boolean isVehicleNumberExists(String vehicleNumber) {
        Connection conn = mySql.openConnection();
        String sql = "SELECT COUNT(*) FROM vehicle WHERE vehicle_number = ? AND is_active = true";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking vehicle number: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
    
    public List<VehiclesData> searchVehicles(String searchTerm) {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE (vehicle_number LIKE ? OR vehicle_name LIKE ? OR " +
                    "vehicle_color LIKE ? OR travel_agency LIKE ?) AND is_active = true ORDER BY vehicle_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(extractVehicleFromResultSet(rs));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching vehicles: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return vehicles;
    }
    
    /**
     * Extract Vehicle object from ResultSet
     * @param rs ResultSet containing vehicle data
     * @return Vehicle object
     * @throws SQLException if database error occurs
     */
    private VehiclesData extractVehicleFromResultSet(ResultSet rs) throws SQLException {
        return new VehiclesData(
            rs.getInt("vehicle_id"),
            rs.getString("vehicle_type"),
            rs.getString("vehicle_number"),
            rs.getInt("number_of_seats"),
            rs.getString("vehicle_name"),
            rs.getString("vehicle_color"),
            rs.getString("travel_agency"),
            rs.getBoolean("is_active"),
            rs.getBytes("vehicles_image"),
            rs.getBigDecimal("price")
        );
    }
    
    /**
     * Get vehicles by price range
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return List of vehicles within price range
     */
    public List<VehiclesData> getVehiclesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE price BETWEEN ? AND ? AND is_active = true ORDER BY price ASC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, minPrice);
            stmt.setBigDecimal(2, maxPrice);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(extractVehicleFromResultSet(rs));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting vehicles by price range: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return vehicles;
    }
    
    public VehiclesData getVehicleByNumber(String vehicleNumber) {
        Connection conn = mySql.openConnection();
        String sql = "SELECT * FROM vehicle WHERE vehicle_number = ? AND is_active = true";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractVehicleFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting vehicle by number: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return null;
    }
    
    
    
    
}