/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class DestinyTest {

    public DestinyTest() {
    }

    /**
     * Test of values method, of class Destiny.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        Destiny[] values = Destiny.values();
        assertEquals(5, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class Destiny.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getMessage method, of class Destiny.
     */
    @Test
    public void testGetMessage(){
        System.out.println("getMessage");
        assertEquals("生存",     Destiny.ALIVE.getMessage());
        assertEquals("突然死",   Destiny.SUDDENDEATH.getMessage());
        assertEquals("処刑死",   Destiny.EXECUTED.getMessage());
        assertEquals("襲撃死",   Destiny.EATEN.getMessage());
        assertEquals("ハム溶け", Destiny.DISSOLVE.getMessage());
        return;
    }

}
