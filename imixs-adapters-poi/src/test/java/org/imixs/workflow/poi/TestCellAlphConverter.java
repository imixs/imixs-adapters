package org.imixs.workflow.poi;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test converting a alphanumeric cell position into a number
 * <p>
 * 
 * @see: https://codereview.stackexchange.com/questions/44545/excel-column-string-to-row-number-and-vice-versa
 * 
 * @author rsoika
 * @version 1.0
 */
public class TestCellAlphConverter {

    /**
     * 
     */
    @Test
    public void testConverter() {
        Assert.assertEquals(0, POIFindReplaceAdapter.cellColumnToNumber("A"));
        Assert.assertEquals(1, POIFindReplaceAdapter.cellColumnToNumber("B"));
        Assert.assertEquals(26, POIFindReplaceAdapter.cellColumnToNumber("AA"));
        Assert.assertEquals(27, POIFindReplaceAdapter.cellColumnToNumber("AB"));
        Assert.assertEquals(51, POIFindReplaceAdapter.cellColumnToNumber("AZ"));
        Assert.assertEquals(702, POIFindReplaceAdapter.cellColumnToNumber("AAA"));
    }

}
