package org.imixs.workflow.poi;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 * The SpredsheetController CDI Bean is just to indicate that the POI feature is
 * available
 * <p>
 * #{poiController!=null}
 * 
 * @author rsoika
 *
 */
@Named("poiController")
@ConversationScoped
public class POIController implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(POIController.class.getName());

}
