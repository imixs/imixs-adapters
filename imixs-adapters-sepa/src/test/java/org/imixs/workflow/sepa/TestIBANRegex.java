package org.imixs.workflow.sepa;

import org.imixs.workflow.sepa.controller.SepaController;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test verifies the IBAN regex
 * 
 * @author rsoika
 * 
 */
public class TestIBANRegex {

	/**
	 * test the fieldlist of the first line of the file
	 */
	@Test
	public void testFieldList() {

		try {
			  // germany 20 digits
			String test_iban = "DE55880109902211842211";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));

			
			// 22 digits
			test_iban = "CZ5503000000000117861373";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));

			
			test_iban = "DE55880109902211842211";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));

			// Belgien 14 digits
			test_iban = "BE45096920886089";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));
			
			// Norwegen
			test_iban = "NO0239916835985";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));
			
			// Saint Lucia
			test_iban = "LC55HEMM000100010012001200023015";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));
			
			//Rumänien
			test_iban = "RO02BRDE445SV75163474450";
			Assert.assertTrue(test_iban.matches(SepaController.IBAN_PATTERN));
			
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}
	}
}