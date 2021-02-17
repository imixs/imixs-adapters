package org.imixs.workflow.sepa;

import org.iban4j.BicFormatException;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.sepa.plugins.IBANBICPlugin;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test verifies the IBAN regex
 * 
 * @author rsoika
 * 
 */
public class TestIBANBIC {

    /**
     * test the fieldlist of the first line of the file
     */
    @Test
    public void testIBAN() {
        ItemCollection workitem = new ItemCollection();

        // test random iban
        Iban iban = Iban.random();
        workitem.setItemValue("iban", iban.toString());
        IBANBICPlugin.validateIBAN(workitem, "iban");

        // test invalid iban
        try {
            // test random iban
            workitem.setItemValue("iban", "DE55880109902211842211");
            IBANBICPlugin.validateIBAN(workitem, "iban");
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

         workitem.setItemValue("bic","DEUTDEFF");
        IBANBICPlugin.validateBIC(workitem, "bic");

        // test invalid bic
        try {
            workitem.setItemValue("bic","DXUXXXFF");
            IBANBICPlugin.validateBIC(workitem, "bic");
           
            Assert.fail();
        } catch (BicFormatException | UnsupportedCountryException e) {
            // expected exception
            System.out.println("invalid bic: " + e.getMessage());
        }
    }
}