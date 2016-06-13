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
public class SysEventTypeTest {

    public SysEventTypeTest() {
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
     * Test of values method, of class SysEventType.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        SysEventType[] values = SysEventType.values();
        assertEquals(29, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class SysEventType.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of getEventFamily method, of class SysEventType.
     */
    @Test
    public void testGetEventFamily(){
        System.out.println("getEventFamily");

        SysEventType[] values = SysEventType.values();

        for(SysEventType type : values){
            switch(type){
            case STARTENTRY:
            case ONSTAGE:
            case STARTMIRROR:
            case OPENROLE:
            case MURDERED:
            case STARTASSAULT:
            case SURVIVOR:
            case COUNTING:
            case SUDDENDEATH:
            case NOMURDER:
            case WINVILLAGE:
            case WINWOLF:
            case WINHAMSTER:
            case PLAYERLIST:
            case PANIC:
            case EXECUTION:
            case VANISH:
            case CHECKOUT:
            case SHORTMEMBER:
                assertEquals(EventFamily.ANNOUNCE, type.getEventFamily());
                break;
            case ASKENTRY:
            case ASKCOMMIT:
            case NOCOMMENT:
            case STAYEPILOGUE:
            case GAMEOVER:
                assertEquals(EventFamily.ORDER, type.getEventFamily());
                break;
            case JUDGE:
            case GUARD:
            case COUNTING2:
                assertEquals(EventFamily.EXTRA, type.getEventFamily());
                break;
            case ASSAULT:
            case UNKNOWN:
                assertNull(type.getEventFamily());
                break;
            default:
                fail();
                break;
            }
        }

        return;
    }

}
