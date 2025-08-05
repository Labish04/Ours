/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author User
 */
public class TravellerData {
    private int travellerID;
    public String firstName;
    public String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private byte[] profilePicture;

    // Constructor with basic fields
    public TravellerData(int travellerID, String firstName, String lastName, String username, String email) {
        this.travellerID = travellerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }
    
    public TravellerData(String firstName, String lastName, String username, String email, 
                        String phoneNumber, String address, String password, byte[] profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    // Default constructor
    public TravellerData() {
    }
    
    // Setters
    public void setTravellerID(int travellerID) {
        this.travellerID = travellerID;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    // Getters
    public int getTravellerID() {
        return this.travellerID;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public byte[] getProfilePicture() {
        return this.profilePicture;
    }

    public String getroute_name() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getpickup_location() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getdestination() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
