package org.imixs.workflow.datev.imports;

import java.util.Collection;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.scheduler.SchedulerException;
import org.imixs.workflow.engine.scheduler.SchedulerService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.InvalidAccessException;
import org.imixs.workflow.exceptions.QueryException;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * This Service provides methods to read and save the DATEV configuration
 * 
 * 
 * @author rsoika
 *
 */
@DeclareRoles({ "org.imixs.ACCESSLEVEL.NOACCESS", "org.imixs.ACCESSLEVEL.READERACCESS",
		"org.imixs.ACCESSLEVEL.AUTHORACCESS", "org.imixs.ACCESSLEVEL.EDITORACCESS",
		"org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@RolesAllowed({ "org.imixs.ACCESSLEVEL.NOACCESS", "org.imixs.ACCESSLEVEL.READERACCESS",
		"org.imixs.ACCESSLEVEL.AUTHORACCESS", "org.imixs.ACCESSLEVEL.EDITORACCESS",
		"org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@LocalBean
public class DatevService {

	public static final String IMPORT_ERROR = "IMPORT_ERROR";

	public static final String DOCUMENT_TYPE = "configuration";

	@EJB
	DocumentService documentService;

	private static Logger logger = Logger.getLogger(DatevService.class.getName());

	/**
	 * Loads the scheduler configuration entity by name. The method returns null if
	 * no scheduler configuration exits.
	 * 
	 * @return
	 */
	public ItemCollection loadConfiguration(String name) {
		try {
			// support deprecated txtname attribure
			String sQuery = "(type:\"" + DOCUMENT_TYPE + "\" AND (name:\"" + name + "\" OR txtname:\"" + name
					+ "\" ) )";
			Collection<ItemCollection> col = documentService.find(sQuery, 1, 0);
			// check if we found a scheduler configuration
			if (col.size() > 0) {
				ItemCollection configuration = col.iterator().next();
				return configuration;
			}
		} catch (QueryException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * This method saves the scheduler configuration. The method ensures that the
	 * following properties are set to default.
	 * <ul>
	 * <li>type</li>
	 * <li>name</li>
	 * <li>$writeAccess</li>
	 * <li>$readAccess</li>
	 * </ul>
	 * The method also updates the timer details of a running timer.
	 * 
	 * @return
	 * @throws AccessDeniedException
	 */
	public ItemCollection saveConfiguration(ItemCollection configuration) {

		// validate and migrate deprecated 'txtname' field
		String name = configuration.getItemValueString("name");
		if (name.isEmpty()) {
			name = configuration.getItemValueString("txtname");
			configuration.replaceItemValue("name", name);
		}
		if (name == null || name.isEmpty()) {
			throw new InvalidAccessException(SchedulerService.class.getName(), SchedulerException.INVALID_WORKITEM,
					" scheduler configuraiton must contain the item 'name'");
		}

		// update write and read access
		configuration.replaceItemValue("type", DOCUMENT_TYPE);
		configuration.replaceItemValue("$snapshot.history", 1);
		configuration.replaceItemValue("$writeAccess", "org.imixs.ACCESSLEVEL.MANAGERACCESS");
		configuration.replaceItemValue("$readAccess", "org.imixs.ACCESSLEVEL.MANAGERACCESS");

		// save entity in new transaction
		configuration = documentService.save(configuration);

		return configuration;
	}

}
