/*
 * MapIntegration class for displaying maps and routes using free APIs
 */
package happytravell.controller;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * Handles map integration using free mapping services
 * Uses OpenStreetMap, Google Maps, and other free APIs
 * 
 * @author Acer
 */
public class MapIntegration {
    
    private static final String OPENSTREETMAP_EMBED_URL = "https://www.openstreetmap.org/export/embed.html";
    private static final String GOOGLE_MAPS_URL = "https://www.google.com/maps";
    
    /**
     * Initialize map in the provided panel using HTML embed
     */
    public void initializeMapInPanel(JPanel mapPanel) {
        try {
            mapPanel.removeAll();
            mapPanel.setLayout(new BorderLayout());
            
            // Create a welcome message
            JLabel welcomeLabel = new JLabel("<html><center>Route Map<br/>Make a booking to see your route here</center></html>");
            welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
            mapPanel.add(welcomeLabel, BorderLayout.CENTER);
            
            // Add button to open full map
            JButton openMapButton = new JButton("Open Map in Browser");
            openMapButton.addActionListener(e -> openMapInBrowser("", ""));
            mapPanel.add(openMapButton, BorderLayout.SOUTH);
            
            mapPanel.revalidate();
            mapPanel.repaint();
            
        } catch (Exception e) {
            System.err.println("Error initializing map: " + e.getMessage());
            showErrorInPanel(mapPanel, "Map initialization failed");
        }
    }
    
    /**
     * Show route between pickup and destination locations
     */
    public void showRoute(String pickupLocation, String destination, JPanel mapPanel) {
        try {
            mapPanel.removeAll();
            mapPanel.setLayout(new BorderLayout());
            
            // Create route information panel
            JPanel routeInfoPanel = new JPanel(new BorderLayout());
            JLabel routeLabel = new JLabel("<html><center><b>Route Map</b><br/>" +
                "From: " + pickupLocation + "<br/>" +
                "To: " + destination + "</center></html>");
            routeLabel.setHorizontalAlignment(JLabel.CENTER);
            routeInfoPanel.add(routeLabel, BorderLayout.NORTH);
            
            // Add embedded map using HTML (OpenStreetMap)
            JEditorPane mapPane = createMapPane(pickupLocation, destination);
            JScrollPane scrollPane = new JScrollPane(mapPane);
            scrollPane.setPreferredSize(new Dimension(280, 200));
            routeInfoPanel.add(scrollPane, BorderLayout.CENTER);
            
            // Add action buttons
            JPanel buttonPanel = new JPanel();
            
            JButton openInBrowserButton = new JButton("Open in Browser");
            openInBrowserButton.addActionListener(e -> openRouteInBrowser(pickupLocation, destination));
            
            JButton getDirectionsButton = new JButton("Get Directions");
            getDirectionsButton.addActionListener(e -> getDirections(pickupLocation, destination));
            
            JButton refreshButton = new JButton("Refresh");
            refreshButton.addActionListener(e -> showRoute(pickupLocation, destination, mapPanel));
            
            buttonPanel.add(openInBrowserButton);
            buttonPanel.add(getDirectionsButton);
            buttonPanel.add(refreshButton);
            
            routeInfoPanel.add(buttonPanel, BorderLayout.SOUTH);
            mapPanel.add(routeInfoPanel, BorderLayout.CENTER);
            
            mapPanel.revalidate();
            mapPanel.repaint();
            
        } catch (Exception e) {
            System.err.println("Error showing route: " + e.getMessage());
            showErrorInPanel(mapPanel, "Failed to load route map");
        }
    }
    
    /**
     * Create embedded map pane using HTML
     */
    private JEditorPane createMapPane(String pickupLocation, String destination) {
        JEditorPane mapPane = new JEditorPane();
        mapPane.setContentType("text/html");
        mapPane.setEditable(false);
        
        String htmlContent = generateMapHTML(pickupLocation, destination);
        mapPane.setText(htmlContent);
        
        return mapPane;
    }
    
