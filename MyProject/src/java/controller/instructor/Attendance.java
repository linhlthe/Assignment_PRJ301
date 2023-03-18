/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CheckAttendanceDBContext;
import dal.SessionDBContext;
import datetime.DateTimeHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import model.Session;
import model.Student;
import model.User;
import model.CheckAttendance;
import model.Instructor;

/**
 *
 * @author DELL
 */
public class Attendance extends BaseRequireAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        
        User u = (User) request.getSession().getAttribute("user");
        if(u.getRole()==1){
        String[] sids = request.getParameterValues("sid");
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));

        Session ss = new Session();
        ss.setSessionID(sessionid);

        ArrayList<CheckAttendance> atts = new ArrayList<>();
        for (String sid : sids) {
            boolean status = request.getParameter("status" + sid).equals("present");
            int aid = Integer.parseInt(request.getParameter("aid" + sid));
            String comment = request.getParameter("comment" + sid);
            CheckAttendance a = new CheckAttendance();                       
            Student s = new Student();
            s.setId(Integer.parseInt(sid));
            Instructor i = new Instructor();
            i.setId(u.getId());
            i.setUsername(u.getUsername());
            a.setTaker(i);
            a.setAid(aid);
            a.setStatus(status);
            a.setComment(comment);            
            a.setRecordedTime(DateTimeHelper.getCurrentTime());
            a.setStudent(s);
            a.setSession(ss);
            atts.add(a);
        }
        CheckAttendanceDBContext db = new CheckAttendanceDBContext();
        db.update(atts, sessionid);
        response.sendRedirect("viewAttendance?id=" + sessionid);
        } else{
            response.getWriter().println("access denied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if(u.getRole()==1){
        int sessionid = Integer.parseInt(request.getParameter("id"));
        int instructorID = Integer.parseInt(request.getParameter("instructor"));
        SessionDBContext sesDB = new SessionDBContext();
        Session ses = sesDB.getSessionByID(sessionid);
        if (ses.getInstructor().getId() != instructorID) {
            response.getWriter().println("You are not responsible for this group of students");
        } else {
            CheckAttendanceDBContext db = new CheckAttendanceDBContext();

            ArrayList<CheckAttendance> atts = db.getAttendancesBySession(sessionid);
            request.setAttribute("atts", atts);
            request.setAttribute("id", sessionid);

            request.getRequestDispatcher("../view/instructor/att.jsp").forward(request, response);
        }
        } else{
            response.getWriter().println("access denied");
        }
    }

}
