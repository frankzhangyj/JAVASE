package com.microsoft.baseAPI;

import sun.util.resources.LocaleData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DataDemo {
    public static void main(String[] args) {
        // Date类已经过时Deprecated 其是许多衍生类的基础
        Date date = new Date();
        // 默认输出系统当前时间
        System.out.println("date = " + date);

        // 获取当前时间戳 效率最高
        long cur = System.currentTimeMillis();
        // Date类的默认构造器调用了System.currentTimeMills()方法获取当前时间戳 所以效率比直接调用System低
        long cur1 = date.getTime();
        // Calendar类获取时间戳 分时区 效率低
        long cur2 = Calendar.getInstance().getTimeInMillis();

        System.out.println("a a a = " + cur);
        System.out.println(new Date());

        // DateFormat和SimpleDateFormat是Date的衍生类 用于对时间格式化 SimpleDateFormat是DateFormat的子类
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd DD E HH:mm:ss:SS");
        // y year M month D Day(一年中的天数) d day(一月中的天数) E week h hour(24h) H Hour(am/pm 12h) m minute s second S milliSecond
        // 利用上面规定的格式输出当前时间
        System.out.println(dateFormat.format(date));


        // Calendar中的方法都是static 可以当工具直接调用
        Calendar calendar = Calendar.getInstance();
        int  year = calendar.get(Calendar.YEAR);
        // 月份从0开始 所以+1
        int month = calendar.get(calendar.MONTH) + 1;
        System.out.println("month = " + month);
        System.out.println(year);


        // 得到两个日期之间的天数差 时间戳是1 jan 1970 开始
        Calendar date1 = Calendar.getInstance();
        date1.set(2023, Calendar.SEPTEMBER, 1);

        Calendar date2 = Calendar.getInstance();
        date2.set(2023, Calendar.OCTOBER, 1);

        long diffInMillis = date2.getTimeInMillis() - date1.getTimeInMillis();
        // 将时间戳的差转换为天数
        long diffInDays = diffInMillis / (24 * 60 * 60 * 1000);

        System.out.println("Days between date1 and date2: " + diffInDays);

        // 也可以设置时间
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER); // 月份也可以使用常量
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        // 获取Calendar时间的相关信息
        int dayDM = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayDM = " + dayDM);
        int dayDW = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("dayDW = " + dayDW);
        int dayWM = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println("dayWM = " + dayWM);
        int dayDY = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("dayDY = " + dayDY);
    }
}
