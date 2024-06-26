package org.imixs.workflow.einvoice;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.imixs.workflow.ItemCollection;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * This util class supports parsing a eInvoice file in UBL format
 */
public class UBLParser {

    private static Logger logger = Logger.getLogger(UBLParser.class.getName());

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * This method parses a Input Stream with a UBL xml document and returns the Dom
     * Tree object.
     * 
     * We set 'namespaceAware' to true to ensure that namespaces can be used for
     * later item parsing.
     */
    public static Document parseInputStream(InputStream xmlInputStream) {
        Document doc = null;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            documentBuilder = factory.newDocumentBuilder();
            doc = documentBuilder.parse(xmlInputStream);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * This method parses a UBL Dom Tree and adapts the eInvoice fields to the Imixs
     * Invoice Items
     * 
     * @throws XPathExpressionException
     */
    public static void parseItems(Document doc, ItemCollection workitem) throws XPathExpressionException {

        // Create XPath instance with extracted namespaces
        XPath xpath = createXPath(doc);

        // evaluate items.....
        readXMLValue(doc, xpath, "/ubl:Invoice/cbc:ID/text()", workitem, "invoice.number", String.class);
        readXMLValue(doc, xpath, "/ubl:Invoice/cbc:IssueDate/text()", workitem, "invoice.date", Date.class);
    }

    /**
     * Reads a tag value from the xml tree and set the value into the given
     * workitem.
     * 
     * Example invoice date -> /ubl:Invoice/cbc:IssueDate/text()
     * <cbc:IssueDate>2024-05-13</cbc:IssueDate>
     * 
     * @param doc        - xml doc
     * @param xpath      - xpath instance
     * @param expression - expression
     * @param workitem
     * @param itemName
     * @param itemType
     */
    private static <T> void readXMLValue(Document doc, XPath xpath, String expression, ItemCollection workitem,
            String itemName, Class<T> itemType)
            throws XPathExpressionException {
        XPathExpression expr = xpath.compile(expression);

        String value = (String) expr.evaluate(doc, XPathConstants.STRING);
        if (itemType == Date.class) {
            // 2024-06-26
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

    /**
     * Creates a XPath instance with the current namespace context defined
     * 
     * @param doc
     * @return
     */
    private static XPath createXPath(Document doc) {
        final Map<String, String> namespaces = extractNamespaces(doc);

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        xpath.setNamespaceContext(new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                return namespaces.get(prefix);
            }

            public String getPrefix(String uri) {
                for (Map.Entry<String, String> entry : namespaces.entrySet()) {
                    if (entry.getValue().equals(uri)) {
                        return entry.getKey();
                    }
                }
                return null;
            }

            public Iterator getPrefixes(String uri) {
                return null; //
            }
        });

        return xpath;
    }

    private static Map<String, String> extractNamespaces(Document doc) {
        Map<String, String> namespaces = new HashMap<>();
        NamedNodeMap attributes = doc.getDocumentElement().getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node node = attributes.item(i);
            String nodeName = node.getNodeName();
            if (nodeName.startsWith("xmlns:")) {
                String prefix = nodeName.substring(6); // Länge von "xmlns:"
                namespaces.put(prefix, node.getNodeValue());
            }
        }
        return namespaces;
    }
}
