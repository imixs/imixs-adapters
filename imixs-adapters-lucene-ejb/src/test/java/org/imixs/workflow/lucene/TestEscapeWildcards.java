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

		query = LuceneSearchService.escapeSearchTerm(query);
		System.out.println("Result String=" + query);

		Assert.assertEquals("xx\\/xx", query);

	}

	/**
	 * Test the imixs normalizer using the Lucence ClassicAnalyzer.
	 * 
	 * @throws PluginException
	 */
	@Test
	public void testLuceneNormalizeSearchPhrasey() throws PluginException {

		String searchTerm = "Aldi/Sued";
		searchTerm = LuceneSearchService.normalizeSearchTerm(searchTerm);
		Assert.assertEquals("aldi sued", searchTerm);

		// test article number
		searchTerm = "gb/82550/201602";
		searchTerm = LuceneSearchService.normalizeSearchTerm(searchTerm);
		Assert.assertEquals("gb\\/82550\\/201602", searchTerm);

		searchTerm = "r555/333";
		searchTerm = LuceneSearchService.normalizeSearchTerm(searchTerm);
		Assert.assertEquals("r555\\/333", searchTerm);

		// test combination word and article number
		searchTerm = "europe/berlin gb/82550/201602"; 
		searchTerm = LuceneSearchService.normalizeSearchTerm(searchTerm);
		Assert.assertEquals("(europe berlin) gb\\/82550\\/201602", searchTerm);

	}

}
