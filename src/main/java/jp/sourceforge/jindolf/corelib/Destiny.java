/*
 * Destiny
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * プレイヤーの死因・運命。
 */
public enum Destiny{

    /** 生存。 */
    ALIVE("生存"),
    /** 突然死。 */
    SUDDENDEATH("突然死"),
    /** 処刑死。 */
    EXECUTED("処刑死"),
    /** 襲撃死。 */
    EATEN("襲撃死"),
    /** ハム溶け。 */
    DISSOLVE("ハム溶け"),
    ;

    private final String message;

    /**
     * コンストラクタ。
     * @param message 死因メッセージ
     */
    private Destiny(String message){
        this.message = message.intern();
        return;
    }

    /**
     * 死因メッセージを取得する。
     * @return 死因文字列
     */
    public String getMessage(){
        return this.message;
    }

}
