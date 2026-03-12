package org.imixs.workflow.sepa.plugins;

import java.util.logging.Logger;

import jakarta.inject.Inject;

import de.speedbanking.bic.Bic;
import de.speedbanking.bic.InvalidBicException;
import de.speedbanking.iban.Iban;
import de.speedbanking.iban.InvalidIbanException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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
 * For validation we use the open source library 'iban-commons'.
 * <p>
 * Note: In some cases the IBAN/BIC is provided on a external invoice with
 * spaces at positions which are strictly not allowed. For example a space
 * between the country and the first to digits 'DE 99'. We accept those settings
 * by removing all spaces before validation. You can disable this behaviour by
 * setting the property sepa.validation.strict=true.
 *
 * @see https://github.com/SpeedBankingDe/iban-commons
 * @see https://www.speedbanking.de/
 *
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
     * Validates the items dbtr.iban, dbtr.bic, cdtr.iban and cdtr.bic. If the
     * input is not valid the method throws a PluginException
     * <p>
     * If the event result provides the tag
     * <p>
     * {@code<validation name="required">false</validation>}
     * <p>
     * the validation will be skipped
     *
     * @throws PluginException - if invalid iban/bic
     *
     **/
    @Override
    public ItemCollection run(ItemCollection workitem, ItemCollection event) throws PluginException {

        // skip if validation tag is required=false
        ItemCollection evalItemCollection = this.getWorkflowService().evalWorkflowResult(event, "validation", workitem);
        if (evalItemCollection != null) {
            if ("false".equalsIgnoreCase(evalItemCollection.getItemValueString("required"))) {
                return workitem;
            }
        }

        // first we remove trailing spaces....
        trimInput(workitem, IBAN_BIC_ITEMS);

        // validate IBANs...
        try {
            validateIBAN(workitem, SepaWorkflowService.ITEM_DBTR_IBAN, SepaWorkflowService.ITEM_CDTR_IBAN);
        } catch (InvalidIbanException ex) {
            logger.warning("Invalid IBAN: " + ex.getMessage());
            String message = resourceBundleHandler.get(ERROR_INVALID_IBANBIC);
            if (message == null || message.isEmpty()) {
                message = ERROR_INVALID_IBANBIC;
            }
            throw new PluginException(this.getClass().getName(), ERROR_INVALID_IBANBIC, message);
        }

        // validate BICs...
        try {
            validateBIC(workitem, SepaWorkflowService.ITEM_DBTR_BIC, SepaWorkflowService.ITEM_CDTR_BIC);
        } catch (InvalidBicException ex) {
            logger.warning("Invalid BIC: " + ex.getMessage());
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
     * Helper method that trims leading/trailing whitespace from a list of items.
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
     * Validates IBAN values stored in the given item names of the workitem.
     * <p>
     * iban-commons always tolerates spaces used to format an IBAN for readability
     * (e.g. "DE44 5001 0517 5407 3249 31"), while tabs and other whitespace are never
     * allowed.
     * <p>
     * In strict mode spaces are also rejected. In non-strict mode (default) all
     * whitespace is stripped before validation.
     *
     * @throws InvalidIbanException if any IBAN value is invalid
     */
    public void validateIBAN(ItemCollection workitem, String... itemNames) {
        for (String itemName : itemNames) {
            String iban = workitem.getItemValueString(itemName);
            if (iban.isEmpty()) {
                continue;
            }
            if (sepaValidationStrict) {
                // Replace spaces with an illegal character so iban-commons rejects them
                iban = iban.replace(' ', '_');
            } else {
                // Remove all whitespace before validation
                iban = iban.replaceAll("\\s+", "");
            }
            Iban.of(iban);
        }
    }

    /**
     * Validates BIC values stored in the given item names of the workitem.
     * <p>
     * In non-strict mode (default) whitespace is stripped before validation.
     *
     * @throws InvalidBicException if any BIC value is invalid
     */
    public void validateBIC(ItemCollection workitem, String... itemNames) {
        for (String itemName : itemNames) {
            String bic = workitem.getItemValueString(itemName);
            if (bic.isEmpty()) {
                continue;
            }
            if (!sepaValidationStrict) {
                bic = bic.replaceAll("\\s+", "");
            }
            Bic.of(bic);
        }
    }

}
