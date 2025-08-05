/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class VerificationCode {
    private String email;
    private String code;
    private long expirationTime;
    
    public VerificationCode(String email, String code, long expirationTime) {
        this.email = email;
        this.code = code;
        this.expirationTime = expirationTime;
    }
    
    // Getters
    public String getEmail() { return email; }
    public String getCode() { return code; }
    public long getExpirationTime() { return expirationTime; }
    
    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }
}
