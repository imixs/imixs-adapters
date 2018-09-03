package org.imixs.workflow.datev.web;


import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.datev.DatevSchedulerService;
import org.imixs.workflow.datev.DatevWorkflowService;

/**
 * This servlet checks scheduler for the Datev import service on startup. The
 * servlet is configured with the option load-on-startup=1 which means that the
 * servlet init() method is triggered after deployment.
 * 
 * The scheduler will not be started automatically if it was stopped by the
 * administrator manually before
 * 
 * @author rsoika
 * 
 */
@WebServlet(loadOnStartup = 1)
// Because of a Deployment Issue in GlassFish we need to disable this runas
// option here!
// @RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
public class DatevSetupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(DatevSetupServlet.class
			.getName());

	@EJB
	private DatevSchedulerService datevSchedulerService;
	
	@EJB
	private DatevWorkflowService datevService;
	
	@Override
	public void init() throws ServletException {

		super.init();
		logger.info("DatevSetupServlet - setup Datev Scheduler Service...");

		Collection<ItemCollection> col =datevService.findAllConfigurations();
		
		for (ItemCollection config : col) {
			try {

				if (config.getItemValueBoolean("_enabled")) {
					logger.info("DatevSetupServlet -  DatevSchedulerService will be started...");
					datevSchedulerService.start(config);
				} else {
					logger.info("DatevSetupServlet -  DatevSchedulerService will NOT be started. Setup Configuration manually.");
				}

			} catch (Exception e) {
				logger.severe("DatevSetupServlet - start DatevSchedulerService failed! - "
						+ e.getMessage());
				e.printStackTrace();
			}
		}

	}

}