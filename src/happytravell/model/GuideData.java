/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class GuideData {
    
  
    private int guideId;
    private String guideName;
    private String phoneNumber;
    private String address;
    private String email;
    
    // Constructors
    public GuideData() {
    }
    
    public GuideData(String guideName, String phoneNumber, String address, String email) {
        this.guideName = guideName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    
    public GuideData(int guideId, String guideName, String phoneNumber, String address, String email) {
        this.guideId = guideId;
        this.guideName = guideName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    
    // Getters and Setters
    public int getGuideId() {
        return guideId;
    }
    
    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }
    
    public String getGuideName() {
        return guideName;
    }
    
    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Guide{" +
                "guideId=" + guideId +
                ", guideName='" + guideName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
    

