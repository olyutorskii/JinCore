/*
 * state of village
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * 村の状態。
 * 滅んだ国の村は全てGAMEOVERとする。
 */
public enum VillageState{

    /** プロローグ進行中。 */
    PROLOGUE("prologue"),
    /** ゲーム進行中。 */
    PROGRESS("progress"),
    /** エピローグ進行中。 */
    EPILOGUE("epilogue"),
    /** ゲーム終了。 */
    GAMEOVER("gameover"),
    /** 状況不明だが村の存在は確認。 */
    UNKNOWN("unknown"),
    ;

    private final String xmlName;

    /**
     * コンストラクタ。
     * @param xmlName XML用シンボル
     */
    VillageState(String xmlName){
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
