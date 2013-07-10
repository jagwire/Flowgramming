/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import edu.missouri.isocial.foundation.AbstractGraphNode;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.model.DraggableProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ryan
 */
public class ComponentContextMenu {

    private JPopupMenu internalMenu;
    private DraggableComponent component;

    public ComponentContextMenu(DraggableComponent component) {
        this.component = component;
        internalMenu = new JPopupMenu();

        addPropertiesMenuItem();
    }

    public void showMenu(JComponent component, int xOnScreen, int yOnScreen) {
        internalMenu.show(component, xOnScreen, yOnScreen);
        internalMenu.setVisible(true);
    }

    private void addPropertiesMenuItem() {
        JMenuItem item = new JMenuItem("Properties");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get string from input box.
                String returned = JOptionPane.showInputDialog(component, "Enter new value: ");

                //get model id
                String id = component.getID();

                AbstractGraphNode node = ApplicationContext.INSTANCE.getGraph().getNode(id);

                //set node's value
                node.fromString(returned);
                
            }
        });

        internalMenu.add(item);
    }
}