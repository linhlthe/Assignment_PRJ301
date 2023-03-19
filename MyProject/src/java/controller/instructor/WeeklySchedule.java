/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.InstructorDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import datetime.DateTimeHelper;
import datetime.Week;
import datetime.Year_Cal;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import model.Instructor;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;

/**
 *
 * @author DELL
 */
public class WeeklySchedule extends BaseRequireAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User s = (User) request.getSession().getAttribute("user");
        //int sid = s.getId();
        request.setAttribute("user", s);
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        LocalDateTime localDate = LocalDateTime.now();
        request.setAttribute("now", localDate);

        int y = localDate.getYear();
        request.setAttribute("currentyear", y);
        ArrayList<Integer> year = new ArrayList<>();
        year.add(y - 2);
        year.add(y - 1);
        year.add(y);
        year.add(y + 1);
        request.setAttribute("listYear", year);

        Year_Cal x = new Year_Cal();
        ArrayList<Week> weeks = new ArrayList<>();
        weeks = x.list(Year.of(y));
        request.setAttribute("listWeek", weeks);
        Week w = new Week();
        DateTimeHelper d = new DateTimeHelper();
        w = w.getWeek(d.toSQLDate(java.util.Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant())));
        request.setAttribute("currentweek", w.getStart());
        ArrayList<Date> dates = DateTimeHelper.getListDate(w.getStart(), w.getEnd());
        request.setAttribute("dates", dates);
        InstructorDBContext insDB = new InstructorDBContext();
        Instructor instructor = insDB.getTimeTable(s.getId(), w.getStart(), w.getEnd());
        ArrayList<Session> ses = new ArrayList<>();
       // ses = instructor.getSessions();
        request.setAttribute("instructor", instructor);

       // request.setAttribute("sessions", ses);
        request.setAttribute("address", "Weekly Schedule");

        request.getRequestDispatcher("../view/instructor/timetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {

    }
    public static void main(String[] args) {
        InstructorDBContext insDB = new InstructorDBContext();
        Instructor instructor = insDB.getTimeTable(16, Date.valueOf("2024-01-01"), Date.valueOf("2024-01-11"));
        ArrayList<Session> ses = new ArrayList<>();
        if (instructor.getSessions() != null) {
            ses = instructor.getSessions();
        }
        for (Session s:ses){
            System.out.println(s.getSessionID());
    }

}
}
