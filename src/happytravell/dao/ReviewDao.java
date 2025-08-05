package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.ReviewData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    
    private final MysqlConnection mySql;
    
    // SQL Query Constants
    private static final String CREATE_REVIEW_TABLE = 
        "CREATE TABLE IF NOT EXISTS reviews ("
        + "review_id INT AUTO_INCREMENT PRIMARY KEY,"
        + "traveller_id INT NOT NULL,"
        + "traveller_name VARCHAR(100) NOT NULL,"
        + "traveller_email VARCHAR(100) NOT NULL,"
        + "rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),"
        + "review_text TEXT,"
        + "review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
        + "booking_reference VARCHAR(50),"
        + "vehicle_type VARCHAR(50),"
        + "driver_name VARCHAR(100),"
        + "place_name VARCHAR(100),"
        + "review_type VARCHAR(20) NOT NULL DEFAULT 'FULL_REVIEW',"
        + "status VARCHAR(20) NOT NULL DEFAULT 'PENDING'"
        + ")";
    
    private static final String INSERT_REVIEW = 
        "INSERT INTO reviews (traveller_id, traveller_name, traveller_email, rating, review_text, "
        + "booking_reference, vehicle_type, driver_name, place_name, place_location, review_type, status) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String GET_ALL_REVIEWS = 
        "SELECT * FROM reviews ORDER BY review_date DESC";
    
    private static final String GET_PENDING_REVIEWS = 
        "SELECT * FROM reviews WHERE status = 'PENDING' ORDER BY review_date DESC";
    
    private static final String GET_APPROVED_REVIEWS = 
        "SELECT * FROM reviews WHERE status = 'APPROVED' ORDER BY review_date DESC";
    
    private static final String GET_REVIEW_BY_ID = 
        "SELECT * FROM reviews WHERE review_id = ?";
    
    private static final String UPDATE_REVIEW_STATUS = 
        "UPDATE reviews SET status = ? WHERE review_id = ?";
    
    private static final String DELETE_REVIEW = 
        "DELETE FROM reviews WHERE review_id = ?";
    
    private static final String GET_PLACE_REVIEWS = 
        "SELECT * FROM reviews WHERE review_type = 'PLACE_REVIEW' AND status = 'APPROVED' ORDER BY review_date DESC";
    
    public ReviewDao() {
        this.mySql = new MysqlConnection();
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists() {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.createStatement();
            stmt.execute(CREATE_REVIEW_TABLE);
        } catch (SQLException e) {
            System.err.println("Error creating reviews table: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, null , conn);
        }
    }
    
    public boolean addReview(ReviewData review) {
        if (!review.isValidRating()) {
            System.err.println("Invalid rating: " + review.getRating() + ". Must be between 1-5.");
            return false;
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(INSERT_REVIEW);
            
            stmt.setInt(1, review.getTravellerId());
            stmt.setString(2, review.getTravellerName());
            stmt.setString(3, review.getTravellerEmail());
            stmt.setInt(4, review.getRating());
            stmt.setString(5, review.getReviewText());
            stmt.setString(6, review.getBookingReference());
            stmt.setString(7, review.getVehicleType());
            stmt.setString(8, review.getDriverName());
            stmt.setString(9, review.getPlaceName());
            stmt.setString(11, review.getReviewType());
            stmt.setString(12, review.getStatus());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding review: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    public List<ReviewData> getAllReviews() {
        return getReviews(GET_ALL_REVIEWS);
    }
    
    public List<ReviewData> getPendingReviews() {
        return getReviews(GET_PENDING_REVIEWS);
    }
    
    public List<ReviewData> getApprovedReviews() {
        return getReviews(GET_APPROVED_REVIEWS);
    }
    
    public List<ReviewData> getPlaceReviews() {
        return getReviews(GET_PLACE_REVIEWS);
    }
    
    private List<ReviewData> getReviews(String query) {
        List<ReviewData> reviews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                reviews.add(createReviewFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving reviews: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return reviews;
    }
    
    public boolean updateReviewStatus(int reviewId, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(UPDATE_REVIEW_STATUS);
            stmt.setString(1, status);
            stmt.setInt(2, reviewId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating review status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    public boolean deleteReview(int reviewId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(DELETE_REVIEW);
            stmt.setInt(1, reviewId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting review: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    private ReviewData createReviewFromResultSet(ResultSet rs) throws SQLException {
        ReviewData review = new ReviewData();
        review.setReviewId(rs.getInt("review_id"));
        review.setTravellerId(rs.getInt("traveller_id"));
        review.setTravellerName(rs.getString("traveller_name"));
        review.setTravellerEmail(rs.getString("traveller_email"));
        review.setRating(rs.getInt("rating"));
        review.setReviewText(rs.getString("review_text"));
        
        Timestamp reviewDate = rs.getTimestamp("review_date");
        if (reviewDate != null) {
            review.setReviewDate(reviewDate.toString());
        }
        
        review.setBookingReference(rs.getString("booking_reference"));
        review.setVehicleType(rs.getString("vehicle_type"));
        review.setDriverName(rs.getString("driver_name"));
        review.setPlaceName(rs.getString("place_name"));
        review.setReviewType(rs.getString("review_type"));
        review.setStatus(rs.getString("status"));
        
        return review;
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
    
    public double getAverageRating(String placeName) {
    String sql = "SELECT AVG(rating) FROM reviews WHERE place_name = ? AND status = 'APPROVED'";
    try (Connection conn = mySql.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, placeName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0;
}
    
}