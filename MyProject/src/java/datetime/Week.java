/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datetime;

import static datetime.DateTimeHelper.toUtilDate;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author DELL
 */
public class Week {

    private Date start;
    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Week getWeek(Date d) {
        Week w = new Week();
        LocalDate date1 = DateTimeHelper.toLocalDate(d);
        java.util.Date date2 = DateTimeHelper.toUtilDate(d);
        String dateOfWeek = date1.getDayOfWeek().name().toUpperCase();
        java.sql.Date end;
        java.sql.Date start;

        switch (dateOfWeek) {
            case ("MONDAY"):
                start = d;
                end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, 6));
                break;

            case ("TUESDAY"):
                end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, 5));
                start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, -1));
                break;

            case ("WEDNESDAY"):
                end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, 4));
                start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, -2));
                break;

            case ("THURSDAY"):
                end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, 3));
                start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, -3));
                break;

            case ("FRIDAY"):
                end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, 2));
                start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, -4));
                break;

            case ("SATURDAY"):
                end = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, 1));
                start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, -5));
                break;

            default:
                end = d;
                start = DateTimeHelper.toSQLDate(DateTimeHelper.addDays(date2, -6));
                break;
        }
        w.setStart(start);
        w.setEnd(end);
        return w;

    }
    

}
