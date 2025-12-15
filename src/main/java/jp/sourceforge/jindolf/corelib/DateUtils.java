/*
 * Date Utilities
 * (ISO8601)
 *
 * License : The MIT License
 * Copyright(c) 2011 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

/*
 * ※ v1.207.10を境に、正規表現によるISO8601時刻表記文字列のパースと、
 * {@link java.util.GregorianCalendar}によるエポックミリ秒変換を改め、
 * JDK8から実装されたjava.time APIを利用することにした。
 */

/**
 * 日付ユーティリティ。
 *
 * ISO8601による時刻表記を解析する。
 */
final class DateUtils{

    /**
     * 隠しコンストラクタ。
     */
    private DateUtils(){
        assert false;
        return;
    }


    /**
     * ISO8601形式の日付をエポック秒msに変換する。
     * JRE1.6 の {@link javax.xml.bind.DatatypeConverter} 代替品
     * 
     * @param date ISO8601形式の日付文字列
     * @return エポック秒ms
     * @throws IllegalArgumentException 形式が変な場合。
     */
    public static long parseISO8601(CharSequence date)
            throws IllegalArgumentException {
        ZonedDateTime zdt;
        try {
            zdt = ZonedDateTime.parse(date);
        } catch(DateTimeParseException e) {
            throw new IllegalArgumentException(e);
        }

        Instant ins = zdt.toInstant();
        
        long result = ins.toEpochMilli();
        return result;
    }

}
