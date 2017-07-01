package org.imixs.workflow.archive.hadoop;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.imixs.workflow.services.rest.RestClient;
import org.imixs.workflow.xml.DocumentCollection;

/**
 * This service provides methods to post a file via the WebHDFS Rest API
 * @author rsoika
 *
 */
@Stateless	
@LocalBean
public class HadoopService {

	
	
	/**
	 * http://<HOST>:<PORT>/webhdfs/v1/<PATH>?op=CREATE
                    [&overwrite=<true |false>][&blocksize=<LONG>][&replication=<SHORT>]
                    [&permission=<OCTAL>][&buffersize=<INT>]"
	 * @throws Exception 
                    
	 */
	public void postFile(String uri, DocumentCollection aDocumentCol) throws Exception {
		
		
		RestClient restClient=new RestClient();
		
		restClient.postCollection(uri, aDocumentCol);
		
	}
}
