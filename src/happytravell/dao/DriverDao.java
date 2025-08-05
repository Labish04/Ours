package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.DriverData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Driver operations
 * Handles all database operations related to drivers
 */
public class DriverDao {
    
    private final MysqlConnection mySql;
    
    // SQL Query Constants
    private static final String INSERT_DRIVER = 
        "INSERT INTO drivers (name, license_number, phone, address, status) VALUES (?, ?, ?, ?, ?)";
    
    private static final String GET_ALL_DRIVERS = 
        "SELECT * FROM drivers ORDER BY name";
    
    private static final String GET_DRIVER_BY_ID = 
        "SELECT * FROM drivers WHERE id = ?";
    
    private static final String UPDATE_DRIVER = 
        "UPDATE drivers SET name = ?, license_number = ?, phone = ?, address = ?, status = ? WHERE id = ?";
    
    private static final String DELETE_DRIVER = 
        "DELETE FROM drivers WHERE id = ?";
    
    private static final String GET_AVAILABLE_DRIVERS = 
        "SELECT * FROM drivers WHERE status = 'AVAILABLE'";
    
    private static final String UPDATE_DRIVER_STATUS = 
        "UPDATE drivers SET status = ? WHERE id = ?";
    
    public DriverDao() {
        this.mySql = new MysqlConnection();
    }
    
    // ================== CRUD Operations ==================
    
    /**
     * Insert a new driver into the database
     * @param driver DriverData object containing driver details
     * @return true if insertion was successful, false otherwise
     */
    public boolean insertDriver(DriverData driver) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(INSERT_DRIVER, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhone());
            stmt.setString(4, driver.getAddress());
            stmt.setString(5, driver.getStatus());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        driver.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error inserting driver: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    /**
     * Retrieve all drivers from the database
     * @return List of DriverData objects
     */
    public List<DriverData> getAllDrivers() {
        List<DriverData> drivers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_ALL_DRIVERS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                drivers.add(createDriverFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving all drivers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return drivers;
    }
    
    /**
     * Retrieve a specific driver by ID
     * @param id ID of the driver to retrieve
     * @return DriverData object or null if not found
     */
    public DriverData getDriverById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_DRIVER_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return createDriverFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving driver with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return null;
    }
    
    /**
     * Update an existing driver in the database
     * @param driver DriverData object with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean updateDriver(DriverData driver) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(UPDATE_DRIVER);
            
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhone());
            stmt.setString(4, driver.getAddress());
            stmt.setString(5, driver.getStatus());
            stmt.setInt(6, driver.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating driver: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    /**
     * Delete a driver from the database
     * @param id ID of the driver to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteDriver(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(DELETE_DRIVER);
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting driver with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    // ================== Utility Operations ==================
    
    /**
     * Retrieve all available drivers (status = 'AVAILABLE')
     * @return List of available DriverData objects
     */
    public List<DriverData> getAvailableDrivers() {
        List<DriverData> drivers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_AVAILABLE_DRIVERS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                drivers.add(createDriverFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving available drivers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return drivers;
    }
    
    /**
     * Update driver status
     * @param id ID of the driver to update
     * @param status New status ('AVAILABLE', 'BUSY', 'INACTIVE')
     * @return true if update was successful, false otherwise
     */
    public boolean updateDriverStatus(int id, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(UPDATE_DRIVER_STATUS);
            stmt.setString(1, status);
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating driver status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    // ================== Helper Methods ==================
    
    /**
     * Create DriverData object from ResultSet
     */
    private DriverData createDriverFromResultSet(ResultSet rs) throws SQLException {
        DriverData driver = new DriverData();
        driver.setId(rs.getInt("id"));
        driver.setName(rs.getString("name"));
        driver.setLicenseNumber(rs.getString("license_number"));
        driver.setPhone(rs.getString("phone"));
        driver.setAddress(rs.getString("address"));
        driver.setStatus(rs.getString("status"));
        return driver;
    }
    
    /**
     * Close database resources properly
     */
    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) mySql.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}