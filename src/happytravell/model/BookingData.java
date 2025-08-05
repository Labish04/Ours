/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

import java.math.BigDecimal;
/**
 *
 * @author Acer
 */
public class BookingData {
    
    private int bookingId;   
    private Integer travellerId;
    private Integer vehiclesId;
    private int driverId;
    private String travellerName; 
    public String pickupAddress;
    public String dropAddress;
    public String departureDateTime;  
    private String returnDateTime;
    private int passengerCount;
    private String vehicleNumber;
    private String vehicleType;    
    private String driverName;
    private String paymentMethod;
    private Double totalAmount;
    private String status;
    
    // Default constructor
    public BookingData() {
    }
   
     public BookingData(int travellerId, String pickupAddress, String dropAddress,
                      String departureDateTime, String returnDateTime,
                      int passengerCount, String vehicleType, String paymentMethod,
                      String vehicleNumber, String driverName, double totalAmount) {
        this(travellerId, pickupAddress, dropAddress, departureDateTime, 
             returnDateTime, passengerCount, vehicleType, paymentMethod);
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.totalAmount = totalAmount;
     }
    
    // Constructor for traveler booking
    public BookingData(int travellerId, String pickupAddress, String dropAddress, 
                      String departureDateTime, String returnDateTime, 
                      int passengerCount, String vehicleType, String paymentMethod) {
        this.travellerId = travellerId;
        this.pickupAddress = pickupAddress;
        this.dropAddress = dropAddress;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.passengerCount = passengerCount;
        this.vehicleType = vehicleType;
        this.paymentMethod = paymentMethod;  
    }
    
    // Constructor for admin view
    public BookingData(int bookingId, int travellerId, String travellerName,
                      String pickupAddress, String dropAddress, 
                      String departureDateTime, String returnDateTime,
                      int passengerCount, String vehicleNumber, String vehicleType,
                      String driverName, double totalAmount) {
        this.bookingId = bookingId;
        this.travellerId = travellerId;
        this.travellerName = travellerName;
        this.pickupAddress = pickupAddress;
        this.dropAddress = dropAddress;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.passengerCount = passengerCount;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.totalAmount = totalAmount;
    }
    
    // Getters and Setters for all fields
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public int getTravellerId() {
        return travellerId;
    }
    
    public void setTravellerId(int travellerId) {
        this.travellerId = travellerId;
    }
    
    public Integer getVehiclesId() {
        return vehiclesId;
    }
    
    public void setVehiclesId(Integer vehiclesId) { 
        this.vehiclesId = vehiclesId;
    }
    
    public Integer getDriverId() {
        return driverId;
    }
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    
    public Double getTotalAmount() {
    return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getTravellerName() {
        return travellerName;
    }
    
    public void setTravellerName(String travellerName) {
        this.travellerName = travellerName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public String getDepartureDateTime() {
        return departureDateTime;
    }
    
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
    
    public String getReturnDateTime() {
        return returnDateTime;
    }
    
    public void setReturnDateTime(String returnDateTime) {
        this.returnDateTime = returnDateTime;
    }
    
    public int getPassengerCount() {
        return passengerCount;
    }
    
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    // Utility method for traveler view
    public String getFormattedBookingSummary() {
        return String.format("Booking #%d - %s from %s to %s", 
            bookingId, vehicleType, pickupAddress, dropAddress);
    }
    
    // Utility method for admin view
    public String getFormattedAdminSummary() {
        return String.format("Booking #%d - %s (%s) - %s - : ", 
            bookingId, travellerName, vehicleType, driverName );
    }
    
    public static class DriverInfo {
        private int driverId;
        private String name;
        private String licenseNumber;
        private String phone;

        // Constructors
        public DriverInfo() {
        }

        public DriverInfo(int driverId, String name) {
            this.driverId = driverId;
            this.name = name;
        }

        // Getters and Setters
        public int getDriverId() {
            return driverId;
        }

        public void setDriverId(int driverId) {
            this.driverId = driverId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLicenseNumber() {
            return licenseNumber;
        }

        public void setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return name; // For display purposes
        }
    }
    
    
    public static class VehicleInfo {
        private int vehicleId;
        private String vehicleType;
        private String vehicleNumber;
        private int numberOfSeats;
        private String vehicleName;
        private String vehicleColor;
        private String travelAgency;
        private boolean isActive;
        private byte[] vehicleImage;
        private BigDecimal price;

        // Constructors
        public VehicleInfo() {
        }

        public VehicleInfo(int vehicleId, String vehicleNumber) {
            this.vehicleId = vehicleId;
            this.vehicleNumber = vehicleNumber;
        }

        // Getters and Setters
        public int getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(int vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }
        
        public BigDecimal getPrice() {
            return price;
        }
        
        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public int getNumberOfSeats() {
            return numberOfSeats;
        }

        public void setNumberOfSeats(int numberOfSeats) {
            this.numberOfSeats = numberOfSeats;
        }

        public String getVehicleName() {
            return vehicleName;
        }

        public void setVehicleName(String vehicleName) {
            this.vehicleName = vehicleName;
        }

        public String getVehicleColor() {
            return vehicleColor;
        }

        public void setVehicleColor(String vehicleColor) {
            this.vehicleColor = vehicleColor;
        }

        public String getTravelAgency() {
            return travelAgency;
        }

        public void setTravelAgency(String travelAgency) {
            this.travelAgency = travelAgency;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
        
        public byte[] getVehicleImage() {
        return vehicleImage;
        }
    
        public void setVehicleImage(byte[] vehicleImage) {
        this.vehicleImage = vehicleImage;
        }
        
        public String getFormattedPrice() {
            if (price != null) {
                return "Rs. " + price.toString();
            }
            return "Price not available";
        }
    }

        @Override
        public String toString() {
            return vehicleNumber + " (" + vehicleType + ")";
        }
        
        
}
    
    

