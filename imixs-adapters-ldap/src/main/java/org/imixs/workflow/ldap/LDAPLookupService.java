package org.imixs.workflow.ldap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

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

import org.imixs.marty.profile.ProfileEvent;
import org.imixs.marty.profile.ProfileService;
import org.imixs.workflow.ItemCollection;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

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

    public static final int MAX_RESULT = 20;
    public static final int TIME_LIMIT = 20000;
    public static final String LDAP_SEARCH_CONTEXT = "ldap.search-context";
    public static final String LDAP_SEARCH_FILTER_DN = "ldap.dn-search-filter";
    public static final String LDAP_SEARCH_FILTER_GROUP = "ldap.group-search-filter";
    public static final String LDAP_SEARCH_FILTER_PHRASE = "ldap.search-filter-phrase";
    public static final String LDAP_USER_ATTRIBUTES = "ldap.user-attributes";

    private boolean enabled = false;
    private boolean initializedDirContextFailed = false;
    private Properties configurationProperties = null;

    private String _dnSearchFilter = null;
    private String _searchFilterPhrase = null;
    private String _groupSearchFilter = null;
    private String _searchContext = null;
    private String[] userAttributesLDAP = null; // ldap attribute names
    private String[] userAttributesImixs = null; // imixs attributes names if |
                                                 // defined

    @Inject
    protected Event<LDAPProfileEvent> ldapProfileEvents;

    @EJB
    ProfileService profileService;

    @EJB
    LDAPCache ldapCache;

    private static Logger logger = Logger.getLogger(LDAPLookupService.class.getName());

    @PostConstruct
    void init() {
        try {
            logger.finest("......init lookup service");

            // load configuration entity
            configurationProperties = new Properties();
            // first test if a custom location for the ldap.properties file is defined. If
            // no we default to the imixs.properties

            String ldapLookupConfig = System.getenv("LDAP_LOOKUP_CONFIG");
            if (ldapLookupConfig != null && !ldapLookupConfig.isEmpty()) {
                logger.info("Read LDAP Lookup Configuration from: " + ldapLookupConfig);
                try (InputStream input = new FileInputStream("path/to/config.properties")) {
                    // load a properties file
                    configurationProperties.load(input);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // default to imixs.properties
                try {
                    configurationProperties.load(
                            Thread.currentThread().getContextClassLoader().getResource("imixs.properties")
                                    .openStream());
                } catch (Exception e) {
                    logger.warning("LDAPLookupService unable to find imixs.properties in current classpath");
                    e.printStackTrace();
                }
            }

            // skip if no configuration
            if (configurationProperties == null) {
                logger.severe("Missing imixs.properties!");
                return;
            }

            // initialize ldap configuration....
            logger.finest("......read LDAP configuration...");
            setSearchContext(configurationProperties.getProperty(LDAP_SEARCH_CONTEXT, ""));
            // issue #64 - backward compatibility.
            if (_searchContext.isEmpty()) {
                // try deprecated propertyname ldap.search.context
                setSearchContext(configurationProperties.getProperty("ldap.search.context", ""));
                if (!_searchContext.isEmpty()) {
                    // we take the deprecated value but we log a warning
                    logger.warning(
                            "imixs property 'ldap.search.context' is deprecated and should be replaced with 'ldap.search-context'");
                }
            }

            logger.finest("......" + LDAP_SEARCH_CONTEXT + "=" + getSearchContext());
            setDnSearchFilter(configurationProperties.getProperty(LDAP_SEARCH_FILTER_DN, "(uid=%u)"));
            logger.finest("......" + LDAP_SEARCH_FILTER_DN + "=" + getDnSearchFilter());

            setSearchFilterPhrase(
                    configurationProperties.getProperty(LDAP_SEARCH_FILTER_PHRASE, "(|(mail=?*)(cn=?*))"));
            logger.finest("......" + LDAP_SEARCH_FILTER_PHRASE + "=" + getSearchFilterPhrase());

            setGroupSearchFilter(configurationProperties.getProperty(LDAP_SEARCH_FILTER_GROUP, "(member=%d)"));
            logger.finest("......" + LDAP_SEARCH_FILTER_GROUP + "=" + getGroupSearchFilter());
            // read user attributes
            String sAttributes = configurationProperties.getProperty(LDAP_USER_ATTRIBUTES, "uid,SN,CN,mail");
            logger.finest("......" + LDAP_USER_ATTRIBUTES + "=" + sAttributes);

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
                    userAttributesLDAP[i] = aAttr.substring(0, sPos).trim();
                    userAttributesImixs[i] = aAttr.substring(sPos + 1).trim();
                } else {
                    userAttributesLDAP[i] = aAttr;
                    userAttributesImixs[i] = aAttr;
                }
                // debug info about the resolved attribute and item names
                logger.finest("......attributesLDAP-" + i + "=" + userAttributesLDAP[i]);
                logger.finest("......attributesImixs-" + i + "=" + userAttributesImixs[i]);
            }

            // test if ldap is enabled (needed for interceptors)...
            logger.finest("......verifing LDAP connection...");
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
                logger.fine("LDAP connection: OK");
            } else {
                logger.warning("LDAP connection: FAILED");
            }

        } catch (Exception e) {
            logger.severe("Unable to initalize LDAPGroupLookupService");
            e.printStackTrace();
        }
    }

    public String getDnSearchFilter() {
        return _dnSearchFilter;
    }

    public void setDnSearchFilter(String dnSearchFilter) {
        this._dnSearchFilter = dnSearchFilter;
    }

    public String getSearchFilterPhrase() {
        return _searchFilterPhrase;
    }

    public void setSearchFilterPhrase(String searchFilterPhrase) {
        this._searchFilterPhrase = searchFilterPhrase;
    }

    public String getGroupSearchFilter() {
        return _groupSearchFilter;
    }

    public void setGroupSearchFilter(String groupSearchFilter) {
        this._groupSearchFilter = groupSearchFilter;
    }

    public String getSearchContext() {
        return _searchContext;
    }

    public void setSearchContext(String searchContext) {
        this._searchContext = searchContext;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the ldap attributes for a given user. If no user was found in LDAP
     * the method returns null. The method uses an internal cache.
     * 
     * @param aUID - user id
     * @return ItemCollection containing the user attributes or null if no
     *         attributes where found.
     */
    public ItemCollection findUser(String aUID) {
        return findUser(aUID, false);
    }

    /**
     * Returns the ldap attributes for a given user. If no user was found in LDAP
     * the method returns null.
     * <p>
     * If the boolean 'refresh' is true the method lookup the user in any case with
     * a search query and updates the cache.
     * 
     * @param aUID    - user id
     * @param refresh - if true, cache will be refreshed.
     * @return ItemCollection containing the user attributes or null if no
     *         attributes where found.
     */
    public ItemCollection findUser(String aUID, boolean refresh) {

        if (aUID == null || aUID.isEmpty()) {
            return null;
        }

        // also null objects can be returned here (if LDAPCache was serialized.)
        if (!refresh && ldapCache.contains(aUID)) {
            logger.finest("......fetching user: '" + aUID + "' from cache...");
            ItemCollection user = ldapCache.getUser(aUID);
            if (user != null && user.getAllItems().size() > 0) {
                return user;
            }
            // user object is expired
            logger.warning("cached LDAP object expired: '" + aUID + "'");
        }
        long l = System.currentTimeMillis();
        // start lookup
        LdapContext ldapCtx = null;
        try {
            logger.finest("......find user: '" + aUID + "'");
            ldapCtx = getDirContext();
            if (ldapCtx != null) {
                ItemCollection user = fetchUser(aUID, ldapCtx);
                if (user != null) {
                    // cache user attributes (also null will be set if no entry was
                    // found!)
                    logger.finest("......put user: '" + aUID + "' into cache.");
                    ldapCache.putUser(aUID, user);
                    logger.fine(
                            "... lookup user '" + aUID + "' successfull in " + (System.currentTimeMillis() - l) + "ms");
                } else {
                    logger.fine("no LDAP object found: '" + aUID + "'");
                }
                return user;
            } else {
                logger.warning("LDAP DirContext could not be opened!");
                return null;
            }

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
     * This method is used to put a user object into the cache. The method can be
     * called form a external service interface.
     * 
     * @param aUID - userid
     * @param user - user profile
     */
    public void cacheUser(String aUID, ItemCollection user) {
        ldapCache.putUser(aUID, user);
    }

    /**
     * Returns a list of user entries form the ldap based on a search phrase
     * 
     * @param searchPhrase - search Phrase
     * @return ItemCollection containing the user attributes or null if no
     *         attributes where found.
     */
    public List<ItemCollection> searchUserList(String searchPhrase) {

        // start lookup
        LdapContext ldapCtx = null;
        try {
            logger.finest("......serachUserList: " + searchPhrase);
            ldapCtx = getDirContext();
            return fetchUserList(searchPhrase, ldapCtx);
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
     * @param aUID - user unique id
     * @return string array of group names
     */
    public String[] findGroups(String aUID) {
        // test cache...
        String[] groups = (String[]) ldapCache.getGroups(aUID);
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
            ldapCache.putGroups(aUID, groups);

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
     * Returns all attributes for a given user in an ItemCollection. If ldap service
     * is disabled or the user was not found then the method returns null.
     * <p>
     * The method makes a direct ldap lookup. No cache is used.
     * <p>
     * It is recommended to use the method findUser instead of the method
     * lookupLdapAttributes to use the internal caching mechanism.
     * 
     * @param aUID - user id
     * @return ItemCollection - containing the user attributes or null if no entry
     *         was found
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ItemCollection lookupLdapAttributes(String aUID) {
        ItemCollection user = null;
        LdapContext ldapCtx = null;
        NamingEnumeration<SearchResult> answer = null;
        String sDN = null;
        if (!enabled || aUID == null || aUID.isEmpty()) {
            return null;
        }

        // start lookup

        try {
            logger.finest("......find user: '" + aUID + "'");
            ldapCtx = getDirContext();

            if (ldapCtx != null) {
                user = new ItemCollection();
                SearchControls ctls = new SearchControls();
                ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                // return all attributes..
                // ctls.setReturningAttributes(userAttributesLDAP);

                String searchFilter = getDnSearchFilter().replace("%u", aUID);
                logger.finest("......lookup: searchContext=" + getSearchContext());
                logger.finest("......lookup: searchFilter=" + searchFilter);
                answer = ldapCtx.search(getSearchContext(), searchFilter, ctls);
                // if nothing found we return null....
                if (answer == null || !answer.hasMore()) {
                    return null;
                }

                // fetch the first result....
                SearchResult entry = (SearchResult) answer.next();
                sDN = entry.getName();
                logger.finest("......DN= " + sDN);

                Attributes attributes = entry.getAttributes();

                for (NamingEnumeration ae = attributes.getAll(); ae.hasMore();) {

                    Attribute attr = (Attribute) ae.next();
                    String itemName = attr.getID();
                    if (attr != null) {
                        NamingEnumeration<?> values = attr.getAll();

                        Vector valueList = new Vector();
                        while (values.hasMore()) {
                            valueList.add(values.next());
                        }
                        if (valueList.size() > 0)
                            user.replaceItemValue(itemName, valueList);
                    }
                }

                if (sDN == null) {
                    // empty user entry
                    sDN = aUID;
                    user.replaceItemValue("dn", sDN);
                }
            } else {
                logger.warning("missing ldap context obejct (context==null)!");
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

            if (ldapCtx != null)
                try {
                    ldapCtx.close();
                    ldapCtx = null;
                } catch (NamingException e) {
                    e.printStackTrace();
                }
        }

        return user;
    }

    /**
     * This method reacts on CDI events of the type ProfileEvent and lookups the
     * profile data in the ldap directory
     * 
     */

    public void onEvent(@Observes ProfileEvent event) {

        if (event.getEventType() == ProfileEvent.ON_PROFILE_LOOKUP) {
            logger.finest("......intercept lookup profile method");
            String sUserID = event.getUserId();
            logger.finest("......userid=" + sUserID);
            ItemCollection ldapUser = findUser(sUserID);
            event.setProfile(ldapUser);
        }

        if (event.getEventType() == ProfileEvent.ON_PROFILE_CREATE) {
            ItemCollection profile = event.getProfile();
            if (profile == null) {
                logger.severe("unable to create profile for userid '" + event.getUserId() + "' no profile object");
            } else {
                // update profile?
                updateProfileLDAPData(event.getUserId(), profile);
            }

        }

    }

    /**
     * This method updates a profile with ldap data.
     * 
     */
    @SuppressWarnings("unchecked")
    public void updateProfileLDAPData(String userID, ItemCollection profile) {
        // compare attributes....
        ItemCollection ldapUser = findUser(userID);
        boolean bUpdate = false;
        if (ldapUser != null) {
            logger.finest("......ldap entry found, verifing attributes...");

            // print all
            Map<String, Object> items = (Map<String, Object>) ldapUser.getItemList();

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                String key = entry.getKey();

                if ("txtname".equalsIgnoreCase(key)) {
                    // in case of txtName we do not Update !!
                    continue;
                }
                if ("name".equalsIgnoreCase(key)) {
                    // in case of txtName we do not Update !!
                    continue;
                }

                Object value = entry.getValue();
                logger.finest(" ...... " + key + "=" + value);

                if (!profile.getItemValue(key).equals(ldapUser.getItemValue(key))) {
                    profile.replaceItemValue(key, ldapUser.getItemValue(key));
                    bUpdate = true;
                }
            }
            // save profile?
            if (bUpdate) {
                logger.fine("Updating user profile '" + userID + "' with new ldap attributes....");
            }
        } else {
            logger.warning("userid " + userID + " not found!");
        }
    }

    /**
     * returns the default attributes for a given user in an ItemCollection. If ldap
     * service is disabled or the user was not found then the method returns null.
     * 
     * @param aUID - user id
     * @return ItemCollection - containing the user attributes or null if no entry
     *         was found
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private ItemCollection fetchUser(String aUID, LdapContext ldapCtx) {
        ItemCollection user = null;
        String sDN = null;
        if (!enabled || aUID == null || aUID.isEmpty()) {
            return null;
        }

        if (ldapCtx != null) {

            NamingEnumeration<SearchResult> answer = null;
            try {
                user = new ItemCollection();
                SearchControls ctls = new SearchControls();
                ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                ctls.setReturningAttributes(userAttributesLDAP);

                String searchFilter = getDnSearchFilter().replace("%u", aUID);
                logger.finest("......fetchUser: searchContext=" + getSearchContext());
                logger.finest("......fetchUser: searchFilter=" + searchFilter);
                answer = ldapCtx.search(getSearchContext(), searchFilter, ctls);
                // if nothing found we return null....
                if (answer == null || !answer.hasMore()) {
                    return null;
                }

                // fetch the first result....
                SearchResult entry = (SearchResult) answer.next();
                sDN = entry.getName();
                logger.finest("......DN= " + sDN);

                Attributes attributes = entry.getAttributes();
                // fetch all attributes
                for (int i = 0; i < userAttributesLDAP.length; i++) {

                    Attribute atr = attributes.get(userAttributesLDAP[i]);

                    logger.finest("......fetch attribute: '" + userAttributesLDAP[i] + "' = " + atr);
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
        } else {
            logger.warning("missing ldap context obejct (context==null)!");
        }

        // adapt userprofile
        // fire event
        if (ldapProfileEvents != null) {
            if (user != null) {
                LDAPProfileEvent event = new LDAPProfileEvent(user);
                ldapProfileEvents.fire(event);
                ItemCollection newUserObject = event.getProfile();
                if (newUserObject != null) {
                    user = newUserObject;
                } else {
                    logger.warning("LDAPProfileEvent returned a null object for '" + aUID + "'");
                }
            }
        } else {
            logger.warning("CDI Support is missing - LDAPProfileEvent wil not be fired");
        }

        return user;
    }

    /**
     * This method searches a list of user objects by a given search phrase.
     * 
     * @param searchPhrase
     * @param ldapCtx
     * @return - list of itemCollection objects.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<ItemCollection> fetchUserList(String searchPhrase, LdapContext ldapCtx) {
        NamingEnumeration<SearchResult> answer = null;
        ArrayList<ItemCollection> result = new ArrayList<ItemCollection>();

        if (searchPhrase == null || searchPhrase.isEmpty()) {
            return result;
        }

        long l = System.currentTimeMillis();
        try {

            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            ctls.setReturningAttributes(userAttributesLDAP);
            ctls.setCountLimit(MAX_RESULT);
            ctls.setTimeLimit(TIME_LIMIT);
            String searchFilter = getSearchFilterPhrase().replace("?", searchPhrase);
            logger.finest("......fetchUser: searchFilter = " + searchFilter);
            answer = ldapCtx.search(getSearchContext(), searchFilter, ctls);
            if (answer == null) {
                logger.finest("......search returend null");
                return result;
            }

            logger.finest("......computing result list...");
            while (answer.hasMore()) {
                SearchResult entry = (SearchResult) answer.next();
                ItemCollection itemColUser = new ItemCollection();
                String sDN = entry.getName();
                logger.finest("......DN = " + sDN);

                Attributes attributes = entry.getAttributes();
                // fetch all attributes
                for (int i = 0; i < userAttributesLDAP.length; i++) {

                    Attribute atr = attributes.get(userAttributesLDAP[i]);

                    // logger.finest("......fetch attribute: '" + userAttributesLDAP[i] + "' = " +
                    // atr);
                    if (atr != null) {
                        NamingEnumeration<?> values = atr.getAll();

                        Vector valueList = new Vector();
                        while (values.hasMore()) {
                            valueList.add(values.next());
                        }
                        if (valueList.size() > 0) {
                            itemColUser.replaceItemValue(userAttributesImixs[i], valueList);
                        }
                    }
                }

                // adapt userprofile
                // fire event
                if (ldapProfileEvents != null) {
                    LDAPProfileEvent event = new LDAPProfileEvent(itemColUser);
                    ldapProfileEvents.fire(event);
                    itemColUser = event.getProfile();
                } else {
                    logger.warning("CDI Support is missing - LDAPProfileEvent wil not be fired");
                }

                // add object to list
                result.add(itemColUser);
                if (result.size() >= MAX_RESULT) {
                    break;
                    // to avoid exception
                }
            }

        } catch (NamingException e) {
            logger.warning("ldap search error: " + e.getMessage());
            if (logger.isLoggable(java.util.logging.Level.FINEST)) {
                e.printStackTrace();
            }
        } finally {
            if (answer != null)
                try {
                    answer.close();
                    answer = null;
                } catch (NamingException e) {
                    e.printStackTrace();
                }
        }

        logger.fine(
                "......search returend " + result.size() + " entries in " + (System.currentTimeMillis() - l) + "ms");
        return result;

    }

    /**
     * Returns a string array containing all group names for a given uid. If not
     * groups are found or the uid did not exist the method returns null.
     * 
     * @param aUID - user id
     * @return array list of user groups or null if no entry was found
     */
    private String[] fetchGroups(String aUID, LdapContext ldapCtx) {
        String sDN = null;
        Vector<String> vGroupList = null;
        String[] groupArrayList = null;

        if (!enabled || aUID == null || aUID.isEmpty()) {
            return null;
        }

        NamingEnumeration<SearchResult> answer = null;
        try {

            vGroupList = new Vector<String>();

            String groupNamePraefix = configurationProperties.getProperty("group-name-praefix");

            ItemCollection user = fetchUser(aUID, ldapCtx);
            // return null if user was not found
            if (user == null)
                return null;

            sDN = user.getItemValueString("dn");

            logger.finest("......fetchGroups for: " + sDN);

            String returnedAtts[] = { "cn" };

            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            ctls.setReturningAttributes(returnedAtts);

            String searchFilter = getGroupSearchFilter().replace("%d", sDN);
            logger.finest("......groupSearchFilter:" + searchFilter);

            answer = ldapCtx.search(getSearchContext(), searchFilter, ctls);
            if (answer == null)
                return null;

            while (answer.hasMore()) {
                SearchResult entry = (SearchResult) answer.next();
                String sGroupName = entry.getName();

                // TODO : it is not possible to ask for the attribute cn - maybe
                // a domino problem so we take the name....
                /*
                 * Attributes attrs = entry.getAttributes(); Attribute attr = attrs.get("cn");
                 * if (attr != null) sGroupName = (String) attr.get(0);
                 */
                sGroupName = sGroupName.substring(3);
                if (sGroupName.indexOf(',') > -1)
                    sGroupName = sGroupName.substring(0, sGroupName.indexOf(','));

                // test groupname praefix..
                if (groupNamePraefix != null && !"".equals(groupNamePraefix)
                        && !sGroupName.startsWith(groupNamePraefix))
                    continue;

                logger.finest("......found Group= " + sGroupName);
                vGroupList.add(sGroupName);
            }

            logger.finest("......found " + vGroupList.size() + " groups");

            groupArrayList = new String[vGroupList.size()];
            vGroupList.toArray(groupArrayList);

            logger.finest("......put groups into cache for '" + aUID + "'");

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
     * This method lookups the ldap context either from a Jndi name 'LdapJndiName'
     * (DisableJndi=false) or manually if DisableJndi=true.
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
        long l = System.currentTimeMillis();

        // test if configuration is available
        if (configurationProperties == null || initializedDirContextFailed == true) {
            return null;
        }

        // try to load dirContext...
        Context initCtx;
        try {
            initCtx = new InitialContext();
            // test if manually ldap context should be build
            String sDisabled = configurationProperties.getProperty("ldap.disable-jndi");

            logger.finest("......ldap.disable-jndi=" + sDisabled);

            if (sDisabled != null && "true".equals(sDisabled.toLowerCase())) {
                logger.finest("......jndi lookup LdapContext.....");
                Hashtable env = new Hashtable();

                // scann all properties starting with 'java.naming'
                Enumeration<Object> keys = configurationProperties.keys();
                while (keys.hasMoreElements()) {
                    String sKey = keys.nextElement().toString();
                    if (sKey.startsWith("java.naming")) {
                        env.put(sKey, configurationProperties.getProperty(sKey));
                        logger.finest("......Set env key: " + sKey + "=" + configurationProperties.getProperty(sKey));
                    }
                }

                // set default params...
                env.put("java.naming.factory.initial", configurationProperties
                        .getProperty("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory"));
                env.put("java.naming.security.authentication",
                        configurationProperties.getProperty("java.naming.security.authentication", "simple"));

                ldapCtx = new InitialLdapContext(env, null);
                logger.finest("......jndi lookup LdapContext successful! ");

            } else {
                // read GlassFish ldap_jndiName from configuration
                ldapJndiName = configurationProperties.getProperty("ldap.jndi-name");
                if ("".equals(ldapJndiName))
                    ldapJndiName = "org.imixs.ldap.directory";
                logger.finest("......lookup LDAP Ctx from pool '" + ldapJndiName + "' .....");
                ldapCtx = (LdapContext) initCtx.lookup(ldapJndiName);
            }

            logger.fine("......LdapContext initialized in " + (System.currentTimeMillis() - l) + " ms");

        } catch (NamingException | RuntimeException e) {
            logger.severe("Failed to open ldap context: " + e.getMessage());
            initializedDirContextFailed = true;
            if (logger.isLoggable(java.util.logging.Level.FINE))
                e.printStackTrace();
        }

        return ldapCtx;
    }
}
