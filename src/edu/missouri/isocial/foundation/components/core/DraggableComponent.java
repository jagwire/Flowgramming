/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.RelativeDirection;
import edu.missouri.isocial.foundation.components.ConnectionInfo;
import edu.missouri.isocial.foundation.components.core.brushes.DraggableJPanelBrush;
import edu.missouri.isocial.foundation.components.core.model.DefaultDraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.LinkModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ryan
 */
public class DraggableComponent extends javax.swing.JPanel {

    /**
     * Creates new form DraggableJPanel
     */
    private Editor editor;
    private DraggableJPanelController controller;
    private List<ConnectionInfo> connections;
    private Color borderColor = Color.BLACK;
    private DraggableJPanelBrush brush;
    private DraggableComponentModel model;
    private Map<RelativeDirection, List<Link>> allLinks;
    protected Map<String, Link> leftLinks;
    protected Map<String, Link> rightLinks;
    protected Map<String, Link> bottomLinks;

    public DraggableComponent(Editor editor, DraggableComponentModel model) {
        this.editor = editor;
        this.connections = new ArrayList<ConnectionInfo>();
        this.model = model;
        initComponents();

        this.setOpaque(false);
        controller = new DraggableJPanelController(this);
        brush = new DraggableJPanelBrush(this);
        this.setSize(200, 200);

        leftLinks = new HashMap<String, Link>();
        rightLinks = new HashMap<String, Link>();
        bottomLinks = new HashMap<String, Link>();


        initializeFromModel();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    @Override
    protected void paintComponent(Graphics g1) {
        brush.paint((Graphics2D) g1);
        repaintConnections();
    }

    public String getCaption() {
        return this.model.getDisplayText();
    }
    public DraggableComponentModel getModel() {
        return this.model;
    }

    public void repaintConnections() {
        for (ConnectionInfo connection : connections) {

            Point startPoint = connection.getStartConnector().getLocationOnScreen();
            Point endPoint = connection.getEndConnector().getLocationOnScreen();
            this.getGraphics().setColor(Color.green);
            this.getGraphics().drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            System.out.println("DRAWING CONNECTION: "
                    + "\nSTART->(" + startPoint.x + "," + startPoint.y + ")\n"
                    + "END->(" + endPoint.x + "," + endPoint.y + ")");


        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 0, 51));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 300, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public Editor getEditor() {
        return editor;
    }

    /**
     * Perform any upkeep needed before this instance is removed from the editor
     */
    protected void cleanup() {
        //override this.
    }

    public Color getBorderColor() {
        return borderColor;
    }

    protected void initializeFromModel() {

        //model.default_properties();

        int maxSideLinks = Math.max(model.getLeft().size(), model.getRight().size());
        int maxBottomLinks = model.getBottom().length;

        double desiredHeight = Math.max(200, (maxSideLinks * 2) + 1);
        System.out.println("DESIRED HEIGHT: " + desiredHeight);
        double desiredWidth = Math.max(200, (maxBottomLinks * 2) + 1);
        double sizeOfConnectorSide = Link.SIDE_SIZE;
        this.setSize(new Double(desiredWidth).intValue(),
                new Double(desiredHeight).intValue());

        //make left links
        for (int index = 0; index < model.getLeft().size(); index++) {
            LinkModel linkModel = model.getLeft().get(index);
            if (linkModel == null) {
                continue;
            }

            Link link = Link.builder()
                    .withEditor(editor)
                    .withParent(this)
                    .withPosition(Link.POSITION.LEFT)
                    .withCaption(linkModel.getCaption()).build();//w Link(editor, this, Link.POSITION.LEFT);
            leftLinks.put(linkModel.getCaption(), link);
            double yPosition = desiredHeight * (1.0 / (model.getLeft().size() + 1.0)) * (index + 1.0);
            link.setLocation(0, new Double(yPosition).intValue());
            link.setVisible(true);

            add(link);

        }

        //make right links
        for (int index = 0; index < model.getRight().size(); index++) {
            LinkModel linkModel = model.getRight().get(index);
            if (linkModel == null) {
                continue;
            }


            Link link = Link.builder()
                    .withEditor(editor)
                    .withParent(this)
                    .withPosition(Link.POSITION.RIGHT)
                    .withCaption(linkModel.getCaption())
                    .build();

            rightLinks.put(linkModel.getCaption(), link);
            double yPosition = desiredHeight * (1.0 / (model.getRight().size() + 1.0)) * (index + 1.0);

            link.setLocation(new Double(desiredWidth - link.getWidth()).intValue(),
                    new Double(yPosition).intValue());
            link.setVisible(true);
            add(link);
        }

        //make bottom links
        for (int index = 0; index < model.getBottom().length; index++) {
            LinkModel linkModel = model.getBottom()[index];
            if (linkModel == null) {
                continue;
            }
            Link link = Link.builder()
                    .withEditor(editor)
                    .withParent(this)
                    .withPosition(Link.POSITION.BOTTOM)
                    .withCaption(linkModel.getCaption())
                    .build();

            bottomLinks.put(linkModel.getCaption(), link);

            double xPosition = desiredWidth * (1.0 / (model.getBottom().length + 1.0)) * (index + 1.0);
            
            link.setLocation(new Double(xPosition).intValue(), new Double(desiredHeight - Link.SIDE_SIZE).intValue());
            link.setVisible(true);
            add(link);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="legacy">
//    public void addConnector(RelativeDirection side, String fieldName) {
//        
//        //create connector
//        Link link = new Link(editor, this);
//        //<editor-fold defaultstate="collapsed" desc="comment">
//        Map<String, Link> links;
//        //add to appropriate list in map.
//        switch (side) {
//            case LEFT:
//                leftLinks.put(fieldName, link);
//                links = leftLinks;
//                break;
//            case RIGHT:
//                rightLinks.put(fieldName, link);
//                links = rightLinks;
//                break;
//            case BOTTOM:
//                bottomLinks.put(fieldName, link);
//                links = bottomLinks;
//                break;
//            default:
//                links = leftLinks;
//        }
//        //</editor-fold>
//        //add as component to this component
//        this.add(link);
//        link.setVisible(true);
//        //<editor-fold defaultstate="collapsed" desc="comment">
//        
//        //perform the analysis step
//        int numberOfConnectors = links.size();
//        int height = this.getHeight();
//        int width = this.getWidth();
//        
//        int desiredDimension = (2 * numberOfConnectors) + 1;
//        
//        //resize height if necessary
//        if (side == RelativeDirection.LEFT || side == RelativeDirection.RIGHT) {
//            //resize height
//            if (height < desiredDimension) {
//                this.setSize(height, desiredDimension);
//            }
//        } else {
//            //resize width
//            if (width < desiredDimension) {
//                this.setSize(width, desiredDimension);
//            }
//        }
//        //</editor-fold>
//        
//        switch (side) {
//            case LEFT:
//            case RIGHT:
//                
//                //relocate left connectors
//                links = leftLinks;
//                for (int index = 0; index < numberOfConnectors; index++) {
//                    Link _connector = links.get(index);
//                    height = (height * 1 / (numberOfConnectors + 1) * (index + 1));
//                    _connector.setLocation(0, height);
//                }
//                
//                //relocate right connectors
//                links = rightLinks;
//                for (int index = 0; index < numberOfConnectors; index++) {
//                    Link _connector = links.get(index);
//                    height = (height * 1 / (numberOfConnectors + 1) * (index + 1));
//                    _connector.setLocation(width - (1 / 2 * sizeOfConnectorSide), height);
//                }
//                break;
//            case TOP:
//                //reserved for top parameters
//                break;
//            case BOTTOM:
//                //reserved for bottom parameters
//                break;
//        }
//    }
    //</editor-fold>

}
