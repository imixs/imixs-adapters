package org.imixs.workflow.ldap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;

import org.imixs.workflow.ItemCollection;

/**
 * This singleton ejb provides a cache to lookup ldap user informations. The
 * cache is used by the LDAPGroupLookupService EJB.
 * 
 * The bean reads its configuration from the configuration property file located
 * in the glassfish domains config folder
 * (GLASSFISH_DOMAIN/config/imixs-office-ldap.properties).
 * 
 * cache-size = maximum number of entries
 * 
 * cache-expires = milliseconds after the cache is discarded
 * 
 * The cache-size should be set to the value of minimum concurrent user
 * sessions. cache-expires specifies the expire time of the cache in
 * milliseconds.
 * 
 * @see http://www.adam-bien.com/roller/abien/entry/singleton_the_perfect_cache_facade
 * @version 1.0
 * @author rsoika
 * 
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class LDAPCache implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LDAPCache.class.getName());

	int DEFAULT_CACHE_SIZE = 30;
	int DEFAULT_EXPIRES_TIME = 60000;
	final static String GROUP_KEY_SUFIX = "-GROUPS";
	long expiresTime = 0;
	long lastReset = 0;
	Properties configurationProperties = null;
	Cache cache = null; // cache holds userdata

	@PostConstruct
	void init() {
		try {
			configurationProperties = new Properties();
			try {
				configurationProperties.load(
						Thread.currentThread().getContextClassLoader().getResource("imixs.properties").openStream());
			} catch (Exception e) {
				logger.warning("LDAPLookupService unable to find imixs.properties in current classpath");
				e.printStackTrace();
			}

			resetCache();
		} catch (Exception e) {
			logger.severe("LDAPCache unable to initalize LDAPCache");
			e.printStackTrace();
		}
	}

	@PrePassivate
	void pp() {
		logger.info("ISSUE #68 - LDAP Analyse @PrePassivate....");
		logger.info("ISSUE #68 - LDAP Analyse cache size= " + cache.size());
	}

	@PostActivate
	void aa() {
		logger.info("ISSUE #68 - LDAP Analyse @PostActivate....");
		logger.info("ISSUE #68 - LDAP Analyse cache size= " + cache.size());

	}

	/**
	 * resets the ldap cache object and reads the config params....
	 * 
	 * 
	 */
	public void resetCache() {
		// determine the cache size....
		logger.finest("......resetCache - initalizing settings....");
		int iCacheSize = DEFAULT_CACHE_SIZE;
		try {
			iCacheSize = Integer.valueOf(configurationProperties.getProperty("ldap.cache-size", "100"));
		} catch (NumberFormatException nfe) {
			iCacheSize = DEFAULT_CACHE_SIZE;
		}
		if (iCacheSize <= 0)
			iCacheSize = DEFAULT_CACHE_SIZE;

		// initialize cache
		cache = new Cache(iCacheSize);

		// read expires time...
		try {
			expiresTime = DEFAULT_EXPIRES_TIME;
			String sExpires = configurationProperties.getProperty("ldap.cache-expires", "600000");
			expiresTime = Long.valueOf(sExpires);
		} catch (NumberFormatException nfe) {
			expiresTime = DEFAULT_EXPIRES_TIME;
		}
		if (expiresTime <= 0) {
			expiresTime = DEFAULT_EXPIRES_TIME;
		}
		logger.info("...Initialize Cache, cache-size=" + iCacheSize + " cache-expires=" + expiresTime + "ms");

		lastReset = System.currentTimeMillis();

	}

	/**
	 * returns true if the key is contained in the cache. This does not mean that
	 * the object is useable!
	 * 
	 */
	public boolean contains(String key) {
		return cache.containsKey(key);
	}

	/**
	 * Returns a cached user object. The value is stored as a Map<String,
	 * List<Object>> and converted into a ItemCollection.
	 * 
	 * @param key
	 * @return Map<String, List<Object>> user items, can be converted into
	 *         ItemCollection
	 */
	@SuppressWarnings("unchecked")
	public ItemCollection getUser(String key) {
		// not cache for null values.
		if (key == null || key.isEmpty()) {
			return null;
		}

		ItemCollection user = null;

		// test if cache is expired
		if (expiresTime > 0) {
			Long now = System.currentTimeMillis();
			if ((now - lastReset) > expiresTime) {
				logger.finest("......LDAPCache Cache expired!");
				resetCache();
				return null;
			}
		}

		// get user object as map....
		Map<String, List<Object>> value = (Map<String, List<Object>>) cache.get(key);

		if (value != null) {
			// convert into ItemCollection
			user = new ItemCollection(value);

			// ISSUE #68 - test txtusername add warning if empty
			if (user.getItemValueString("txtusername").trim().isEmpty()) {
				logger.warning("ISSUE #68 - getUser txtusername for '" + key + "' is empty!");
			}
		}

		return user;
	}

	/**
	 * The method puts a user ItemCollection into the cache. The method puts only
	 * the internal items of the ItemCollection into the cache, because the
	 * ItemCollection is not serializable.
	 * 
	 * @param key
	 * @param user
	 */
	public void putUser(String key, ItemCollection user) {

		// not cache for null values.
		if (key == null || key.isEmpty()) {
			return;
		}

		// ISSUE #68 - test txtusername add warning if empty
		if (user != null && user.getItemValueString("txtusername").trim().isEmpty()) {
			logger.warning("ISSUE #68 - putUser txtusername for '" + key + "' is empty!");
		}
		if (user == null) {
			logger.warning("ISSUE #68 - putUser user object for '" + key + "' is null and will not be cached!");
			return;
		}

		cache.put(key, user.getAllItems());
	}

	/**
	 * Returns a cached list of group names associated with a userId.
	 * 
	 * @param key
	 * @return
	 */
	public String[] getGroups(String key) {
		// not cache for null values.
		if (key == null || key.isEmpty()) {
			return null;
		}

		// test if cache is expired
		if (expiresTime > 0) {
			Long now = System.currentTimeMillis();
			if ((now - lastReset) > expiresTime) {
				logger.finest("......LDAPCache Cache expired!");
				resetCache();
				return null;
			}
		}

		return (String[]) cache.get(key + GROUP_KEY_SUFIX);
	}

	/**
	 * Stores a list of group names for a given userId.
	 * 
	 * @param key
	 * @param groups
	 */
	public void putGroups(String key, String[] groups) {
		// not cache for null values.
		if (key == null || key.isEmpty()) {
			return;
		}

		cache.put(key, groups + GROUP_KEY_SUFIX);
	}

	/**
	 * Cache implementation to hold userData objects
	 * 
	 * @author rsoika
	 */
	class Cache extends ConcurrentHashMap<String, Object> implements Serializable {
		private static final long serialVersionUID = 1L;
		private final int capacity;

		public Cache(int capacity) {
			super(capacity + 1, 1.1f);
			this.capacity = capacity;
		}

		protected boolean removeEldestEntry(Entry<String, Object> eldest) {
			return size() > capacity;
		}
	}
}