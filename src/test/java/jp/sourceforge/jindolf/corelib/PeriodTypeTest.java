/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class PeriodTypeTest {

    public PeriodTypeTest() {
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
