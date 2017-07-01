package org.imixs.workflow.archive.hadoop;

import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.services.rest.RestClient;
import org.imixs.workflow.xml.XMLItemCollection;
import org.imixs.workflow.xml.XMLItemCollectionAdapter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class to check a HDFS
 * 
 * @author rsoika
 * 
 */
public class HadoopTest {

	public static String TEST_DATA = "/test-data.txt";

	// change this to string arg in main
	public static final String inputfile = "hdfsinput.txt";
	public static final String outputfile = "hdfsoutput.txt";
	public static final String inputmsg = "Imixs-Archive - this is a test sentence!\n";

	/**
	 * Test the conversion of a workitem into XML 
	 */
	@Test
	public void testXML() {

		ItemCollection workitem = new ItemCollection();

		workitem.replaceItemValue(WorkflowKernel.MODELVERSION, "1.0.0");
		workitem.replaceItemValue("_name", "Anna");
		workitem.replaceItemValue("_subject", "Hello Hadoop");
		workitem.replaceItemValue("_age", 40);
		workitem.replaceItemValue("_visible", true);
		try {

			// convert the ItemCollection into a XMLItemcollection...
			XMLItemCollection xmlItemCollection = XMLItemCollectionAdapter.putItemCollection(workitem);

			// marshal the Object into an XML Stream....
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(XMLItemCollection.class);
			Marshaller m = context.createMarshaller();
			m.marshal(xmlItemCollection, writer);
			String xmlData=writer.toString();
			System.out.println(xmlData);

			Assert.assertTrue(xmlData.contains("<item name=\"_subject\"><value xsi:type=\"xs:string\">Hello Hadoop</value></item>"));
		
		} catch (JAXBException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	
	
	/**
	 * Test the Hadoop WebHDFS Interface 
	 * 
	 *  http://<HOST>:<PORT>/webhdfs/v1/<PATH>?op=CREATE
                    [&overwrite=<true |false>][&blocksize=<LONG>][&replication=<SHORT>]
                    [&permission=<OCTAL>][&buffersize=<INT>]"
	 */
	@Test
	public void testWebHDFS() {
		ItemCollection workitem = new ItemCollection();

		workitem.replaceItemValue(WorkflowKernel.MODELVERSION, "1.0.0");
		workitem.replaceItemValue("_name", "Anna");
		workitem.replaceItemValue("_subject", "Hello Hadoop");
		workitem.replaceItemValue("_age", 40);
		workitem.replaceItemValue("_visible", true);
		
		List<ItemCollection> col=new ArrayList<ItemCollection>();
		col.add(workitem);
		
		
		
		HDFSClient hdfsClient=new HDFSClient();
		
		String uri="http://my-hadoop-cluster.local:50070/webhdfs/v1/2017/06/test?op=CREATE&overwrite=true";
		
		try {
			boolean redirect=false;
			int status=hdfsClient.postCollection(uri, XMLItemCollectionAdapter.putCollection(col));
			
			
			// normally, 3xx is redirect
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}
			Assert.assertTrue(redirect);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	
}
