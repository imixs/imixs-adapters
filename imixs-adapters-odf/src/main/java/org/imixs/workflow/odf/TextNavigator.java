package org.imixs.workflow.odf;

import java.util.Iterator;
import java.util.logging.Logger;

import org.odftoolkit.odfdom.changes.TextContainingElement;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.odftoolkit.odfdom.pkg.OdfElement;

/**
 * The helper class iterates through a OdfTextDocument and finds all paragraphs containing a given search pattern.
 */
public class TextNavigator implements Iterator<TextContainingElement> {
    private static Logger logger = Logger.getLogger(TextNavigator.class.getName());
	
    OdfTextDocument doc=null;
    String pattern=null;

    OdfElement root=null;
    OdfElement parent=null;

    TextContainingElement currentParagraph =null;

    
    public TextNavigator(OdfTextDocument doc, String pattern) throws Exception {
        this.doc = doc;
        this.pattern = pattern;
        root = doc.getContentRoot();
        parent=root;
        currentParagraph = OdfElement.findFirstChildNode(TextPElement.class, root);
    }




    @Override
    public boolean hasNext() {

        // find the next paragraph containing the search pattern.
        if (currentParagraph!=null) {
            String text=currentParagraph.getTextContent();
            if (text.contains(pattern)) {
                return true;
            } else {
                // test for childs...
                TextContainingElement child = TextContainingElement.findNextChildNode(TextContainingElement.class, currentParagraph);
                if (child!=null) {
                    parent=currentParagraph;
                    currentParagraph=child;
                    return hasNext();
                } else {
                    // end of document
                    currentParagraph=null;
                }
            }
        }
        return false;
    }

    @Override
    public TextContainingElement next() {
       return currentParagraph;
    }

 
}
