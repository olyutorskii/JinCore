/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class TalkTypeTest {

    public TalkTypeTest() {
    }

    /**
     * Test of values method, of class TalkType.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        TalkType[] values = TalkType.values();
        assertEquals(4, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class TalkType.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getXmlName method, of class TalkType.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals("public", TalkType.PUBLIC.getXmlName());
        assertEquals("wolf", TalkType.WOLFONLY.getXmlName());
        assertEquals("private", TalkType.PRIVATE.getXmlName());
        assertEquals("grave", TalkType.GRAVE.getXmlName());
        return;
    }

}
