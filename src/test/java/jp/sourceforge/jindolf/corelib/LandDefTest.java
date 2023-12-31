/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 */
public class LandDefTest {

    private DocumentBuilder builder;

    public LandDefTest() {
    }

    @BeforeEach
    public void setUp() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            this.builder = factory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            fail();
        }
        return;
    }

    /**
     * Test of parseIntList method, of class LandDef.
     */
    @Test
    public void testParseIntList(){
        System.out.println("parseIntList");
        CharSequence seq;
        Collection<Integer> coll;
        List<Integer> result;

        seq = "1,2,3";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));

        seq = "\u0020\t1\u0020,2,\u00203\t\u0020";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));

        seq = "1-3";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));

        seq = "1\u0020\t-\t\u00203";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));

        seq = "3,2,1";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));

        seq = "3-1";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));

        seq = "1,2,3,11-13";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(6, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(11), result.get(3));
        assertEquals(Integer.valueOf(12), result.get(4));
        assertEquals(Integer.valueOf(13), result.get(5));

        seq = "1,2,11-13,3";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(6, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(11), result.get(3));
        assertEquals(Integer.valueOf(12), result.get(4));
        assertEquals(Integer.valueOf(13), result.get(5));

        seq = "1,1";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));

        seq = "1,2,1";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));

        seq = "1-3,2-4";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(4, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(4), result.get(3));

        seq = "";
        coll = LandDef.parseIntList(seq);
        result = new ArrayList<>(coll);
        assertEquals(0, result.size());

        seq = "x";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "1,x,3";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "-";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "1-2-3";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "1-x";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "x-3";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "1-";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        seq = "-3";
        try{
            LandDef.parseIntList(seq);
            fail();
        }catch(IllegalArgumentException e){
        }

        return;
    }

    /**
     * Test of buildLandDefList method, of class LandDef.
     */
    @Test
    public void testBuildLandDefList(){
        System.out.println("buildLandDefList");

        List<LandDef> result;
        try{
            result = LandDef.buildLandDefList(this.builder);
        }catch(SAXException | IOException e){
            fail();
            return;
        }

        assertEquals(9, result.size());

        assertEquals("wolf", result.get(0).getLandId());
        assertEquals("wolff", result.get(7).getLandId());
        assertEquals("wolfg", result.get(8).getLandId());

        return;
    }

    private List<LandDef> loadList(){
        List<LandDef> result;
        try{
            result = LandDef.buildLandDefList(this.builder);
        }catch(SAXException | IOException e){
            fail();
            return null;
        }
        return result;
    }

    private LandDef selectLand(String id){
        for(LandDef land : loadList()){
            if(land.getLandId().equals(id)) return land;
        }
        return null;
    }

    /**
     * Test of getLandName method, of class LandDef.
     */
    @Test
    public void testGetLandName(){
        System.out.println("getLandName");

        assertEquals("人狼BBS:古国", selectLand("wolf").getLandName());
        assertEquals("人狼BBS:F国", selectLand("wolff").getLandName());
        assertEquals("人狼BBS:G国", selectLand("wolfg").getLandName());

        return;
    }

    /**
     * Test of getLandId method, of class LandDef.
     */
    @Test
    public void testGetLandId(){
        System.out.println("getLandId");

        assertEquals("wolf", selectLand("wolf").getLandId());
        assertEquals("wolf0", selectLand("wolf0").getLandId());
        assertEquals("wolff", selectLand("wolff").getLandId());
        assertEquals("wolfg", selectLand("wolfg").getLandId());

        return;
    }

    /**
     * Test of getFormalName method, of class LandDef.
     */
    @Test
    public void testGetFormalName(){
        System.out.println("getFormalName");

        assertEquals("旧人狼BBS 2", selectLand("wolf").getFormalName());
        assertEquals("人狼BBS:F", selectLand("wolff").getFormalName());
        assertEquals("人狼BBS:G", selectLand("wolfg").getFormalName());

        return;
    }

    /**
     * Test of getLandPrefix method, of class LandDef.
     */
    @Test
    public void testGetLandPrefix(){
        System.out.println("getLandPrefix");

        assertEquals("", selectLand("wolf").getLandPrefix());
        assertEquals("", selectLand("wolf0").getLandPrefix());
        assertEquals("F", selectLand("wolff").getLandPrefix());
        assertEquals("G", selectLand("wolfg").getLandPrefix());

        return;
    }

    /**
     * Test of getLandState method, of class LandDef.
     */
    @Test
    public void testGetLandState(){
        System.out.println("getLandState");

        assertEquals(LandState.HISTORICAL, selectLand("wolf0").getLandState());
        assertEquals(LandState.HISTORICAL, selectLand("wolff").getLandState());
        assertEquals(LandState.HISTORICAL, selectLand("wolfg").getLandState());

        return;
    }

    /**
     * Test of getMinMembers method, of class LandDef.
     */
    @Test
    public void testGetMinMembers(){
        System.out.println("getMinMembers");

        assertEquals(8, selectLand("wolf0").getMinMembers());
        assertEquals(11, selectLand("wolff").getMinMembers());
        assertEquals(10, selectLand("wolfg").getMinMembers());

        return;
    }

    /**
     * Test of getMaxMembers method, of class LandDef.
     */
    @Test
    public void testGetMaxMembers(){
        System.out.println("getMaxMembers");

        assertEquals(16, selectLand("wolf0").getMaxMembers());
        assertEquals(16, selectLand("wolff").getMaxMembers());
        assertEquals(16, selectLand("wolfg").getMaxMembers());

        return;
    }

    /**
     * Test of getWebURI method, of class LandDef.
     */
    @Test
    public void testGetWebURI(){
        System.out.println("getWebURI");

        assertEquals("http://ninjinix.x0.com/wolf/",
                     selectLand("wolf").getWebURI().toString());
        assertEquals("http://ninjinix.x0.com/wolff/",
                     selectLand("wolff").getWebURI().toString());
        assertEquals("http://ninjinix.x0.com/wolfg/",
                     selectLand("wolfg").getWebURI().toString());

        return;
    }

    /**
     * Test of getCgiURI method, of class LandDef.
     */
    @Test
    public void testGetCgiURI(){
        System.out.println("getCgiURI");

        assertEquals("http://ninjinix.x0.com/wolf/index.rb",
                     selectLand("wolf").getCgiURI().toString());
        assertEquals("http://ninjinix.x0.com/wolff/index.rb",
                     selectLand("wolff").getCgiURI().toString());
        assertEquals("http://ninjinix.x0.com/wolfg/index.rb",
                     selectLand("wolfg").getCgiURI().toString());

        return;
    }

    /**
     * Test of getTombFaceIconURI method, of class LandDef.
     */
    @Test
    public void testGetTombFaceIconURI(){
        System.out.println("getTombFaceIconURI");

        assertEquals("plugin_wolf/img/face99.jpg",
                     selectLand("wolf").getTombFaceIconURI().toString());
        assertEquals("../img/face99.jpg",
                     selectLand("wolf0").getTombFaceIconURI().toString());
        assertEquals("plugin_wolf/img/face99.jpg",
                     selectLand("wolff").getTombFaceIconURI().toString());
        assertEquals("plugin_wolf/img/face99.jpg",
                     selectLand("wolfg").getTombFaceIconURI().toString());

        return;
    }

    /**
     * Test of getTombBodyIconURI method, of class LandDef.
     */
    @Test
    public void testGetTombBodyIconURI(){
        System.out.println("getTombBodyIconURI");

        assertEquals("plugin_wolf/img/body99.jpg",
                     selectLand("wolf").getTombBodyIconURI().toString());
        assertEquals("../img/body99.jpg",
                     selectLand("wolf0").getTombBodyIconURI().toString());
        assertEquals("plugin_wolf/img/body99.jpg",
                     selectLand("wolff").getTombBodyIconURI().toString());
        assertEquals("plugin_wolf/img/body99.jpg",
                     selectLand("wolfg").getTombBodyIconURI().toString());

        return;
    }

    /**
     * Test of getFaceURITemplate method, of class LandDef.
     */
    @Test
    public void testGetFaceURITemplate(){
        System.out.println("getFaceURITemplate");

        assertEquals("./plugin_wolf/img/face{0,number,#00}.jpg",
                     selectLand("wolf").getFaceURITemplate());
        assertEquals("../img/face{0,number,#00}.jpg",
                     selectLand("wolf0").getFaceURITemplate());
        assertEquals("./plugin_wolf/img/face{0,number,#00}.jpg",
                     selectLand("wolff").getFaceURITemplate());
        assertEquals("./plugin_wolf/img/face{0,number,#00}.jpg",
                     selectLand("wolfg").getFaceURITemplate());

        return;
    }

    /**
     * Test of getBodyURITemplate method, of class LandDef.
     */
    @Test
    public void testGetBodyURITemplate(){
        System.out.println("getBodyURITemplate");

        assertEquals("./plugin_wolf/img/body{0,number,#00}.jpg",
                     selectLand("wolf").getBodyURITemplate());
        assertEquals("../img/body{0,number,#00}.jpg",
                     selectLand("wolf0").getBodyURITemplate());
        assertEquals("./plugin_wolf/img/body{0,number,#00}.jpg",
                     selectLand("wolff").getBodyURITemplate());
        assertEquals("./plugin_wolf/img/body{0,number,#00}.jpg",
                     selectLand("wolfg").getBodyURITemplate());

        return;
    }

    /**
     * Test of getLocale method, of class LandDef.
     */
    @Test
    public void testGetLocale(){
        System.out.println("getLocale");

        assertEquals(Locale.JAPAN, selectLand("wolf").getLocale());
        assertEquals(Locale.JAPAN, selectLand("wolff").getLocale());
        assertEquals(Locale.JAPAN, selectLand("wolfg").getLocale());

        return;
    }

    /**
     * Test of getEncoding method, of class LandDef.
     */
    @Test
    public void testGetEncoding(){
        System.out.println("getEncoding");

        Charset sj = Charset.forName("Shift_JIS");

        assertEquals(sj, selectLand("wolf").getEncoding());
        assertEquals(sj, selectLand("wolff").getEncoding());

        Charset utf8 = Charset.forName("UTF-8");

        assertEquals(utf8, selectLand("wolfg").getEncoding());

        return;
    }

    /**
     * Test of getTimeZone method, of class LandDef.
     */
    @Test
    public void testGetTimeZone(){
        System.out.println("getTimeZone");

        TimeZone tz = TimeZone.getTimeZone("GMT+09");

        assertTrue(tz.hasSameRules(selectLand("wolf0").getTimeZone()));
        assertTrue(tz.hasSameRules(selectLand("wolff").getTimeZone()));
        assertTrue(tz.hasSameRules(selectLand("wolfg").getTimeZone()));

        return;
    }

    /**
     * Test of getStartDateTime method, of class LandDef.
     */
    @Test
    public void testGetStartDateTime(){
        System.out.println("getStartDateTime");

        TimeZone tz = TimeZone.getTimeZone("GMT+09");

        GregorianCalendar cal = new GregorianCalendar();
        cal.clear();
        cal.setTimeZone(tz);

        cal.set(2004, 8-1, 15, 23, 31, 36);
        assertEquals(cal.getTimeInMillis(),
                     selectLand("wolf").getStartDateTime());

        cal.set(2005, 9-1, 12, 20, 21, 3);
        assertEquals(cal.getTimeInMillis(),
                     selectLand("wolff").getStartDateTime());

        cal.set(2010, 3-1,  7, 13, 53, 27);
        assertEquals(cal.getTimeInMillis(),
                     selectLand("wolfg").getStartDateTime());

        return;
    }

    /**
     * Test of getEndDateTime method, of class LandDef.
     */
    @Test
    public void testGetEndDateTime(){
        System.out.println("getEndDateTime");

        TimeZone tz = TimeZone.getTimeZone("GMT+09");

        GregorianCalendar cal = new GregorianCalendar();
        cal.clear();
        cal.setTimeZone(tz);

        cal.set(2004, 9-1, 30, 13, 58, 31);
        assertEquals(cal.getTimeInMillis(),
                     selectLand("wolf").getEndDateTime());

        cal.set(2020, 2-1, 10, 1, 45, 1);
        assertEquals(cal.getTimeInMillis(),
                     selectLand("wolfg").getEndDateTime());

        return;
    }

    /**
     * Test of getDescription method, of class LandDef.
     */
    @Test
    public void testGetDescription(){
        System.out.println("getDescription");

        assertNotNull(selectLand("wolf0").getDescription());
        assertNotNull(selectLand("wolff").getDescription());
        assertNotNull(selectLand("wolfg").getDescription());

        return;
    }

    /**
     * Test of getContactInfo method, of class LandDef.
     */
    @Test
    public void testGetContactInfo(){
        System.out.println("getContactInfo");

        assertNotNull(selectLand("wolf0").getContactInfo());
        assertNotNull(selectLand("wolff").getContactInfo());
        assertNotNull(selectLand("wolfg").getContactInfo());

        return;
    }

    /**
     * Test of isValidVillageId method, of class LandDef.
     */
    @Test
    public void testIsValidVillageId(){
        System.out.println("isValidVillageId");

        assertTrue(selectLand("wolfa").isValidVillageId(339));
        assertFalse(selectLand("wolfa").isValidVillageId(340));
        assertTrue(selectLand("wolfa").isValidVillageId(343));
        assertTrue(selectLand("wolfa").isValidVillageId(366));
        assertFalse(selectLand("wolfa").isValidVillageId(367));

        assertFalse(selectLand("wolfb").isValidVillageId(149));

        assertFalse(selectLand("wolfc").isValidVillageId(451));

        return;
    }

    /**
     * Test of buildLocale method, of class LandDef.
     */
    @Test
    public void testBuildLocale(){
        System.out.println("buildLocale");
        assertEquals(Locale.JAPAN, LandDef.buildLocale("ja-JP"));
        assertEquals(Locale.JAPANESE, LandDef.buildLocale("ja"));
        return;
    }

}
