/*
 * village tag-name
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 村名一覧。
 */
public enum VillageTag{

    /** 平凡な村。 */
    HEIBON        ("平凡な村"),
    /** 星狩りの村。 */
    HOSHIKARI     ("星狩りの村"),
    /** 日の差さぬ村。 */
    HINOSASANU    ("日の差さぬ村"),
    /** 隠された村。 */
    KAKUSARETA    ("隠された村"),
    /** 日の沈まぬ村。 */
    HINOSHIZUMANU ("日の沈まぬ村"),
    /** 消え行く村。 */
    KIEYUKU       ("消え行く村"),
    /** 怪しげな村。 */
    AYASHIGE      ("怪しげな村"),
    /** 山影の村。 */
    YAMAKAGE      ("山影の村"),
    /** 平和な村。 */
    HEIWA         ("平和な村"),
    /** 嘘つきの村。 */
    USOTSUKI      ("嘘つきの村"),
    /** 悲しみの村。 */
    KANASHIMI     ("悲しみの村"),
    /** 恐ろしい村。 */
    OSOROSHII     ("恐ろしい村"),
    /** 盆地の村。 */
    BONCHI        ("盆地の村"),
    /** 幻の村。 */
    MABOROSHI     ("幻の村"),
    /** 謀略の村。 */
    BOURYAKU      ("謀略の村"),
    /** 最果ての村。 */
    SAIHATE       ("最果ての村"),
    /** 封印の村。 */
    HUUIN         ("封印の村"),
    /** 海辺の村。 */
    UMIBE         ("海辺の村"),
    /** 開拓者の村。 */
    KAITAKUSHA    ("開拓者の村"),
    /** 断崖の村。 */
    DANGAI        ("断崖の村"),
    /** 出会いの村。 */
    DEAI          ("出会いの村"),
    /** ふもとの村。 */
    HUMOTO        ("ふもとの村"),
    /** 峠の村。 */
    TOUGE         ("峠の村"),
    /** 血塗られた村。 */
    CHINURARETA   ("血塗られた村"),
    /** 僻地の村。 */
    HEKICHI       ("僻地の村"),
    /** 安らぎの村。 */
    YASURAGI      ("安らぎの村"),
    /** 最後の村。 */
    SAIGO         ("最後の村"),
    /** 疑心暗鬼の村。 */
    GISHINANKI    ("疑心暗鬼の村"),
    /** 呪われた村。 */
    NOROWARETA    ("呪われた村"),
    /** 新緑の村。 */
    SHINRYOKU     ("新緑の村"),
    /** 荒地の村。 */
    ARECHI        ("荒地の村"),
    /** 残された村。 */
    NOKOSARETA    ("残された村"),
    /** 小さな村。 */
    CHIISANA      ("小さな村"),
    /** 極寒の村。 */
    GOKUKAN       ("極寒の村"),
    /** 見捨てられた村。 */
    MISUTERARETA  ("見捨てられた村"),
    /** 希望の村。 */
    KIBOU         ("希望の村"),
    /** 裏切りの村。 */
    URAGIRI       ("裏切りの村"),
    /** 沈黙の村。 */
    CHINMOKU      ("沈黙の村"),
    /** 雨の止まぬ村。 */
    AMENOYAMANU   ("雨の止まぬ村"),
    /** 辺境の村。 */
    HENKYOU       ("辺境の村"),
    /** 忘れられた村。 */
    WASURERARETA  ("忘れられた村"),
    /** 谷底の村。 */
    TANIZOKO      ("谷底の村"),
    ;

    private static final Map<String, VillageTag> TAGMAP = new HashMap<>();
    private static final VillageTag[] VALUES = values();
    private static final int VALUE_NUM = values().length;
    private static final Pattern VTAG_PATTERN;

    static{
        for(VillageTag vtag : VALUES){
            String tagName = vtag.getTagName();
            TAGMAP.put(tagName, vtag);
        }

        StringBuilder vtagRegex = new StringBuilder();
        for(VillageTag tag : values()){
            if(vtagRegex.length() > 0){
                vtagRegex.append('|');
            }
            vtagRegex.append('(')
                     .append(Pattern.quote(tag.getTagName()))
                     .append(')');
        }
        VTAG_PATTERN = Pattern.compile(vtagRegex.toString());
    }


    private final String tagName;


    /**
     * 隠しコンストラクタ。
     * @param tagName 村名
     */
    VillageTag(String tagName){
        this.tagName = tagName.intern();
        return;
    }


    /**
     * 村名からEnumメンバを得る。
     * @param tagName 村名
     * @return Enumメンバ
     */
    public static VillageTag tagNameToVillageTag(String tagName){
        VillageTag result = TAGMAP.get(tagName);
        return result;
    }

    /**
     * 与えられたマッチャ先頭がいずれかの村名に一致しないか調べる。
     * @param matcher マッチャ
     * @return 一致した村名。どれにも一致しなければnull。
     */
    public static VillageTag lookingAtVillageTag(Matcher matcher){
        matcher.usePattern(VTAG_PATTERN);

        if( ! matcher.lookingAt() ) return null;
        int groupCt = matcher.groupCount();
        for(int group = 1; group <= groupCt; group++){
            if(matcher.start(group) >= 0){
                VillageTag tag = values()[group - 1];
                return tag;
            }
        }

        return null;
    }


    /**
     * 村名を得る。
     * @return 村名
     */
    public String getTagName(){
        return this.tagName;
    }

    /**
     * 次のEnumメンバを得る。
     * 先頭と末尾は循環する。
     * @return 次のEnumメンバ
     */
    public VillageTag getNextVillageTag(){
        int ordinal = ordinal();
        ordinal = (ordinal+1) % VALUE_NUM;
        VillageTag result = VALUES[ordinal];
        return result;
    }

    /**
     * 前のEnumメンバを得る。
     * 先頭と末尾は循環する。
     * @return 前のEnumメンバ
     */
    public VillageTag getPrevVillageTag(){
        int ordinal = ordinal();
        ordinal--;
        if(ordinal < 0) ordinal += VALUE_NUM;
        VillageTag result = VALUES[ordinal];
        return result;
    }

}
