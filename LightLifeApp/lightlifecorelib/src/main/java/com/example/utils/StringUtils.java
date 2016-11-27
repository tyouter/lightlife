package com.example.utils;

/**
 * For String operations
 * Created by Administrator on 2016/11/27.
 */
public class StringUtils {

    /**
     * long value to String
     *
     * @param v value
     * @return String value
     */
    public static String long2String(long v) {
        try {
            return String.valueOf(v);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * double value to String
     *
     * @param v double value
     * @return String value
     */
    public static String double2String(Double v) {
        try {
            return String.valueOf(v);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * String to long value
     *
     * @param s String value
     * @return long value
     */
    public static long string2Long(String s) {
        try {
            return Long.valueOf(s);
        } catch (Exception e) {
            return 0;
        }
    }

    /** String to double value
     * @param s string value
     * @return double value
     */
    public static double string2Double(String s) {
        try {
            String trim = s.trim();
            return Double.valueOf(trim);
        } catch (Exception e) {
            return 0;
        }
    }
}
