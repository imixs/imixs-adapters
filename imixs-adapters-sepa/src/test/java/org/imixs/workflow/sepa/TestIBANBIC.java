package org.imixs.workflow.sepa;

import java.util.logging.Logger;

import org.iban4j.BicFormatException;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.sepa.plugins.IBANBICPlugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This test verifies the IBAN regex
 * 
 * @author rsoika
 * 
 */
public class TestIBANBIC {
    private static Logger logger = Logger.getLogger(IBANBICPlugin.class.getName());

    private IBANBICPlugin ibanbicPlugin = null;

    @Before
    public void setup() throws PluginException {
        ibanbicPlugin = new IBANBICPlugin();
    }

    /**
     * test the fieldlist of the first line of the file
     */
    @Test
    public void testIBAN() {
        ItemCollection workitem = new ItemCollection();

        // test random iban
        Iban iban = Iban.random();
        workitem.setItemValue("iban", iban.toString());
        ibanbicPlugin.validateIBAN(workitem, "iban");

        // test invalid iban
        try {
            // test random iban
            workitem.setItemValue("iban", "DE55880109902211842211");
            ibanbicPlugin.validateIBAN(workitem, "iban");
            Assert.fail();
        } catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
            // expected exception
            System.out.println("invalid iban: " + e.getMessage());
        }
    }

    /**
     * test the fieldlist of the first line of the file
     */
    @Test
    public void testBIC() {

        ItemCollection workitem = new ItemCollection();

        workitem.setItemValue("bic", "DEUTDEFF");
        ibanbicPlugin.validateBIC(workitem, "bic");

        // test invalid bic
        try {
            workitem.setItemValue("bic", "DXUXXXFF");
            ibanbicPlugin.validateBIC(workitem, "bic");

            Assert.fail();
        } catch (BicFormatException | UnsupportedCountryException e) {
            // expected exception
            System.out.println("invalid bic: " + e.getMessage());
        }
    }

    /**
     * Here we are testing a IBAN with a space at a invalid position (3) This should
     * be tolerated by the Imxis IBANPlugin in the mode SEPA_VALIDATION_STRICT=false
     * (default)
     */
    @Test
    public void testValidationMode() {
        ItemCollection workitem = new ItemCollection();

        // test random iban
        Iban iban = Iban.random();
        workitem.setItemValue("iban", iban.toString());

        // test invalid iban
        try {
            // test random iban
            String test_iban = iban.toString();

            logger.info("IBAN='" + test_iban + "'");
            // add a space at position 3
            test_iban = test_iban.substring(0, 2) + " " + test_iban.substring(2);
            logger.info("IBAN='" + test_iban + "'");

            workitem.setItemValue("iban", test_iban);
            ibanbicPlugin.validateIBAN(workitem, "iban");

            try {
                // now we switch in the strict mode where a space is disallowed
                ibanbicPlugin.setSepaValidationStrict(true);
                ibanbicPlugin.validateIBAN(workitem, "iban");
                Assert.fail();
            } catch (Exception e) {
                // in strict mode we expect a exception
            }

        } catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
            // expected exception
            System.out.println("invalid iban: " + e.getMessage());
            Assert.fail();
        }
    }
}