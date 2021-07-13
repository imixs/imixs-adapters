package org.imixs.workflow.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.imixs.archive.core.SnapshotService;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;

/**
 * This POIFindReplaceAdapter can be used to find and replace text fragements in
 * a MS Word (docx) document.
 * <p>
 * The adapter can be configured by the event workflow result:
 * <p>
 * 
 * <pre>
 * {@code
      <poi-update name=
        "filename">PLA Membership Agreement-<itemvalue>numsequencenumber</itemvalue>.docx</poi-update>
        <poi-update name="findreplace">
               <find>[company.name]</find>
               <replace><itemvalue>company.name</itemvalue></replace>
        </poi-update>
        <poi-update name="findreplace">
               <find>[company.country]</find>
               <replace><itemvalue>company.country</itemvalue></replace>
        </poi-update>
        <poi-update name="findreplace">
               <find>[contract.startdate]</find>
               <replace><itemvalue format=
        "EEE, MMM d, yyyy">contract.start</itemvalue></replace>
       </poi-update>
   }
 * </pre>
 * 
 * 
 * 
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class POIFindReplaceAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(POIFindReplaceAdapter.class.getName());
    public static final String DOCUMENT_ERROR = "DOCUMENT_ERROR";
    public static final String CONFIG_ERROR = "CONFIG_ERROR";

    @Inject
    WorkflowService workflowService;

    @Inject
    SnapshotService snapshotService;

    @Inject
    ReportService reportService;

    /**
     * This method computes the findreplace configuration and updates an attachments
     * form type .docx
     * 
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    @Override
    public ItemCollection execute(ItemCollection document, ItemCollection event)
            throws AdapterException, PluginException {

        // read the config
        ItemCollection poiConfig = workflowService.evalWorkflowResult(event, "poi-update", document, false);
        if (poiConfig == null || !poiConfig.hasItem("findreplace")) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong poi configuration");
        }
        String fileName = poiConfig.getItemValueString("filename");
        fileName = workflowService.adaptText(fileName, document);

        logger.fine("......starting update file: " + fileName + "...");
        XWPFDocument doc = null;
        try {

            // test if first file hast a content
            FileData fileData = document.getFileData(fileName);
            if (fileData.getContent() == null || fileData.getContent().length < 3) {
                // load the snapshot
                fileData = snapshotService.getWorkItemFile(document.getUniqueID(), fileName);
            }
            InputStream imputStream = new ByteArrayInputStream(fileData.getContent());
            doc = new XWPFDocument(imputStream);
            logger.fine("XWPFDocument loaded");

            List<String> replaceDevList = poiConfig.getItemValue("findreplace");
            if (replaceDevList.size() == 0) {
                throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                        "wrong poi configuration");
            }
            for (String entityDev : replaceDevList) {
                ItemCollection entityData = XMLParser.parseItemStructure(entityDev);

                if (entityData != null) {
                    String find = entityData.getItemValueString("find");
                    find = workflowService.adaptText(find, document);
                    String replace = entityData.getItemValueString("replace");
                    replace = workflowService.adaptText(replace, document);
                    replaceParagraphs(doc, find, replace);
                }
            }

            logger.fine("findreplace completed");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doc.write(byteArrayOutputStream);

            byte[] bytes = byteArrayOutputStream.toByteArray();
            // public FileData(String name, byte[] content, String contentType, Map<String,
            // List<Object>> attributes) {
            FileData fileDataNew = new FileData(fileData.getName(), bytes, fileData.getContentType(), null);

            document.addFileData(fileDataNew);
            logger.fine("new document added");

        } catch (IOException e) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), DOCUMENT_ERROR,
                    "doucment '" + fileName + "' not readable: " + e.getMessage());
        }

        return document;
    }

    /**
     * Helph method replaces a given test in a XWPFDocument
     */
    private void replaceParagraphs(XWPFDocument doc, String find, String replace) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains(find)) {
                        text = text.replace(find, replace);
                        logger.fine("..replace " + find + " with  " + replace);
                        r.setText(text, 0);
                    }
                }
            }
        }
    }

}