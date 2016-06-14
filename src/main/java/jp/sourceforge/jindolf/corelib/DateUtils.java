/*
 * Date Utilities
 * (ISO8601)
 *
 * License : The MIT License
 * Copyright(c) 2011 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日付ユーティリティ。
 */
final class DateUtils{

    private static final Pattern ISO8601_PATTERN;
    private static final String REG_PLUS   = "\\+";
    private static final String REG_HYPHEN = "\\-";

    static{
        StringBuilder txt = new StringBuilder();

        String gYear   = "([0-9][0-9][0-9][0-9])";
        String gMonth  = "([0-1][0-9])";
        String gDay    = "([0-3][0-9])";

        txt.append(gYear).append(REG_HYPHEN);
        txt.append(gMonth).append(REG_HYPHEN);
        txt.append(gDay);

        txt.append('T');

        String gHour   = "([0-2][0-9])";
        String gMinute = "([0-5][0-9])";
        String gSecond = "([0-6][0-9])";

        txt.append(gHour).append(':');
        txt.append(gMinute).append(':');
        txt.append(gSecond);

        String diffHour = "[" + REG_PLUS + REG_HYPHEN + "][0-2][0-9]";
        String diffMin  = "(?:" + ":?[0-5][0-9]" + ")?";
        String gTimezone = "(" + diffHour + diffMin + "|Z)";

        txt.append(gTimezone);

        String iso8601Regex = txt.toString();

        ISO8601_PATTERN = Pattern.compile(iso8601Regex);
    }


    /**
     * 隠しコンストラクタ。
     */
    private DateUtils(){
        assert false;
        return;
    }


    /**
     * ISO8601形式の日付をエポック秒msに変換する。
     * JRE1.6 の javax.xml.bind.DatatypeConverter 代替品
     * @param date ISO8601形式の日付文字列
     * @return エポック秒ms
     * @throws IllegalArgumentException 形式が変な場合。
     */
    public static long parseISO8601(CharSequence date)
            throws IllegalArgumentException {
        Matcher matcher = ISO8601_PATTERN.matcher(date);
        if( ! matcher.matches() ){
            throw new IllegalArgumentException(date.toString());
        }

        int gid = 1;
        String yearStr   = matcher.group(gid++);
        String monthStr  = matcher.group(gid++);
        String dayStr    = matcher.group(gid++);
        String hourStr   = matcher.group(gid++);
        String minuteStr = matcher.group(gid++);
        String secondStr = matcher.group(gid++);
        String tzString  = matcher.group(gid++);

        int year   = Integer.parseInt(yearStr);
        int month  = Integer.parseInt(monthStr);
        int day    = Integer.parseInt(dayStr);
        int hour   = Integer.parseInt(hourStr);
        int minute = Integer.parseInt(minuteStr);
        int second = Integer.parseInt(secondStr);

        String tzID;
        if( tzString.compareToIgnoreCase("Z") == 0 ) tzID = "GMT+00:00";
        else                                         tzID = "GMT" + tzString;
        TimeZone timezone = TimeZone.getTimeZone(tzID);

        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.setTimeZone(timezone);
        calendar.set(year, month - 1, day, hour, minute, second);

        long result = calendar.getTimeInMillis();

        return result;
    }

}
