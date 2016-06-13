/*
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

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
public class VillageTagTest {

    public VillageTagTest() {
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
     * Test of values method, of class VillageTag.
     */
    @Test
    public void testValues(){
        System.out.println("values");
        VillageTag[] values = VillageTag.values();
        assertEquals(42, values.length);
        return;
    }

    /**
     * Test of valueOf method, of class VillageTag.
     */
    @Test
    public void testValueOf(){
        // NOTHING
        return;
    }

    /**
     * Test of tagNameToVillageTag method, of class VillageTag.
     */
    @Test
    public void testTagNameToVillageTag(){
        System.out.println("tagNameToVillageTag");

        for(VillageTag tag : VillageTag.values()){
            String tagName = tag.getTagName();
            assertEquals(tag, VillageTag.tagNameToVillageTag(tagName));
        }

        return;
    }

    /**
     * Test of getTagName method, of class VillageTag.
     */
    @Test
    public void testGetTagName(){
        System.out.println("getTagName");
        assertEquals("平凡な村", VillageTag.HEIBON.getTagName());
        assertEquals("星狩りの村", VillageTag.HOSHIKARI.getTagName());
        assertEquals("日の差さぬ村", VillageTag.HINOSASANU.getTagName());
        assertEquals("隠された村", VillageTag.KAKUSARETA.getTagName());
        assertEquals("日の沈まぬ村", VillageTag.HINOSHIZUMANU.getTagName());
        assertEquals("消え行く村", VillageTag.KIEYUKU.getTagName());
        assertEquals("怪しげな村", VillageTag.AYASHIGE.getTagName());
        assertEquals("山影の村", VillageTag.YAMAKAGE.getTagName());
        assertEquals("平和な村", VillageTag.HEIWA.getTagName());
        assertEquals("嘘つきの村", VillageTag.USOTSUKI.getTagName());
        assertEquals("悲しみの村", VillageTag.KANASHIMI.getTagName());
        assertEquals("恐ろしい村", VillageTag.OSOROSHII.getTagName());
        assertEquals("盆地の村", VillageTag.BONCHI.getTagName());
        assertEquals("幻の村", VillageTag.MABOROSHI.getTagName());
        assertEquals("謀略の村", VillageTag.BOURYAKU.getTagName());
        assertEquals("最果ての村", VillageTag.SAIHATE.getTagName());
        assertEquals("封印の村", VillageTag.HUUIN.getTagName());
        assertEquals("海辺の村", VillageTag.UMIBE.getTagName());
        assertEquals("開拓者の村", VillageTag.KAITAKUSHA.getTagName());
        assertEquals("断崖の村", VillageTag.DANGAI.getTagName());
        assertEquals("出会いの村", VillageTag.DEAI.getTagName());
        assertEquals("ふもとの村", VillageTag.HUMOTO.getTagName());
        assertEquals("峠の村", VillageTag.TOUGE.getTagName());
        assertEquals("血塗られた村", VillageTag.CHINURARETA.getTagName());
        assertEquals("僻地の村", VillageTag.HEKICHI.getTagName());
        assertEquals("安らぎの村", VillageTag.YASURAGI.getTagName());
        assertEquals("最後の村", VillageTag.SAIGO.getTagName());
        assertEquals("疑心暗鬼の村", VillageTag.GISHINANKI.getTagName());
        assertEquals("呪われた村", VillageTag.NOROWARETA.getTagName());
        assertEquals("新緑の村", VillageTag.SHINRYOKU.getTagName());
        assertEquals("荒地の村", VillageTag.ARECHI.getTagName());
        assertEquals("残された村", VillageTag.NOKOSARETA.getTagName());
        assertEquals("小さな村", VillageTag.CHIISANA.getTagName());
        assertEquals("極寒の村", VillageTag.GOKUKAN.getTagName());
        assertEquals("見捨てられた村", VillageTag.MISUTERARETA.getTagName());
        assertEquals("希望の村", VillageTag.KIBOU.getTagName());
        assertEquals("裏切りの村", VillageTag.URAGIRI.getTagName());
        assertEquals("沈黙の村", VillageTag.CHINMOKU.getTagName());
        assertEquals("雨の止まぬ村", VillageTag.AMENOYAMANU.getTagName());
        assertEquals("辺境の村", VillageTag.HENKYOU.getTagName());
        assertEquals("忘れられた村", VillageTag.WASURERARETA.getTagName());
        assertEquals("谷底の村", VillageTag.TANIZOKO.getTagName());
        return;
    }

    /**
     * Test of getNextVillageTag method, of class VillageTag.
     */
    @Test
    public void testGetNextVillageTag(){
        System.out.println("getNextVillageTag");

        VillageTag[] values = VillageTag.values();
        for(int idx=0; idx <= values.length-1-1; idx++){
            VillageTag tag = values[idx];
            VillageTag next = values[idx+1];
            assertEquals(next, tag.getNextVillageTag());
        }

        VillageTag last = values[values.length-1];
        assertEquals(values[0], last.getNextVillageTag());

        return;
    }

    /**
     * Test of getPrevVillageTag method, of class VillageTag.
     */
    @Test
    public void testGetPrevVillageTag(){
        System.out.println("getPrevVillageTag");

        VillageTag[] values = VillageTag.values();
        for(int idx=1; idx <= values.length-1; idx++){
            VillageTag tag = values[idx];
            VillageTag prev = values[idx-1];
            assertEquals(prev, tag.getPrevVillageTag());
        }

        VillageTag top = values[0];
        assertEquals(values[values.length-1], top.getPrevVillageTag());

        return;
    }

    /**
     * Test of lookingAtVillageTag method, of class VillageTag.
     */
    @Test
    public void testLookingAtVillageTag(){
        System.out.println("lookingAtVillageTag");

        Pattern pattern = Pattern.compile("");

        Matcher matcher;

        matcher = pattern.matcher("平凡な村");
        assertEquals(VillageTag.HEIBON, VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("谷底の村");
        assertEquals(VillageTag.TANIZOKO, VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("x平凡な村");
        assertNull(VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("x平凡な村x");
        assertNull(VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("平");
        assertNull(VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("平凡な村x");
        assertEquals(VillageTag.HEIBON, VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("");
        assertNull(VillageTag.lookingAtVillageTag(matcher));

        matcher = pattern.matcher("xxx");
        assertNull(VillageTag.lookingAtVillageTag(matcher));

        return;
    }

}
