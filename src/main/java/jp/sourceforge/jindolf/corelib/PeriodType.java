/*
 * type of period
 *
 * Copyright(c) 2009 olyutorskii
 * $Id: PeriodType.java 889 2009-11-04 06:44:30Z olyutorskii $
 */

package jp.sourceforge.jindolf.corelib;

/**
 * Period(個別の日々)の種別。
 */
public enum PeriodType{

    /** プロローグ。 */
    PROLOGUE("prologue"),
    /** 通常のゲーム進行。 */
    PROGRESS("progress"),
    /** エピローグ。 */
    EPILOGUE("epilogue"),
    ;

    private final String xmlName;

    /**
     * コンストラクタ。
     * @param xmlName XML用シンボル
     */
    private PeriodType(String xmlName){
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
