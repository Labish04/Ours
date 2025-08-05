package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.BusTicketsData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BusTicketsDao {
    private static final String BUS_TICKETS_TABLE = "bus_Tickets";
    private static final String BUS_SEATS_TABLE = "bus_seats";
    
    private MysqlConnection mysql = new MysqlConnection();

    private static final String CREATE_BUS_TICKETS_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + BUS_TICKETS_TABLE + "("
        + "id INT AUTO_INCREMENT PRIMARY KEY,"
        + "full_name VARCHAR(100) NOT NULL,"
        + "phone_number VARCHAR(50) NOT NULL,"
        + "bus_number VARCHAR(20) NOT NULL,"
        + "pickup_address TEXT NOT NULL,"
        + "drop_address TEXT NOT NULL,"
        + "departure_date_time DATETIME NOT NULL,"
        + "return_date_time DATETIME,"
        + "ticket_price DECIMAL(10,2) NOT NULL,"
        + "seat_number VARCHAR(50) NOT NULL,"
        + "traveller_id INT,"
        + "FOREIGN KEY (seat_number) REFERENCES " + BUS_SEATS_TABLE + "(seat_number) ON DELETE CASCADE,"
        + "FOREIGN KEY (traveller_id) REFERENCES traveller(traveller_ID) ON DELETE CASCADE,"
        + "UNIQUE KEY unique_seat_booking (seat_number)"
        + ")";

    private static final String CREATE_BUS_SEATS_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + BUS_SEATS_TABLE + "("
        + "seat_number VARCHAR(50) PRIMARY KEY,"
        + "status ENUM('PENDING', 'ACTIVE', 'AVAILABLE', 'BOOKED') DEFAULT 'AVAILABLE'"
        + ")";

    private static final String INSERT_SEATS = 
        "INSERT IGNORE INTO " + BUS_SEATS_TABLE + " (seat_number) VALUES " +
        "('A1'), ('A2'), ('A3'), ('A4'), ('A5'), ('A6'), ('A7'), ('A8'), ('A9'), ('A10'), ('A11'), ('A12'), ('A13'), ('A14')," +
        "('B1'), ('B2'), ('B3'), ('B4'), ('B5'), ('B6'), ('B7'), ('B8'), ('B9'), ('B10'), ('B11'), ('B12'), ('B13'), ('B14')";

    // Fixed the INSERT statement with explicit column order
    private static final String ADD_BUS_TICKET = 
        "INSERT INTO " + BUS_TICKETS_TABLE + " (full_name, phone_number, bus_number, pickup_address, "
        + "drop_address, departure_date_time, return_date_time, ticket_price, seat_number, traveller_id) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_BUS_TICKETS = 
        "SELECT * FROM " + BUS_TICKETS_TABLE;

    private static final String GET_AVAILABLE_SEATS = 
        "SELECT seat_number FROM " + BUS_SEATS_TABLE + " WHERE status = 'AVAILABLE'";

    private static final String GET_BOOKED_SEATS = 
        "SELECT seat_number FROM " + BUS_TICKETS_TABLE;

    private static final String UPDATE_SEAT_STATUS = 
        "UPDATE " + BUS_SEATS_TABLE + " SET status = ? WHERE seat_number = ?";

    private static final String GET_TICKET_BY_ID = 
        "SELECT * FROM " + BUS_TICKETS_TABLE + " WHERE id = ?";

    private static final String DELETE_TICKET = 
        "DELETE FROM " + BUS_TICKETS_TABLE + " WHERE id = ?";

    // Add a method to calculate ticket price
    public double calculateTicketPrice(String seatNumber, Timestamp departure, Timestamp returnTime) {
        double basePrice = 2000.0;
        if (seatNumber.startsWith("A")) {
            basePrice = 2000.0;
        }
        
        long days = 1;
        if (returnTime != null) {
            long diff = returnTime.getTime() - departure.getTime();
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        }
        
        return basePrice * days;
    }
    
    // Initialize database tables
    private void initializeTables() {
        Connection conn = null;
        PreparedStatement createTicketsTableStmt = null;
        PreparedStatement createSeatsTableStmt = null;
        PreparedStatement insertSeatsStmt = null;

        try {
            conn = mysql.openConnection();
            
            createSeatsTableStmt = conn.prepareStatement(CREATE_BUS_SEATS_TABLE);
            createSeatsTableStmt.executeUpdate();
            
            insertSeatsStmt = conn.prepareStatement(INSERT_SEATS);
            insertSeatsStmt.executeUpdate();
            
            createTicketsTableStmt = conn.prepareStatement(CREATE_BUS_TICKETS_TABLE);
            createTicketsTableStmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error initializing tables: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(createTicketsTableStmt, createSeatsTableStmt, insertSeatsStmt);
            mysql.closeConnection(conn);
        }
    }

    // Add bus ticket with traveller_id validation - FIXED PARAMETER BINDING
    public boolean addBusTicket(BusTicketsData ticket, int travellerId) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(ADD_BUS_TICKET);
            
            // Debug logging to verify the values being inserted
            System.out.println("DEBUG - Inserting ticket data:");
            System.out.println("1. full_name: " + ticket.getName());
            System.out.println("2. phone_number: " + ticket.getPhoneNumber());
            System.out.println("3. bus_number: " + ticket.getBusNumber());
            System.out.println("4. pickup_address: " + ticket.getPickupAddress());
            System.out.println("5. drop_address: " + ticket.getDropAddress());
            System.out.println("6. departure_date_time: " + ticket.getDepartureDateTime());
            System.out.println("7. return_date_time: " + ticket.getReturnDateTime());
            System.out.println("8. ticket_price: " + ticket.getTicketPrice());
            System.out.println("9. seat_number: " + ticket.getSeatNumber());
            System.out.println("10. traveller_id: " + travellerId);
            
            // Set parameters in the exact order as defined in the SQL statement
            stmt.setString(1, ticket.getName());              // full_name
            stmt.setString(2, ticket.getPhoneNumber());       // phone_number
            stmt.setString(3, ticket.getBusNumber());         // bus_number
            stmt.setString(4, ticket.getPickupAddress());     // pickup_address
            stmt.setString(5, ticket.getDropAddress());       // drop_address
            stmt.setTimestamp(6, ticket.getDepartureDateTime()); // departure_date_time
            stmt.setTimestamp(7, ticket.getReturnDateTime()); // return_date_time
            stmt.setDouble(8, ticket.getTicketPrice());       // ticket_price
            stmt.setString(9, ticket.getSeatNumber());        // seat_number
            
            if (travellerId > 0) {
                stmt.setInt(10, travellerId);                 // traveller_id
            } else {
                stmt.setNull(10, java.sql.Types.INTEGER);
            }
            
            int rowsAffected = stmt.executeUpdate();
            boolean success = rowsAffected > 0;
            
            if (success) {
                System.out.println("DEBUG - Ticket inserted successfully");
            } else {
                System.err.println("DEBUG - Failed to insert ticket - no rows affected");
            }
            
            return success;
        } catch (SQLException e) {
            System.err.println("Error adding bus ticket: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    // Get available seats
    public List<String> getAvailableSeats() {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> availableSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_AVAILABLE_SEATS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                availableSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting available seats: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return availableSeats;
    }
    
    // Get booked seats
    public List<String> getBookedSeats() {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> bookedSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKED_SEATS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookedSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting booked seats: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookedSeats;
    }
    
    // Update seat status
    public boolean updateSeatStatus(String seatNumber, String status) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_SEAT_STATUS);
            stmt.setString(1, status);
            stmt.setString(2, seatNumber);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating seat status: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    // Get ticket by ID
    public BusTicketsData getTicketById(int id) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_TICKET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                BusTicketsData ticket = new BusTicketsData(
                    rs.getString("full_name"),
                    rs.getString("phone_number"),
                    rs.getString("bus_number"),
                    rs.getString("pickup_address"),
                    rs.getString("drop_address"),
                    rs.getTimestamp("departure_date_time"),
                    rs.getTimestamp("return_date_time"),
                    rs.getDouble("ticket_price"),
                    rs.getString("seat_number")
                );
                ticket.setId(rs.getInt("id"));
                return ticket;
            }
        } catch (SQLException e) {
            System.err.println("Error getting ticket by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }
    
    // Delete ticket
    public boolean deleteTicket(int id) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(DELETE_TICKET);
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting ticket: " + e.getMessage());
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
                    System.err.println("Error closing resource: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Get booked seats for specific vehicle
    public List<String> getBookedSeatsForVehicle(String vehicleNumber) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> bookedSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement("SELECT seat_number FROM " + BUS_TICKETS_TABLE + 
                                      " WHERE bus_number = ?");
            stmt.setString(1, vehicleNumber);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookedSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting booked seats for vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookedSeats;
    }

    // Get available seats for specific vehicle
    public List<String> getAvailableSeatsForVehicle(String vehicleNumber) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> availableSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(
                "SELECT s.seat_number FROM " + BUS_SEATS_TABLE + " s " +
                "WHERE s.seat_number NOT IN (" +
                "    SELECT t.seat_number FROM " + BUS_TICKETS_TABLE + " t " +
                "    WHERE t.bus_number = ?" +
                ") AND s.status = 'AVAILABLE'");
            stmt.setString(1, vehicleNumber);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                availableSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting available seats for vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return availableSeats;
    }

    // Get tickets by traveller ID
    public List<BusTicketsData> getTicketsByTravellerId(int travellerId) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BusTicketsData> tickets = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement("SELECT * FROM " + BUS_TICKETS_TABLE + " WHERE traveller_id = ?");
            stmt.setInt(1, travellerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BusTicketsData ticket = new BusTicketsData(
                    rs.getString("full_name"),
                    rs.getString("phone_number"),
                    rs.getString("bus_number"),
                    rs.getString("pickup_address"),
                    rs.getString("drop_address"),
                    rs.getTimestamp("departure_date_time"),
                    rs.getTimestamp("return_date_time"),
                    rs.getDouble("ticket_price"),
                    rs.getString("seat_number")
                );
                ticket.setId(rs.getInt("id"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error getting tickets by traveller ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return tickets;
    }

    // Update seat status for a specific vehicle
    public boolean updateSeatStatusForVehicle(String vehicleNumber, String seatNumber, String status) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_SEAT_STATUS);
            stmt.setString(1, status);
            stmt.setString(2, seatNumber);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating seat status for vehicle: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    // Get all bus tickets
    public List<BusTicketsData> getAllBusTickets() {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BusTicketsData> tickets = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_ALL_BUS_TICKETS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BusTicketsData ticket = new BusTicketsData(
                    rs.getString("full_name"),
                    rs.getString("phone_number"),
                    rs.getString("bus_number"),
                    rs.getString("pickup_address"),
                    rs.getString("drop_address"),
                    rs.getTimestamp("departure_date_time"),
                    rs.getTimestamp("return_date_time"),
                    rs.getDouble("ticket_price"),
                    rs.getString("seat_number")
                );
                ticket.setId(rs.getInt("id"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all bus tickets: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return tickets;
    }
}