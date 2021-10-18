package org.imixs.workflow.sepa.plugins;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.iban4j.BicFormatException;
import org.iban4j.BicUtil;
import org.iban4j.IbanFormat;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.faces.util.ResourceBundleHandler;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The IBANBICPlugin validates the items dbtr.iban, dbtr.bic, cdtr.iban and
 * cdtr.bic.
 * <p>
 * Validation is skipped in case no data is provided.
 * <p>
 * For validation we use the open source library 'iban4j'
 * <p>
 * Note: In some cases the IBAN/BIC is provided on a external invoice with
 * spaces at positions which are strictly not allowed. For example a space
 * between the country and the first to digits 'DE 99'. We accept those settings
 * by removing all spaces before validation. You can disable this behaviour by
 * setting the property sepa.validation.strict=true.
 * 
 * @see https://github.com/arturmkrtchyan/iban4j
 * @author rsoika
 * @version 1.0
 * 
 */
public class IBANBICPlugin extends AbstractPlugin {

    public static final String ERROR_INVALID_IBANBIC = "ERROR_INVALID_IBANBIC";
    public static final String SEPA_VALIDATION_STRICT = "SEPA_VALIDATION_STRICT";
    

    public static final String[] IBAN_BIC_ITEMS = { SepaWorkflowService.ITEM_DBTR_IBAN,
            SepaWorkflowService.ITEM_CDTR_IBAN, SepaWorkflowService.ITEM_CDTR_BIC, SepaWorkflowService.ITEM_DBTR_BIC };
    public static final String[] IBAN_ITEMS = { SepaWorkflowService.ITEM_DBTR_IBAN,
            SepaWorkflowService.ITEM_CDTR_IBAN };
    public static final String[] BIC_ITEMS = { SepaWorkflowService.ITEM_CDTR_BIC, SepaWorkflowService.ITEM_DBTR_BIC };

    private static Logger logger = Logger.getLogger(IBANBICPlugin.class.getName());

    @Inject
    @ConfigProperty(name = SEPA_VALIDATION_STRICT, defaultValue = "false")
    boolean sepaValidationStrict;
  

    @Inject
    ResourceBundleHandler resourceBundleHandler;

    /**
     * Validates the items dbtr.iban, dbtr.bic, cdtr.iban and cdtr.bic. If the input
     * is not valid the method throws a PluginException
     * <p>
     * If the event result provides the tag
     * <p>
     * {@code<validation name="required">false</validation>}
     * <p>
     * the validation will be scipped
     * 
     * @throws PluginException - if invalid iban/bic
     * 
     **/
    @Override
    public ItemCollection run(ItemCollection workitem, ItemCollection event) throws PluginException {

        // skip if validaten tag is required=false
        ItemCollection evalItemCollection = this.getWorkflowService().evalWorkflowResult(event, "validation", workitem);
        if (evalItemCollection != null) {
            // evaluate the validation rules...
            if ("false".equalsIgnoreCase(evalItemCollection.getItemValueString("required"))) {
                return workitem;
            }
        }

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

    
    public void setSepaValidationStrict(boolean sepaValidationStrict) {
        this.sepaValidationStrict = sepaValidationStrict;
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
    public  void validateIBAN(ItemCollection workitem, String... itemNames) {

        for (String itemName : itemNames) {
            String iban = workitem.getItemValueString(itemName);
            if (iban.isEmpty()) {
                continue;
            }
            
            // strip spaces?
            if (sepaValidationStrict==false) {
                // yes...
                iban=iban.replaceAll("\\s+", "");
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
    public  void validateBIC(ItemCollection workitem, String... itemNames) {

        for (String itemName : itemNames) {
            String bic = workitem.getItemValueString(itemName);
            if (bic.isEmpty()) {
                continue;
            }
            // strip spaces?
            if (sepaValidationStrict==false) {
                // yes...
                bic=bic.replaceAll("\\s+", "");
            }
            
            BicUtil.validate(bic);
        }
    }

}
