package com.ahzaz.java.newsbox.utils;

/**
 * @author Ahzaz
 */
public class StringUtils {

    public static String truncate(String text, int len) {
        if (text.length() < len)
            return text;
        String s = text.substring(0, len);
        return s.substring(0, s.lastIndexOf(" ")).trim();
    }

    public static String truncateWithDots(String text, int len) {
        if (text.length() > len)
            return truncate(text, len) + "...";
        else
            return text;
    }
}
