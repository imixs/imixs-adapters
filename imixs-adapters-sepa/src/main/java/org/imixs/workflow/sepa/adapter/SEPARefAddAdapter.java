package org.imixs.workflow.sepa.adapter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SEPARefAddAdapter links an invoice with a SEPA Export for the dbtr.iban
 * item . If there is currently no open SEPA Export for this key, the adapter
 * automatically creates a new process instance.
 * <p>
 * If the invoice has already been linked to a SEPA Export, nothing happens.
 * <p>
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPARefAddAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPARefAddAdapter.class.getName());

    public static final String ERROR_CONFIG = "CONFIG_ERROR";

 
    @Inject
    SepaWorkflowService sepaWorkflowService;

    /**
     * This method finds or create the SEPA Export and adds a reference
     * ($workitemref) to the current invoice.
     * 
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    @Override
    public ItemCollection execute(ItemCollection invoice, ItemCollection event)
            throws AdapterException, PluginException {

        // test if invoice has a _dbtr_iban and _dbtr_bic
        if (invoice.getItemValueString(SepaWorkflowService.ITEM_DBTR_IBAN).isEmpty()
                || invoice.getItemValueString(SepaWorkflowService.ITEM_DBTR_BIC).isEmpty()) {
            // overtake _dbtr_iban from sepa configuration...
            String paymentType = invoice.getItemValueString("payment.type");
            ItemCollection dbtrOption = sepaWorkflowService.findDbtrOptionByPaymentType(paymentType,
                    sepaWorkflowService.loadConfiguration());
            if (dbtrOption != null) {
                invoice.setItemValue(SepaWorkflowService.ITEM_DBTR_IBAN,
                        dbtrOption.getItemValue(SepaWorkflowService.ITEM_DBTR_IBAN));
                invoice.setItemValue(SepaWorkflowService.ITEM_DBTR_BIC,
                        dbtrOption.getItemValue(SepaWorkflowService.ITEM_DBTR_BIC));
                invoice.setItemValue(SepaWorkflowService.ITEM_DBTR_NAME,
                        dbtrOption.getItemValue(SepaWorkflowService.ITEM_DBTR_NAME));

                // set optional SEPA report definition
                invoice.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                        dbtrOption.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));
            } else {
                logger.warning(
                        "...Warning: payment.type '" + paymentType + "' not found in SEPA configuration");
            }
        }
        
        String key = sepaWorkflowService.computeKey(invoice);

        logger.info("......Update SEPA export for: '" + key + "'...");
        ItemCollection sepaExport;
        try {
            sepaExport = sepaWorkflowService.findSEPAExport(key);
            if (sepaExport == null) {
                // create a new one
                sepaExport = sepaWorkflowService.createNewSEPAExport(key, invoice,event);
            }

            // add Invoice to SEPA export
            List<String> refList = sepaExport.getItemValue("$workitemref");
            if (!refList.contains(invoice.getUniqueID())) {
                sepaExport.appendItemValueUnique("$workitemref", invoice.getUniqueID());
                // set event 100
                sepaExport.event(100);
                sepaWorkflowService.processSEPAExport(sepaExport);
            }

        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(SEPARefAddAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }
        
        return invoice;
    }

}