package org.imixs.workflow.qrcode;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Cache service to cache qr-codes
 * 
 * @author rsoika
 * 
 */
@ApplicationScoped
public class ImageCache {
	final int DEFAULT_CACHE_SIZE = 100;

	final int MAX_SEARCH_COUNT = 1;
	private Cache cache;

	private static Logger logger = Logger.getLogger(ImageCache.class.getName());

	/**
	 * PostContruct event - inizalize cache
	 */
	@PostConstruct
	void init() {
		int iCacheSize = DEFAULT_CACHE_SIZE;
		cache = new Cache(iCacheSize);
	}

	public byte[] getQrCode(String key) {
		return cache.get(key);
	}

	public void putQrCode(String key, byte[] qrCode) {
		logger.fine("put qr-code " + key + " into cache...");
		cache.put(key, qrCode);
	}

	/**
	 * Cache implementation to hold qr-code entities
	 * 
	 * @author rsoika
	 * 
	 */
	class Cache extends LinkedHashMap<String, byte[]> implements Serializable {
		private static final long serialVersionUID = 1L;
		private final int capacity;

		public Cache(int capacity) {
			super(capacity + 1, 1.1f, true);
			this.capacity = capacity;
		}

		protected boolean removeEldestEntry(Entry<String, byte[]> eldest) {
			return size() > capacity;
		}
	}
}
