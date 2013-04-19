/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.Editor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Ryan
 */
public class ParameterConnector extends Connector {
    
    public ParameterConnector(Editor editor, DraggableJPanel draggable) {
        super(editor, draggable);
    }
 
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        
        GeneralPath path = new GeneralPath();
        path.moveTo(0, 0);
        path.lineTo(10, 0);
        path.lineTo(10/2, 10);
        path.lineTo(0, 0);
        
        g.setColor(getCurrentColor());
        g.fill(path);
    }
    
}