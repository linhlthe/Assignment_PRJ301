/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CheckAttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.CheckAttendance;
import model.User;

/**
 *
 * @author DELL
 */
public class ViewAttendance extends BaseRequireAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if (u.getRole() == 1) {
            int sessionid = Integer.parseInt(request.getParameter("id"));
            CheckAttendanceDBContext db = new CheckAttendanceDBContext();
            ArrayList<CheckAttendance> atts = db.getAttendancesBySession(sessionid);
            request.setAttribute("atts", atts);

            request.getRequestDispatcher("../view/instructor/viewAttendance.jsp").forward(request, response);
        } else {
            response.getWriter().println("access denied");
        }
    }

}
