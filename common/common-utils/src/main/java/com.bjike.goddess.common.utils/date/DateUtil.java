package com.bjike.goddess.common.utils.date;

import com.bjike.goddess.common.api.exception.SerException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-13 10:42]
 * @Description: [日期工具类]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DateUtil {
    private static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static Calendar calendar = null;

    /**
     * 日期时间转换
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME);
    }

    /**
     * 日期转换
     *
     * @param date
     * @return
     */

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE);
    }

    /**
     * 时间转换
     *
     * @param time
     * @return
     */
    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time, TIME);
    }


    /**
     * 日期时间转相应字符串
     *
     * @param date (LocalDate,LocalTime,LocalDateTime)
     * @return String
     */
    public static <DATE> String dateToString(DATE date) {
        if (date.getClass().equals(LocalDate.class)) {
            return ((LocalDate) date).format(DATE);
        } else if (date.getClass().equals(LocalDateTime.class)) {
            return ((LocalDateTime) date).format(DATETIME);
        } else {
            return ((LocalTime) date).format(TIME);
        }
    }


    public static LocalDateTime parseTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取本周星期开始的时间
     *
     * @return
     */
    public static LocalDate getStartWeek() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();

    }

    /**
     * 获取本周星期结束的时间
     *
     * @return
     */
    public static LocalDate getEndWeek() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();

    }

    /**
     * 获取本月开始的时间
     *
     * @return
     */

    public static LocalDate getStartMonth() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();

    }


    /**
     * 获取本月结束的时间
     *
     * @return
     */

    public static LocalDate getEndMonth() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();

    }

    /**
     * 获取本年开始的时间
     *
     * @return
     */

    public static LocalDate getStartYear() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();

    }


    /**
     * 获取本年结束的时间
     *
     * @return
     */

    public static LocalDate getEndYear() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();

    }


    /**
     * 获取本季度开始的时间
     *
     * @return
     */

    public static LocalDateTime getStartQuart() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime quarte_start = null;
        int mon = now.getMonthValue();
        if( mon%3 == 1){
            quarte_start = now.minusMonths(0);
        }else if( mon%3 == 2){
            quarte_start = now.minusMonths(1);
        }else if( mon%3 == 0){
            quarte_start = now.minusMonths(2);
        }
        return  quarte_start;

    }


    /**
     * 获取本季度结束的时间
     *
     * @return
     */

    public static LocalDateTime getEndQuart() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime quarte_end = null;
        int mon = now.getMonthValue();
        if( mon%3 == 1){
            quarte_end = now.plusMonths(2);
        }else if( mon%3 == 2){
            quarte_end = now.plusMonths(1);
        }else if( mon%3 == 0){
            quarte_end = now.plusMonths(0);
        }

        return quarte_end;
    }



    //获取今年指定月份的第一天 月份从0开始算0~11
    public static LocalDate getStartDayOfMonth(Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
    }

    //获取今年指定月份的最后一天 月份从0开始算0~11
    public static LocalDate getEndDaYOfMonth(Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
    }

    //获取今年指定年份、月份的第一天 月份从0开始算0~11
    public static LocalDate getStartDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
    }

    //获取指定年份、月份的最后一天 月份从0开始算0~11
    public static LocalDate getEndDaYOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
    }

    /**
     * Date转LocalDateTime
     *
     * @param date 日期
     * @return
     */
    public static LocalDateTime changeLocaDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * 通过年月获取月份的天数
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    public static Integer getDayByDate(Integer year, Integer month) throws SerException {
        try {
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String str_date = year + "-" + (month < 10 ? "0" + month : month);
            Date date = sdf.parse(str_date);
            calendar.setTime(date); //放入你的日期
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            throw new SerException(e.getMessage());
        }
    }


}
