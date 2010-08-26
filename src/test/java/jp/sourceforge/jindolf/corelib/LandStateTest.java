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
public class LandStateTest {

    public LandStateTest() {
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
