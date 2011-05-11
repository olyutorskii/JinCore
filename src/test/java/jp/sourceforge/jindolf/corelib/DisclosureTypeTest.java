/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class DisclosureTypeTest{

    public DisclosureTypeTest(){
    }

    @BeforeClass
    public static void setUpClass() throws Exception{
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
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
