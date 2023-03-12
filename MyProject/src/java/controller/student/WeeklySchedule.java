/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import controller.authentication.BaseRequireAuthenticationController;
import dal.GroupDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import model.Instructor;
import model.Session;
import model.Student;
import model.TimeSlot;
import datetime.DateTimeHelper;
import datetime.Week;
import datetime.Year_Cal;
import java.time.ZoneId;
import model.Group;
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
        int sid= s.getId();
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.all();
        request.setAttribute("slots", slots);
        StudentDBContext stuDB = new StudentDBContext();

        LocalDateTime localDate = LocalDateTime.now();
        request.setAttribute("now", localDate);

        int y = localDate.getYear();
        request.setAttribute("currentyear", y);
        ArrayList<Integer> year = new ArrayList<>();
        year.add(y - 5);
        year.add(y - 4);
        year.add(y - 3);
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
        DateTimeHelper d= new DateTimeHelper();
        w = w.getWeek(d.toSQLDate(java.util.Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant())));
        request.setAttribute("currentweek", w.getStart());
        Student stud= stuDB.getTimeTable(sid, w.getStart(), w.getEnd());
        ArrayList<Date> dates = DateTimeHelper.getListDate(w.getStart(), w.getEnd());
        request.setAttribute("dates", dates);
        
        request.setAttribute("s", stud);
        
        request.getRequestDispatcher("../view/student/timetable.jsp").forward(request, response);
    }

   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        
    }
    public static void main(String[] args) {
          StudentDBContext stuDB = new StudentDBContext();
        Student stud= stuDB.getTimeTable(17, java.sql.Date.valueOf("2023-03-13"), java.sql.Date.valueOf("2023-03-20"));
        for(Group g:stud.getGroups()){
            for(Session s:g.getSessions()){
                System.out.println(s.getRoom().getRoomName()+s.getDate()+"\n");
                
            }
        }
        
    }
    
    
}


