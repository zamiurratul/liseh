package com.liseh.bll.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatDate(java.util.Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if (date == null) {
            date = new Date();
        }
        return simpleDateFormat.format(date);
    }

    public static java.util.Date parseDate(String source, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(source);
        } catch (Exception ex) {
            return null;
        }
    }
}
