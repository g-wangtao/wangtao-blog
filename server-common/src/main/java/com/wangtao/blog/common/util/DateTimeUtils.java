package com.wangtao.blog.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName:DateTimeUtils
 * @author: KarlWang
 * @Description: TODO(JDK1.8日期时间工具类) 
 * @date:2017年4月24日 下午2:58:19
 * @see com.wangtao.blog.common.util.DateTimeUtil
 */
public class DateTimeUtils {

    // 默认时间格式
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormat.LONG_DATE_PATTERN_LINE.formatter;

    /**
     * 构造函数私有化
     */
    private DateTimeUtils() {}

    /**
     * String 转时间
     * @param timeStr
     * @return
     */
    public static LocalDateTime parseTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * String 转时间
     * @param timeStr
     * @param format  时间格式
     * @return
     */
    public static LocalDateTime parseTime(String timeStr, DateTimeFormat format) {
        return LocalDateTime.parse(timeStr, format.formatter);
    }

    /**
     * 时间转 String
     * @param time
     * @return
     */
    public static String parseTime(LocalDateTime time) {
        return DEFAULT_DATETIME_FORMATTER.format(time);
    }

    /**
     * 时间转 String
     * @param time
     * @param format 时间格式
     * @return
     */
    public static String parseTime(LocalDateTime time, DateTimeFormat format) {
        return format.formatter.format(time);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDatetime() {
        return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取当前时间
     * @param format 时间格式
     * @return
     */
    public static String getCurrentDatetime(DateTimeFormat format) {
        return format.formatter.format(LocalDateTime.now());
    }

    /**
     * 内部日期格式化枚举类
     */
    public enum DateTimeFormat {

        // 返回分钟数
        DATE_M("M"),

        // 返回小时数
        DATE_H("H"),

        // 返回天数
        DATE_D("D"),

        // 返回时分秒
        TIME_FORMAT("HH:mm:ss"),

        /**
         * 短日期格式
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),

        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),

        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),

        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

        /**
         * 长日期格式
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),

        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),

        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),

        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

        /**
         * 长日期时间格式 带毫秒
         */
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS"),

        // 一天开始时间
        DAYTIME_START("00:00:00"),

        // 一天结束时间
        DAYTIME_END("23:59:59")
        ;

        /**
         * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
         */
        private transient DateTimeFormatter formatter;

        DateTimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }
}
