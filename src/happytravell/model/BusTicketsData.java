/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

import java.sql.Timestamp;
/**
 *
 * @author Acer
 */
public class BusTicketsData {
   
    private int id;
    private int travellerId;
    private String name;
    private String phoneNumber;
    private String busNumber;
    private String pickupAddress;
    private String dropAddress;
    private Timestamp departureDateTime;
    private Timestamp returnDateTime;
    private double ticketPrice;
    private String seatNumber;
    

    public BusTicketsData(String name, String phoneNumber, String busNumber, String pickupAddress, 
                         String dropAddress, Timestamp departureDateTime, Timestamp returnDateTime, 
                         double ticketPrice, String seatNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.busNumber = busNumber;
        this.pickupAddress = pickupAddress;
        this.dropAddress = dropAddress;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.ticketPrice = ticketPrice;
        this.seatNumber = seatNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getTravellerId() {
        return travellerId;
    }

    public void setTravellerId(int travellerId) {
        this.travellerId = travellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public Timestamp getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Timestamp departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Timestamp getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(Timestamp returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

     public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}

    