    /**
     * Generate HTML content for embedded map
     */
    private String generateMapHTML(String pickupLocation, String destination) {
        try {
            String encodedPickup = URLEncoder.encode(pickupLocation, StandardCharsets.UTF_8.toString());
            String encodedDestination = URLEncoder.encode(destination, StandardCharsets.UTF_8.toString());
            
            // Create HTML with embedded OpenStreetMap
            return String.format(
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 5px; }" +
                ".route-info { background: #f0f0f0; padding: 10px; border-radius: 5px; margin-bottom: 10px; }" +
                ".location { margin: 5px 0; }" +
                ".map-container { text-align: center; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='route-info'>" +
                "<div class='location'><b>🚩 Start:</b> %s</div>" +
                "<div class='location'><b>🏁 End:</b> %s</div>" +
                "</div>" +
                "<div class='map-container'>" +
                "<p><b>Interactive Map</b></p>" +
                "<p>📍 Route from %s to %s</p>" +
                "<p style='color: #666; font-size: 12px;'>" +
                "Click 'Open in Browser' for interactive map with directions" +
                "</p>" +
                "<div style='border: 2px solid #ddd; height: 150px; background: #f9f9f9; " +
                "display: flex; align-items: center; justify-content: center; border-radius: 5px;'>" +
                "<div style='text-align: center;'>" +
                "<div style='font-size: 24px; margin-bottom: 10px;'>🗺️</div>" +
                "<div>Map View</div>" +
                "<div style='font-size: 12px; color: #888;'>Interactive map available in browser</div>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>",
                pickupLocation, destination, pickupLocation, destination
            );
        } catch (Exception e) {
            return "<html><body><p>Error loading map content</p></body></html>";
        }
    }
    
    /**
     * Open route in web browser using Google Maps
     */
    public void openRouteInBrowser(String pickupLocation, String destination) {
        try {
            String encodedPickup = URLEncoder.encode(pickupLocation, StandardCharsets.UTF_8.toString());
            String encodedDestination = URLEncoder.encode(destination, StandardCharsets.UTF_8.toString());
            
            // Create Google Maps directions URL
            String mapUrl = String.format(
                "%s/dir/%s/%s",
                GOOGLE_MAPS_URL,
                encodedPickup,
                encodedDestination
            );
            
            // Open in default browser
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(mapUrl));
            } else {
                JOptionPane.showMessageDialog(null,
                    "Please open this URL in your browser:\n" + mapUrl,
                    "Map URL",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            System.err.println("Error opening map in browser: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Failed to open map in browser. Please check your internet connection.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Open general map in browser
     */
    public void openMapInBrowser(String location1, String location2) {
        try {
            String mapUrl;
            if (location1.isEmpty() || location2.isEmpty()) {
                mapUrl = GOOGLE_MAPS_URL;
            } else {
                openRouteInBrowser(location1, location2);
                return;
            }
            
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(mapUrl));
            } else {
                JOptionPane.showMessageDialog(null,
                    "Please open this URL in your browser:\n" + mapUrl,
                    "Map URL",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            System.err.println("Error opening map: " + e.getMessage());
        }
    }
    
    /**
     * Get directions between two locations
     */
    public void getDirections(String pickupLocation, String destination) {
        try {
            // Show directions info dialog
            String message = String.format(
                "Route Directions:\n\n" +
                "From: %s\n" +
                "To: %s\n\n" +
                "For detailed turn-by-turn directions,\n" +
                "click 'Open in Browser' to view in Google Maps.",
                pickupLocation, destination
            );
            
            int result = JOptionPane.showConfirmDialog(null,
                message,
                "Route Directions",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
                
            if (result == JOptionPane.OK_OPTION) {
                openRouteInBrowser(pickupLocation, destination);
            }
            
        } catch (Exception e) {
            System.err.println("Error getting directions: " + e.getMessage());
        }
    }
    
    /**
     * Search for places along the route
     */
    public void searchAlongRoute(String pickupLocation, String destination, String searchQuery, JPanel mapPanel) {
        try {
            String encodedPickup = URLEncoder.encode(pickupLocation, StandardCharsets.UTF_8.toString());
            String encodedDestination = URLEncoder.encode(destination, StandardCharsets.UTF_8.toString());
            String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());
            
            // Create search URL for places along route
            String searchUrl = String.format(
                "%s/search/%s/data=!4m6!2m5!1m4!2s%s!3s%s!4m2!1d0!2d0",
                GOOGLE_MAPS_URL,
                encodedQuery,
                encodedPickup,
                encodedDestination
            );
            
            int result = JOptionPane.showConfirmDialog(null,
                String.format("Search for '%s' along your route from %s to %s?\n\nThis will open in your web browser.",
                    searchQuery, pickupLocation, destination),
                "Search Along Route",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                
            if (result == JOptionPane.YES_OPTION) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(searchUrl));
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Please open this URL in your browser:\n" + searchUrl,
                        "Search URL",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error searching along route: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Failed to perform search. Please try again.",
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Show error in map panel
     */
    private void showErrorInPanel(JPanel mapPanel, String errorMessage) {
        mapPanel.removeAll();
        mapPanel.setLayout(new BorderLayout());
        
        JLabel errorLabel = new JLabel("<html><center>⚠️<br/>" + errorMessage + "<br/>Please check your internet connection</center></html>");
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        mapPanel.add(errorLabel, BorderLayout.CENTER);
        
        JButton retryButton = new JButton("Retry");
        retryButton.addActionListener(e -> initializeMapInPanel(mapPanel));
        mapPanel.add(retryButton, BorderLayout.SOUTH);
        
        mapPanel.revalidate();
        mapPanel.repaint();
    }
    
    /**
     * Calculate estimated distance (placeholder - in real implementation, use geocoding API)
     */
    public String getEstimatedDistance(String pickupLocation, String destination) {
        // This is a placeholder. In a real implementation, you would use a geocoding API
        // to get coordinates and calculate actual distance
        return "Distance calculation requires internet connection";
    }
    
    /**
     * Get estimated travel time (placeholder)
     */
    public String getEstimatedTravelTime(String pickupLocation, String destination) {
        // This is a placeholder. In a real implementation, you would use a routing API
        return "Travel time calculation requires internet connection";
    }
}