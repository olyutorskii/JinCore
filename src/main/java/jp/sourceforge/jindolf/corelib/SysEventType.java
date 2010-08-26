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
    STARTENTRY,
    /** キャラ初登場。 */
    ONSTAGE,
    /** 役職確定。 */
    STARTMIRROR,
    /** 役職人数内訳発表。 */
    OPENROLE,
    /** 襲撃成功。 */
    MURDERED,
    /** 襲撃開始。 */
    STARTASSAULT,
    /** 生存者確認。 */
    SURVIVOR,
    /** 投票結果。 */
    COUNTING,
    /** 突然死。 */
    SUDDENDEATH,
    /** 襲撃失敗。 */
    NOMURDER,
    /** 村側勝利。 */
    WINVILLAGE,
    /** 狼側勝利。 */
    WINWOLF,
    /** ハムスター勝利。 */
    WINHAMSTER,
    /** 役職&ログイン名公開。 */
    PLAYERLIST,
    /** 何らかのシステムトラブル。A国末期で頻発。 */
    PANIC,
    /** 参加者募集。 */
    ASKENTRY,
    /** 行動確定要求。 */
    ASKCOMMIT,
    /** 未発言者一覧。 */
    NOCOMMENT,
    /** エピローグ案内。 */
    STAYEPILOGUE,
    /** 村終了。 */
    GAMEOVER,
    /** 占い先表示。 */
    JUDGE,
    /** 護衛先表示。 */
    GUARD,
    /** 襲撃。 */
    ASSAULT,
    /** 処刑結果表示。 */
    EXECUTION,
    /** 投票結果表示(G国版)。 */
    COUNTING2,
    /** 失踪。 */
    VANISH,
    /** 未定義。 */
    UNKNOWN,
    ;

    /**
     * イベントファミリを得る。
     * ASSAULTとUNKNOWNに関してはnullを返す。
     * @return イベントファミリ
     */
    public EventFamily getEventFamily(){
        switch(this){
        case STARTENTRY:
        case ONSTAGE:
        case STARTMIRROR:
        case OPENROLE:
        case MURDERED:
        case STARTASSAULT:
        case SURVIVOR:
        case COUNTING:
        case SUDDENDEATH:
        case NOMURDER:
        case WINVILLAGE:
        case WINWOLF:
        case WINHAMSTER:
        case PLAYERLIST:
        case PANIC:
        case EXECUTION:
        case VANISH:
            return EventFamily.ANNOUNCE;
        case ASKENTRY:
        case ASKCOMMIT:
        case NOCOMMENT:
        case STAYEPILOGUE:
        case GAMEOVER:
            return EventFamily.ORDER;
        case JUDGE:
        case GUARD:
        case COUNTING2:
            return EventFamily.EXTRA;
        case ASSAULT:
        case UNKNOWN:
            return null;
        default:
            assert false;
            break;
        }

        return null;
    }

}
