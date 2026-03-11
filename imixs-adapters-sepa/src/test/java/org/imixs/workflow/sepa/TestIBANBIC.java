package org.imixs.workflow.sepa;

import java.util.logging.Logger;

import de.speedbanking.bic.InvalidBicException;
import de.speedbanking.iban.Iban;
import de.speedbanking.iban.InvalidIbanException;
import de.speedbanking.iban.RandomIban;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.sepa.plugins.IBANBICPlugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for IBAN and BIC validation in IBANBICPlugin.
 *
 * @author rsoika
 */
public class TestIBANBIC {

    private static Logger logger = Logger.getLogger(IBANBICPlugin.class.getName());

    private IBANBICPlugin ibanbicPlugin;

    @Before
    public void setup() throws PluginException {
        ibanbicPlugin = new IBANBICPlugin();
        // ensure default (non-strict) mode for each test
        ibanbicPlugin.setSepaValidationStrict(false);
    }

    @Test
    public void testValidIBAN() {
        ItemCollection workitem = new ItemCollection();

        Iban iban = RandomIban.ofSepa();
        workitem.setItemValue("iban", iban.toString());

        // must not throw
        ibanbicPlugin.validateIBAN(workitem, "iban");
    }

    @Test
    public void testInvalidIBAN() {
        ItemCollection workitem = new ItemCollection();
        workitem.setItemValue("iban", "DE55880109902211842211");

        try {
            ibanbicPlugin.validateIBAN(workitem, "iban");
            Assert.fail("Expected InvalidIbanException for a malformed IBAN");
        } catch (InvalidIbanException e) {
            logger.info("Expected exception for invalid IBAN: " + e.getMessage());
        }
    }

    @Test
    public void testValidBIC() {
        ItemCollection workitem = new ItemCollection();
        workitem.setItemValue("bic", "DEUTDEFF");

        // must not throw
        ibanbicPlugin.validateBIC(workitem, "bic");
    }

    @Test
    public void testInvalidBIC() {
        ItemCollection workitem = new ItemCollection();
        workitem.setItemValue("bic", "DXUXXXFF");

        try {
            ibanbicPlugin.validateBIC(workitem, "bic");
            Assert.fail("Expected InvalidBicException for a malformed BIC");
        } catch (InvalidBicException e) {
            logger.info("Expected exception for invalid BIC: " + e.getMessage());
        }
    }

    /**
     * An IBAN with a space at an atypical position (e.g. after the country code)
     * must be accepted in non-strict mode and rejected in strict mode.
     */
    @Test
    public void testSpacesInIBANNonStrictMode() {
        ItemCollection workitem = new ItemCollection();
        Iban iban = RandomIban.ofSepa();
        // Insert a space after the country code — not a standard group boundary
        String ibanWithSpace = iban.toString().substring(0, 2) + " " + iban.toString().substring(2);
        logger.info("IBAN with mid-group space: '" + ibanWithSpace + "'");

        workitem.setItemValue("iban", ibanWithSpace);

        // non-strict mode: spaces are stripped, validation must pass
        ibanbicPlugin.setSepaValidationStrict(false);
        ibanbicPlugin.validateIBAN(workitem, "iban");
    }

    @Test
    public void testSpacesInIBANStrictMode() {
        ItemCollection workitem = new ItemCollection();
        Iban iban = RandomIban.ofSepa();
        String ibanWithSpace = iban.toString().substring(0, 2) + " " + iban.toString().substring(2);
        logger.info("IBAN with mid-group space (strict): '" + ibanWithSpace + "'");

        workitem.setItemValue("iban", ibanWithSpace);

        // strict mode: any space must cause a validation failure
        ibanbicPlugin.setSepaValidationStrict(true);
        try {
            ibanbicPlugin.validateIBAN(workitem, "iban");
            Assert.fail("Expected an exception for space in IBAN in strict mode");
        } catch (Exception e) {
            logger.info("Expected exception in strict mode: " + e.getMessage());
        }
    }
}
