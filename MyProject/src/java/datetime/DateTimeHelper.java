/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datetime;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author DELL
 */
public class DateTimeHelper {

    public static ArrayList<Date> getListDate(Date from, Date to) {
        ArrayList<Date> dates = new ArrayList<>();
        Date loop = from;
        while (loop.compareTo(to) <= 0) {
            dates.add(loop);
            java.util.Date loop_util = toUtilDate(loop);
            loop = toSQLDate(addDays(loop_util, 1));
        }
        return dates;
    }

    public static java.sql.Date toSQLDate(java.util.Date value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static java.util.Date addDays(java.util.Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static java.util.Date toUtilDate(java.sql.Date value) {
        return new java.util.Date(value.getTime());
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }
    public static String getCurrentTime(){
        LocalDateTime myDateObj = LocalDateTime.now();            
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String recordedTime = myDateObj.format(myFormatObj);
            return recordedTime;
    }
   

}
