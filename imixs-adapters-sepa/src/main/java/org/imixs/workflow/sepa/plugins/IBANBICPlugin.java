package org.imixs.workflow.sepa.plugins;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.imixs.marty.util.ResourceBundleHandler;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The IBANBICPlugin validates the items dbtr.iban, dbtr.bic, cdtr.iban and
 * cdtr.bic.
 * <p>
 * Validation is skipped in case no data is provided.
 * 
 * @author rsoika
 * @version 1.0
 * 
 */
public class IBANBICPlugin extends AbstractPlugin {

    // is empty or match iban/bic pattern - this pattern allows blanks
    public static final String REGEX_IBAN_PATTERN = "^$|(^[A-Z]{2}(?:[ ]?[A-Z0-9]){13,32}$)";
    public static final String REGEX_BIC_PATTERN = "^$|(^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?))";

    public static final String ERROR_INVALID_IBANBIC = "ERROR_INVALID_IBANBIC";

    private static Logger logger = Logger.getLogger(IBANBICPlugin.class.getName());

    @Inject
    ResourceBundleHandler resourceBundleHandler;

    /**
     * Validates the items dbtr.iban, dbtr.bic, cdtr.iban and cdtr.bic. If the input
     * is not valid the method throws a PluginException
     * 
     * 
     * @throws PluginException - if invalid iban/bic
     * 
     **/
    @Override
    public ItemCollection run(ItemCollection workitem, ItemCollection documentActivity) throws PluginException {

        if (!isValidIBAN(workitem.getItemValueString(SepaWorkflowService.ITEM_DBTR_IBAN))
                || !isValidIBAN(workitem.getItemValueString(SepaWorkflowService.ITEM_CDTR_IBAN))
                || !isValidBIC(workitem.getItemValueString(SepaWorkflowService.ITEM_CDTR_BIC))
                || !isValidBIC(workitem.getItemValueString(SepaWorkflowService.ITEM_DBTR_BIC))) {
            logger.warning("Invalid iban/bic!");
            String message = resourceBundleHandler.get(ERROR_INVALID_IBANBIC);
            if (message == null || message.isEmpty()) {
                message = ERROR_INVALID_IBANBIC;
            }
            throw new PluginException(this.getClass().getName(), ERROR_INVALID_IBANBIC, message);
        }

        return workitem;
    }

    private boolean isValidIBAN(String itemValueString) throws PluginException {
        if (itemValueString.trim().isEmpty()) {
            return true;
        }
        // we have a value...
        return itemValueString.matches(REGEX_IBAN_PATTERN);
    }

    private boolean isValidBIC(String itemValueString) throws PluginException {
        if (itemValueString.trim().isEmpty()) {
            return true;
        }
        // we have a value...
        return itemValueString.matches(REGEX_BIC_PATTERN);
    }

}
