/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class AdminData {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private byte[] profilePicture;
    
    // Default constructor - needed for DAO object creation pattern
    public AdminData() {
    }
    
    // Constructor with basic fields
    public AdminData(int id, String firstName, String lastName, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Constructor without ID (for registration)
    public AdminData(String firstName, String lastName, String username, String email, String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    // Constructor with all fields
    public AdminData(int id, String firstName, String lastName, String username, String email, String phoneNumber, String address, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
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
    
    public void setProfilePicture(byte[] profilePicture){
        this.profilePicture = profilePicture;
    }

    // Getters
    public int getId() {
        return this.id;
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
    
    public byte[] getProfilePicture(){
        return this.profilePicture;
    }
}
