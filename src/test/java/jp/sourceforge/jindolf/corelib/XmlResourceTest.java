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

        for(Map.Entry<URI,URI> entry : map.entrySet()){
            URI out = entry.getKey();
            URI in = entry.getValue();
            try{
                out.toURL().openStream();
                in.toURL().openStream();
            }catch(IOException e){
                fail();
            }
        }

        return;
    }

}
