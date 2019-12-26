package com.h2t.study.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 时间类转化工具类
 *
 * @version 1.0
 * @author: hetiantian
 * @date: 2019/12/25 14:47
 */
public class LocalDateTimeUtil {
    /**
     * 时间戳转化为LocalDateTime
     *
     * @param timestamp 时间戳
     * @return
     */
    public static LocalDateTime longToLocalDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * 格式化LocalDateTime
     *
     * @param localDateTime
     * @param pattern       格式化类型
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return format.format(localDateTime);
    }

    /**
     * LocalDateTime转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToLong(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 格式化的String类型时间转LocalDateTime
     *
     * @param patternTime 格式化时间
     * @param pattern     格式化类型
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String patternTime, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(patternTime, format);
    }
}
