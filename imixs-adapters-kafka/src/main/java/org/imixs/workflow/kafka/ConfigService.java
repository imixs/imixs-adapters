package org.imixs.workflow.kafka;

import java.io.Serializable;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;

/**
 * The ConfigService is used to provide static String and environment access methods.
 * 
 * @version 1.0
 * @author rsoika
 * 
 */
@Stateless
public class ConfigService implements Serializable {

	public static final String ENV_KAFKA_BROKERS = "KAFKA_BROKERS";
	public static final String ENV_KAFKA_CLIENTID = "KAFKA_CLIENTID";
	public static final String ENV_KAFKA_AUTOWIRE = "KAFKA_AUTOWIRE";

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ConfigService.class.getName());


	/**
	 * Returns a environment variable. An environment variable can be provided as a
	 * System property. 
	 * 
	 * @param env
	 *            - environment variable name
	 * @param defaultValue
	 *            - optional default value
	 * @return value
	 */
	public static String getEnv(String env, String defaultValue) {
		logger.finest("......read env: " + env);
		String result = System.getenv(env);
		if (result == null || result.isEmpty()) {
			result = defaultValue;
		}
		return result;
	}
}