/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import edu.missouri.isocial.foundation.components.core.model.LinkModel.LinkModelBuilder;

/**
 *
 * @author Ryan
 */
@DraggableItem
public class DefaultDraggableComponentModel extends DraggableComponentModel {

    @Override
    public void default_properties() {
        displayText = "example";
        ObjName = "example";
        ObjCategory = "examples";

        left[0] = link().withCaption("input").expectingType(Object.class).build();
        left[1] = link().withCaption("input").expectingType(Object.class).build();
        left[2] = link().withCaption("input").expectingType(Object.class).build();
        left[3] = link().withCaption("input").expectingType(Object.class).build();
        left[4] = link().withCaption("input").expectingType(Object.class).build();
        left[5] = link().withCaption("input").expectingType(Object.class).build();

        right[0] = link().withCaption("output").expectingType(Object.class).build();
        bottom[0] = link().withCaption("parameter1").expectingType(Integer.class).build();
    }

    
}