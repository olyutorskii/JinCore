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
public class PeriodTypeTest {

    public PeriodTypeTest() {
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
     * Test of values method, of class PeriodType.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        PeriodType[] values = PeriodType.values();
        assertEquals(3, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class PeriodType.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getXmlName method, of class PeriodType.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals("prologue", PeriodType.PROLOGUE.getXmlName());
        assertEquals("progress", PeriodType.PROGRESS.getXmlName());
        assertEquals("epilogue", PeriodType.EPILOGUE.getXmlName());
        return;
    }

}
