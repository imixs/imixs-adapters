package org.imixs.workflow.lucene;

import java.util.ArrayList;
import java.util.List;
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
public class TestRegex {
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

		 String query = "(type=workitem) AND (*xx/xx*)";

		query=LuceneSearchService.escapeSearchTerm(query);
		System.out.println("Result String=" + query);

		Assert.assertEquals("(type=workitem) AND (*xx\\/xx*)", query);

	}
	
	@Test
	public void testLuceneEscapeSearchPhrase() throws PluginException {

		// String query = "(type=workitem) AND (*xx/xx*)";

		String query = "(type=workitem) AND (*xx/xx* *yyyy* *(abc.123)*)";

		query=LuceneSearchService.escapeSearchTerm(query);
		System.out.println("Result String=" + query);

		Assert.assertEquals("(type=workitem) AND (*xx\\/xx* *yyyy* *\\(abc.123)*)", query);

	}
	
	

	@Test
	public void testLuceneEscapeSearchPhraseComplex() throws PluginException {

		// String query = "(type=workitem) AND (*xx/xx*)";

		String searchTerm = "(($modelversion:datev*) AND ($processid:1???)) AND  (*222*)";
	
		searchTerm=LuceneSearchService.escapeSearchTerm(searchTerm);
		System.out.println("Result String=" + searchTerm);

		Assert.assertEquals("(($modelversion:datev*) AND ($processid:1???)) AND  (*222*)", searchTerm);

	}
	
	
	
	@Test
	public void debugtestLuceneEscapeSearchPhraseComplex() throws PluginException {

		// String query = "(type=workitem) AND (*xx/xx*)";

		String searchTerm = "(($modelversion:datev*) AND ($processid:1???)) AND  (*222*)";
		searchTerm = "(type=workitem) AND (*xx/xx* *yyyy* *(abc.123)*)";
		
		
		List<String> wildcardTokens = new ArrayList<String>();

		Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
		Matcher matcher = pattern.matcher(searchTerm);
		
		String lastBlock=null;
		while (matcher.find()) {
			lastBlock=matcher.group(0);
			
			//System.out.println(matcher.group(1));
		}
		System.out.println(lastBlock);

	
		

//		searchTerm=LuceneSearchService.escapeSearchTerm(searchTerm);
//		System.out.println("Result String=" + searchTerm);
//
//		Assert.assertEquals("(($modelversion:datev*) AND ($processid:1???)) AND  (*222*)", searchTerm);

	}




}
