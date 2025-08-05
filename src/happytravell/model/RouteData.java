/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class RouteData {
    private String route_name;
    private String pickup_location;
    private String destination;
    
    
    public RouteData(String route_name,String pickup_location,String destination){
        this.route_name = route_name;
        this.pickup_location = pickup_location;
        this.destination = destination;
    }
    
    
    public void setRouteName(String route_name) {
        this.route_name = route_name;
    }
     public void setPickupLocation(String pickup_location) {
        this.pickup_location = pickup_location;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
     
    public  String getRouteName() {
        return this.route_name;
    }
    
     public String getPickupLocation( ) {
        return this.pickup_location;
    }
     public String getDestination() {
        return this.destination;
    }
     
     
    
}
