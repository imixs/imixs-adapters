/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011 Imixs Software Solutions GmbH,  
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
 *  Project: 
 *  	http://www.imixs.org
 *  	http://java.net/projects/imixs-workflow
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.wopi;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.exceptions.QueryException;

/**
 * The TextBlockHelperService is helper service to fetch a textBlock provided by the Imixs-Office-Workflow project.
 * A text-block document is identified by its ID (txtname) and
 * holds a HTML or PlainText information.
 * 
 * A text-block document holds the following items
 * 
 * <ul>
 * <li>txtmode - html/text</li>
 * <li>txtcontent - textual information</li>
 * </ul>
 * 
 * The type of a textBlock document is 'textblock'
 * 
 * @author rsoika
 */

@DeclareRoles({ "org.imixs.ACCESSLEVEL.NOACCESS", "org.imixs.ACCESSLEVEL.READERACCESS",
        "org.imixs.ACCESSLEVEL.AUTHORACCESS", "org.imixs.ACCESSLEVEL.EDITORACCESS",
        "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@RolesAllowed({ "org.imixs.ACCESSLEVEL.NOACCESS", "org.imixs.ACCESSLEVEL.READERACCESS",
        "org.imixs.ACCESSLEVEL.AUTHORACCESS", "org.imixs.ACCESSLEVEL.EDITORACCESS",
        "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@LocalBean
public class TextBlockHelperService {

 
    @EJB
    private DocumentService documentService;

    @Resource
    SessionContext ctx;

    final String TYPE = "textblock";

    private static Logger logger = Logger.getLogger(TextBlockHelperService.class.getName());

  

    /**
     * This method returns a text-block ItemCollection for a specified name or id.
     * If no text-block is found for this name the Method creates an empty
     * text-block object. The text-block entity are not cached by this service. See
     * also the TextBlockService in the Imixs-Office-Worklfow-Util project.
     * 
     * @param name         in attribute txtname
     * 
     * @param discardCache - indicates if the internal cache should be discarded.
     */
    public ItemCollection loadTextBlock(String name) {
        ItemCollection textBlockItemCollection = null;

        // try to load by ID....
        textBlockItemCollection = documentService.load(name);
        if (textBlockItemCollection == null) {
            // not found by ID so lets try to load it by txtname.....
            // load text-block....
            String sQuery = "(type:\"" + TYPE + "\" AND txtname:\"" + name + "\")";
            Collection<ItemCollection> col;
            try {
                col = documentService.find(sQuery, 1, 0);

                if (col.size() > 0) {
                    textBlockItemCollection = col.iterator().next();
                } else {
                    logger.warning("Missing text-block : '" + name + "'");
                }
            } catch (QueryException e) {
                logger.warning("getTextBlock - invalid query: " + e.getMessage());
            }

        }

        if (textBlockItemCollection == null) {
            // create default values
            textBlockItemCollection = new ItemCollection();
            textBlockItemCollection.replaceItemValue("type", TYPE);
            textBlockItemCollection.replaceItemValue("txtname", name);
        }

        return textBlockItemCollection;
    }

}
