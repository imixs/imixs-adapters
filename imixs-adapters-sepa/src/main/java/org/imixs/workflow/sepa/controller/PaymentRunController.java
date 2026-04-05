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
package org.imixs.workflow.sepa.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.exceptions.QueryException;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The PaymentRunController is used to show invoices by a payment run
 * 
 * See sections/sepa/payment_run.xhtml
 * 
 * 
 * @author rsoika
 * @version 1.0
 */

@Named
@ConversationScoped
public class PaymentRunController implements Serializable {

    private static final long serialVersionUID = 1L;

    protected List<ItemCollection> invoiceList = null;

    @Inject
    DocumentService documentService;

    private static Logger logger = Logger.getLogger(PaymentRunController.class.getName());

    /**
     * This method computes the sum for a given item in a list of workitems. The
     * result is rounded to 2 digits.
     * 
     * @param refids - list of workitem uniqueIds
     * @param item   - name of the item to summarize
     * @return sum rounded to 2 digits
     */
    public double calculateSum(ItemCollection workitem, String item) {
        double result = 0;

        List<ItemCollection> invoices = getInvoices(workitem);
        if (invoices != null) {
            for (ItemCollection doc : invoices) {
                result = result + doc.getItemValueDouble(item);
            }
        }
        // rond with 2 digits
        return Math.round(result * 100.0) / 100.0;

    }

    /**
     * Loads all invoices for the current sepa run
     * 
     * @return
     */
    public List<ItemCollection> getInvoices(ItemCollection workitem) {

        if (invoiceList == null) {
            String query = "(type:workitem OR type:workitemarchive) AND ($workitemref:"
                    + workitem.getUniqueID() + ")";

            try {
                invoiceList = documentService.find(query, 9999, 0);

            } catch (QueryException e) {
                logger.warning("Failed to select invoices: " + e.getMessage());
            }
        }
        return invoiceList;
    }

}
