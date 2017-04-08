/*
 * family of system-event-message
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * システムイベントメッセージのおおまかな種別。
 */
public enum EventFamily{

    /** &lt;div class="announce"&gt;メッセージに対応。 */
    ANNOUNCE("announce"),
    /** &lt;div class="order"&gt;メッセージに対応。 */
    ORDER("order"),
    /** &lt;div class="extra"&gt;メッセージに対応。 */
    EXTRA("extra"),
    ;

    private final String divClass;

    /**
     * コンストラクタ。
     * @param divClass divタグclass名
     */
    EventFamily(String divClass){
        this.divClass = divClass;
        return;
    }

    /**
     * 由来となった&lt;div&gt;要素のclass属性値を得る。
     * @return class属性値
     */
    public String getDivClass(){
        return this.divClass;
    }

    /**
     * XML用シンボルを取得する。
     * @return XML用シンボル
     */
    public String getXmlName(){
        return this.divClass;
    }

}
