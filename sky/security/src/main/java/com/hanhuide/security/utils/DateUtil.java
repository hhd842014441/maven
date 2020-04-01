package com.hanhuide.security.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @program: maven
 * @description: 日期工具类
 * @author: 韩惠德
 * @create: 2019-11-29 14:24
 * @version: 1.0
 **/
@Slf4j
public class DateUtil {

    public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdf_date_format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前时间的YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     *
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return true;
        }
        return s.compareTo(e) > 0;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(compareDate(DateUtil.getTime(),DateUtil.getTime()+ 7 * 24 * 60 * 60 * 1000));
    }

    /**
     * 获取当前时间的后i天
     *
     * @param i
     * @return
     */
    public static String getAddDay(int i) {
        String currentTime = DateUtil.getTime();
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return sdf_date_format.format(gCal.getTime());
    }

    /**
     * 获取当前时间的后i天
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDayTime(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000);
        return sdfTime.format(date);
    }

    /**
     * 获取当前时间的+多少秒
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDaySecond(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 1000);
        return sdfTime.format(date);
    }
}
