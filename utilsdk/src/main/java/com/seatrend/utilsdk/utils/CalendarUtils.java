package com.seatrend.utilsdk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ly on 2021/1/26 16:10
 */
public class CalendarUtils {

    /**
     * System.out.println(Ts.dateToWeek("2019-01-01"));
     * @param datetime
     * @return
     * @throws java.text.ParseException
     */
    public static String dateToWeek(String datetime) throws java.text.ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        datet = (Date) f.parse(datetime);
        cal.setTime(datet);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        System.out.println(weekDays[w]);//星期二
        return weekDays[w];
    }
}
