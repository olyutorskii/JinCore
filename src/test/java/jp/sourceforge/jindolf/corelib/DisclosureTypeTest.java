/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class DisclosureTypeTest{

    public DisclosureTypeTest(){
    }

    /**
     * Test of values method, of class DisclosureType.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        DisclosureType[] types = DisclosureType.values();
        assertEquals(3, types.length);
        return;
    }

    /**
     * Test of valueOf method, of class DisclosureType.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getXmlName method, of class DisclosureType.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals("complete", DisclosureType.COMPLETE.getXmlName());
        assertEquals("uncomplete", DisclosureType.UNCOMPLETE.getXmlName());
        assertEquals("hot", DisclosureType.HOT.getXmlName());
        return;
    }

}
