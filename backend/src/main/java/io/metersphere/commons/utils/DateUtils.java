package io.metersphere.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DateUtils {
    public static final String DATE_PATTERM = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public static Date getDate(String dateString) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERM);
        return dateFormat.parse(dateString);
    }
    public static Date getTime(String timeString) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        return dateFormat.parse(timeString);
    }

    public static String getDateString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERM);
        return dateFormat.format(date);
    }

    public static String getDateString(long timeStamp) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERM);
        return dateFormat.format(timeStamp);
    }

    public static String getTimeString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        return dateFormat.format(date);
    }

    public static String getTimeString(long timeStamp) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        return dateFormat.format(timeStamp);
    }

    public static String getTimeStr(long timeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        return dateFormat.format(timeStamp);
    }
    public static String getDataStr(long timeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERM);
        return dateFormat.format(timeStamp);
    }


    public static Date dateSum (Date date,int countDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,countDays);

        return calendar.getTime();
    }

    /**
     * ??????????????????????????????????????????????????? ???????????????????????????????????????
     *
     * @return Map<String, String>(2); key???????????????firstTime/lastTime
     */
    public static Map<String, Date> getWeedFirstTimeAndLastTime(Date date) {
        Map<String, Date> returnMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        //Calendar???????????????????????????????????????????????????????????????????????????"+1"
        int weekDayAdd = 1;

        try {
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DAY_OF_MONTH,weekDayAdd);

            //???????????????????????? 00:00:00 ????????????????????????????????????????????????
            Date thisWeekFirstTime = getDate(getDateString(calendar.getTime()));

            calendar.clear();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DAY_OF_MONTH,weekDayAdd);

            //?????????????????????????????????23:59:59??? ??????????????????????????????????????????-1
            calendar.add(Calendar.DAY_OF_MONTH,1);
            Date nextWeekFirstDay = getDate(getDateString(calendar.getTime()));
            Date thisWeekLastTime = getTime(getTimeString(nextWeekFirstDay.getTime()-1));

            returnMap.put("firstTime", thisWeekFirstTime);
            returnMap.put("lastTime", thisWeekLastTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;

    }


    public static void main(String[] args) throws Exception {
        System.out.println("start:");
        Date paramTime = getTime(getTimeString(new Long("1607672440731")));

        Map<String, Date> weekDate = getWeedFirstTimeAndLastTime(paramTime);

        for (Map.Entry<String, Date> entry :
                weekDate.entrySet()) {
            System.out.println(entry.getKey() + ":" + getTimeString(entry.getValue())+":"+entry.getValue().getTime());
        }

        long countTimeLong = new Long("1607672440731");

        System.out.println(getTimeString(--countTimeLong));

    }


    /**
     * ???????????????????????????Date
     * @param time  ????????????  ?????? 2020-12-13 06:12:42
     * @return  ?????????????????? ?????? 2020-12-13 00:00:00
     * @throws Exception
     */
    public static Date getDayStartTime(Date time) throws Exception {
        return getDate(getDateString(time));
    }
}
