/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.InstructorDBContext;
import datetime.DateTimeHelper;
import datetime.Week;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import model.Instructor;
import model.Session;
import model.User;

/**
 *
 * @author DELL
 */
public class TakeAttendance extends BaseRequireAuthenticationController {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if(u.getRole()==1){
        response.setContentType("text/html;charset=UTF-8");
        User s = (User) request.getSession().getAttribute("user");        
        LocalDateTime localDate = LocalDateTime.now();
        request.setAttribute("now", localDate);
        Week w = new Week();
        DateTimeHelper d = new DateTimeHelper();
        w = w.getWeek(d.toSQLDate(java.util.Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant())));
        InstructorDBContext insDB = new InstructorDBContext();
        Instructor instructor = insDB.getTimeTable(s.getId(), w.getStart(), w.getEnd());
        ArrayList<Session> ses = new ArrayList<>();
        ses = instructor.getSessions();

        request.setAttribute("sessions", ses);

        request.getRequestDispatcher("../view/instructor/checkAttendance.jsp").forward(request, response);
        } else{
            response.getWriter().println("access denied");
        }
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
        processRequest(request, response,user);
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
        processRequest(request, response, user);
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


