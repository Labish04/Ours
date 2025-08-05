package happytravell.popup;

import happytravell.dao.BusTicketsDao;
import happytravell.model.BusTicketsData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TicketsPopup {
    
    public static void showTicketDetails(List<BusTicketsData> tickets) {
        // Create the dialog
        JDialog dialog = new JDialog((Frame) null, "Bus Ticket Details", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500); // Increased width to accommodate ticket price column
        dialog.setLocationRelativeTo(null);
        
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Create header
        JPanel headerPanel = createHeaderPanel(tickets.size());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create table panel
        JPanel tablePanel = createTablePanel(tickets);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Create close button
        JPanel buttonPanel = createButtonPanel(dialog);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private static JPanel createHeaderPanel(int ticketCount) {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(34, 139, 34));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("Booking Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel countLabel = new JLabel("(" + ticketCount + " ticket" + (ticketCount != 1 ? "s" : "") + ")");
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        countLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(countLabel);
        
        return headerPanel;
    }
    
    private static JPanel createTablePanel(List<BusTicketsData> tickets) {
        JPanel tablePanel = new JPanel(new BorderLayout());
        
        // Create table with ticket price column added
        String[] columnNames = {
            "Name", "Phone Number", "Bus Number", 
            "Pickup Address", "Drop Address", 
            "Departure Date & Time", "Ticket Price", "Seat Number"
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        // Populate table with ticket data including price
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        for (BusTicketsData ticket : tickets) {
            Object[] rowData = {
                ticket.getName(),
                ticket.getPhoneNumber(),
                ticket.getBusNumber(),
                ticket.getPickupAddress(),
                ticket.getDropAddress(),
                ticket.getDepartureDateTime() != null ? 
                    dateTimeFormat.format(ticket.getDepartureDateTime()) : "Not Set",
                String.format("Rs. %.2f", ticket.getTicketPrice()), // Format price with currency
                ticket.getSeatNumber()
            };
            tableModel.addRow(rowData);
        }
        
        // Create and style table
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Style table header
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));
        
        // Set column widths for better display including ticket price
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120); // Name
        columnModel.getColumn(1).setPreferredWidth(100); // Phone
        columnModel.getColumn(2).setPreferredWidth(80);  // Bus Number
        columnModel.getColumn(3).setPreferredWidth(150); // Pickup Address
        columnModel.getColumn(4).setPreferredWidth(150); // Drop Address
        columnModel.getColumn(5).setPreferredWidth(130); // Departure DateTime
        columnModel.getColumn(6).setPreferredWidth(90);  // Ticket Price
        columnModel.getColumn(7).setPreferredWidth(70);  // Seat Number
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Ticket Information"));
        
        // Add empty state message if no tickets
        if (tickets.isEmpty()) {
            JLabel emptyLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h3>No Booking Details Found</h3>" +
                "<p>There are no tickets to display.</p></div></html>");
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            emptyLabel.setForeground(Color.GRAY);
            tablePanel.add(emptyLabel, BorderLayout.CENTER);
        } else {
            tablePanel.add(scrollPane, BorderLayout.CENTER);
        }
        
        return tablePanel;
    }
    
    private static JPanel createButtonPanel(JDialog dialog) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.setBackground(new Color(220, 53, 69));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 12));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createRaisedBevelBorder());
        
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        buttonPanel.add(closeButton);
        return buttonPanel;
    }
    
    // Convenience method to show single ticket details
    public static void showSingleTicketDetails(BusTicketsData ticket) {
        List<BusTicketsData> tickets = new ArrayList<>();
        tickets.add(ticket);
        showTicketDetails(tickets);
    }
    
    // Method to show tickets for a specific traveller ID
    public static void showTicketsForTraveller(int travellerId) {
        BusTicketsDao dao = new BusTicketsDao();
        List<BusTicketsData> tickets = dao.getTicketsByTravellerId(travellerId);
        
        System.out.println("Debug: Traveller ID = " + travellerId);
        System.out.println("Debug: Number of tickets found = " + tickets.size());
        
        // Debug: Print ticket details including price
        for (int i = 0; i < tickets.size(); i++) {
            BusTicketsData ticket = tickets.get(i);
            System.out.println("Debug: Ticket " + (i+1) + " - Name: " + ticket.getName() + 
                             ", Bus: " + ticket.getBusNumber() + 
                             ", Seat: " + ticket.getSeatNumber() +
                             ", Price: Rs. " + ticket.getTicketPrice());
        }
        
        showTicketDetails(tickets);
    }
}