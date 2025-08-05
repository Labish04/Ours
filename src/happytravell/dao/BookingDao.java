package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.BookingData;
import happytravell.model.VehiclesData;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Enhanced Data Access Object for Booking operations with price calculation
 * Handles all database operations related to bookings including vehicle pricing
 * 
 * @author Acer
 */
public class BookingDao {
    
    private final MysqlConnection mySql;
    private final VehiclesDao vehiclesDao;
    
    // SQL Query Constants
    private static final String INSERT_BOOKING = 
    "INSERT INTO bookings (traveller_id, pickup_address, drop_address, " +
    "departure_date_time, return_date_time, passenger_number, vehicles_number, " +
    "driver_name, vehicle_type, payment_method, vehicle_id, driver_id, total_amount) " +
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String GET_BOOKINGS_BY_TRAVELLER = 
        "SELECT b.*, t.first_name, t.last_name, v.vehicle_number, d.name as driver_name " +
        "FROM bookings b " +
        "LEFT JOIN traveller t ON b.traveller_id = t.traveller_ID " +
        "LEFT JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
        "LEFT JOIN drivers d ON b.driver_id = d.id " +
        "WHERE b.traveller_id = ? ORDER BY b.departure_date_time DESC";
    
    private static final String GET_ALL_BOOKINGS = 
        "SELECT b.*, t.first_name, t.last_name, v.vehicle_number, d.name as driver_name " +
        "FROM bookings b " +
        "LEFT JOIN traveller t ON b.traveller_id = t.traveller_ID " +
        "LEFT JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
        "LEFT JOIN drivers d ON b.driver_id = d.id " +
        "ORDER BY b.departure_date_time DESC";
    
    private static final String UPDATE_BOOKING_STATUS = 
        "UPDATE bookings SET booking_status = ? WHERE booking_ID = ?";
    
    private static final String ASSIGN_VEHICLE_AND_DRIVER = 
        "UPDATE bookings SET vehicle_id = ?, driver_id = ?, booking_status = 'CONFIRMED' " +
        "WHERE booking_ID = ?";
    
    private static final String GET_AVAILABLE_VEHICLES_BY_TYPE = 
    "SELECT vehicle_id, vehicle_number, number_of_seats, travel_agency, vehicles_image, price " +
    "FROM vehicle WHERE vehicle_type = ? AND is_active = TRUE";

    private static final String GET_AVAILABLE_DRIVERS = 
        "SELECT id, name FROM drivers WHERE status = 'AVAILABLE'";
    
    private static final String GET_BOOKING_BY_ID = 
        "SELECT b.*, t.first_name, t.last_name, v.vehicle_number, d.name as driver_name " +
        "FROM bookings b " +
        "LEFT JOIN traveller t ON b.traveller_id = t.traveller_ID " +
        "LEFT JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
        "LEFT JOIN drivers d ON b.driver_id = d.id " +
        "WHERE b.booking_ID = ?";
    
    public BookingDao() {
        this.mySql = new MysqlConnection();
        this.vehiclesDao = new VehiclesDao();
    }
    
    // ================== CRUD Operations ==================
    
    /**
     * Insert booking with automatic price calculation
     * @param booking BookingData object
     * @return true if successful, false otherwise
     */
    public boolean insertBooking(BookingData booking) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
            
            // Calculate total amount before inserting
            BigDecimal totalAmount = calculateBookingAmount(booking);
            booking.setTotalAmount(totalAmount.doubleValue());
            
