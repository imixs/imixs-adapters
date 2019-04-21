package org.imixs.workflow.ldap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ldap.LDAPCache.Cache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the LDAPCache
 * 
 * @author rsoika
 * @version 1.0
 */
public class LDAPCacheTest {

	LDAPCache ldapCache;

	@Before
	public void setup() {
		ldapCache = new LDAPCache();
		ldapCache.init();
	}

	/**
	 * Test seralization / deserialization....
	 * 
	 */
	@Test
	public void testSimple() {

		ItemCollection user = new ItemCollection();

		user.replaceItemValue("txtname", "QX06396");
		user.replaceItemValue("txtlastname", "Soika");
		user.replaceItemValue("txtEmail", "ralph.soika@imixs.com");
		user.replaceItemValue("txtfirstname", "Ralph");
		user.replaceItemValue("txtdepartment", "ABC");
		user.replaceItemValue("txtPhone", "089");
		user.replaceItemValue("txtusername", "Ralph Soika");
		user.replaceItemValue("CN", "CN=RAlph Soika OU=IMXIS");
		user.replaceItemValue("objectClass", "Partner");

		ldapCache.putUser("QX06396", user);

		ItemCollection cachedUser = (ItemCollection) ldapCache.getUser("QX06396");

		Assert.assertEquals(user.getItemValueString("txtname"), cachedUser.getItemValueString("txtname"));

	}

	
}
