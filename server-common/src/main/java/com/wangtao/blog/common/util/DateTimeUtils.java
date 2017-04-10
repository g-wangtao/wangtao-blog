package com.wangtao.blog.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author kalr
 *
 */
public final class DateTimeUtils {

    // 返回分钟数
    public static final Character DATE_M = 'M';
    // 返回小时数
    public static final Character DATE_H = 'H';
    // 返回天数
    public static final Character DATE_D = 'D';
    // 返回时分秒
    public static final String TIME_FORMAT = "HH:mm:ss";
    // 返回年月日
    public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
    // 返回年月日
    public static final String DATE_STAMP_FORMAT = "yyyy-MM-dd";
    // 中文日期格式
    public static final String DATE_CH_FORMAT = "yyyy年MM月dd日";
    // 返回年月日时分秒
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    // 返回年月日时分秒毫秒
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    // 一天开始时间
    public static final String DAYTIME_START = "00:00:00";
    // 一天结束时间
    public static final String DAYTIME_END = "23:59:59";

    // 指定格式日期处理对象
    public  static SimpleDateFormat formatYmd = new SimpleDateFormat(DATE_STAMP_FORMAT);

    // 指定格式日期处理对象
    public static SimpleDateFormat formatYmdHms = new SimpleDateFormat(DATE_TIME_FORMAT);

    // 加锁安全日期对象
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

    /**
     * @see format date
     * @author Barlow
     * @param date
     * @param formatPattern
     * @return String
     */
    public static String formatDate(Date date, String formatPattern) {
        SimpleDateFormat formatter = local.get();
        if (null == formatter){
            formatter = new SimpleDateFormat(formatPattern);
        }
        return formatter.format(date);
    }

    /**
     * @see get date from string
     * @author Barlow
     * @param dateStr
     * @return Date
     */
    public static Date getDateFromString(String dateStr,String formatPattern) {
        DateFormat formatter = local.get();
        if (null == formatter) {
            formatter = new SimpleDateFormat(formatPattern);
        }
        Date date;
        try {
            date  = formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

}
