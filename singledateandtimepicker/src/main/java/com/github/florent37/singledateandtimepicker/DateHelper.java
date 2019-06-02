package com.github.florent37.singledateandtimepicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateHelper {
    public static Calendar getCalendarOfDate(Date date){
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public static int getHour(Date date){
        return getCalendarOfDate(date).get(Calendar.HOUR);
    }

    public static int getHourOfDay(Date date){
        return getCalendarOfDate(date).get(Calendar.HOUR);
    }

    public static int getHour(Date date, boolean isAmPm){
        if(isAmPm){
            return getHourOfDay(date);
        } else {
            return getHour(date);
        }
    }

    public static int getMinuteOf(Date date) {
        return getCalendarOfDate(date).get(Calendar.MINUTE);
    }

    public static Date today() {
        return Calendar.getInstance(Locale.getDefault()).getTime();
    }

    public static int getMonth(Date date) {
        return getCalendarOfDate(date).get(Calendar.MONTH);
    }

    public static int getDay(Date date){
        return getCalendarOfDate(date).get(Calendar.DAY_OF_MONTH);
    }

    public static String replaceToArabicMonthNames(String text) {
        String s = text
                .replaceAll("أيار", "مايو")
                .replaceAll("حزيران", "يونيو")
                .replaceAll("تموز", "يوليو")
                .replaceAll("آب", "أغسطس")
                .replaceAll("أيلول", "سبتمبر")
                .replaceAll("تشرين الأول", "أكتوبر")
                .replaceAll("تشرين الثاني", "نوفمبر")
                .replaceAll("كانون الأول", "ديسمبر")
                .replaceAll("كانون الثاني", "يناير")
                .replaceAll("شباط", "فبراير")
                .replaceAll("آذار", "مارس")
                .replaceAll("نيسان", "أبريل");
        return s;
    }

    public static String replaceToAssyrianMonthNames(String text) {
        return replaceToAssyrianMonthNames(Collections.singletonList(text)).get(0);
    }

    public static List<String> replaceToAssyrianMonthNames(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String text : list) {
            String s = text
                    .replaceAll("مايو", "أيار")
                    .replaceAll("يونيو", "حزيران")
                    .replaceAll("يوليو", "تموز")
                    .replaceAll("أغسطس", "آب")
                    .replaceAll("سبتمبر", "أيلول")
                    .replaceAll("أكتوبر", "تشرين الأول")
                    .replaceAll("نوفمبر", "تشرين الثاني")
                    .replaceAll("ديسمبر", "كانون الأول")
                    .replaceAll("يناير", "كانون الثاني")
                    .replaceAll("فبراير", "شباط")
                    .replaceAll("مارس", "آذار")
                    .replaceAll("أبريل", "نيسان");

            result.add(s);
        }
        return result;
    }

    public static List<String> convetFromHindiToArabicNumbers(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String text : list) {
            String s = convertHindiNumbersToArabic(text);
            result.add(s);
        }
        return result;
    }

        /**
         * Convert arabic numbers in [text] to English numbers for example:
         * `لنسير ١٣ متر`
         * will be converted to:
         * `لنسير 13 متر`
         */
    public static String convertHindiNumbersToArabic(String text) {
        char[] chars = new char[text.length()];
        boolean previousCharacterMatched = false;
        for (int i = 0; i<text.length(); i++) {
            char ch = text.charAt(i);
            int chInt = ch;

            if (ch == '٫' && previousCharacterMatched) { // Replace arabic comma with English one
                ch = '.';
                previousCharacterMatched = false;
            } else if (chInt >= 0x0660 && chInt <= 0x0669) {
                char value = (0x0660 - '0');
                ch = (char) (ch - value);
                previousCharacterMatched = true;
            } else if (chInt >= 0x06f0 && chInt <= 0x06F9) {
                char value = (0x06f0 - '0');
                ch = (char) (ch - value);
                previousCharacterMatched = true;
            } else {
                previousCharacterMatched = false;
            }
            chars[i] = ch;
        }

        return getStringRepresentation(chars);
    }

    private static String getStringRepresentation(char[] list)
    {
        StringBuilder builder = new StringBuilder(list.length);
        for(char ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
}
