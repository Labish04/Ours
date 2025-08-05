/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.popup;

import happytravell.model.GuideData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Acer
 */
public class GuideSelectionPopup {
    

    private JDialog dialog;
    private GuideData selectedGuide;
    private JTable guideTable;

    public GuideSelectionPopup(JFrame parent, List<GuideData> guides) {
        // Create the dialog
        dialog = new JDialog(parent, "Select Guide", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        // Create components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 219, 164));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Available Guides - Select One");
        titleLabel.setFont(new Font("Candara", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create table
        createGuideTable(guides);
        JScrollPane scrollPane = new JScrollPane(guideTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBackground(new Color(239, 204, 150));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton selectButton = new JButton("Select");
        selectButton.setBackground(new Color(76, 175, 80));
        selectButton.setForeground(Color.WHITE);
        selectButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(158, 158, 158));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        selectButton.addActionListener(e -> handleSelection());
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
    }

    private void createGuideTable(List<GuideData> guides) {
        String[] columnNames = {"ID", "Name", "Phone", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (GuideData guide : guides) {
            Object[] row = {
                guide.getGuideId(),
                guide.getGuideName(),
                guide.getPhoneNumber(),
                guide.getEmail()
            };
            model.addRow(row);
        }

        guideTable = new JTable(model);
        guideTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guideTable.getTableHeader().setReorderingAllowed(false);
        guideTable.setRowHeight(25);
    }

    private void handleSelection() {
        int selectedRow = guideTable.getSelectedRow();
        if (selectedRow >= 0) {
            selectedGuide = new GuideData(
                (int) guideTable.getValueAt(selectedRow, 0),
                (String) guideTable.getValueAt(selectedRow, 1),
                (String) guideTable.getValueAt(selectedRow, 2),
                "",
                (String) guideTable.getValueAt(selectedRow, 3)
            );
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog, 
                "Please select a guide", 
                "No Guide Selected", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public GuideData showDialog() {
        dialog.setVisible(true);
        return selectedGuide;
    }
}

