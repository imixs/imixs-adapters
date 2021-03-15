package org.imixs.workflow.sepa.plugins;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.iban4j.BicFormatException;
import org.iban4j.BicUtil;
import org.iban4j.IbanFormat;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
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
 * <p>
 * For validation we use the open source library 'iban4j'
 * 
 * @see https://github.com/arturmkrtchyan/iban4j
 * @author rsoika
 * @version 1.0
 * 
 */
public class IBANBICPlugin extends AbstractPlugin {

    // is empty or match iban/bic pattern - this pattern allows blanks
    // public static final String REGEX_IBAN_PATTERN = "^$|(^[A-Z]{2}(?:[
    // ]?[A-Z0-9]){13,32}$)";
    // public static final String REGEX_BIC_PATTERN =
    // "^$|(^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?))";

    public static final String ERROR_INVALID_IBANBIC = "ERROR_INVALID_IBANBIC";

    public static final String[] IBAN_BIC_ITEMS = { SepaWorkflowService.ITEM_DBTR_IBAN,
            SepaWorkflowService.ITEM_CDTR_IBAN, SepaWorkflowService.ITEM_CDTR_BIC, SepaWorkflowService.ITEM_DBTR_BIC };
    public static final String[] IBAN_ITEMS = { SepaWorkflowService.ITEM_DBTR_IBAN,
            SepaWorkflowService.ITEM_CDTR_IBAN };
    public static final String[] BIC_ITEMS = { SepaWorkflowService.ITEM_CDTR_BIC, SepaWorkflowService.ITEM_DBTR_BIC };

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

        // first we remove tailing spaces....
        trimInput(workitem, IBAN_BIC_ITEMS);

        // validate IBANs...
        try {
            validateIBAN(workitem, SepaWorkflowService.ITEM_DBTR_IBAN, SepaWorkflowService.ITEM_CDTR_IBAN);
        } catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
            logger.warning("Invalid iban!");
            String message = resourceBundleHandler.get(ERROR_INVALID_IBANBIC);
            if (message == null || message.isEmpty()) {
                message = ERROR_INVALID_IBANBIC;
            }
            throw new PluginException(this.getClass().getName(), ERROR_INVALID_IBANBIC, message);
        }

        // validate BICs...
        try {
            validateBIC(workitem, SepaWorkflowService.ITEM_DBTR_BIC, SepaWorkflowService.ITEM_CDTR_BIC);
        } catch (BicFormatException | UnsupportedCountryException e) {
            logger.warning("Invalid bic!");
            String message = resourceBundleHandler.get(ERROR_INVALID_IBANBIC);
            if (message == null || message.isEmpty()) {
                message = ERROR_INVALID_IBANBIC;
            }
            throw new PluginException(this.getClass().getName(), ERROR_INVALID_IBANBIC, message);
        }

        return workitem;
    }

    /**
     * This helper method trims the input if necessary
     */
    private void trimInput(ItemCollection workitem, String[] itemList) {
        for (String itemName : itemList) {
            String value = workitem.getItemValueString(itemName);
            String valueTrimed = value.trim();
            if (!value.equals(valueTrimed)) {
                workitem.setItemValue(itemName, valueTrimed);
            }
        }
    }

    /**
     * This method validates an iban item.
     * <p>
     * The method supports formated IBAN input as well as normal IBAN (without
     * spaces)
     * 
     * @param workitem
     * @param itemName
     * @return
     * @throws PluginException
     */
    public static void validateIBAN(ItemCollection workitem, String... itemNames) {

        for (String itemName : itemNames) {
            String iban = workitem.getItemValueString(itemName);
            if (iban.isEmpty()) {
                continue;
            }
            if (iban.contains(" ")) {
                // formated
                IbanUtil.validate(iban, IbanFormat.Default);
            } else {
                // normal
                IbanUtil.validate(iban, IbanFormat.None);
            }
        }

    }

    /**
     * This method validates an bic item.
     * <p>
     * 
     * 
     * @param workitem
     * @param itemName
     * @return
     * @throws PluginException
     */
    public static void validateBIC(ItemCollection workitem, String... itemNames) {

        for (String itemName : itemNames) {
            String bic = workitem.getItemValueString(itemName);
            if (bic.isEmpty()) {
                continue;
            }
            BicUtil.validate(bic);
        }
    }

}
