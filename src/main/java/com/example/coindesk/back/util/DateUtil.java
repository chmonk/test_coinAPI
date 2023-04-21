package com.example.coindesk.back.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {

    public static String yyyyMMdd_HHmmss = "yyyy/MM/dd HH:mm:ss";

    public static DateTime dateFromISOstr(String dataStr) {
        return new DateTime(dataStr);
    }
    
    public static String dateToString(DateTime date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.print(date);
    }


}