            setBookingParameters(stmt, booking);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                setGeneratedBookingId(stmt, booking);
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error inserting booking: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    /**
     * Calculate total booking amount based on vehicle price and return trip
     * @param booking BookingData object
     * @return calculated total amount
     */
    public BigDecimal calculateBookingAmount(BookingData booking) {
    BigDecimal totalAmount = BigDecimal.ZERO;
    
    try {
        // Debug logging
        System.out.println("=== Booking Amount Calculation Debug ===");
        System.out.println("Vehicle ID: " + booking.getVehiclesId());
        System.out.println("Vehicle Number: " + booking.getVehicleNumber());
        System.out.println("Vehicle Type: " + booking.getVehicleType());
        
        // Get vehicle price based on vehicle number or vehicle ID
        VehiclesData vehicle = null;
        
        // Priority 1: Try to get vehicle by ID if available
        if (booking.getVehiclesId() != null && booking.getVehiclesId() > 0) {
            System.out.println("Attempting to fetch vehicle by ID: " + booking.getVehiclesId());
            vehicle = vehiclesDao.getVehicleById(booking.getVehiclesId());
            
            if (vehicle == null) {
                System.err.println("Vehicle not found with ID: " + booking.getVehiclesId());
            } else {
                System.out.println("Vehicle found by ID: " + vehicle.getVehicleNumber());
            }
        }
        
        // Priority 2: Try to get vehicle by number if ID didn't work
        if (vehicle == null && booking.getVehicleNumber() != null && !booking.getVehicleNumber().trim().isEmpty()) {
            System.out.println("Attempting to fetch vehicle by number: " + booking.getVehicleNumber());
            vehicle = vehiclesDao.getVehicleByNumber(booking.getVehicleNumber().trim());
            
            if (vehicle == null) {
                System.err.println("Vehicle not found with number: " + booking.getVehicleNumber());
            } else {
                System.out.println("Vehicle found by number: " + vehicle.getVehicleNumber());
            }
        }
        
        // Priority 3: Try to get vehicle by type if both ID and number failed
        if (vehicle == null && booking.getVehicleType() != null && !booking.getVehicleType().trim().isEmpty()) {
            System.out.println("Attempting to fetch any vehicle of type: " + booking.getVehicleType());
            List<BookingData.VehicleInfo> availableVehicles = getAvailableVehiclesByType(booking.getVehicleType());
            
            if (!availableVehicles.isEmpty()) {
                BookingData.VehicleInfo firstAvailable = availableVehicles.get(0);
                // Convert VehicleInfo to VehiclesData (you might need to adjust this based on your VehiclesData structure)
                vehicle = vehiclesDao.getVehicleById(firstAvailable.getVehicleId());
                System.out.println("Using first available vehicle of type: " + vehicle.getVehicleNumber());
            } else {
                System.err.println("No available vehicles found for type: " + booking.getVehicleType());
            }
        }
        
        if (vehicle != null) {
            System.out.println("Vehicle details - ID: " + vehicle.getVehicleId() + 
                             ", Number: " + vehicle.getVehicleNumber() + 
                             ", Type: " + vehicle.getVehicleType() +
                             ", Price: " + vehicle.getPrice());
            
            if (vehicle.getPrice() != null && vehicle.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                totalAmount = vehicle.getPrice();
                System.out.println("Base price: " + totalAmount);
                
                // Double the price if it's a return trip
                if (booking.getReturnDateTime() != null && !booking.getReturnDateTime().trim().isEmpty()) {
                    totalAmount = totalAmount.multiply(BigDecimal.valueOf(2));
                    System.out.println("Return trip detected. Price doubled: " + totalAmount);
                } else {
                    System.out.println("One-way trip. Price remains: " + totalAmount);
                }
                
                // Optional: Apply passenger count multiplier if your business logic requires it
                // Uncomment the lines below if you want to multiply by passenger count
                /*
                if (booking.getPassengerCount() > 0) {
                    totalAmount = totalAmount.multiply(BigDecimal.valueOf(booking.getPassengerCount()));
                    System.out.println("Price multiplied by passenger count (" + booking.getPassengerCount() + "): " + totalAmount);
                }
                */
                
                System.out.println("Final calculated total amount: " + totalAmount);
            } else {
                System.err.println("Vehicle price is null or zero for vehicle: " + vehicle.getVehicleNumber());
                // Set a default price or throw an exception based on your business logic
                totalAmount = BigDecimal.valueOf(1000); // Default price - adjust as needed
                System.out.println("Using default price: " + totalAmount);
            }
        } else {
            System.err.println("Vehicle not found with provided criteria:");
            System.err.println("- Vehicle ID: " + booking.getVehiclesId());
            System.err.println("- Vehicle Number: " + booking.getVehicleNumber());
            System.err.println("- Vehicle Type: " + booking.getVehicleType());
            
            // Set a default price or throw an exception
            totalAmount = BigDecimal.valueOf(1000); // Default price - adjust as needed
            System.out.println("Using default price due to missing vehicle: " + totalAmount);
        }
        
        System.out.println("=== End Booking Amount Calculation ===");
        
    } catch (Exception e) {
        System.err.println("Error calculating booking amount: " + e.getMessage());
        e.printStackTrace();
        
        // Return a default amount in case of error
        totalAmount = BigDecimal.valueOf(1000); // Default price - adjust as needed
        System.out.println("Using default price due to calculation error: " + totalAmount);
    }
    
    return totalAmount;
}
    
    /**
     * Insert booking with specific vehicle ID and automatic price calculation
     * @param booking BookingData object
     * @param vehicleId Selected vehicle ID
     * @return true if successful, false otherwise
     */
    public boolean insertBookingWithVehicle(BookingData booking, int vehicleId) {
        // Set the vehicle ID in booking
        booking.setVehiclesId(vehicleId);
        
        // Get vehicle details to set vehicle number
        VehiclesData vehicle = vehiclesDao.getVehicleById(vehicleId);
        if (vehicle != null) {
            booking.setVehicleNumber(vehicle.getVehicleNumber());
            booking.setVehicleType(vehicle.getVehicleType());
        }
        
        return insertBooking(booking);
    }
    
