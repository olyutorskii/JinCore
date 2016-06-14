/*
 * Roles in game
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 役職。
 */
public enum GameRole{

    /** 村人。 */
    INNOCENT("村人", '村', "innocent",         Team.VILLAGE, -50),
    /** 人狼。 */
    WOLF("人狼", '狼', "wolf",                 Team.WOLF,    +20),
    /** 占い師。 */
    SEER("占い師", '占', "seer",               Team.VILLAGE, -30),
    /** 霊能者。 */
    SHAMAN("霊能者", '霊', "shaman",           Team.VILLAGE, -20),
    /** 狂人。 */
    MADMAN("狂人", '狂', "madman",             Team.WOLF,    +10),
    /** 狩人。 */
    HUNTER("狩人", '狩', "hunter",             Team.VILLAGE, -10),
    /** 共有者。 */
    FRATER("共有者", '共', "frater",           Team.VILLAGE, -40),
    /** ハムスター人間。 */
    HAMSTER("ハムスター人間", '公', "hamster", Team.HAMSTER,   0),
    ;

    private static final Comparator<GameRole> BALANCE_COMPARATOR =
            new PowerBalanceComparator();

    private static final Pattern ROLE_PATTERN;

    static{
        StringBuilder roleRegex = new StringBuilder();
        for(GameRole role : values()){
            if(roleRegex.length() > 0){
                roleRegex.append('|');
            }
            roleRegex.append('(')
                     .append(Pattern.quote(role.getRoleName()))
                     .append(')');
        }
        ROLE_PATTERN = Pattern.compile(roleRegex.toString());
    }


    private final String roleName;
    private final char shortName;
    private final String xmlName;
    private final Team team;
    private final int order;


    /**
     * コンストラクタ。
     * @param roleName 役職名
     * @param shortName 短縮名
     * @param xmlName XML用シンボル
     * @param team 陣営
     * @param order 順位
     */
    private GameRole(String roleName,
                      char shortName,
                      String xmlName,
                      Team team,
                      int order ){
        this.roleName = roleName.intern();
        this.shortName = shortName;
        this.xmlName = xmlName.intern();
        this.team = team;
        this.order = order;
        return;
    }


    /**
     * 与えられたマッチャ先頭が定義済みRole名に一致しないか調べる。
     * @param matcher マッチャ
     * @return 一致した役職。どれにも一致しなければnull。
     */
    public static GameRole lookingAtRole(Matcher matcher){
        matcher.usePattern(ROLE_PATTERN);

        if( ! matcher.lookingAt() ) return null;
        int groupCt = matcher.groupCount();
        for(int group = 1; group <= groupCt; group++){
            if(matcher.start(group) >= 0){
                GameRole role = values()[group - 1];
                return role;
            }
        }

        return null;
    }

    /**
     * 勢力バランス表記用Comparatorを得る。
     * 「村共占霊狩公狂狼」の順で役職を一意に順序づける。
     * @return Comparator
     */
    public static Comparator<GameRole> getPowerBalanceComparator(){
        return BALANCE_COMPARATOR;
    }


    /**
     * 役職名を返す。
     * @return 役職名
     */
    public String getRoleName(){
        return this.roleName;
    }

    /**
     * 一文字に短縮された役職名を得る。
     * @return 短縮された役職名
     */
    public char getShortName(){
        return this.shortName;
    }

    /**
     * XML用シンボルを取得する。
     * @return XML用シンボル
     */
    public String getXmlName(){
        return this.xmlName;
    }

    /**
     * 各役職のチームを得る。
     * @return チーム
     */
    public Team getTeam(){
        return this.team;
    }

    /**
     * 順位を返す。
     * @return 順位
     */
    private int getOrder(){
        return this.order;
    }


    /**
     * 勢力バランス表記用Comparator。
     * 「村共占霊狩公狂狼」の順で役職を一意に順序づける。
     */
    @SuppressWarnings("serial")
    private static final class PowerBalanceComparator
            implements Comparator<GameRole> {

        /**
         * コンストラクタ。
         */
        private PowerBalanceComparator(){
            super();
            return;
        }

        /**
         * 役職に順序を割り当てる。
         * 村人陣営のほうが狼陣営より小さい値を返す。
         * @param role 役職
         * @return 順位
         */
        private static int getPowerValue(GameRole role){
            if(role == null) return Integer.MIN_VALUE;
            int result = role.getOrder();
            return result;
        }

        /**
         * {@inheritDoc}
         * @param role1 {@inheritDoc}
         * @param role2 {@inheritDoc}
         * @return {@inheritDoc}
         */
        @Override
        public int compare(GameRole role1, GameRole role2){
            int order1 = getPowerValue(role1);
            int order2 = getPowerValue(role2);
            return order1 - order2;
        }

    }

}
