package com.ohoyee.weight.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String format(Date date,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return  sdf.format(new Date());
    }

    public static Date parse(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return  sdf.parse(str);
    }

    public static String addMonth(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        return DateUtils.format(calendar.getTime(),"yyyyMM");
    }

}
