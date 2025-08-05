/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.UI;

import happytravell.model.GuideData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Acer
 */
public class AdminGuideDetailsCardPanel extends JPanel {
    
    private GuideData guide;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private boolean isSelected = false;
    
    // Interface for handling card click events
    public interface GuideCardClickListener {
        void onGuideCardClicked(GuideData guide);
    }
    
    private GuideCardClickListener clickListener;
    
    public AdminGuideDetailsCardPanel(GuideData guide) {
        this.guide = guide;
        initializeComponents();
        setupLayout();
        setupMouseListener();
    }
    
    private void initializeComponents() {
        setBackground(new Color(239, 204, 150));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        setPreferredSize(new Dimension(600, 1600));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Create labels with guide information
        nameLabel = new JLabel("Name: " + guide.getGuideName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(Color.decode("#000000"));
        
        phoneLabel = new JLabel("Phone: " + guide.getPhoneNumber());
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneLabel.setForeground(Color.decode("#000000"));
        
        addressLabel = new JLabel("Address: " + guide.getAddress());
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressLabel.setForeground(Color.decode("#000000"));
        
        emailLabel = new JLabel("Email: " + guide.getEmail());
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(Color.decode("#000000"));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(239, 204, 150));
        contentPanel.setOpaque(false);
        
        // Add some spacing
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(nameLabel);
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(phoneLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(addressLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(emailLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Add click indicator
        JLabel clickIndicator = new JLabel("Click to edit/delete");
        clickIndicator.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        clickIndicator.setForeground(Color.decode("#000000"));
        clickIndicator.setHorizontalAlignment(SwingConstants.RIGHT);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(clickIndicator, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void setupMouseListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickListener != null) {
                    clickListener.onGuideCardClicked(guide);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(239, 204, 150));
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                    new EmptyBorder(14, 19, 14, 19)
                ));
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(239, 204, 150));
                    setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        new EmptyBorder(15, 20, 15, 20)
                    ));
                    repaint();
                }
            }
        };
        
        addMouseListener(mouseAdapter);
        
        // Add mouse listener to all child components
        addMouseListenerToChildren(this, mouseAdapter);
    }
    
    private void addMouseListenerToChildren(Container container, MouseAdapter listener) {
        for (Component component : container.getComponents()) {
            component.addMouseListener(listener);
            if (component instanceof Container) {
                addMouseListenerToChildren((Container) component, listener);
            }
        }
    }
    
    public void setSelected(boolean selected) {
        this.isSelected = selected;
        if (selected) {
            setBackground(new Color(230, 240, 250));
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                new EmptyBorder(14, 19, 14, 19)
            ));
        } else {
            setBackground(Color.decode("#F8D985"));
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(15, 20, 15, 20)
            ));
        }
        repaint();
    }
    
    public void setClickListener(GuideCardClickListener listener) {
        this.clickListener = listener;
    }
    
    public GuideData getGuide() {
        return guide;
    }
    
    public void updateGuide(GuideData updatedGuide) {
        this.guide = updatedGuide;
        nameLabel.setText("Name: " + guide.getGuideName());
        phoneLabel.setText("Phone: " + guide.getPhoneNumber());
        addressLabel.setText("Address: " + guide.getAddress());
        emailLabel.setText("Email: " + guide.getEmail());
        repaint();
    }
}
