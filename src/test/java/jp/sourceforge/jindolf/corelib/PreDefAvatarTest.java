/*
 * Copyright(c) 2009 olyutorskii
 * $Id: PreDefAvatarTest.java 683 2009-08-19 05:10:06Z olyutorskii $
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;

/**
 */
public class PreDefAvatarTest {

    private DocumentBuilder builder;

    public PreDefAvatarTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception{
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
    }

    @Before
    public void setUp() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            this.builder = factory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            fail();
        }
        return;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of buildPreDefAvatarList method, of class PreDefAvatar.
     */
    @Test
    public void testBuildPreDefAvatarList(){
        System.out.println("buildPreDefAvatarList");

        List<PreDefAvatar> result;
        try{
            result = PreDefAvatar.buildPreDefAvatarList(this.builder);
        }catch(SAXException e){
            fail();
            return;
        }catch(IOException e){
            fail();
            return;
        }

        assertEquals(20, result.size());

        assertEquals("gerd", result.get(0).getAvatarId());
        assertEquals("simon", result.get(19).getAvatarId());

        return;
    }

    private List<PreDefAvatar> loadList(){
        List<PreDefAvatar> result;
        try{
            result = PreDefAvatar.buildPreDefAvatarList(this.builder);
        }catch(SAXException e){
            fail();
            return null;
        }catch(IOException e){
            fail();
            return null;
        }
        return result;
    }

    /**
     * Test of getAvatarId method, of class PreDefAvatar.
     */
    @Test
    public void testGetAvatarId(){
        System.out.println("getAvatarId");

        List<PreDefAvatar> list = loadList();

        assertEquals("gerd", list.get(0).getAvatarId());

        return;
    }

    /**
     * Test of getFullName method, of class PreDefAvatar.
     */
    @Test
    public void testGetFullName(){
        System.out.println("getFullName");

        List<PreDefAvatar> list = loadList();

        assertEquals("楽天家\u0020ゲルト", list.get(0).getFullName());

        return;
    }

    /**
     * Test of getJobTitle method, of class PreDefAvatar.
     */
    @Test
    public void testGetJobTitle(){
        System.out.println("getJobTitle");

        List<PreDefAvatar> list = loadList();

        assertEquals("楽天家", list.get(0).getJobTitle());

        return;
    }

    /**
     * Test of getShortName method, of class PreDefAvatar.
     */
    @Test
    public void testGetShortName(){
        System.out.println("getShortName");

        List<PreDefAvatar> list = loadList();

        assertEquals("ゲルト", list.get(0).getShortName());

        return;
    }

    /**
     * Test of getSerialNo method, of class PreDefAvatar.
     */
    @Test
    public void testGetSerialNo(){
        System.out.println("getSerialNo");

        List<PreDefAvatar> list = loadList();

        assertEquals(1, list.get(0).getSerialNo());

        return;
    }

}
