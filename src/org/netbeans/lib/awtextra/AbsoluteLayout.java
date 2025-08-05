package org.netbeans.lib.awtextra;

import java.awt.*;
import java.io.Serializable;

/**
 * AbsoluteLayout is a LayoutManager that works as a replacement for "null" layout
 * to gain absolute positioning of components.
 * 
 * @author Acer
 */
public class AbsoluteLayout implements LayoutManager2, Serializable {
    
    public static class AbsoluteConstraints implements Serializable {
        public int x;
        public int y;
        public int width;
        public int height;
        
        public AbsoluteConstraints(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = -1;
            this.height = -1;
        }
        
        public AbsoluteConstraints(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
    
    private java.util.Map<Component, AbsoluteConstraints> constraints = new java.util.HashMap<>();
    
    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new IllegalArgumentException("Use addLayoutComponent(Component, Object)");
    }
    
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof AbsoluteConstraints) {
            this.constraints.put(comp, (AbsoluteConstraints) constraints);
        } else {
            this.constraints.put(comp, new AbsoluteConstraints(0, 0));
        }
    }
    
    @Override
    public void removeLayoutComponent(Component comp) {
        constraints.remove(comp);
    }
    
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int maxX = 0, maxY = 0;
        for (Component comp : parent.getComponents()) {
            AbsoluteConstraints ac = constraints.get(comp);
            if (ac != null) {
                int x = ac.x + (ac.width > 0 ? ac.width : comp.getPreferredSize().width);
                int y = ac.y + (ac.height > 0 ? ac.height : comp.getPreferredSize().height);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }
        return new Dimension(maxX, maxY);
    }
    
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }
    
    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.0f;
    }
    
    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.0f;
    }
    
    @Override
    public void invalidateLayout(Container target) {
        // Nothing to do
    }
    
    @Override
    public void layoutContainer(Container parent) {
        for (Component comp : parent.getComponents()) {
            AbsoluteConstraints ac = constraints.get(comp);
            if (ac != null) {
                int width = ac.width > 0 ? ac.width : comp.getPreferredSize().width;
                int height = ac.height > 0 ? ac.height : comp.getPreferredSize().height;
                comp.setBounds(ac.x, ac.y, width, height);
            }
        }
    }
} 