package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import happytravell.model.LoginRequest;
import happytravell.model.ResetPasswordRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TravellerDao {
   
    private static final String TRAVELLER_TABLE = "traveller";
    private static final String BOOKING_TABLE = "bookings";
    private final MysqlConnection mysql = new MysqlConnection();
    
    private static final String CREATE_TRAVELLER_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + TRAVELLER_TABLE + "("
        + "traveller_ID INT AUTO_INCREMENT PRIMARY KEY,"
        + "first_name VARCHAR(100) NOT NULL,"
        + "last_name VARCHAR(100) NOT NULL,"
        + "username VARCHAR(100) NOT NULL,"
        + "phone_number VARCHAR(15) NOT NULL,"
        + "address VARCHAR(100) NOT NULL,"
        + "email VARCHAR(100) UNIQUE NOT NULL,"
        + "password VARCHAR(100) NOT NULL,"
        + "profile_picture MEDIUMBLOB"
        + ")";

   
    private static final String INSERT_TRAVELLER = 
        "INSERT INTO " + TRAVELLER_TABLE + " (first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_TRAVELLER_BY_ID = 
        "SELECT traveller_ID, first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture "
        + "FROM " + TRAVELLER_TABLE + " WHERE traveller_ID=?";

    private static final String UPDATE_TRAVELLER_PROFILE = 
        "UPDATE " + TRAVELLER_TABLE + " SET first_name = ?, last_name = ?, username = ?, " +
        "phone_number = ?, email = ?, address = ? WHERE traveller_ID = ?";

    private static final String UPDATE_PROFILE_PICTURE = 
        "UPDATE " + TRAVELLER_TABLE + " SET profile_picture = ? WHERE traveller_ID = ?";

    private static final String GET_PROFILE_PICTURE = 
        "SELECT profile_picture FROM " + TRAVELLER_TABLE + " WHERE traveller_ID = ?";

    private static final String UPDATE_PASSWORD = 
        "UPDATE " + TRAVELLER_TABLE + " SET password = ? WHERE traveller_ID = ?";

    private static final String DELETE_TRAVELLER = 
        "DELETE FROM " + TRAVELLER_TABLE + " WHERE traveller_ID = ?";

    private static final String LOGIN_QUERY = 
        "SELECT traveller_ID, first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture "
        + "FROM " + TRAVELLER_TABLE + " WHERE email=? AND password=?";

    private static final String CHECK_EMAIL_QUERY = 
        "SELECT 1 FROM " + TRAVELLER_TABLE + " WHERE email=?";

    private static final String RESET_PASSWORD_QUERY = 
        "UPDATE " + TRAVELLER_TABLE + " SET password=? WHERE email=?";

 
    private static final String GET_BOOKINGS_WITH_IMAGES = 
        "SELECT b.booking_ID, b.pickup_address, b.drop_address, b.departure_date_time, "
        + "b.return_date_time, b.passenger_number, b.vehicles_number, b.driver_name, "
        + "b.vehicle_type, b.payment_method, "
        + "t.traveller_ID, t.first_name, t.last_name, t.profile_picture "
        + "FROM " + BOOKING_TABLE + " b "
        + "JOIN " + TRAVELLER_TABLE + " t ON b.traveller_id = t.traveller_ID "
        + "WHERE t.profile_picture IS NOT NULL";

    private static final String GET_BOOKINGS_BY_TRAVELLER = 
        "SELECT booking_ID, pickup_address, drop_address, departure_date_time, "
        + "return_date_time, passenger_number, vehicles_number, driver_name, "
        + "vehicle_type, payment_method, booking_status, total_amount "
        + "FROM " + BOOKING_TABLE + " WHERE traveller_id=?";

    private static final String GET_BOOKING_BY_ID = 
        "SELECT * FROM " + BOOKING_TABLE + " WHERE booking_ID = ?";

    private static final String UPDATE_BOOKING = 
        "UPDATE " + BOOKING_TABLE + " SET pickup_address = ?, drop_address = ?, " +
        "departure_date_time = ?, return_date_time = ?, passenger_number = ?, " +
        "vehicles_number = ?, driver_name = ?, vehicle_type = ?, payment_method = ?, " +
        "booking_status = ?, total_amount = ? WHERE booking_ID = ?";

    private static final String DELETE_BOOKING = 
        "DELETE FROM " + BOOKING_TABLE + " WHERE booking_ID = ?";

  
    public boolean register(TravellerData traveller) {
        Connection conn = null;
        PreparedStatement createTableStmt = null;
        PreparedStatement insertStmt = null;
        
        try {
            conn = mysql.openConnection();
            
            // Create table if not exists
            createTableStmt = conn.prepareStatement(CREATE_TRAVELLER_TABLE);
            createTableStmt.executeUpdate();
            
            // Insert traveller data
            insertStmt = conn.prepareStatement(INSERT_TRAVELLER);
            insertStmt.setString(1, traveller.getFirstName());
            insertStmt.setString(2, traveller.getLastName());
            insertStmt.setString(3, traveller.getUsername());
            insertStmt.setString(4, traveller.getPhoneNumber());
            insertStmt.setString(5, traveller.getAddress());
            insertStmt.setString(6, traveller.getEmail());
            insertStmt.setString(7, traveller.getPassword());
            insertStmt.setBytes(8, traveller.getProfilePicture());
            
            return insertStmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(createTableStmt, insertStmt);
            mysql.closeConnection(conn);
        }
    }

    
    
    public TravellerData getTravellerById(int travellerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_TRAVELLER_BY_ID);
            stmt.setInt(1, travellerId);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToTraveller(rs);
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
     * Updates traveller profile information
     * @param travellerId The ID of the traveller
     * @param firstName Updated first name
     * @param lastName Updated last name
     * @param username Updated username
     * @param phoneNumber Updated phone number
     * @param email Updated email
     * @param address Updated address
     * @return true if update successful, false otherwise
     */
    public boolean updateTravellerProfile(int travellerId, String firstName, String lastName, 
                                        String username, String phoneNumber, String email, 
                                        String address) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_TRAVELLER_PROFILE);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, email);
            stmt.setString(6, address);
            stmt.setInt(7, travellerId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    
    public boolean updateProfilePicture(int travellerId, byte[] profilePicture) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_PROFILE_PICTURE);
            stmt.setBytes(1, profilePicture);
            stmt.setInt(2, travellerId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    
    public byte[] getProfilePicture(int travellerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_PROFILE_PICTURE);
            stmt.setInt(1, travellerId);
            
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

    
    public boolean updatePassword(int travellerId, String newPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_PASSWORD);
            stmt.setString(1, newPassword);
            stmt.setInt(2, travellerId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    
    public boolean deleteTraveller(int travellerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(DELETE_TRAVELLER);
            stmt.setInt(1, travellerId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    
    public TravellerData travellerLogin(LoginRequest loginRequest) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(LOGIN_QUERY);
            stmt.setString(1, loginRequest.getEmail());
            stmt.setString(2, loginRequest.getPassword());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToTraveller(rs);
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
     * Checks if an email exists in the database
     * @param email The email to check
     * @return true if email exists, false otherwise
     */
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

    
    public List<BookingData> getAllBookingDetailsWithImage() {
        List<BookingData> bookingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKINGS_WITH_IMAGES);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData booking = mapResultSetToBookingWithTraveller(rs);
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookingList;
    }

    
    public List<BookingData> getBookingsByTravellerId(int travellerId) {
        List<BookingData> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKINGS_BY_TRAVELLER);
            stmt.setInt(1, travellerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData booking = mapResultSetToBooking(rs, travellerId);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookings;
    }

    
    public BookingData getBookingById(int bookingId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKING_BY_ID);
            stmt.setInt(1, bookingId);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToFullBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }

    
    public boolean updateBooking(BookingData bookingToUpdate) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_BOOKING);
            stmt.setString(1, bookingToUpdate.getPickupAddress());
            stmt.setString(2, bookingToUpdate.getDropAddress());
            stmt.setString(3, bookingToUpdate.getDepartureDateTime());
            stmt.setString(4, bookingToUpdate.getReturnDateTime());
            stmt.setInt(5, bookingToUpdate.getPassengerCount());
            stmt.setString(6, bookingToUpdate.getVehicleNumber());
            stmt.setString(7, bookingToUpdate.getDriverName());
            stmt.setString(8, bookingToUpdate.getVehicleType());
            stmt.setString(9, bookingToUpdate.getPaymentMethod());
            stmt.setInt(12, bookingToUpdate.getBookingId());
            
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
     * Deletes a booking
     * @param bookingId The ID of the booking to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteBooking(int bookingId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(DELETE_BOOKING);
            stmt.setInt(1, bookingId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    
    private TravellerData mapResultSetToTraveller(ResultSet rs) throws SQLException {
        TravellerData traveller = new TravellerData();
        traveller.setTravellerID(rs.getInt("traveller_ID"));
        traveller.setFirstName(rs.getString("first_name"));
        traveller.setLastName(rs.getString("last_name"));
        traveller.setUsername(rs.getString("username"));
        traveller.setPhoneNumber(rs.getString("phone_number"));
        traveller.setAddress(rs.getString("address"));
        traveller.setEmail(rs.getString("email"));
        traveller.setPassword(rs.getString("password"));
        traveller.setProfilePicture(rs.getBytes("profile_picture"));
        return traveller;
    }

    
    private BookingData mapResultSetToBooking(ResultSet rs, int travellerId) throws SQLException {
        BookingData booking = new BookingData();
        booking.setBookingId(rs.getInt("booking_ID"));
        booking.setPickupAddress(rs.getString("pickup_address"));
        booking.setDropAddress(rs.getString("drop_address"));
        booking.setDepartureDateTime(rs.getString("departure_date_time"));
        booking.setReturnDateTime(rs.getString("return_date_time"));
        booking.setPassengerCount(rs.getInt("passenger_number"));
        booking.setVehicleNumber(rs.getString("vehicles_number"));
        booking.setDriverName(rs.getString("driver_name"));
        booking.setVehicleType(rs.getString("vehicle_type"));
        booking.setPaymentMethod(rs.getString("payment_method"));
        booking.setTravellerId(travellerId);
        return booking;
    }

    
    private BookingData mapResultSetToBookingWithTraveller(ResultSet rs) throws SQLException {
        // Create TravellerData
        TravellerData traveller = new TravellerData();
        traveller.setTravellerID(rs.getInt("traveller_ID"));
        traveller.setFirstName(rs.getString("first_name"));
        traveller.setLastName(rs.getString("last_name"));
        traveller.setProfilePicture(rs.getBytes("profile_picture"));
        
        // Create BookingData
        BookingData booking = new BookingData();
        booking.setBookingId(rs.getInt("booking_ID"));
        booking.setPickupAddress(rs.getString("pickup_address"));
        booking.setDropAddress(rs.getString("drop_address"));
        booking.setDepartureDateTime(rs.getString("departure_date_time"));
        booking.setReturnDateTime(rs.getString("return_date_time"));
        booking.setPassengerCount(rs.getInt("passenger_number"));
        booking.setVehicleNumber(rs.getString("vehicles_number"));
        booking.setDriverName(rs.getString("driver_name"));
        booking.setVehicleType(rs.getString("vehicle_type"));
        booking.setPaymentMethod(rs.getString("payment_method"));
        booking.setTravellerId(traveller.getTravellerID());
        
        return booking;
    }

    /**
     * Maps complete booking ResultSet to BookingData (for getBookingById)
     */
    private BookingData mapResultSetToFullBooking(ResultSet rs) throws SQLException {
        BookingData booking = new BookingData();
        booking.setBookingId(rs.getInt("booking_ID"));
        booking.setTravellerId(rs.getInt("traveller_id"));
        booking.setVehiclesId(rs.getInt("vehicle_id"));
        booking.setDriverId(rs.getInt("driver_id"));
        booking.setPickupAddress(rs.getString("pickup_address"));
        booking.setDropAddress(rs.getString("drop_address"));
        booking.setDepartureDateTime(rs.getString("departure_date_time"));
        booking.setReturnDateTime(rs.getString("return_date_time"));
        booking.setPassengerCount(rs.getInt("passenger_number"));
        booking.setVehicleNumber(rs.getString("vehicles_number"));
        booking.setDriverName(rs.getString("driver_name"));
        booking.setVehicleType(rs.getString("vehicle_type"));
        booking.setPaymentMethod(rs.getString("payment_method"));
        return booking;
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