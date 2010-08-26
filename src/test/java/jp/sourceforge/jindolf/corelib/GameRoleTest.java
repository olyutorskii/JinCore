/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class GameRoleTest {

    public GameRoleTest() {
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
     * Test of values method, of class GameRole.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        GameRole[] values = GameRole.values();
        assertEquals(8, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class GameRole.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of lookingAtRole method, of class GameRole.
     */
    @Test
    public void testLookingAtRole(){
        System.out.println("lookingAtRole");

        Pattern pattern = Pattern.compile("");

        Matcher matcher;

        matcher = pattern.matcher("村人");
        assertEquals(GameRole.INNOCENT, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("人狼");
        assertEquals(GameRole.WOLF, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("占い師");
        assertEquals(GameRole.SEER, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("霊能者");
        assertEquals(GameRole.SHAMAN, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("狂人");
        assertEquals(GameRole.MADMAN, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("狩人");
        assertEquals(GameRole.HUNTER, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("共有者");
        assertEquals(GameRole.FRATER, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("ハムスター人間");
        assertEquals(GameRole.HAMSTER, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("xxx");
        assertNull(GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("");
        assertNull(GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("x村人");
        assertNull(GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("村");
        assertNull(GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("村人x");
        assertEquals(GameRole.INNOCENT, GameRole.lookingAtRole(matcher));

        matcher = pattern.matcher("x村人x");
        assertNull(GameRole.lookingAtRole(matcher));

        return;
    }

    /**
     * Test of getPowerBalanceComparator method, of class GameRole.
     */
    @Test
    public void testGetPowerBalanceComparator(){
        System.out.println("getPowerBalanceComparator");

        Comparator<GameRole> cmp = GameRole.getPowerBalanceComparator();
        assertNotNull(cmp);
        Comparator<GameRole> cmp2 = GameRole.getPowerBalanceComparator();
        assertTrue(cmp == cmp2);

        assertTrue(cmp.compare(GameRole.INNOCENT, GameRole.INNOCENT) == 0);
        assertTrue(cmp.compare(GameRole.INNOCENT, GameRole.FRATER) < 0);
        assertTrue(cmp.compare(GameRole.FRATER, GameRole.SEER) < 0);
        assertTrue(cmp.compare(GameRole.SEER, GameRole.SHAMAN) < 0);
        assertTrue(cmp.compare(GameRole.SHAMAN, GameRole.HUNTER) < 0);
        assertTrue(cmp.compare(GameRole.HUNTER, GameRole.HAMSTER) < 0);
        assertTrue(cmp.compare(GameRole.HAMSTER, GameRole.MADMAN) < 0);
        assertTrue(cmp.compare(GameRole.MADMAN, GameRole.WOLF) < 0);
        assertTrue(cmp.compare(GameRole.WOLF, GameRole.INNOCENT) > 0);
        assertTrue(cmp.compare(GameRole.FRATER, GameRole.INNOCENT) > 0);

        return;
    }

    /**
     * Test of getRoleName method, of class GameRole.
     */
    @Test
    public void testGetRoleName(){
        System.out.println("getRoleName");
        assertEquals("村人", GameRole.INNOCENT.getRoleName());
        assertEquals("人狼", GameRole.WOLF.getRoleName());
        assertEquals("占い師", GameRole.SEER.getRoleName());
        assertEquals("霊能者", GameRole.SHAMAN.getRoleName());
        assertEquals("狂人", GameRole.MADMAN.getRoleName());
        assertEquals("狩人", GameRole.HUNTER.getRoleName());
        assertEquals("共有者", GameRole.FRATER.getRoleName());
        assertEquals("ハムスター人間", GameRole.HAMSTER.getRoleName());
        return;
    }

    /**
     * Test of getShortName method, of class GameRole.
     */
    @Test
    public void testGetShortName(){
        System.out.println("getShortName");
        assertEquals('村', GameRole.INNOCENT.getShortName());
        assertEquals('狼', GameRole.WOLF.getShortName());
        assertEquals('占', GameRole.SEER.getShortName());
        assertEquals('霊', GameRole.SHAMAN.getShortName());
        assertEquals('狂', GameRole.MADMAN.getShortName());
        assertEquals('狩', GameRole.HUNTER.getShortName());
        assertEquals('共', GameRole.FRATER.getShortName());
        assertEquals('公', GameRole.HAMSTER.getShortName());
        return;
    }

    /**
     * Test of getTeam method, of class GameRole.
     */
    @Test
    public void testGetTeam(){
        System.out.println("getTeam");
        assertEquals(Team.VILLAGE, GameRole.INNOCENT.getTeam());
        assertEquals(Team.WOLF,    GameRole.WOLF.getTeam());
        assertEquals(Team.VILLAGE, GameRole.SEER.getTeam());
        assertEquals(Team.VILLAGE, GameRole.SHAMAN.getTeam());
        assertEquals(Team.WOLF,    GameRole.MADMAN.getTeam());
        assertEquals(Team.VILLAGE, GameRole.HUNTER.getTeam());
        assertEquals(Team.VILLAGE, GameRole.FRATER.getTeam());
        assertEquals(Team.HAMSTER, GameRole.HAMSTER.getTeam());
        return;
    }

    /**
     * Test of getXmlName method, of class GameRole.
     */
    @Test
    public void testGetXmlName() {
        System.out.println("getXmlName");
        assertEquals("innocent", GameRole.INNOCENT.getXmlName());
        assertEquals("wolf", GameRole.WOLF.getXmlName());
        assertEquals("seer", GameRole.SEER.getXmlName());
        assertEquals("shaman", GameRole.SHAMAN.getXmlName());
        assertEquals("madman", GameRole.MADMAN.getXmlName());
        assertEquals("hunter", GameRole.HUNTER.getXmlName());
        assertEquals("frater", GameRole.FRATER.getXmlName());
        assertEquals("hamster", GameRole.HAMSTER.getXmlName());
        return;
    }

}
