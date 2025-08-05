package happytravell.model;

/**
 * Data model for Review and Rating information
 * @author Acer
 */
public class ReviewData {
    private int reviewId;
    private int travellerId;
    private String travellerName;
    private String travellerEmail;
    private int rating; // 1-5 stars
    private String reviewText;
    private String reviewDate;
    private String bookingReference;
    private String vehicleType;
    private String driverName;
    private String placeName;
    private String reviewType; // "RATING_ONLY" or "FULL_REVIEW" or "PLACE_REVIEW"
    private String status; // "PENDING", "APPROVED", "REJECTED"
    
    // Constructors
    public ReviewData() {}
    
    public ReviewData(int travellerId, String travellerName, String travellerEmail, int rating, 
                     String reviewText, String bookingReference, String vehicleType, 
                     String driverName, String placeName, String placeLocation, String reviewType) {
        this.travellerId = travellerId;
        this.travellerName = travellerName;
        this.travellerEmail = travellerEmail;
        this.rating = Math.max(1, Math.min(5, rating)); // Ensure rating is between 1-5
        this.reviewText = reviewText;
        this.bookingReference = bookingReference;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.placeName = placeName;
        this.reviewType = reviewType;
        this.status = "PENDING"; // Default status
    }
    
    // Getters and Setters
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    
    public int getTravellerId() { return travellerId; }
    public void setTravellerId(int travellerId) { this.travellerId = travellerId; }
    
    public String getTravellerName() { return travellerName; }
    public void setTravellerName(String travellerName) { this.travellerName = travellerName; }
    
    public String getTravellerEmail() { return travellerEmail; }
    public void setTravellerEmail(String travellerEmail) { this.travellerEmail = travellerEmail; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { 
        this.rating = Math.max(1, Math.min(5, rating));
    }
    
    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
    
    public String getReviewDate() { return reviewDate; }
    public void setReviewDate(String reviewDate) { this.reviewDate = reviewDate; }
    
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    
    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }
    
    public String getReviewType() { return reviewType; }
    public void setReviewType(String reviewType) { this.reviewType = reviewType; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Helper methods
    public boolean isRatingOnly() {
        return "RATING_ONLY".equals(reviewType);
    }
    
    public boolean isFullReview() {
        return "FULL_REVIEW".equals(reviewType);
    }
    
    public boolean isPlaceReview() {
        return "PLACE_REVIEW".equals(reviewType);
    }
    
    public String getRatingStars() {
        return "★".repeat(rating) + "☆".repeat(5 - rating);
    }
    
    public boolean isValidRating() {
        return rating >= 1 && rating <= 5;
    }
    
    public boolean isPending() {
        return "PENDING".equals(status);
    }
    
    public boolean isApproved() {
        return "APPROVED".equals(status);
    }
    
    public boolean isRejected() {
        return "REJECTED".equals(status);
    }
}