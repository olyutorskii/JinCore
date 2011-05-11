/*
 * type of talk
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * 発言種別。
 */
public enum TalkType{

    /** 公開発言。白発言。say属性。 */
    PUBLIC("public"),
    /** 狼発言。赤発言。whisper属性。 */
    WOLFONLY("wolf"),
    /** 独り言。灰発言。think属性。 */
    PRIVATE("private"),
    /** 墓下発言。青発言。groan属性。 */
    GRAVE("grave"),
    ;

    private final String xmlName;

    /**
     * コンストラクタ。
     * @param xmlName XML用シンボル
     */
    private TalkType(String xmlName){
        this.xmlName = xmlName.intern();
        return;
    }

    /**
     * XML用シンボルを取得する。
     * @return XML用シンボル
     */
    public String getXmlName(){
        return this.xmlName;
    }

}
