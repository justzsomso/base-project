package com.base.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * 时间日期工具类（基于 Java 原生 API 实现，无第三方依赖）
 */
public class DateTimeUtils {

    // 默认时区（可根据项目调整，如 Asia/Shanghai）
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    // 默认日期时间格式（yyyy-MM-dd HH:mm:ss）
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 默认日期格式（yyyy-MM-dd）
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // 默认时间格式（HH:mm:ss）
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    // ==================== 日期格式化（转 String）====================
    /**
     * LocalDateTime 转 String（默认格式：yyyy-MM-dd HH:mm:ss）
     */
    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * LocalDateTime 转 String（自定义格式）
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        if (Objects.isNull(localDateTime) || isBlank(pattern)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDateTime.format(formatter);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("日期格式错误：" + pattern, e);
        }
    }

    /**
     * Date 转 String（默认格式：yyyy-MM-dd HH:mm:ss）
     */
    public static String format(Date date) {
        return format(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * Date 转 String（自定义格式）
     */
    public static String format(Date date, String pattern) {
        if (Objects.isNull(date) || isBlank(pattern)) {
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
        return format(localDateTime, pattern);
    }

    /**
     * LocalDate 转 String（默认格式：yyyy-MM-dd）
     */
    public static String format(LocalDate localDate) {
        return format(localDate, DEFAULT_DATE_FORMAT);
    }

    /**
     * LocalDate 转 String（自定义格式）
     */
    public static String format(LocalDate localDate, String pattern) {
        if (Objects.isNull(localDate) || isBlank(pattern)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("日期格式错误：" + pattern, e);
        }
    }

    // ==================== 日期解析（String 转日期对象）====================
    /**
     * String 转 LocalDateTime（默认格式：yyyy-MM-dd HH:mm:ss）
     */
    public static LocalDateTime parseLocalDateTime(String dateStr) {
        return parseLocalDateTime(dateStr, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * String 转 LocalDateTime（自定义格式）
     */
    public static LocalDateTime parseLocalDateTime(String dateStr, String pattern) {
        if (isBlank(dateStr) || isBlank(pattern)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("日期解析失败：" + dateStr + "，格式：" + pattern, e);
        }
    }

    /**
     * String 转 Date（默认格式：yyyy-MM-dd HH:mm:ss）
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * String 转 Date（自定义格式）
     */
    public static Date parseDate(String dateStr, String pattern) {
        LocalDateTime localDateTime = parseLocalDateTime(dateStr, pattern);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * String 转 LocalDate（默认格式：yyyy-MM-dd）
     */
    public static LocalDate parseLocalDate(String dateStr) {
        return parseLocalDate(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * String 转 LocalDate（自定义格式）
     */
    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        if (isBlank(dateStr) || isBlank(pattern)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("日期解析失败：" + dateStr + "，格式：" + pattern, e);
        }
    }

    // ==================== 日期类型转换（Date ↔ LocalDateTime/LocalDate）====================
    /**
     * Date 转 LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    }

    /**
     * LocalDateTime 转 Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * Date 转 LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate();
    }

    /**
     * LocalDate 转 Date（默认时间：00:00:00）
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return localDateTimeToDate(localDateTime);
    }

    // ==================== 日期计算（加减年/月/日/时/分/秒）====================
    /**
     * LocalDateTime 加减指定时间（支持年/月/日/时/分/秒）
     * @param localDateTime 原始日期
     * @param amount 增减数量（正数加，负数减）
     * @param unit 时间单位（如 ChronoUnit.DAYS、ChronoUnit.MONTHS）
     */
    public static LocalDateTime plusOrMinus(LocalDateTime localDateTime, long amount, ChronoUnit unit) {
        if (Objects.isNull(localDateTime) || Objects.isNull(unit)) {
            return null;
        }
        return localDateTime.plus(amount, unit);
    }

    /**
     * Date 加减指定天数
     */
    public static Date plusDays(Date date, int days) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        LocalDateTime newDateTime = localDateTime.plusDays(days);
        return localDateTimeToDate(newDateTime);
    }

    /**
     * Date 加减指定月数
     */
    public static Date plusMonths(Date date, int months) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        LocalDateTime newDateTime = localDateTime.plusMonths(months);
        return localDateTimeToDate(newDateTime);
    }

    /**
     * LocalDate 加减指定天数
     */
    public static LocalDate plusDays(LocalDate localDate, int days) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.plusDays(days);
    }

    // ==================== 时间差计算 ====================
    /**
     * 计算两个 LocalDateTime 的时间差（单位：指定时间单位）
     * @return 时间差（正数：end > start；负数：end < start）
     */
    public static long between(LocalDateTime start, LocalDateTime end, ChronoUnit unit) {
        if (Objects.isNull(start) || Objects.isNull(end) || Objects.isNull(unit)) {
            return 0;
        }
        return unit.between(start, end);
    }

    /**
     * 计算两个 Date 的天数差（end - start）
     */
    public static long betweenDays(Date start, Date end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            return 0;
        }
        LocalDate startDate = dateToLocalDate(start);
        LocalDate endDate = dateToLocalDate(end);
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算两个 Date 的小时差（end - start）
     */
    public static long betweenHours(Date start, Date end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            return 0;
        }
        LocalDateTime startTime = dateToLocalDateTime(start);
        LocalDateTime endTime = dateToLocalDateTime(end);
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    // ==================== 常用工具方法 ====================
    /**
     * 获取当前 LocalDateTime（默认时区）
     */
    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(DEFAULT_ZONE_ID);
    }

    /**
     * 获取当前 Date（默认时区）
     */
    public static Date nowDate() {
        return localDateTimeToDate(nowLocalDateTime());
    }

    /**
     * 获取当前 LocalDate（默认时区）
     */
    public static LocalDate nowLocalDate() {
        return LocalDate.now(DEFAULT_ZONE_ID);
    }

    /**
     * 判断是否为今天（LocalDateTime）
     */
    public static boolean isToday(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return false;
        }
        LocalDate currentDate = nowLocalDate();
        LocalDate targetDate = localDateTime.toLocalDate();
        return currentDate.equals(targetDate);
    }

    /**
     * 判断是否为今天（Date）
     */
    public static boolean isToday(Date date) {
        if (Objects.isNull(date)) {
            return false;
        }
        LocalDate currentDate = nowLocalDate();
        LocalDate targetDate = dateToLocalDate(date);
        return currentDate.equals(targetDate);
    }

    /**
     * 判断是否为闰年（LocalDate）
     */
    public static boolean isLeapYear(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return false;
        }
        return localDate.isLeapYear();
    }

    /**
     * 比较两个 LocalDateTime 的大小（start 是否在 end 之前）
     */
    public static boolean isBefore(LocalDateTime start, LocalDateTime end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            return false;
        }
        return start.isBefore(end);
    }

    /**
     * 比较两个 Date 的大小（start 是否在 end 之前）
     */
    public static boolean isBefore(Date start, Date end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            return false;
        }
        return start.before(end);
    }

    // ==================== 内部辅助方法（字符串空值判断）====================
    /**
     * 判断字符串是否为空白（null、空字符串、全空格）
     */
    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}