package org.imixs.workflow.lucene;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.imixs.workflow.exceptions.PluginException;
import org.junit.Test;

import junit.framework.Assert;

/**
 * Test regex used by the LuceneSearchService
 * 
 * ..*xx/xx*... must be replaced with ..*xx\/xx*...
 * 
 * @author rsoika
 */
public class TestEscapeWildcards {
	Pattern pattern;
	Matcher matcher;

	/**
	 * This is a simple regex test to parse a lucene query string for wildCard
	 * tokens and escape special characters with the Query.escape() method
	 * 
	 * @throws PluginException
	 */
	@Test
	public void testLuceneEscapeSearchPhraseSimple() throws PluginException {

		 String query = "xx/xx";

		query=LuceneSearchService.escapeSearchTerm(query);
		System.out.println("Result String=" + query);

		Assert.assertEquals("xx\\/xx", query);

	}
	



}
