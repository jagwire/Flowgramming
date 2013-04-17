/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.components.core.Connection;
import edu.missouri.isocial.foundation.components.core.ConnectionController;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.sequence.SequenceAction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.util.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class EditorApplication extends javax.swing.JFrame {

    /**
     * Creates new form EditorApplication
     */
    private Connection c1;

    public EditorApplication() {
        initComponents();

        DraggableJPanel draggable = new DraggableJPanel(this);
        draggable.setSize(200, 200);
        add(draggable);
        draggable.setVisible(true);

        addDefaultFileMenuItems();
        addDefaultEditMenuItems();




        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("ON SCREEN: x->" + e.getXOnScreen() + ", y->" + e.getYOnScreen() + "\n"
                        + "EVENT: x->" + e.getX() + ", y->" + e.getY());
            }
        });

        

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("MOUSE CLICK!");
//                if(e.isPopupTrigger()) {
                    PopupMenu menu = new PopupMenu();
                    menu.show(e.getComponent(), e.getXOnScreen(), e.getYOnScreen());
                    menu.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setText("File");
        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");
        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 929, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditorApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditorApplication().setVisible(true);
            }
        });
    }

    private void addNode(Point location) {
//        DraggableJPanel node = new DraggableJPanel(EditorApplication.this);
        SequenceAction node = new SequenceAction(this);
        node.setLocation(location);
        node.setSize(200, 200);
        add(node);
        node.setVisible(true);

//        this.validate();
//        node.validate();
        node.repaint();
//        this.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    private void addDefaultFileMenuItems() {
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        fileMenu.add(exitMenuItem);
    }

    private void addDefaultEditMenuItems() {
        JMenuItem addNodeMenuItem = new JMenuItem("Add Node");
        addNodeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addNode(MouseInfo.getPointerInfo().getLocation());
            }
        });
        editMenu.add(addNodeMenuItem);
    }

    public Connection addConnection(Connector parentConnector, Connector targetConnector) {
        Connection connection = new Connection(parentConnector, targetConnector);
        


//        connection.setSize(getSize());
        add(connection);
        connection.setVisible(true);
        ConnectionController cc = new ConnectionController(this, parentConnector, targetConnector, connection);
        cc.setVisible(true);
//        setComponentZOrder(connection, 0);
//        connection.setVisible(true);
//        connection.repaint();
        //setComponentZOrder(connection, 3);

        return connection;
    }

    private class PopupMenu extends JPopupMenu {

        JMenuItem addMenuItem;
        JMenuItem exitMenuItem;

        public PopupMenu() {
            addMenuItem = new JMenuItem("Add Node");
            addMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addNode(MouseInfo.getPointerInfo().getLocation());
                }
            });
            add(addMenuItem);

            exitMenuItem = new JMenuItem("Exit");
            exitMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            add(exitMenuItem);



        }
    }
}