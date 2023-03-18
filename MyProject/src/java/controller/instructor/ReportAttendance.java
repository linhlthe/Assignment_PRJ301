/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CheckAttendanceDBContext;
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
        int groupID= Integer.parseInt(request.getParameter("group"));
        StudentDBContext stuDB = new StudentDBContext();
        SessionDBContext sesDB= new SessionDBContext();
        ArrayList<Session> sessions=sesDB.getAllSessionInGroup(groupID);
        ArrayList<Date> dates= new ArrayList<>();
        for(Session s:sessions){
            dates.add(s.getDate());
            
        }
        
        ArrayList<Student> students = stuDB.all(groupID);
        request.setAttribute("students", students);
        
            CheckAttendanceDBContext caDB= new CheckAttendanceDBContext();
            ArrayList<CheckAttendance> alist= caDB.getAttendancesByGroup(groupID);
            request.setAttribute("attends", alist);
        
        
        request.setAttribute("dates", dates);
        request.getRequestDispatcher("../view/instructor/reportAttendance.jsp").forward(request, response);
        
    }
    

}
