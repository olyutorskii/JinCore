/*
 * disclosure type
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * 各Periodの発言開示状況。
 */
public enum DisclosureType{

    /** プレイ真っ最中の最新日。 */
    HOT("hot"),
    /** 未公開発言があり得る。 */
    UNCOMPLETE("uncomplete"),
    /** 全発言は完全に公開された。 */
    COMPLETE("complete"),
    ;

    private final String xmlName;

    /**
     * コンストラクタ。
     * @param xmlName XML用シンボル
     */
    private DisclosureType(String xmlName){
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
