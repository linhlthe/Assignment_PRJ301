/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequireAuthenticationController;
import dal.SemesterDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Group;
import model.Semester;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user )
            throws ServletException, IOException {
        
        
        
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
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response , User user) throws ServletException, IOException {
      int termID = Integer.parseInt((String) request.getAttribute("termID"));
        User  s = (User) request.getSession().getAttribute("user");
        SemesterDBContext seDB = new SemesterDBContext();
        ArrayList<Semester> semesters= new ArrayList<>();
        StudentDBContext stuDB= new StudentDBContext();
        ArrayList<Group> groups = new ArrayList<>();
        Semester ses= new Semester();
        ses.setTermID(termID);
        groups=stuDB.getAllGroupInTerm(s.getId(), ses);
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("../view/student/reportAttendance.jsp").forward(request, response); 
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User  s = (User) request.getSession().getAttribute("user");
        SemesterDBContext seDB = new SemesterDBContext();
        ArrayList<Semester> semesters= new ArrayList<>();
        semesters = seDB.allOfStudent(s.getId());
        request.setAttribute("semesters", semesters);
        /*int termID = Integer.parseInt((String) request.getAttribute("termID"));
        
        StudentDBContext stuDB= new StudentDBContext();
        ArrayList<Group> groups = new ArrayList<>();
        Semester ses= new Semester();
        ses.setTermID(termID);
        groups=stuDB.getAllGroupInTerm(s, ses);
        request.setAttribute("groups", groups);*/
        request.getRequestDispatcher("../view/student/reportAttendance.jsp").forward(request, response);
        
        
        
    }

}
