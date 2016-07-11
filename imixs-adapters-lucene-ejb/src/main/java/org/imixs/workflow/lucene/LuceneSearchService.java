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

package org.imixs.workflow.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockFactory;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.jee.ejb.EntityService;
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.jee.util.PropertyService;

/**
 * This session ejb provides a service to search the lucene index. The EJB uses
 * the IndexSearcher to query the current index. As the index can change across
 * multiple searches we can not share a single IndexSearcher instance. For that
 * reason the EJB is creating a new IndexSearch per-search.
 * 
 * The service provides a set of public methods which can be used to query
 * workitems or collections of workitems. A search term can be escaped by
 * calling the method <code>escpeSearchTerm</code>. This method prepends a
 * <code>\</code> for those characters that QueryParser expects to be escaped.
 * 
 * @see http://stackoverflow.com/questions/34880347/why-did-lucene-indexwriter-
 *      did-not-update-the-index-when-called-from-a-web-modul
 * @version 2.0
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
public class LuceneSearchService {

	public static final String UNDEFINED_ERROR = "UNDEFINED_ERROR";
	public static final String INVALID_INDEX = "INVALID_INDEX";

	private static final int MAX_SEARCH_RESULT = 100;

	@EJB
	PropertyService propertyService;

	private static Logger logger = Logger.getLogger(LuceneSearchService.class.getName());

	/**
	 * PostContruct event - loads the imixs.properties.
	 */
	@PostConstruct
	void init() {
	}

	/**
	 * Returns a ItemCollection List matching the provided search term. The
	 * provided search team will we extended with a users roles to test the read
	 * access level of each workitem matching the search term. The usernames and
	 * user roles will be search lowercase!
	 * 
	 * @param sSearchTerm
	 * @param workflowService
	 * @return collection of search result
	 */
	public List<ItemCollection> search(String sSearchTerm, WorkflowService workflowService) {
		// no sort order
		return search(sSearchTerm, workflowService, null, null, MAX_SEARCH_RESULT);
	}

	public List<ItemCollection> search(String sSearchTerm, WorkflowService workflowService, int maxResult) {
		// no sort order
		return search(sSearchTerm, workflowService, null, null, maxResult);
	}

	/**
	 * Returns a ItemCollection List matching the provided search term. The
	 * provided search team will we extended with a users roles to test the read
	 * access level of each workitem matching the search term. The usernames and
	 * user roles will be search lowercase!
	 * 
	 * The optional param 'searchOrder' can be set to force lucene to sort the
	 * search result by any search order.
	 * 
	 * The optional param 'defaultOperator' can be set to Operator.AND
	 * 
	 * @param sSearchTerm
	 * @param workflowService
	 * @param sortOrder
	 *            - optional to sort the result
	 * @param defaultOperator
	 *            - optional to change the default search operator
	 * @return collection of search result
	 */
	public List<ItemCollection> search(String sSearchTerm, WorkflowService workflowService, Sort sortOrder,
			Operator defaultOperator, int maxResult) {

		if (maxResult > MAX_SEARCH_RESULT) {
			maxResult = MAX_SEARCH_RESULT;
		}
		logger.fine("lucene search max_result=" + maxResult);

		ArrayList<ItemCollection> workitems = new ArrayList<ItemCollection>();

		// test if searchtem is provided
		if (sSearchTerm == null || "".equals(sSearchTerm))
			return workitems;

		long ltime = System.currentTimeMillis();
		Properties prop = propertyService.getProperties();
		if (prop.isEmpty())
			return workitems;

		try {
			IndexSearcher searcher = createIndexSearcher(prop);
			QueryParser parser = createQueryParser(prop);

			// extend the Search Term
			if (!workflowService.isUserInRole(EntityService.ACCESSLEVEL_MANAGERACCESS)) {
				// get user names list
				List<String> userNameList = workflowService.getUserNameList();
				// create search term
				String sAccessTerm = "($readaccess:ANONYMOUS";
				for (String aRole : userNameList) {
					if (!"".equals(aRole))
						sAccessTerm += " $readaccess:\"" + aRole + "\"";
				}
				sAccessTerm += ") AND ";
				sSearchTerm = sAccessTerm + sSearchTerm;
			}
			logger.fine("lucene search query final:" + sSearchTerm);

			if (!"".equals(sSearchTerm)) {
				parser.setAllowLeadingWildcard(true);

				// set default operator?
				if (defaultOperator != null)
					parser.setDefaultOperator(defaultOperator);

				TopDocs topDocs = null;
				if (sortOrder != null) {
					logger.fine("lucene sortOrder= '" + sortOrder + "' ");

					topDocs = searcher.search(parser.parse(sSearchTerm), maxResult, sortOrder);
				} else {
					topDocs = searcher.search(parser.parse(sSearchTerm), maxResult);
				}

				logger.fine("lucene total hits=" + topDocs.totalHits);

				// Get an array of references to matched documents
				ScoreDoc[] scoreDosArray = topDocs.scoreDocs;
				for (ScoreDoc scoredoc : scoreDosArray) {
					// Retrieve the matched document and show relevant details
					Document doc = searcher.doc(scoredoc.doc);

					String sID = doc.get("$uniqueid");
					logger.fine("lucene lookup $uniqueid=" + sID);
					ItemCollection itemCol = workflowService.getEntityService().load(sID);
					if (itemCol != null) {
						workitems.add(itemCol);
					} else {
						logger.warning("lucene index returned un unreadable workitem : " + sID);
						// this situation happens if the search index returned
						// documents the current user has no read access.
						// this should normally avoided with the $readaccess
						// search phrase! So if this happens we need to check
						// the createDocument method!
					}
				}

			}

			searcher.getIndexReader().close();

			logger.fine("lucene search result computed in " + (System.currentTimeMillis() - ltime) + " ms");
		} catch (Exception e) {
			logger.warning("ucene search error!");
			e.printStackTrace();
		}

		return workitems;
	}

	/**
	 * Creates a Lucene FSDirectory Instance. The method uses the proeprty
	 * LockFactory to set a custom LockFactory.
	 * 
	 * For example: org.apache.lucene.store.SimpleFSLockFactory
	 * 
	 * @return
	 * @throws IOException
	 */
	Directory createIndexDirectory(Properties prop) throws IOException {

		logger.fine("lucene createIndexDirectory...");
		// read configuration
		String sLuceneLockFactory = prop.getProperty("lucence.lockFactory");
		String sIndexDir = prop.getProperty("lucence.indexDir");

		Directory indexDir = FSDirectory.open(new File(sIndexDir));

		// set lockFactory
		// NativeFSLockFactory: using native OS file locks
		// SimpleFSLockFactory: recommended for NFS based access to an index,
		if (sLuceneLockFactory != null && !"".equals(sLuceneLockFactory)) {
			// indexDir.setLockFactory(new SimpleFSLockFactory());
			// set factory by class name
			logger.fine("lucene set LockFactory=" + sLuceneLockFactory);
			try {
				Class<?> fsFactoryClass;
				fsFactoryClass = Class.forName(sLuceneLockFactory);
				LockFactory factoryInstance = (LockFactory) fsFactoryClass.newInstance();
				indexDir.setLockFactory(factoryInstance);
			} catch (ClassNotFoundException e) {
				logger.severe("lucene error - unable to create Lucene LockFactory!");
				e.printStackTrace();
				return null;
			} catch (InstantiationException e) {
				logger.severe("lucene error - unable to create Lucene LockFactory!");
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				logger.severe("lucene error - unable to create Lucene LockFactory!");
				e.printStackTrace();
				return null;
			}
		}
		return indexDir;
	}

	/**
	 * returns a IndexSearcher instance
	 * 
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	IndexSearcher createIndexSearcher(Properties prop) throws Exception {
		logger.fine("lucene createIndexSearcher...");

		Directory indexDir = createIndexDirectory(prop);
		IndexReader reader = DirectoryReader.open(indexDir);
		IndexSearcher searcher = new IndexSearcher(reader);

		return searcher;
	}

	/**
	 * Returns in instance of a QueyParser based on a KeywordAnalyser
	 * 
	 * @param prop
	 * @return
	 */
	QueryParser createQueryParser(Properties prop) {
		Analyzer analyzer = null;
		String analyserClass = prop.getProperty("lucence.analyzerClass", LuceneUpdateService.DEFAULT_ANALYSER);
		try {
			analyzer = (Analyzer) Class.forName(analyserClass).newInstance();
			logger.fine("Analyzer Class: " + analyserClass);
		} catch (Exception e) {
			logger.warning("Unable to instanciate Analyzer Class '" + analyserClass + "' - verify imixs.properties");
			logger.warning("Create default analyzer: " + LuceneUpdateService.DEFAULT_ANALYSER);
			analyzer = new ClassicAnalyzer();
		}

		QueryParser parser = new QueryParser("content", analyzer);

		// check the default operator
		String defaultOperator = prop.getProperty("lucene.defaultOperator");
		if (defaultOperator != null && "AND".equals(defaultOperator.toUpperCase())) {
			logger.fine("DefaultOperator: AND");
			parser.setDefaultOperator(Operator.AND);
		}

		return parser;
	}

	/**
	 * This helper method escapes wildcard tokens found in a lucene search term.
	 * The method can be used by clients to prepare a search phrase.
	 * 
	 * The method rewrites the lucene <code>QueryParser.escape</code> method and
	 * did not! escape '*' char.
	 * 
	 * @param searchTerm
	 * @return
	 */
	public static String escapeSearchTerm(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return searchTerm;
		}

		// this is the code from the QueryParser.escape() method without the '*' char!
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < searchTerm.length(); i++) {
			char c = searchTerm.charAt(i);
			// These characters are part of the query syntax and must be escaped
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^'
					|| c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~' || c == '?'
					|| c == '|' || c == '&' || c == '/') {
				sb.append('\\');
			}
			sb.append(c);
		}
		return sb.toString();

	}

}
