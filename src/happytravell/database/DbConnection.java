/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.database;
import java.sql.*;
/**
 *
 * @author User
 */
public interface DbConnection {
    Connection openConnection() throws SQLException;
    
    /**
     * Closes an existing database connection
     * @param conn Connection to close
     * @throws SQLException if closing fails
     */
    void closeConnection(Connection conn) throws SQLException;
    
    /**
     * Optional: Check if a connection is valid
     * @param conn Connection to validate
     * @return true if connection is valid
     */
    default boolean isConnectionValid(Connection conn) {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    
}
