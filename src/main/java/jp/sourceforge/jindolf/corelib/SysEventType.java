/*
 * type of system-event-message
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

/**
 * イベント種別。
 */
public enum SysEventType{

    /** エントリースタート。 */
    STARTENTRY(EventFamily.ANNOUNCE),
    /** キャラ初登場。 */
    ONSTAGE(EventFamily.ANNOUNCE),
    /** 役職確定。 */
    STARTMIRROR(EventFamily.ANNOUNCE),
    /** 役職人数内訳発表。 */
    OPENROLE(EventFamily.ANNOUNCE),
    /** 襲撃成功。 */
    MURDERED(EventFamily.ANNOUNCE),
    /** 襲撃開始。 */
    STARTASSAULT(EventFamily.ANNOUNCE),
    /** 生存者確認。 */
    SURVIVOR(EventFamily.ANNOUNCE),
    /** 投票結果。 */
    COUNTING(EventFamily.ANNOUNCE),
    /** 突然死。 */
    SUDDENDEATH(EventFamily.ANNOUNCE),
    /** 襲撃失敗。 */
    NOMURDER(EventFamily.ANNOUNCE),
    /** 村側勝利。 */
    WINVILLAGE(EventFamily.ANNOUNCE),
    /** 狼側勝利。 */
    WINWOLF(EventFamily.ANNOUNCE),
    /** ハムスター勝利。 */
    WINHAMSTER(EventFamily.ANNOUNCE),
    /** 役職&ログイン名公開。 */
    PLAYERLIST(EventFamily.ANNOUNCE),
    /** 何らかのシステムトラブル。A国末期で頻発。 */
    PANIC(EventFamily.ANNOUNCE),
    /** 参加者募集。 */
    ASKENTRY(EventFamily.ORDER),
    /** 行動確定要求。 */
    ASKCOMMIT(EventFamily.ORDER),
    /** 未発言者一覧。 */
    NOCOMMENT(EventFamily.ORDER),
    /** エピローグ案内。 */
    STAYEPILOGUE(EventFamily.ORDER),
    /** 村終了。 */
    GAMEOVER(EventFamily.ORDER),
    /** 占い先表示。 */
    JUDGE(EventFamily.EXTRA),
    /** 護衛先表示。 */
    GUARD(EventFamily.EXTRA),
    /** 襲撃。 */
    ASSAULT(null),
    /** 処刑結果表示。 */
    EXECUTION(EventFamily.ANNOUNCE),
    /** 投票結果表示(G国版)。 */
    COUNTING2(EventFamily.EXTRA),
    /** 失踪。 */
    VANISH(EventFamily.ANNOUNCE),
    /** チェックアウト。 */
    CHECKOUT(EventFamily.ANNOUNCE),
    /** 定員不足。 */
    SHORTMEMBER(EventFamily.ANNOUNCE),
    /** 未定義。 */
    UNKNOWN(null),
    ;


    private final EventFamily family;


    /**
     * コンストラクタ。
     * @param family イベントファミリ
     */
    SysEventType(EventFamily family){
        this.family = family;
        return;
    }


    /**
     * イベントファミリを得る。
     * ASSAULTとUNKNOWNに関してはnullを返す。
     * @return イベントファミリ
     */
    public EventFamily getEventFamily(){
        return this.family;
    }

}
