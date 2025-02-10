/*******************************************************************************
 *  Imixs Workflow Technology
 *  Copyright (C) 2003, 2008 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika
 *  
 *******************************************************************************/
package org.imixs.workflow.datev.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.datev.imports.DatevService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.faces.data.WorkflowController;
import org.imixs.workflow.faces.data.WorkflowEvent;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Der DatevBookingController verwaltet die Buchungszeilen in einer Form
 * 
 * @author rsoika
 * @version 1.0
 */
@Named
@ConversationScoped
public class DatevBookingController implements Serializable {

    public static final String ITEM_DATEV_BOOKING_LIST = "datev.booking.list";

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(DatevBookingController.class.getName());

    protected List<ItemCollection> bookingList = null;

    @Inject
    WorkflowController workflowController;

    @Inject
    DatevService datevService;

    private List<DatevSearchEntry> searchResult = null;

    public void setBookingList(List<ItemCollection> bookingList) {
        this.bookingList = bookingList;
    }

    /**
     * This method returns a ItemCollections for each booking row stored in the
     * property datev.booking.list.
     * 
     * Example: <code>
     *   #{datevchildController.childItems}
     * </code>
     * 
     * @return
     */
    public List<ItemCollection> getBookingList() {
        return bookingList;
    }

    /**
     * Adds a new order item
     */
    public void add() {
        if (bookingList != null) {
            ItemCollection itemCol = new ItemCollection();
            itemCol.replaceItemValue("numPos", bookingList.size() + 1);
            bookingList.add(itemCol);
        }
    }

    public void remove(int pos) {
        if (bookingList != null) {
            bookingList.remove(pos - 1);
        }

        // now we need to reorder the numPos attribute for all existing childs..
        int iPos = 1;
        for (ItemCollection item : bookingList) {
            item.replaceItemValue("numPos", iPos);
            iPos++;
        }

    }

    /**
     * WorkflowEvent listener to convert embeded HashMaps into ItemCollections and
     * reconvert them before processing
     * 
     * @param workflowEvent
     * @throws AccessDeniedException
     */
    public void onWorkflowEvent(@Observes WorkflowEvent workflowEvent) throws AccessDeniedException {

        int eventType = workflowEvent.getEventType();
        ItemCollection workitem = workflowEvent.getWorkitem();
        if (workitem == null) {
            return;
        }

        // reset orderItems if workItem has changed
        if (WorkflowEvent.WORKITEM_CHANGED == eventType || WorkflowEvent.WORKITEM_CREATED == eventType) {
            // reset state
            bookingList = explodeChildList(workitem, ITEM_DATEV_BOOKING_LIST);
        }

        // before the workitem is saved we update the field txtOrderItems
        if (WorkflowEvent.WORKITEM_BEFORE_PROCESS == eventType) {
            implodeChildList(workitem, bookingList, ITEM_DATEV_BOOKING_LIST);
        }

        if (WorkflowEvent.WORKITEM_AFTER_PROCESS == eventType) {
            // reset state
            bookingList = explodeChildList(workitem, ITEM_DATEV_BOOKING_LIST);
        }

    }

    /**
     * converts the Map List of a workitem into a List of ItemCollectons
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<ItemCollection> explodeChildList(ItemCollection workitem, String childItemName) {
        // convert current list of childItems into ItemCollection elements
        ArrayList<ItemCollection> result = new ArrayList<ItemCollection>();

        List<Object> mapOrderItems = workitem.getItemValue(childItemName);
        for (Object mapOderItem : mapOrderItems) {
            if (mapOderItem instanceof Map) {
                ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
                result.add(itemCol);
            }
        }
        return result;
    }

    /**
     * Convert the List of ItemCollections back into a List of Map elements
     * 
     * @param workitem
     */
    @SuppressWarnings({ "rawtypes" })
    public static void implodeChildList(ItemCollection workitem, List<ItemCollection> _childItems,
            String childItemName) {
        List<Map> mapOrderItems = new ArrayList<Map>();
        // convert the child ItemCollection elements into a List of Map
        if (_childItems != null) {
            // iterate over all order items..
            for (ItemCollection orderItem : _childItems) {
                mapOrderItems.add(orderItem.getAllItems());
            }
            workitem.replaceItemValue(childItemName, mapOrderItems);
        }
    }

}
