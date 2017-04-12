package org.imixs.workflow.ldap;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.imixs.workflow.ItemCollection;

/**
 * This EJB provides a ldap lookup service for user informations
 * 
 * The bean reads its configuration from the configuration property file located
 * in the glassfish domains config folder
 * (GLASSFISH_DOMAIN/config/imixs-office-ldap.properties).
 * 
 * 
 * 
 * IMPORTANT!
 * 
 * The LDAPLookupService can not use the EJB PropertyService because of the
 * PropertyInterceptor Class which will lead into a endless loop between
 * LDAPGroupInterceptor and PropertyInterceptor !!!
 * 
 * For that reason the proertyService is used manually here!!
 * 
 * 
 * 
 * 
 * @version 1.0
 * @author rsoika
 * 
 */
@Stateless
@LocalBean
public class LDAPLookupService {

	private boolean enabled = false;
	private Properties configurationProperties = null;

	private String dnSearchFilter = null;
	private String groupSearchFilter = null;
	private String searchContext = null;
	private String[] userAttributesLDAP = null; // ldap attribute names
	private String[] userAttributesImixs = null; // imixs attributes names if |
													// defined

	@EJB
	LDAPCache ldapCache;

	// Disabled!!
	// @EJB
	// PropertyService xpropertyService;

	private static Logger logger = Logger.getLogger(LDAPLookupService.class.getName());

