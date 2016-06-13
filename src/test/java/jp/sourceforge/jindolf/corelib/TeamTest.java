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
public class TeamTest {

    public TeamTest() {
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
     * Test of values method, of class Team.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        Team[] values = Team.values();
        assertEquals(3, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class Team.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getTeamName method, of class Team.
     */
    @Test
    public void testGetTeamName(){
        System.out.println("getTeamName");
        assertEquals("村陣営",       Team.VILLAGE.getTeamName());
        assertEquals("狼陣営",       Team.WOLF.getTeamName());
        assertEquals("ハムスター人間", Team.HAMSTER.getTeamName());
        return;
    }

    /**
     * Test of getXmlName method, of class Team.
     */
    @Test
    public void testGetXmlName(){
        System.out.println("getXmlName");
        assertEquals("village", Team.VILLAGE.getXmlName());
        assertEquals("wolf", Team.WOLF.getXmlName());
        assertEquals("hamster", Team.HAMSTER.getXmlName());
        return;
    }

}
