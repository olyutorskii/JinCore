/*
 * Copyright(c) 2009 olyutorskii
 * $Id: VillageStateTest.java 865 2009-10-21 11:43:41Z olyutorskii $
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
public class VillageStateTest {

    public VillageStateTest() {
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
     * Test of values method, of class VillageState.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        VillageState[] values = VillageState.values();
        assertEquals(5, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class VillageState.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getXmlName method, of class VillageState.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals("prologue", VillageState.PROLOGUE.getXmlName());
        assertEquals("progress", VillageState.PROGRESS.getXmlName());
        assertEquals("epilogue", VillageState.EPILOGUE.getXmlName());
        assertEquals("gameover", VillageState.GAMEOVER.getXmlName());
        assertEquals("unknown", VillageState.UNKNOWN.getXmlName());
        return;
    }

}
