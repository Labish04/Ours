/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class PlaceData{
    private int placeId;  // FIXED: Changed from 'id' to 'placeId' to match usage in controller
    private String placeName;
    private String description;
    private byte[] placeImage;
  
    // Constructor without ID (for new places)
    public PlaceData(String placeName, String description, byte[] placeImage) {
        this.placeName = placeName;
        this.description = description;
        this.placeImage = placeImage;
    }
    
    // Constructor with ID (for existing places from database)
    public PlaceData(int placeId, String placeName, String description, byte[] placeImage) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.description = description;
        this.placeImage = placeImage;
    }
    
    // Getters and Setters
    public int getPlaceId() {
        return placeId;
    }
    
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
    
   public String getPlaceName() {
        return placeName;
    }
    
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public byte[] getPlaceImage() {
        return placeImage;
    }
    
    public void setPlaceImage(byte[] placeImage) {
        this.placeImage = placeImage;
    }
    
   
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        PlaceData place = (PlaceData) obj;
        return placeId == place.placeId && 
               (placeName != null ? placeName.equals(place.placeName) : place.placeName == null);
    }
    
    @Override
    public int hashCode() {
        int result = placeId;
        result = 31 * result + (placeName != null ? placeName.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "PlaceData{" +
               "placeId=" + placeId +
               ", placeName='" + placeName + '\'' +
               ", description='" + description + '\'' +
               ", hasImage=" + (placeImage != null) +
               '}';
    }
    
    
    
}