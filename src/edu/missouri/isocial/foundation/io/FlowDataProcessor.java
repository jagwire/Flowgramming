/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.GraphView;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.Link;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.utils.ClassUtils;
import edu.missouri.isocial.foundation.xml.DraggableInstance;
import edu.missouri.isocial.foundation.xml.FlowDTO;
import edu.missouri.isocial.foundation.xml.InstanceConnection;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class FlowDataProcessor {

    private GraphView graphView;

    public FlowDataProcessor(GraphView editor) {
        this.graphView = editor;
    }

    public void process(FlowDTO flowData) {
        processInstances(flowData.getInstances().getInstances());
    }

    private void processInstances(List<DraggableInstance> instances) {
        for (DraggableInstance instance : instances) {
            String className = instance.getModel();
            String id = instance.getID();
            String position = instance.getPosition();

            //instantiate draggable model from className
            Object obj = ClassUtils.newInstanceFromClassName(className);

            //create component
            DraggableComponent dc = new DraggableComponent(graphView, (DraggableComponentModel) obj);

            //create point from position
            Point location = getPointFromString(position);

            //set component's location from point
            dc.setLocation(location);

            //add draggable and component to editor
            graphView.addDraggable((DraggableComponentModel) obj, dc);

            //set component's visibility to true
            dc.setVisible(true);

            //repaint component
            dc.repaint();

        }

        for (DraggableInstance instance : instances) {

            if (instance.getConnection() == null) {
                continue;
            }

            for (InstanceConnection connection : instance.getConnection()) {
                //get start link caption
                String startLinkID = connection.getForParameter();

                //get end component (this assumes there is only one input link)
                //TODO: figure out a good way to refactor out this chain as a 
                //"data-based" chain of execution versus sequential or functional
                String endDraggableID = connection.getTo();

                //remove leading #
                endDraggableID = endDraggableID.replace("#", "");
                String endLinkID = endDraggableID.split(":")[1];

                //get start draggable
                DraggableComponent startDraggable = graphView.getDraggableWithID(instance.getID());

                //use start draggable to get start link
                Link startLink = startDraggable.getLinkWithID(startLinkID);

                //get end draggable
                DraggableComponent endDraggable = graphView.getDraggableWithID(endDraggableID);

                //use end draggable to get end link
                Link endLink = endDraggable.getLinkWithID(endLinkID);


                if (!graphView.connectionExists(startLink, endLink)) {
                    //create connection with editor
                    graphView.addConnection(startLink, endLink);
                }
            }
        }
    }

    private Point getPointFromString(String commaSeparatedPair) {
        String[] tokens = commaSeparatedPair.split(",");

        Point output = new Point(Integer.valueOf(tokens[0]),
                Integer.valueOf(tokens[1]));

        return output;

    }
}
