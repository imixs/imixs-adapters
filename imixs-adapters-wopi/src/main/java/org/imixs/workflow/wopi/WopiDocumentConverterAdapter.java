package org.imixs.workflow.wopi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.imixs.archive.core.SnapshotService;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;

/**
 * The WopiDocumentConverterAdapter can be used to convert office documents into
 * PDF and other file formats. The Adapter simply calls a build in RestAPI of
 * Collabora to convert documents based on the JODConverter
 * (https://github.com/sbraconnier/jodconverter)
 * <p>
 * There is no need to implement the libraries in case a Collabora instance is
 * up and running. In this case the rest API endpoint 'lool/convert-to/'
 * provides a convenience function. See details here:
 * https://www.collaboraoffice.com/de/document-conversion/
 * <p>
 * Documents can be converted into different formats by calling the
 * corresponding endpoint.
 * <ul>
 * <li>https://localhost:9980/lool/convert-to/pdf for pdf
 * <li>https://localhost:9980/lool/convert-to/png for png
 * </ul>
 * <p>
 * The rest service automatically detects the input document format.
 * <p>
 * The adapter simply posts a given document to the service endpoint. The
 * adapter can be configured by the event workflow result:
 * <p>
 * 
 * <pre>
 * {@code
    <wopi-converter name=
"api-endpoint">https://localhost:9980/lool/convert-to/</wopi-converter>
    <wopi-converter name="filename">......</wopi-converter>
    <wopi-converter name="convert-to">pdf</wopi-converter>
   }
 * </pre>
 * <p>
 * The Collabora API endpoint must point to a collabora instance. The 'filename'
 * is the file attached to the current workitem. The option 'convert-to' is
 * optional and default value is 'pdf'
 * 
 * 
 * @author rsoika
 *
 */
public class WopiDocumentConverterAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(WopiDocumentConverterAdapter.class.getName());

    public static final String DOCUMENT_ERROR = "DOCUMENT_ERROR";
    public static final String CONFIG_ERROR = "CONFIG_ERROR";

    @Inject
    WorkflowService workflowService;

    @Inject
    SnapshotService snapshotService;

    @Override
    public ItemCollection execute(ItemCollection document, ItemCollection event)
            throws AdapterException, PluginException {

        ItemCollection wopiConverterConfig = workflowService.evalWorkflowResult(event, "wopi-converter", document, false);
        if (wopiConverterConfig == null || !wopiConverterConfig.hasItem("api-endpoint")) {
            throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "Converter Error: 'api-endpoint' is not defined in current BPMN configuration");
        }
        if (wopiConverterConfig == null || !wopiConverterConfig.hasItem("filename")) {
            throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "Converter Error: 'filename' is not defined in current BPMN configuration");
        }

        String fileName = wopiConverterConfig.getItemValueString("filename");
        fileName = workflowService.adaptText(fileName, document);

        String apiEndpoint = wopiConverterConfig.getItemValueString("api-endpoint");
        String convertTo = wopiConverterConfig.getItemValueString("convert-to");
        if (convertTo.isEmpty()) {
            convertTo = "pdf";
        }
        logger.info("WopiDocumentConverter: " + apiEndpoint + "/" + convertTo + " - " + fileName);

        // load the document content
        // test if first file hast a content
        FileData fileData = document.getFileData(fileName);
        if (fileData == null) {
            throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "Converter Error: " + fileName + " not found in current workitem!");

        }
        if (fileData.getContent() == null || fileData.getContent().length < 3) {
            // load the snapshot
            fileData = snapshotService.getWorkItemFile(document.getUniqueID(), fileName);
        }

        // curl -F "data=@test.txt" https://localhost:9980/lool/convert-to/pdf > out.pdf
        String uri = apiEndpoint + "/" + convertTo;

        // post multipart/form-data
        
        try {
            postDocumentData(uri,fileData);
        } catch (IOException e) {
            throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), DOCUMENT_ERROR,
                    "WopiDocumentConverter Error - failed to post document data: " + e.getMessage());
        }

        return null;
    }

    /**
     * Helper method to post the content of a file to the Collabora Rest API
     * endpoint.
     * 
     * @param url
     * @param fileData
     * @throws MalformedURLException
     * @throws IOException
     */
    private void postDocumentData(String url, FileData fileData) throws MalformedURLException, IOException {

        String boundary = Long.toHexString(System.currentTimeMillis());
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));

            writer.println("--" + boundary);
            writer.println("Content-Disposition: form-data; name=\"" + fileData.getName() + "\"; filename=\""
                    + fileData.getName() + "\"");
            writer.println("Content-Type: text/plain; charset=UTF-8");
            writer.println();
            BufferedReader reader = null;

            writer.println(new String(fileData.getContent()));

            writer.println("--" + boundary + "--");
        } finally {
            if (writer != null)
                writer.close();
        }
        // Connection is lazily executed whenever you request any status.
        int responseCode = ((HttpURLConnection) connection).getResponseCode();
        // Handle response
        logger.info("response code=" + responseCode);
    }
}