    public List<BookingData> getBookingsByTravellerId(int travellerId) {
        List<BookingData> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKINGS_BY_TRAVELLER);
            stmt.setInt(1, travellerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(createBookingFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving bookings for traveller " + travellerId + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return bookings;
    }
    
    public List<BookingData> getAllBookings() {
    List<BookingData> bookings = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        System.out.println("Attempting to connect to database...");
        conn = mySql.openConnection();
        if (conn == null) {
            System.err.println("Failed to get database connection");
            return Collections.emptyList();
        }
        
        System.out.println("Executing query: " + GET_ALL_BOOKINGS);
        stmt = conn.prepareStatement(GET_ALL_BOOKINGS);
        rs = stmt.executeQuery();
        
        int count = 0;
        while (rs.next()) {
            count++;
            BookingData booking = createBookingFromResultSet(rs);
            if (booking != null) {
                bookings.add(booking);
            }
        }
        System.out.println("Successfully loaded " + count + " bookings");
        
    } catch (SQLException e) {
        System.err.println("SQL Error retrieving all bookings: " + e.getMessage());
        e.printStackTrace();
        // Return empty list instead of null
        return Collections.emptyList();
    } catch (Exception e) {
        System.err.println("General Error retrieving all bookings: " + e.getMessage());
        e.printStackTrace();
        return Collections.emptyList();
    } finally {
        closeResources(rs, stmt, conn);
    }
    
    return bookings;
}
    
    public BookingData getBookingById(int bookingId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKING_BY_ID);
            stmt.setInt(1, bookingId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return createBookingFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving booking " + bookingId + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return null;
    }
    
    // ================== Update Operations ==================
    