	@PostConstruct
	void init() {
		try {
			logger.finest("init lookup service");
			// load confiugration entity

			// Disabled because of confglict with LDAPGroupInterceptor
			// configurationProperties = propertyService.getProperties();
			configurationProperties = new Properties();
			try {
				configurationProperties.load(
						Thread.currentThread().getContextClassLoader().getResource("imixs.properties").openStream());
			} catch (Exception e) {
				logger.warning("LDAPLookupService unable to find imixs.properties in current classpath");
				e.printStackTrace();
			}

			// skip if no configuration
			if (configurationProperties == null)
				return;

			// initialize ldap configuration....
			logger.fine("read LDAP configuration...");
			searchContext = configurationProperties.getProperty("ldap.search-context", "");
			logger.fine("ldap.search-context=" +searchContext );
			dnSearchFilter = configurationProperties.getProperty("ldap.dn-search-filter", "(uid=%u)");
			logger.fine("ldap.dn-search-filter=" +dnSearchFilter );
			groupSearchFilter = configurationProperties.getProperty("ldap.group-search-filter", "(member=%d)");
			logger.fine("ldap.group-search-filter=" +groupSearchFilter );
			// read user attributes
			String sAttributes = configurationProperties.getProperty("ldap.user-attributes", "uid,SN,CN,mail");
			logger.fine("ldap.user-attributes=" +sAttributes );
			
			
			String[] userAttributeList = sAttributes.split(",");

			// now we split up the ldap attribute name form the imixs name if a
			// | is contained
			userAttributesLDAP = new String[userAttributeList.length];
			userAttributesImixs = new String[userAttributeList.length];

			// the userAttributesLDAP hold only the left part of a attribute
			// name separated with a |
			// e.g. : mail|txtEmail
			for (int i = 0; i < userAttributeList.length; i++) {
				String aAttr = userAttributeList[i].trim();
				int sPos = aAttr.indexOf('|');
				if (sPos > 0) {
					userAttributesLDAP[i] = aAttr.substring(0, sPos ).trim();
					userAttributesImixs[i] = aAttr.substring(sPos + 1).trim();
				} else {
					userAttributesLDAP[i] = aAttr;
					userAttributesImixs[i] = aAttr;
				}
				// debug info about the resolved attribute and item names
				logger.finest("attributesLDAP-" + i+"=" +userAttributesLDAP[i] );
				logger.finest("attributesImixs-" + i+"=" +userAttributesImixs[i] );
			}

			// test if ldap is enabled...
			logger.fine("Verifing LDAP connection...");
			enabled = false;
			LdapContext ldapCtx = null;
			try {
				ldapCtx = getDirContext();
				enabled = (ldapCtx != null);
			} finally {
				try {
					if (ldapCtx != null) {
						ldapCtx.close();
						ldapCtx = null;
					}
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}

			if (enabled) {
				logger.info("LDAP connection: OK");
			} else {
				logger.warning("LDAP connection: FAILED");
			}

		} catch (Exception e) {
			logger.severe("Unable to initalize LDAPGroupLookupService");
			e.printStackTrace();
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Returns the ldap attributes for a given user. If no user was found in
	 * LDAP the method returns null.
	 * 
	 * @param aUID
	 *            - user id
	 * @return ItemCollection containing the user attributes or null if no
	 *         attributes where found.
	 */
	public ItemCollection findUser(String aUID) {

		// also null objects can be returned here (if no ldap attributes exist)
		if (ldapCache.contains(aUID))
			return (ItemCollection) ldapCache.get(aUID);

		// start lookup
		LdapContext ldapCtx = null;
		try {
			logger.fine("find user: " + aUID);
			ldapCtx = getDirContext();
			ItemCollection user = fetchUser(aUID, ldapCtx);
			// cache user attributes (also null will be set if no entry was
			// found!)
			ldapCache.put(aUID, user);
			return user;

		} finally {
			if (ldapCtx != null)
				try {
					ldapCtx.close();
					ldapCtx = null;
				} catch (NamingException e) {
					e.printStackTrace();
				}

		}

	}

	/**
	 * Returns a string array containing all group names for a given UID. If no
	 * groups exist or the uid was not found the method returns an empty string
	 * array!.
	 * 
	 * 
	 * @param aUID
	 *            - user unique id
	 * @return string array of group names
	 */
	public String[] findGroups(String aUID) {
		// test cache...
		String[] groups = (String[]) ldapCache.get(aUID + "-GROUPS");
		if (groups != null) {
			return groups;
		}

		LdapContext ldapCtx = null;
		try {
			logger.fine("find user groups for: " + aUID);
			ldapCtx = getDirContext();
			groups = fetchGroups(aUID, ldapCtx);
			if (groups == null)
				groups = new String[0];
			if (logger.isLoggable(java.util.logging.Level.FINE)) {
				String groupList = "";
				for (String aGroup : groups)
					groupList += "'" + aGroup + "' ";
				logger.fine("groups found for " + aUID + "=" + groupList);
			}

			// cache Group list
			ldapCache.put(aUID + "-GROUPS", groups);

			return groups;

		} finally {
			if (ldapCtx != null)
				try {
					ldapCtx.close();
					ldapCtx = null;
				} catch (NamingException e) {
					e.printStackTrace();
				}
		}

	}

	/**
	 * returns the default attributes for a given user in an ItemCollection. If
	 * ldap service is disabled or the user was not found then the method
	 * returns null.
	 * 
	 * @param aUID
	 *            - user id
	 * @return ItemCollection - containing the user attributes or null if no
	 *         entry was found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ItemCollection fetchUser(String aUID, LdapContext ldapCtx) {
		ItemCollection user = null;
		String sDN = null;
		if (!enabled) {
			return null;
		}

		NamingEnumeration<SearchResult> answer = null;
		try {
			user = new ItemCollection();
			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctls.setReturningAttributes(userAttributesLDAP);

			String searchFilter = dnSearchFilter.replace("%u", aUID);
			logger.fine("fetchUser: searchContext=" + searchContext);
			logger.fine("fetchUser: searchFilter=" + searchFilter);
			answer = ldapCtx.search(searchContext, searchFilter, ctls);
			if (answer == null)
				return null;

			if (answer.hasMore()) {
				SearchResult entry = (SearchResult) answer.next();
				sDN = entry.getName();
				logger.finest("DN= " + sDN);

				Attributes attributes = entry.getAttributes();
				// fetch all attributes
				for (int i = 0; i < userAttributesLDAP.length; i++) {

					Attribute atr = attributes.get(userAttributesLDAP[i]);
					
					logger.fine("...fetch attribute: '" +userAttributesLDAP[i] +"' = "+ atr);	
					if (atr != null) {
						NamingEnumeration<?> values = atr.getAll();

						Vector valueList = new Vector();
						while (values.hasMore()) {
							valueList.add(values.next());
						}
						if (valueList.size() > 0)
							user.replaceItemValue(userAttributesImixs[i], valueList);
					}
				}				
			}

			if (sDN == null) {
				// empty user entry
				sDN = aUID;
				user.replaceItemValue("dn", sDN);
			}

		} catch (NamingException e) {
			// return null
			user = null;
			logger.warning("Unable to fetch DN for: " + aUID);
			logger.warning(e.getMessage());
			if (logger.isLoggable(java.util.logging.Level.FINEST))
				e.printStackTrace();

		} finally {
			if (answer != null)
				try {
					answer.close();
					answer = null;
				} catch (NamingException e) {
					e.printStackTrace();
				}
		}
		return user;
	}

	/**
	 * Returns a string array containing all group names for a given uid. If not
	 * groups are found or the uid did not exist the method returns null.
	 * 
	 * @param aUID
	 *            - user id
	 * @return array list of user groups or null if no entry was found
	 */
	private String[] fetchGroups(String aUID, LdapContext ldapCtx) {
		String sDN = null;
		Vector<String> vGroupList = null;
		String[] groupArrayList = null;

		if (!enabled)
			return null;

		NamingEnumeration<SearchResult> answer = null;
		try {

			vGroupList = new Vector<String>();

			String groupNamePraefix = configurationProperties.getProperty("group-name-praefix");

			ItemCollection user = fetchUser(aUID, ldapCtx);
			// return null if user was not found
			if (user == null)
				return null;

			sDN = user.getItemValueString("dn");

			logger.fine("fetchGroups for: " + sDN);

			String returnedAtts[] = { "cn" };

			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctls.setReturningAttributes(returnedAtts);

			String searchFilter = groupSearchFilter.replace("%d", sDN);
			logger.finest("search:" + searchFilter);

			answer = ldapCtx.search(searchContext, searchFilter, ctls);
			if (answer == null)
				return null;

			while (answer.hasMore()) {
				SearchResult entry = (SearchResult) answer.next();
				String sGroupName = entry.getName();

				// TODO : it is not possible to ask for the attribute cn - maybe
				// a
				// domino
				// problem so we take the name....
				/*
				 * Attributes attrs = entry.getAttributes(); Attribute attr =
				 * attrs.get("cn"); if (attr != null) sGroupName = (String)
				 * attr.get(0);
				 */
				sGroupName = sGroupName.substring(3);
				if (sGroupName.indexOf(',') > -1)
					sGroupName = sGroupName.substring(0, sGroupName.indexOf(','));

				// test groupname praefix..
				if (groupNamePraefix != null && !"".equals(groupNamePraefix)
						&& !sGroupName.startsWith(groupNamePraefix))
					continue;

				logger.finest("found Group= " + sGroupName);
				vGroupList.add(sGroupName);
			}

			logger.finest("found " + vGroupList.size() + " groups");

			groupArrayList = new String[vGroupList.size()];
			vGroupList.toArray(groupArrayList);

			logger.finest("put groups into cache for '" + aUID + "'");

		} catch (NamingException e) {
			groupArrayList = null;
			logger.warning("Unable to fetch groups for: " + aUID);
			if (logger.isLoggable(java.util.logging.Level.FINEST))
				e.printStackTrace();
		} finally {
			if (answer != null)
				try {
					answer.close();
					answer = null;
				} catch (NamingException e) {

					e.printStackTrace();
				}
		}
		return groupArrayList;
	}

	/**
	 * This method lookups the ldap context either from a Jndi name
	 * 'LdapJndiName' (DisableJndi=false) or manually if DisableJndi=true.
	 * 
	 * @see http://java.net/projects/imixs-workflow-marty/pages/Useldapgroups
	 * 
	 * @return LdapContext object
	 * @throws NamingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private LdapContext getDirContext() {
		String ldapJndiName = null;
		LdapContext ldapCtx = null;

		// test if configuration is available
		if (configurationProperties == null) {
			return null;
		}

		// try to load dirContext...

		Context initCtx;
		try {
			initCtx = new InitialContext();

			// test if manually ldap context should be build
			String sDisabled = configurationProperties.getProperty("ldap.disable-jndi");

			logger.fine("ldap.disable-jndi=" + sDisabled);

			if (sDisabled != null && "true".equals(sDisabled.toLowerCase())) {
				logger.fine("lookup LDAP Ctx manually.....");
				Hashtable env = new Hashtable();

				// scann all properties starting with 'java.naming'
				Enumeration<Object> keys = configurationProperties.keys();
				while (keys.hasMoreElements()) {
					String sKey = keys.nextElement().toString();
					if (sKey.startsWith("java.naming")) {
						env.put(sKey, configurationProperties.getProperty(sKey));
						logger.fine("Set key: " + sKey + "=" + configurationProperties.getProperty(sKey));
					}

				}

				// set default params...

				env.put("java.naming.factory.initial", configurationProperties
						.getProperty("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory"));
				env.put("java.naming.security.authentication",
						configurationProperties.getProperty("java.naming.security.authentication", "simple"));

				ldapCtx = new InitialLdapContext(env, null);
				logger.finest("Get DirContext Manually successful! ");

			} else {
				// read GlassFish ldap_jndiName from configuration
				ldapJndiName = configurationProperties.getProperty("ldap.jndi-name");
				if ("".equals(ldapJndiName))
					ldapJndiName = "org.imixs.office.ldap";
				logger.fine("lookup LDAP Ctx from pool '" + ldapJndiName + "' .....");
				ldapCtx = (LdapContext) initCtx.lookup(ldapJndiName);

			}

			logger.fine("Context initialized");

		} catch (NamingException e) {
			logger.severe("Failed to open ldap conntext: " + e.getMessage());
			if (logger.isLoggable(java.util.logging.Level.FINE))
				e.printStackTrace();
		}

		return ldapCtx;
	}
}
