/*
 * License : The MIT License
 * Copyright(c) 2011 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 */
public class DateUtilsTest {

    public DateUtilsTest() {
    }

    /**
     * Test of parseISO8601 method, of class LandDef.
     */
    @Test
    public void testParseISO8601(){
        System.out.println("parseISO8601");

        CharSequence seq;
        long result;

        GregorianCalendar cal;
        cal = new GregorianCalendar();
        cal.clear();

        TimeZone tz;
        tz = TimeZone.getTimeZone("GMT+00");
        cal.setTimeZone(tz);

        seq = "1990-01-01T00:00:00Z";
        result = DateUtils.parseISO8601(seq);
        cal.set(1990, 1-1, 1, 0, 0, 0);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "1991-02-03T04:05:06Z";
        result = DateUtils.parseISO8601(seq);
        cal.set(1991, 2-1, 3, 4, 5, 6);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "2099-12-31T23:59:59Z";
        result = DateUtils.parseISO8601(seq);
        cal.set(2099, 12-1, 31, 23, 59, 59);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "1991-02-03T04:05:06+23";
        result = DateUtils.parseISO8601(seq);
        tz = TimeZone.getTimeZone("GMT+23");
        cal.setTimeZone(tz);
        cal.set(1991, 2-1, 3, 4, 5, 6);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "1991-02-03T04:05:06-23";
        result = DateUtils.parseISO8601(seq);
        tz = TimeZone.getTimeZone("GMT-23");
        cal.setTimeZone(tz);
        cal.set(1991, 2-1, 3, 4, 5, 6);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "1991-02-03T04:05:06+2345";
        result = DateUtils.parseISO8601(seq);
        tz = TimeZone.getTimeZone("GMT+2345");
        cal.setTimeZone(tz);
        cal.set(1991, 2-1, 3, 4, 5, 6);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "1991-02-03T04:05:06+23:45";
        result = DateUtils.parseISO8601(seq);
        tz = TimeZone.getTimeZone("GMT+2345");
        cal.setTimeZone(tz);
        cal.set(1991, 2-1, 3, 4, 5, 6);
        assertEquals(cal.getTimeInMillis(), result);

        seq = "!!!";
        try{
            DateUtils.parseISO8601(seq);
            fail();
        }catch(IllegalArgumentException e){
            // GOOD
        }

        return;
    }

}