    public boolean updateBookingStatus(int bookingId, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(UPDATE_BOOKING_STATUS);
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating booking status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    /**
     * Assign vehicle and driver with automatic price recalculation
     * @param bookingId Booking ID
     * @param vehicleId Vehicle ID
     * @param driverId Driver ID
     * @return true if successful, false otherwise
     */
    public boolean assignVehicleAndDriver(int bookingId, int vehicleId, int driverId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // First get the existing booking to recalculate price
            BookingData existingBooking = getBookingById(bookingId);
            if (existingBooking == null) {
                System.err.println("Booking not found for ID: " + bookingId);
                return false;
            }
            
            // Update vehicle ID and recalculate price
            existingBooking.setVehiclesId(vehicleId);
            BigDecimal newAmount = calculateBookingAmount(existingBooking);
            
            conn = mySql.openConnection();
            
            // Update booking with new vehicle, driver, and recalculated amount
            String updateSql = "UPDATE bookings SET vehicle_id = ?, driver_id = ?, " +
                              "booking_status = 'CONFIRMED', total_amount = ? WHERE booking_ID = ?";
            
            stmt = conn.prepareStatement(updateSql);
            stmt.setInt(1, vehicleId);
            stmt.setInt(2, driverId);
            stmt.setBigDecimal(3, newAmount);
            stmt.setInt(4, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error assigning vehicle and driver: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    public boolean updateBookingAmount(int bookingId, double totalAmount) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement("UPDATE bookings SET total_amount = ? WHERE booking_ID = ?");
            stmt.setDouble(1, totalAmount);
            stmt.setInt(2, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating booking amount: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    /**
     * Get available vehicles by type with pricing information
     * @param vehicleType Type of vehicle
     * @return List of available vehicles with prices
     */
    public List<BookingData.VehicleInfo> getAvailableVehiclesByType(String vehicleType) {
        List<BookingData.VehicleInfo> vehicles = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_AVAILABLE_VEHICLES_BY_TYPE);
            stmt.setString(1, vehicleType);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData.VehicleInfo vehicle = new BookingData.VehicleInfo();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setVehicleNumber(rs.getString("vehicle_number"));
                vehicle.setNumberOfSeats(rs.getInt("number_of_seats"));
                vehicle.setTravelAgency(rs.getString("travel_agency"));
                vehicle.setVehicleImage(rs.getBytes("vehicles_image"));
                vehicle.setPrice(rs.getBigDecimal("price")); // Include price
                vehicles.add(vehicle);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving available vehicles: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return vehicles;
    }
    
    public List<BookingData.DriverInfo> getAvailableDrivers() {
        List<BookingData.DriverInfo> drivers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_AVAILABLE_DRIVERS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData.DriverInfo driver = new BookingData.DriverInfo();
                driver.setDriverId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                drivers.add(driver);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving available drivers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return drivers;
    }
    
    // ================== Helper Methods ==================
    
    private void setBookingParameters(PreparedStatement stmt, BookingData booking) throws SQLException {
        stmt.setInt(1, booking.getTravellerId());
        stmt.setString(2, booking.getPickupAddress());
        stmt.setString(3, booking.getDropAddress());
        stmt.setTimestamp(4, Timestamp.valueOf(booking.getDepartureDateTime()));
        
        if (booking.getReturnDateTime() != null && !booking.getReturnDateTime().isEmpty()) {
            stmt.setTimestamp(5, Timestamp.valueOf(booking.getReturnDateTime()));
        } else {
            stmt.setNull(5, Types.TIMESTAMP);
        }
        
        stmt.setInt(6, booking.getPassengerCount());
        stmt.setString(7, booking.getVehicleNumber());
        stmt.setString(8, booking.getDriverName());
        stmt.setString(9, booking.getVehicleType());
        stmt.setString(10, booking.getPaymentMethod());
        
        // Set vehicle_id and driver_id (can be null initially)
        if (booking.getVehiclesId() != null && booking.getVehiclesId() > 0) {
            stmt.setInt(11, booking.getVehiclesId());
        } else {
            stmt.setNull(11, Types.INTEGER);
        }
        
        if (booking.getDriverId() != null && booking.getDriverId() > 0) {
            stmt.setInt(12, booking.getDriverId());
        } else {
            stmt.setNull(12, Types.INTEGER);
        }
        
        // Set total amount
        stmt.setDouble(13, booking.getTotalAmount());
    }
    
    private void setGeneratedBookingId(PreparedStatement stmt, BookingData booking) throws SQLException {
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                booking.setBookingId(generatedKeys.getInt(1));
            }
        }
    }
    
    private BookingData createBookingFromResultSet(ResultSet rs) throws SQLException {
        BookingData booking = new BookingData();
        booking.setBookingId(rs.getInt("booking_ID"));
        booking.setTravellerId(rs.getInt("traveller_id"));
        booking.setStatus(rs.getString("booking_status"));
        
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String fullName = (firstName != null ? firstName : "") + 
                         (lastName != null ? " " + lastName : "");
        booking.setTravellerName(fullName.trim());
        
        booking.setPickupAddress(rs.getString("pickup_address"));
        booking.setDropAddress(rs.getString("drop_address"));
        
        Timestamp departureTs = rs.getTimestamp("departure_date_time");
        if (departureTs != null) {
            booking.setDepartureDateTime(departureTs.toString());
        }
        
        Timestamp returnTs = rs.getTimestamp("return_date_time");
        if (returnTs != null) {
            booking.setReturnDateTime(returnTs.toString());
        }
        
        booking.setPassengerCount(rs.getInt("passenger_number"));
        booking.setVehicleNumber(rs.getString("vehicles_number"));
        booking.setVehicleType(rs.getString("vehicle_type"));
        booking.setDriverName(rs.getString("driver_name"));
        booking.setPaymentMethod(rs.getString("payment_method"));
        
        // Get total amount
        double totalAmount = rs.getDouble("total_amount");
        if (!rs.wasNull()) {
            booking.setTotalAmount(totalAmount);
        }
        
        // Set vehicle and driver IDs if they exist
        int vehicleId = rs.getInt("vehicle_id");
        if (!rs.wasNull()) {
            booking.setVehiclesId(vehicleId);
            booking.setVehicleNumber(rs.getString("vehicle_number"));
        }
        
        int driverId = rs.getInt("driver_id");
        if (!rs.wasNull()) {
            booking.setDriverId(driverId);
        }
        
        return booking;
    }
    
    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) mySql.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean deleteBooking(int bookingId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement("DELETE FROM bookings WHERE booking_ID = ?");
            stmt.setInt(1, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    // Check seat availability for a specific bus and date
    private static final String CHECK_SEAT_AVAILABILITY = 
        "SELECT seat_number FROM booked_seats WHERE vehicle_number = ? AND travel_date = ?";

    public boolean isSeatAvailable(String vehicleNumber, String travelDate, String seatNumber) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(CHECK_SEAT_AVAILABILITY);
            stmt.setString(1, vehicleNumber);
            stmt.setString(2, travelDate);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("seat_number").equals(seatNumber)) {
                    return false; // Seat is already booked
                }
            }
            return true; // Seat is available
            
        } catch (SQLException e) {
            System.err.println("Error checking seat availability: " + e.getMessage());
            return false;
        } finally {
            closeResources(rs, stmt, conn);
        }
    }
    
   
}