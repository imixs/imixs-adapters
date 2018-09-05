package org.imixs.workflow.sepa.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.sepa.services.SepaSchedulerService;

/**
 * This servlet checks sepa scheduler on startup. The servlet is configured with
 * the option load-on-startup=1 which means that the servlet init() method is
 * triggered after deployment.
 * 
 * The scheduler will not be started automatically if it was stopped by the
 * administrator manually before
 * 
 * @author rsoika
 * 
 */
@WebServlet(loadOnStartup = 1)
public class SepaSchedulerSetupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(SepaSchedulerSetupServlet.class.getName());

	@EJB
	private SepaSchedulerService sepaSchedulerService;

	@Override
	public void init() throws ServletException {

		super.init();
		logger.info("setup SEPA Scheduler Service...");

		ItemCollection config = sepaSchedulerService.loadConfiguration();
		// is timmer running?
		if (config != null && config.getItemValueBoolean("_enabled")
				&& sepaSchedulerService.findTimer(config.getUniqueID()) == null) {
			try {
				sepaSchedulerService.start(config);
				logger.info("SEPA Scheduler Service will be started...");

			} catch (Exception e) {
				logger.severe("...start of SEPA Scheduler Service failed! - " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.info("...SEPA Scheduler Service will NOT be started. Setup Configuration manually.");
		}

	}

}