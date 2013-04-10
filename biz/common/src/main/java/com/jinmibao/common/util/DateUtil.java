package com.jinmibao.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String MINUTE_DATE_FORMAT  = "yyyy-MM-dd HH:mm";

    public static final String DAY_DATE_FORMAT     = "yyyy-MM-dd";

    public static final String UNLINE_DATE_FORMAT  = "yyyy_MM_dd_HH_mm_ss";

    public static final String SHOW_DATE_FORMAT    = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 把字符串格式化为新的模式
     * 
     * @param dataStr
     * @param oldFormat
     * @param newFormat
     * @return
     */
    public static String stringDateFormat(String dataStr, String oldFormat, String newFormat) {
        Date date = parseDate(dataStr, oldFormat);
        return date2String(date, newFormat);
    }

    /**
     * 判断当前时间是否在合法范围内
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean currentIfBetweenDate(Date startDate, Date endDate) {
        Date cd = new Date();
        if (cd.after(startDate) && cd.before(endDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取整小时的时间
     * 
     * @param date
     * @param Hour
     * @return
     */
    public static Date getDateAtHourMinute(Date date, int Hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        return calendar.getTime();
    }

    /**
     * 获取日期和星期的字符串
     * 
     * @param date
     * @return
     */
    public static String getDateAndWeek(Date date, String dateFormat) {
        String dateStr = date2String(date, dateFormat);
        String weekStr = getDayOfWeekStr(date);
        return dateStr + "(" + weekStr + ")";
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取凌晨的时间 如：2011-11-11 00:00:00
     * 
     * @return
     */
    public static Date getTodayFirstTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            calendar.setTime(new Date());
        } else {
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static String getDayOfWeekStr(Date date) {
        int i = getDayOfWeek(date);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "无法识别的星期";
        }
    }

    /**
     * 该方法在同步渠道订单被用到，切勿轻易改动！
     * 
     * @param date
     * @param dateformat
     * @return
     */
    public static Date parseDate(String date, String dateformat) {
        dateformat = StringUtil.defaultIfBlank(dateformat, DEFAULT_DATE_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 该方法在同步渠道订单被用到，切勿轻易改动！
     * 
     * @param date
     * @param dateformat
     * @return
     */
    public static String date2String(Date date, String dateformat) {
        dateformat = StringUtil.defaultIfBlank(dateformat, DEFAULT_DATE_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 该方法在同步渠道订单被用到，切勿轻易改动！
     * 
     * @param aDate
     * @param minute
     * @return
     */
    public static final Date increaseMin(Date aDate, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 增加或减少天数
     * 
     * @param aDate
     * @param minute
     * @return
     */
    public static final Date increaseDate(Date aDate, int date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(Calendar.DATE, date);
        return cal.getTime();
    }

    public static Date reduceDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - days);

        return cal.getTime();
    }

    /**
     * 获得当前时间n天后的日期
     * 
     * @param n 几天
     * @param pattern 日期格式 如：yyyy/MM/dd
     * @return
     */
    public static String getAfterDaysDate(int n, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + n);
        return sdf.format(calendar.getTime());
    }

    /**
     * 返回偏移指定小时数的日期对象
     * 
     * @param aDate
     * @param hour
     * @return
     */
    public static final Date offsetHour(Date aDate, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    /**
     * 把开始时间和结束时间按照一定时间步长来切分
     * 
     * @param gmtStart 起始时间
     * @param gmtEnd 结束时间
     * @param timeStep 步长
     * @return
     */
    public static List<TimeUnit> splitQueryPeriod(Date gmtStart, Date gmtEnd, int timeStep) {
        List<TimeUnit> timeUnitList = new ArrayList<TimeUnit>();

        if ((gmtStart.before(gmtEnd) || gmtStart.compareTo(gmtEnd) == 0)
            && DateUtil.increaseMin(gmtStart, timeStep).after(gmtEnd)) {
            TimeUnit timeUnit = new TimeUnit(gmtStart, gmtEnd);
            timeUnitList.add(timeUnit);
            return timeUnitList;
        }

        Date dateCursorStart = gmtStart;
        Date dateCursorEnd = DateUtil.increaseMin(gmtStart, timeStep);
        while (dateCursorEnd.before(gmtEnd)) {
            TimeUnit timeUnit = new TimeUnit(dateCursorStart, dateCursorEnd);
            timeUnitList.add(timeUnit);
            dateCursorStart = dateCursorEnd;
            dateCursorEnd = DateUtil.increaseMin(dateCursorStart, timeStep);
            if (dateCursorEnd.after(gmtEnd) || dateCursorEnd.compareTo(gmtEnd) == 0) {
                timeUnitList.add(new TimeUnit(dateCursorStart, gmtEnd));
            }
        }

        return timeUnitList;
    }

    /**
     * 计算两个日期之间的相差分钟数
     * 
     * @param gmtEnd 结束时间
     * @param gmtStart 起始时间
     * @return
     */
    public static long getDeltaMinutes(Date gmtEnd, Date gmtStart) {
        return (gmtEnd.getTime() - gmtStart.getTime()) / (1000 * 60);
    }

    // 获取系统当前时间-模式一
    public static String getCurrentDateM1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    // 获取当前年份
    public static String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "";
    }

    /**
     * 获取当前月份
     * 
     * @param autoZero 是否自动补零
     * @return
     */
    public static String getCurrentMonth(boolean autoZero) {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.MONTH) + 1;
        if (y < 10 && autoZero) {
            return "0" + y;
        } else {
            return y + "";
        }
    }

    /**
     * 获取当前天数
     * 
     * @param autoZero 是否自动补零
     * @return
     */
    public static String getCurrentDate(boolean autoZero) {
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DATE);
        if (d < 10) {
            return "0" + d;
        } else {
            return d + "";
        }
    }

    // 获取当前时间，返回的是Timestamp
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(getCurrentDateM1());
    }

    /**
     * 获取今天所在的周是今年的第几周，从1开始
     * 
     * @param date
     * @return
     */
    public static String getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.WEEK_OF_YEAR);
        return val + "";
    }

    public static String getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + "";
    }

    public static String getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.MONTH) + 1;
        if (val < 10) {
            return "0" + val;
        } else {
            return val + "";
        }
    }

    public static String getDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.DATE);
        if (val < 10) {
            return "0" + val;
        } else {
            return val + "";
        }
    }

    /**
     * 获取今天所在的周是本月的第几周
     * 
     * @param date
     * @return
     */
    public static String getWeekOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.WEEK_OF_MONTH);
        return val + "";
    }

    /**
     * 获取今天是本月的第几天
     * 
     * @param date
     * @return
     */
    public static String getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.DAY_OF_MONTH);
        return val + "";
    }

    /**
     * 获取今天是本年的第几天
     * 
     * @param date
     * @return
     */
    public static String getDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.DAY_OF_YEAR);
        return val + "";
    }

    /**
     * 今天是本月的第几周
     * 
     * @param date
     * @return
     */
    public static String getDayOfWeekInMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int val = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        return val + "";
    }

    /**
     * 求两个时间相差的秒数- 重载方法
     * 
     * @param earlierDate
     * @param lateDate
     * @return
     */
    public static long getTwoDateDiffSeconds(String earlierDate, String lateDate) {
        Date ed = parseDate(earlierDate, null);
        Date ld = parseDate(lateDate, null);
        long t = ld.getTime() - ed.getTime();
        long s = t / 1000;
        return s;
    }

    /**
     * 求两个时间相差的天时分秒
     * 
     * @param earlierDate
     * @param lateDate
     * @return
     */
    public static String getTwoDateDiff(String earlierDate, String lateDate) {

        Date ed = parseDate(earlierDate, null);
        Date ld = parseDate(lateDate, null);
        long t = ld.getTime() - ed.getTime();
        long day = t / (24 * 60 * 60 * 1000);
        long hour = (t / (60 * 60 * 1000) - day * 24);
        long min = ((t / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (t / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return "" + day + "天" + hour + "时" + min + "分" + s + "秒";

    }

    public static String getTwoDateDiff(Date earlierDate, Date lateDate) {

        long t = lateDate.getTime() - earlierDate.getTime();
        long day = t / (24 * 60 * 60 * 1000);
        long hour = (t / (60 * 60 * 1000) - day * 24);
        long min = ((t / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (t / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return "" + day + "天" + hour + "时" + min + "分" + s + "秒";

    }

    public static void main(String[] args) {
        Date date = new Date();
        String yearVal = DateUtil.getYear(date);
        String monthVal = DateUtil.getMonth(date);
        String dateVal = DateUtil.getDate(date);
        System.out.println(yearVal);
        System.out.println(monthVal);
        System.out.println(dateVal);
    }
}
