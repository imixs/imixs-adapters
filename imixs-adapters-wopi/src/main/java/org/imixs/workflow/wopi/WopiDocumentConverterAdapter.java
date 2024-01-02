package org.imixs.workflow.wopi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.imixs.archive.core.SnapshotService;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.util.XMLParser;

import jakarta.inject.Inject;

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
 * is the file attached to the current workitem. This can also be a regular
 * expression. The option 'convert-to' is optional and default value is 'pdf'
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

    /**
     * The execute method expects a 'wop-converter' configuraiton and a
     * corresponding attachement. The method calls the Collabora Rest API to convert
     * the file into PDF and attaches the new file.
     */
    @Override
    public ItemCollection execute(ItemCollection document, ItemCollection event)
            throws AdapterException, PluginException {

        ItemCollection evalItemCollection = null;
        List<ItemCollection> wopiConverterConfigList = null;

        // test for deprecated configuration using the <item> tag....
        if (isDeprecatedConfiguration(document, event)) {
            logger.warning(
                    "WopiTemplateAdapter is using deprecated configuration! Please use <wopi-template> instead of <wopi-converter name='source-path'>  - see documentation for details!");
            evalItemCollection = workflowService.evalWorkflowResult(event, "wopi-converter", document,
                    true);
            wopiConverterConfigList = new ArrayList<>();
            wopiConverterConfigList.add(evalItemCollection);
        } else {
            String workflowResult = event.getItemValueString("txtActivityResult");
            wopiConverterConfigList = XMLParser.parseTagList(workflowResult, "wopi-converter");
        }

        if (wopiConverterConfigList == null || wopiConverterConfigList.size() == 0) {
            throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "missing wopi-converter configuraiton in BPMN event!");
        }

        // iterate over all wopi-template configurations
        for (ItemCollection wopiConverterConfig : wopiConverterConfigList) {

            if (!wopiConverterConfig.hasItem("api-endpoint")) {
                throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), CONFIG_ERROR,
                        "Wopi-Converter Error: 'api-endpoint' is not defined in current BPMN configuration");
            }
            if (!wopiConverterConfig.hasItem("filename")) {
                throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), CONFIG_ERROR,
                        "Wopi-Converter Error: 'filename' is not defined in current BPMN configuration");
            }

            String apiEndpoint = wopiConverterConfig.getItemValueString("api-endpoint");
            String fileName = wopiConverterConfig.getItemValueString("filename");
            String convertTo = wopiConverterConfig.getItemValueString("convert-to");

            fileName = workflowService.adaptText(fileName, document);

            if (!apiEndpoint.endsWith("/")) {
                apiEndpoint = apiEndpoint + "/";
            }

            if (convertTo.isEmpty()) {
                convertTo = "pdf";
            }
            String uri = apiEndpoint + convertTo;

            logger.info("WopiDocumentConverter: " + fileName + " => " + uri);

            // test all file matching the filename or regular expression
            FileData fileData = document.getFileData(fileName);
            if (fileData != null) {
                // file data found by name directly - so we can convert it....
                convertFile(fileData, document, uri);
            } else {
                // not found, we can test regular expressions...
                List<String> fileNames = document.getFileNames();
                Pattern pattern = Pattern.compile(fileName);
                // get all fileNames....
                for (String aFileName : fileNames) {
                    // test if aFilename matches the pattern or the pattern is null
                    if (pattern.matcher(aFileName).find()) {
                        // fetch the file
                        fileData = document.getFileData(aFileName);
                        if (fileData != null) {
                            // file data found - so we can updated it....
                            convertFile(fileData, document, uri);
                        }

                    }
                }
            }
        }

        return document;
    }

    /**
     * 
     * This helper method converts the content of a given FileData obejct
     * <p>
     * The method verifies if the content of the file need to be loaded from the
     * snapshot
     * 
     * @param fileData
     * @param document
     * @param uri
     * @throws PluginException
     */
    void convertFile(FileData fileData, ItemCollection document, String uri) throws PluginException {

        if (fileData == null) {
            throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "Converter Error: invalid filedata!");

        }
        String fileName = fileData.getName();

        if (fileData.getContent() == null || fileData.getContent().length < 3) {
            // load the snapshot
            fileData = snapshotService.getWorkItemFile(document.getUniqueID(), fileName);
        }

        // curl -F "data=@test.txt" https://localhost:9980/lool/convert-to/pdf > out.pdf
        try {
            FileData pdfFile = postDocumentData(uri, fileData);
            document.addFileData(pdfFile);
        } catch (IOException e) {
            throw new PluginException(WopiDocumentConverterAdapter.class.getSimpleName(), DOCUMENT_ERROR,
                    "WopiDocumentConverter Error - failed to post document data: " + e.getMessage());
        }
    }

    /**
     * Helper method to post the content of a file to the Collabora Rest API
     * endpoint. The method returns a new FileData object with the PDF content.
     * 
     * 
     * @param url
     * @param fileData
     * @throws MalformedURLException
     * @throws IOException
     */
    private FileData postDocumentData(String url, FileData fileData) throws MalformedURLException, IOException {

        logger.finest("post " + fileData.getName() + " " + fileData.getContent().length + " bytes....");

        String fileName = fileData.getName();

        String boundary = Long.toHexString(System.currentTimeMillis());
        String CRLF = "\r\n";

        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        OutputStream output = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);

        writer.append("--" + boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + fileName + "\"")
                .append(CRLF);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(CRLF);
        writer.append("Content-Transfer-Encoding: binary").append(CRLF);
        writer.append(CRLF).flush();
        // write file content..
        output.write(fileData.getContent());
        output.flush();
        writer.append(CRLF).flush();
        writer.append("--" + boundary + "--").append(CRLF).flush();

        HttpURLConnection httpConnection = (HttpURLConnection) connection;

        // read response
        int responseCode = httpConnection.getResponseCode();
        logger.finest("response code=" + responseCode);
        InputStream response = httpConnection.getInputStream();
        byte[] pdfData = readAllBytes(response);

        logger.finest("read " + pdfData.length + " bytes");

        // construct a new FileData object
        String pdfFileName = fileName;
        pdfFileName = pdfFileName.substring(0, pdfFileName.lastIndexOf(".")) + ".pdf";
        FileData resultFileData = new FileData(pdfFileName, pdfData, "application/pdf", null);
        // return new pdf fileData object
        return resultFileData;
    }

    /**
     * Helper method to read from a inputStream into a byte array.
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                    outputStream.write(buf, 0, readLen);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null)
                inputStream.close();
            else
                try {
                    inputStream.close();
                } catch (IOException e) {
                    exception.addSuppressed(e);
                }
        }
    }

    /**
     * This method tests if the BPMN configuration is still using the deprecated tag
     * 
     * <wopi-template name="source-path">....
     * 
     * instead of the new
     * 
     * <wopi-template>...
     * 
     * @param event
     * @return
     * @throws PluginException
     */
    private boolean isDeprecatedConfiguration(ItemCollection workitem, ItemCollection event) throws PluginException {

        String workflowResult = event.getItemValueString("txtActivityResult");
        if (!workflowResult.contains("<wopi-converter>")) {
            return true;
        }

        return false;
    }
}
