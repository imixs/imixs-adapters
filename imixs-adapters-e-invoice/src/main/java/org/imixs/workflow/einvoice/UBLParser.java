package org.imixs.workflow.einvoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.imixs.workflow.ItemCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * This util class supports parsing a eInvoice file in UBL format
 */
public class UBLParser {

    private static Logger logger = Logger.getLogger(UBLParser.class.getName());

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * This method parses a UBL Dom Tree and adapts the eInvoice fields to the Imixs
     * Inovice Items
     */
    public static void parse(Document doc, ItemCollection workitem) {

        readXMLValue(doc, "/Invoices/Invoice/InvoiceHeader/InvoiceNumber",
                workitem, "invoice.number", String.class);

    }

    /**
     * Reads a tag value from the xml tree and set the value into the given
     * workitem.
     * 
     * /Invoices/Invoice/InvoiceHeader/Client/Code
     * 
     * Beispiel Datum:
     * <InvoiceDate>2024-05-13T00:00:00+02:00</InvoiceDate>
     * 
     * @param doc        - xml doc
     * @param expression - xpath expression
     * @param workitem
     * @param itemName
     * @param itemType
     */
    private static <T> void readXMLValue(Document doc, String expression, ItemCollection workitem, String itemName,
            Class<T> itemType) {
        // create XPath...
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression xPathExpression;
        try {
            xPathExpression = xpath.compile(expression);

            // extract node value
            Node valueNode = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
            if (valueNode != null) {
                String value = valueNode.getTextContent();

                if (itemType == Date.class) {
                    // 2024-05-13T00:00:00+02:00
                    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                    formatter.setTimeZone(TimeZone.getTimeZone("CET"));
                    try {
                        workitem.setItemValue(itemName, formatter.parse(value));
                    } catch (ParseException e) {
                        logger.warning("Invalid Date Format");
                    }
                    return;
                }
                if (itemType == Double.class && value != null && !value.isEmpty()) {
                    workitem.setItemValue(itemName, Double.parseDouble(value));
                    return;
                }
                if (itemType == Integer.class && value != null && !value.isEmpty()) {
                    workitem.setItemValue(itemName, Integer.parseInt(value));
                    return;
                }
                // Default String format
                workitem.setItemValue(itemName, value);

            }
        } catch (XPathExpressionException e) {
            logger.warning("Unable to read data field '" + expression + "' : " + e.getMessage());

        }
    }

}
