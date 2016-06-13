/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class XmlResourceTest {

    public XmlResourceTest() {
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
     * Test of static field.
     */
    @Test
    public void testStaticField(){
        System.out.println("staticfields");
        Map<URI, URI> map = XmlResource.RESOLVE_MAP;
        assertNotNull(map);

        assertEquals(XmlResource.I_URI_EXTXML, map.get(XmlResource.O_URI_EXTXML));
        assertEquals(XmlResource.I_URI_CORETYPE, map.get(XmlResource.O_URI_CORETYPE));
        assertEquals(XmlResource.I_URI_COREXML, map.get(XmlResource.O_URI_COREXML));

        return;
    }

    /**
     * Test of resource.
     */
    @Test
    public void testResource(){
        System.out.println("resource");
        Map<URI, URI> map = XmlResource.RESOLVE_MAP;

        for(Map.Entry<URI,URI> entry : map.entrySet()){
            URI in = entry.getValue();
            try{
                in.toURL().openStream();
            }catch(IOException e){
                fail();
            }
        }

        return;
    }

    /**
     * Test of net resource.
     */
//  @Test
    public void nettestNetResource(){
        System.out.println("netresource");
        Map<URI, URI> map = XmlResource.RESOLVE_MAP;

        for(Map.Entry<URI,URI> entry : map.entrySet()){
            URI out = entry.getKey();
            try{
                out.toURL().openStream();
            }catch(IOException e){
                fail();
            }
        }

        return;
    }

}
