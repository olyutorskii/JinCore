/*
 * team in game
 *
 * Copyright(c) 2009 olyutorskii
 * $Id: Team.java 889 2009-11-04 06:44:30Z olyutorskii $
 */

package jp.sourceforge.jindolf.corelib;

/**
 * 陣営種別。
 */
public enum Team{

    /** 村陣営。 */
    VILLAGE ("村陣営", "village"),
    /** 狼陣営。 */
    WOLF    ("狼陣営", "wolf"),
    /** ハムスター陣営。 */
    HAMSTER ("ハムスター人間", "hamster"),
    ;

    private final String teamName;
    private final String xmlName;

    /**
     * コンストラクタ。
     * @param teamName 陣営名
     * @param xmlName XML用シンボル
     */
    private Team(String teamName, String xmlName){
        this.teamName = teamName.intern();
        this.xmlName = xmlName.intern();
        return;
    }

    /**
     * チーム名を取得する。
     * @return チーム名
     */
    public String getTeamName(){
        return this.teamName;
    }

    /**
     * XML用シンボルを取得する。
     * @return XML用シンボル
     */
    public String getXmlName(){
        return this.xmlName;
    }

}
