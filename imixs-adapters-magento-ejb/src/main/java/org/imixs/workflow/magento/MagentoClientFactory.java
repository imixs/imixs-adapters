package org.imixs.workflow.magento;

import java.util.logging.Logger;

public class MagentoClientFactory {
	private static Logger logger = Logger.getLogger(MagentoClientFactory.class
			.getName());

	public static MagentoClient createClient(String className) {

		MagentoClient client = null;
		try {
			client = (MagentoClient) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			logger.severe("[MagentoClientFactory] unable to locate client! - "
					+ e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.severe("[MagentoClientFactory] unable to locate client! - "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.severe("[MagentoClientFactory] unable to locate client! - "
					+ e.getMessage());
			e.printStackTrace();
		}
		return client;
	}
}
