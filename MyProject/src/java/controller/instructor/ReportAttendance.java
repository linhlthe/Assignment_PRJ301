/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CheckAttendanceDBContext;
import dal.GroupDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import model.CheckAttendance;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author DELL
 */
public class ReportAttendance extends BaseRequireAuthenticationController {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if (u.getRole() == 1) {
            int groupID = Integer.parseInt(request.getParameter("group"));
            StudentDBContext stuDB = new StudentDBContext();
            SessionDBContext sesDB = new SessionDBContext();
            ArrayList<Session> sessions = sesDB.getAllSessionInGroup(groupID);
            ArrayList<Date> dates = new ArrayList<>();
            for (Session s : sessions) {
                dates.add(s.getDate());

            }
            GroupDBContext groupDB = new GroupDBContext();
            int numOfSlot = groupDB.getNumOfSlot(groupID);
            Hashtable<Student, Integer> percent = new Hashtable<>();

            ArrayList<Student> students = stuDB.all(groupID);
            for (Student s : students) {
                StudentDBContext stDB = new StudentDBContext();
                int num = stDB.getNumOfAbsent(s.getId(), groupID);
                int per = Math.round(num * 100 / numOfSlot);
                percent.put(s, num);
            }
            request.setAttribute("students", students);
            request.setAttribute("percent", percent);

            CheckAttendanceDBContext caDB = new CheckAttendanceDBContext();
            ArrayList<CheckAttendance> alist = caDB.getAttendancesByGroup(groupID);
            request.setAttribute("attends", alist);

            request.setAttribute("dates", dates);
            request.getRequestDispatcher("../view/instructor/reportAttendance.jsp").forward(request, response);

        } else {
            response.getWriter().println("access denied");
        }
    }
}
