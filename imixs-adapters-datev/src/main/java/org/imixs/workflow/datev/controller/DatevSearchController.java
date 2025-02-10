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

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ItemCollectionComparator;
import org.imixs.workflow.datev.imports.DatevService;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.index.SchemaService;
import org.imixs.workflow.faces.data.WorkflowController;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/**
 * The DATEVSearchController is used to search DATEV stammdaten
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
@Named
@ConversationScoped
public class DatevSearchController implements Serializable {

    public static final int MAX_SEARCH_RESULT = 100;

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(DatevSearchController.class.getName());

    private ItemCollection configuration = null;

    @Inject
    DocumentService documentService;

    @Inject
    WorkflowController workflowController;

    @Inject
    SchemaService schemaService;

    @Inject
    DatevService datevService;

    private List<DatevSearchEntry> searchResult = null;

    /**
     * PostContruct event - loads the imixs.properties.
     */
    @PostConstruct
    void init() {
        // load config
        configuration = datevService.loadConfiguration();
    }

    /**
     * This method searches a text phrase within the list of DATEV sachkonten
     * <p>
     * JSF Integration:
     * 
     * {@code
       <h:commandScript name="datevSearch" action=
     * "#{datevSearchController.searchSachkonto()}"
     * render="autocomplete-resultlist-datev" onevent="autocompleteShowResult" />
     * }
     */
    public void searchSachkonto() {

        List<ItemCollection> dataList = null;
        searchResult = new ArrayList<DatevSearchEntry>();
        // get the param from faces context....
        FacesContext fc = FacesContext.getCurrentInstance();
        String phrase = fc.getExternalContext().getRequestParameterMap().get("phrase");
        if (phrase == null) {
            return;
        }

        logger.fine("search prase '" + phrase + "'");
        if (phrase == null || phrase.length() < 2) {
            return;
        }

        logger.finest(".......trigger searchSachkonto: " + phrase);

        logger.fine("search for=" + phrase);

        // DATEV Sachkonten suchen
        // optional use clientID from workitem
        if (workflowController.getWorkitem().hasItem(DatevService.ITEM_DATEV_CLIENT_ID)) {
            String temp_clientID = workflowController.getWorkitem()
                    .getItemValueString(DatevService.ITEM_DATEV_CLIENT_ID);
            dataList = searchEntity(phrase, "kontenbeschriftungen", temp_clientID);
        } else {
            // standard suche
            dataList = searchEntity(phrase, "kontenbeschriftungen", null);
        }
        for (ItemCollection dbtr : dataList) {
            String kontoNumber = dbtr.getItemValueString("_konto");
            String display = kontoNumber + " - " + dbtr.getItemValueString("_kontobeschriftung");

            display = display.replace("\"", "");
            display = display.replace("'", "");
            searchResult.add(new DatevSearchEntry(kontoNumber, display, buildSachkontoJsonData(dbtr)));
        }

    }

    /**
     * This method searches a text phrase within the list of DATEV kreditoren
     * <p>
     * JSF Integration:
     * 
     * {@code
       <h:commandScript name="datevSearch" action=
     * "#{datevSearchController.searchCdtr()}"
     * render="autocomplete-resultlist-datev" onevent="autocompleteShowResult" />
     * }
     */
    public void searchCdtr() {
        String client = null;
        List<ItemCollection> dataList = null;
        searchResult = new ArrayList<DatevSearchEntry>();
        // get the param from faces context....
        FacesContext fc = FacesContext.getCurrentInstance();
        String phrase = fc.getExternalContext().getRequestParameterMap().get("phrase");
        if (phrase == null) {
            return;
        }

        logger.fine("search prase '" + phrase + "'");
        if (phrase == null || phrase.length() < 2) {
            return;
        }

        logger.finest(".......trigger datev search...");
        client = workflowController.getWorkitem().getItemValueString(DatevService.ITEM_DATEV_CLIENT_ID);
        logger.fine("search for=" + phrase);
        dataList = searchEntity(phrase, "debitoren/kreditoren", client);

        for (ItemCollection dbtr : dataList) {
            // displayname = konto.getItemValueString("_konto") + " " +
            // konto.getItemValueString("_kurzbezeichnung");
            String dbtrNo = dbtr.getItemValueString("_konto");
            // resolve name
            String name = dbtr.getItemValueString("_name_(adressattyp_unternehmen)");
            if (name.isEmpty()) {
                name = dbtr.getItemValueString("_name_(adressattyp_keine_angabe)");
            }
            if (name.isEmpty()) {
                name = dbtr.getItemValueString("_name_(adressattyp_natürl_person)");
            }
            if (name.isEmpty()) {
                name = dbtr.getItemValueString("_kurzbezeichnung");
            }
            dbtr.setItemValue("_name", name);

            String display = dbtrNo + " - " + name;
            display = display.replace("\"", "");
            display = display.replace("'", "");
            searchResult.add(new DatevSearchEntry(dbtrNo, display, buildCdtrJsonData(dbtr)));
        }

    }

    /**
     * Die Resultliste wird als eine Liste einen Arrays zurückgegeben. Der Erste
     * Eintrag
     * 
     * @return
     */
    public List<DatevSearchEntry> getSearchResult() {
        return searchResult;
    }

    /**
     * Hilfsmethode die eine JSON Struktur mit allen relevanten Daten für ein
     * Sachkonto
     * erzeugt.
     * 
     * @return
     */
    private String buildSachkontoJsonData(ItemCollection konto) {

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder(). //
                add("konto", jsonVal(konto.getItemValueString("_konto"))). //
                add("name", jsonVal(konto.getItemValueString("_kontobeschriftung")));
        JsonObject jsonObject = objectBuilder.build();

        String jsonString = "{}";
        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        } catch (IOException e) {
            logger.warning("Unable to build json structure");
        }
        return jsonString;
    }

    /**
     * Hilfsmethode die eine JSON Struktur mit allen relevanten Daten für einen Cdtr
     * erzeugt.
     * 
     * @return
     */
    private String buildCdtrJsonData(ItemCollection cdtr) {

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder(). //
                add("konto", jsonVal(cdtr.getItemValueString("_konto"))). //
                add("name", jsonVal(cdtr.getItemValueString("_name"))). //

                add("iban", jsonVal(cdtr.getItemValueString("_iban-nr_1"))). //
                add("iban2", jsonVal(cdtr.getItemValueString("_iban-nr_2"))). //
                add("iban3", jsonVal(cdtr.getItemValueString("_iban-nr_3"))). //
                add("iban4", jsonVal(cdtr.getItemValueString("_iban-nr_4"))). //
                add("iban5", jsonVal(cdtr.getItemValueString("_iban-nr_5"))). //

                add("bic", jsonVal(cdtr.getItemValueString("_swift-code_1"))). //
                add("bic2", jsonVal(cdtr.getItemValueString("_swift-code_2"))). //
                add("bic3", jsonVal(cdtr.getItemValueString("_swift-code_3"))). //
                add("bic4", jsonVal(cdtr.getItemValueString("_swift-code_4"))). //
                add("bic5", jsonVal(cdtr.getItemValueString("_swift-code_5"))); //

        JsonObject jsonObject = objectBuilder.build();

        String jsonString = "{}";
        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        } catch (IOException e) {
            logger.warning("Unable to build json structure");
        }
        return jsonString;
    }

    /**
     * Helper method to remove " and ' characters - causing problems
     * 
     * @param val
     * @return
     */
    public static String jsonVal(String val) {
        val = val.replace("\"", "");
        val = val.replace("'", "");
        return val;
    }

    /**
     * This method returns a list of ItemCollections matching the search phrase and
     * type. The type depends on the datev import file
     * <p>
     * The param clientID is optional and restricts the result to a specific DATEV
     * client. NOTE: the item _datev_client_id must be part of the property
     * lucence.indexFieldListNoAnalyze.
     * 
     * @param phrase
     *                 - search phrase
     * @param type
     *                 - DATEV type of the object
     * @param clientID
     *                 - optional restriction to a specific client id
     *                 (_datev_client_id)
     * @return - list of matching profiles
     */
    public List<ItemCollection> searchEntity(String phrase, String type, String clientID) {

        List<ItemCollection> searchResult = new ArrayList<ItemCollection>();

        if (phrase == null || phrase.isEmpty())
            return searchResult;

        Collection<ItemCollection> col = null;
        try {
            phrase = phrase.trim();
            // phrase = LuceneSearchService.escapeSearchTerm(phrase);
            phrase = schemaService.normalizeSearchTerm(phrase);
            String sQuery = "(type:\"" + type + "\"";

            // restrict to client id?
            if (clientID != null && !clientID.isEmpty()) {
                sQuery += " AND _datev_client_id:\"" + clientID + "\"";
            }

            sQuery += ")";

            sQuery += " AND (" + phrase + "*)";

            logger.finest("searchprofile: " + sQuery);

            // start lucene search
            logger.fine("searchWorkitems: " + sQuery);
            col = documentService.find(sQuery, MAX_SEARCH_RESULT, 0);
        } catch (Exception e) {
            logger.warning("  lucene error - " + e.getMessage());
        }

        for (ItemCollection kreditor : col) {
            searchResult.add(kreditor);
        }
        // sort by txtname..
        Collections.sort(searchResult, new ItemCollectionComparator("txtname", true));

        return searchResult;

    }

}
