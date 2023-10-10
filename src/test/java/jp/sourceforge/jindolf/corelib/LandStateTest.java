/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class LandStateTest {

    public LandStateTest() {
    }

    /**
     * Test of values method, of class LandState.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        LandState[] values = LandState.values();
        assertEquals(3, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class LandState.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getXmlName method, of class LandState.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals("closed", LandState.CLOSED.getXmlName());
        assertEquals("historical", LandState.HISTORICAL.getXmlName());
        assertEquals("active", LandState.ACTIVE.getXmlName());
    }

}
