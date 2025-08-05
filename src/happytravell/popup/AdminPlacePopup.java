package happytravell.popup;

import happytravell.model.PlaceData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AdminPlacePopup {
    private JDialog dialog;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JLabel imageLabel;
    private byte[] imageData;
    private boolean saved = false;
    
    public AdminPlacePopup() {
        dialog = new JDialog();
        dialog.setTitle("Add New Place");
        dialog.setSize(400, 450);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(255, 242, 227));
        
        // Place Name Panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(new Color(255, 242, 227));
        JLabel nameLabel = new JLabel("Place Name:");
        nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        // Description Panel
        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.setBackground(new Color(255, 242, 227));
        JLabel descLabel = new JLabel("Description:");
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descPanel.add(descLabel, BorderLayout.NORTH);
        descPanel.add(descScroll, BorderLayout.CENTER);
        
        // Image Panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(255, 242, 227));
        JLabel imageTitleLabel = new JLabel("Place Image:");
        imageLabel = new JLabel("", JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 150));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setOpaque(true);
        
        JButton browseButton = new JButton("Browse Image");
        browseButton.addActionListener(e -> browseImage());
        
        imagePanel.add(imageTitleLabel, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(browseButton, BorderLayout.SOUTH);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 242, 227));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saved = true;
            dialog.dispose();
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Add components to main panel
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(descPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(imagePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(buttonPanel);
        
        dialog.add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
    }
    
    private void browseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Place Image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
                       name.endsWith(".png") || name.endsWith(".gif");
            }
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });
        
        if (fileChooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                imageData = Files.readAllBytes(selectedFile.toPath());
                ImageIcon icon = new ImageIcon(imageData);
                Image img = icon.getImage().getScaledInstance(
                    imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(dialog, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public PlaceData showDialog() {
        dialog.setVisible(true);
        if (saved && !nameField.getText().isEmpty() && !descriptionArea.getText().isEmpty() && imageData != null) {
            return new PlaceData(nameField.getText(), descriptionArea.getText(), imageData);
        }
        return null;
    }
}