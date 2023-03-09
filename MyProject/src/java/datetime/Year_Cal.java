/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datetime;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author DELL
 */
public class Year_Cal {

    public java.sql.Date firstMonday(Year y) {

        java.sql.Date date = null;

        for (int i = 1; i <= 7; i++) {
            String first = y.atDay(i).getDayOfWeek().name();
            if (first.toUpperCase().equalsIgnoreCase("MONDAY")) {
                date = java.sql.Date.valueOf(y.atDay(i));
            }
        }
        return date;
    }

    public ArrayList<Week> list(Year y) {
        ArrayList<Week> list = new ArrayList<>();
        java.sql.Date start = firstMonday(y);
        java.sql.Date end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(start, 6));

        for (int i = 0; i < 52; i++) {
            Week w = new Week();
            w.setStart(start);
            w.setEnd(end);
            list.add(w);
            start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(start, 7));
            end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(end, 7));
        }
        return list;
    }
    

}
