/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class XmlResourceTest {

    public XmlResourceTest() {
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
