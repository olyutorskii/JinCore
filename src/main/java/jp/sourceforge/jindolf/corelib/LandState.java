/*
 * status of land
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * 国の状態。
 */
public enum LandState{

    /** アクセス不可。 */
    CLOSED("closed"),
    /** 過去ログの閲覧のみが可能。 */
    HISTORICAL("historical"),
    /** ゲームへの参加が可能。 */
    ACTIVE("active"),
    ;

    private final String xmlName;

    /**
     * コンストラクタ。
     * @param xmlName XML用シンボル
     */
    private LandState(String xmlName){
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
