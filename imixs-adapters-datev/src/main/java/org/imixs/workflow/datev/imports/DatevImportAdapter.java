package org.imixs.workflow.datev.imports;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;

/**
 * Der DatevImportAdapter liest eine DATEV Datei ein und parst dazu die
 * einzelnen Header Informationen.
 * <ul>
 * <li>DATEV-Format-KZ
 * <li>Versionsnummer
 * <li>Datenkategorie
 * <li>Formatname
 * <li>Formatversion
 * <li>Erzeugt am
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class DatevImportAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(DatevImportAdapter.class.getName());

    public static final String DATEV_IMPORT_ERROR = "DATEV_IMPORT_ERROR";
    public static final String ENCODING = "ISO-8859-1";

    /**
     * This method finds or create the Datev Export and adds a reference
     * ($workitemref) to the current invoice.
     * 
     * @throws PluginException
     */
    @Override
    public ItemCollection execute(ItemCollection workitem, ItemCollection event)
            throws AdapterException, PluginException {

        logger.info("...start datev file import...");
        validateDATEVFile(workitem);
        readHeader(workitem);
        return workitem;
    }

    /**
     * Diese Hilfsmethode prüft ob das DATEV Datei Format eingehalten ist.
     * 
     * 
     * @param workitem
     * @throws PluginException
     */
    private void validateDATEVFile(ItemCollection workitem) throws PluginException {

        // test 1 file only
        List<FileData> fileDataList = workitem.getFileData();
        if (fileDataList.size() != 1) {
            throw new PluginException(DatevImportAdapter.class.getName(), DATEV_IMPORT_ERROR,
                    "Only one file expected!");
        }
        // test file name pattern..
        FileData fileData = fileDataList.get(0);
        String filename = fileData.getName().toLowerCase();
        if (!filename.startsWith("dtvf_") && !filename.startsWith("extf_")) {
            throw new PluginException(DatevImportAdapter.class.getName(), DATEV_IMPORT_ERROR,
                    "DTVF or EXTF prefix expected!");
        }

        if (!filename.endsWith(".csv")) {
            throw new PluginException(DatevImportAdapter.class.getName(), DATEV_IMPORT_ERROR, ".csv suffix expected!");
        }

    }

    /**
     * Liest die erste header zeile aus
     * 
     * @param document
     * @throws PluginException 
     */
    private void readHeader(ItemCollection workitem) throws PluginException {
        FileData fileData = workitem.getFileData().get(0);
        ByteArrayInputStream imputStream = new ByteArrayInputStream(fileData.getContent());

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(imputStream, ENCODING));
            // read first line containing the object type
            String header1;
            header1 = in.readLine();
            String[] header1List = header1.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 99);

            if (header1List.length<13) {
                throw new PluginException(DatevImportAdapter.class.getName(), DATEV_IMPORT_ERROR, "Invalid header-1!");
            }
            
            
            
            workitem.setItemValue("datev.format-kz", csvVal(header1List[0]));
            workitem.setItemValue("datev.Versionsnummer", csvVal(header1List[1]));
            workitem.setItemValue("datev.Datenkategorie", csvVal(header1List[2]));
            workitem.setItemValue("datev.Formatname", csvVal(header1List[3]));
            workitem.setItemValue("datev.Erzeugt_am", csvVal(header1List[5]));
            workitem.setItemValue("datev.WJ-Beginn", csvVal(header1List[12]));
            

        } catch (IOException e) {
            throw new PluginException(DatevImportAdapter.class.getName(), DATEV_IMPORT_ERROR, "Unable to read file",e);
        }

    }

    /**
     * Diese methode entfernt optional " Zeichen am begin und ende
     * @param value
     * @return
     */
    public static String csvVal(String value) {
        
        if (value.startsWith("\"")) {
            value=value.substring(1);
        }
        if (value.endsWith("\"")) {
            value=value.substring(0,value.length()-1);
        }
        
        return value;
        
    }
}