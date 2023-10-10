/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class VillageStateTest {

    public VillageStateTest() {
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
