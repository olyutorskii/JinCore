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
public class EventFamilyTest {

    public EventFamilyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception{
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class EventFamily.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        EventFamily[] values = EventFamily.values();
        assertEquals(3, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class EventFamily.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getDivClass method, of class EventFamily.
     */
    @Test
    public void testGetDivClass(){
        System.out.println("getDivClass");
        assertEquals("announce", EventFamily.ANNOUNCE.getDivClass());
        assertEquals("order",    EventFamily.ORDER.getDivClass());
        assertEquals("extra",    EventFamily.EXTRA.getDivClass());
        return;
    }

    /**
     * Test of getXmlName method, of class EventFamily.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals(EventFamily.ANNOUNCE.getXmlName(), EventFamily.ANNOUNCE.getDivClass());
        assertEquals(EventFamily.ORDER.getXmlName(),    EventFamily.ORDER.getDivClass());
        assertEquals(EventFamily.EXTRA.getXmlName(),    EventFamily.EXTRA.getDivClass());
        return;
    }

}
