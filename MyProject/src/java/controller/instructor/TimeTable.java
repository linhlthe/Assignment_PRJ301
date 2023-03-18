/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CheckAttendanceDBContext;
import dal.InstructorDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import datetime.DateTimeHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.CheckAttendance;
import model.Instructor;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;

/**
 *
 * @author DELL
 */
public class TimeTable extends BaseRequireAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        request.getRequestDispatcher("../view/instructor/timetable2.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        request.getRequestDispatcher("../view/instructor/timetable2.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");

        Date from = Date.valueOf(request.getParameter("from"));
        Date to = Date.valueOf(request.getParameter("to"));
        int id = Integer.parseInt(request.getParameter("instructorID"));

        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.all();
        request.setAttribute("slots", slots);
        if (from.compareTo(to) > 0) {
            response.getWriter().println("Choose again!");
        }

        ArrayList<Date> dates = DateTimeHelper.getListDate(from, to);
        request.setAttribute("dates", dates);

        InstructorDBContext insDB = new InstructorDBContext();
        Instructor instructor = insDB.getTimeTable(u.getId(), from, to);
        

        request.setAttribute("instructor",instructor);
        String inform = "timetable";
        request.setAttribute("inform", inform);
        request.setAttribute("from", from);
        request.setAttribute("to", to);

        request.getRequestDispatcher("../view/instructor/timetable2.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
