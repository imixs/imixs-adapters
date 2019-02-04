package org.imixs.workflow.ldap;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;

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
 * 
 * @version 1.0
 * @author rsoika
 * 
 */
@Singleton
public class LDAPCache {

	int DEFAULT_CACHE_SIZE = 30;
	int DEFAULT_EXPIRES_TIME = 60000;
	long expiresTime = 0;
	long lastReset = 0;
	Properties configurationProperties = null;
	Cache cache = null; // cache holds userdata

	private static Logger logger = Logger.getLogger(LDAPCache.class
			.getName());

	
	
	@PostConstruct
	void init() {
		try {
			
			configurationProperties= new Properties();
			try {
				configurationProperties.load(Thread.currentThread()
						.getContextClassLoader()
						.getResource("imixs.properties").openStream());
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
		logger.info("LDAP Analyse @PrePassivate....");
		logger.info("LDAP Analyse cache size= " + cache.size());
	}
	
	@PostActivate
	void aa() {
		logger.info("LDAP Analyse@@PostActivate....");
		logger.info("LDAP Analyse cache size= " + cache.size());
		
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
			iCacheSize = Integer.valueOf(configurationProperties
					.getProperty("ldap.cache-size", "100"));
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
			String sExpires = configurationProperties.getProperty(
					"ldap.cache-expires", "600000");
			expiresTime = Long.valueOf(sExpires);
		} catch (NumberFormatException nfe) {
			expiresTime = DEFAULT_EXPIRES_TIME;
		}
		if (expiresTime <= 0)
			expiresTime = DEFAULT_EXPIRES_TIME;

		lastReset = System.currentTimeMillis();

	}

	public Object get(String key) {
		// test if cache is expired
		if (expiresTime > 0) {
			Long now = System.currentTimeMillis();
			if ((now - lastReset) > expiresTime) {
				logger.finest("......LDAPCache Cache expired!");
				resetCache();
			}
		}
		return cache.get(key);
	}

	/**
	 * returns true if the key is contained in the cache.
	 * This does not mean that the object is useable!
	 * 
	 */
	public boolean contains(String key) {
		return cache.containsKey(key);
	}

	public void put(String key, Object value) {
		cache.put(key, value);
	}

	/**
	 * Cache implementation to hold userData objects
	 * 
	 * @author rsoika
	 * 
	 */
	class Cache extends LinkedHashMap<String, Object> implements Serializable {
		private static final long serialVersionUID = 1L;
		private final int capacity;

		public Cache(int capacity) {
			super(capacity + 1, 1.1f, true);
			this.capacity = capacity;
		}

		protected boolean removeEldestEntry(Entry<String, Object> eldest) {
			return size() > capacity;
		}
	}
}