/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CheckAttendanceDBContext;
import dal.GroupDBContext;
import dal.SemesterDBContext;
import dal.StudentDBContext;
import datetime.DateTimeHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.CheckAttendance;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User s = (User) request.getSession().getAttribute("user");
        SemesterDBContext seDB = new SemesterDBContext();

        ArrayList<Semester> semesters = seDB.all();

        request.setAttribute("semesters", semesters);
        request.getRequestDispatcher("../view/student/reportAttendance.jsp").forward(request, response);

        /* User  s = (User) request.getSession().getAttribute("user");
        SemesterDBContext seDB = new SemesterDBContext();
        
        ArrayList<Semester> semesters= seDB.all();
        
        request.setAttribute("semesters", semesters);
        Semester currentSes= new Semester();
        Date date = new Date();
        for (Semester ses:semesters){
            if((date.compareTo(DateTimeHelper.toUtilDate(ses.getStartDate()))>=0) && (date.compareTo(DateTimeHelper.toUtilDate(ses.getEndDate()))<=0)){
                currentSes=ses;
            }
        }
        request.setAttribute("currentterm", currentSes);
        GroupDBContext gDB= new GroupDBContext();
        ArrayList<Group> groups= gDB.getAllGroupInTerm(s.getId(), currentSes.getTermID());
        request.setAttribute("groups", groups);
        Group g= groups.get(1);
         request.setAttribute("selectedgroup", g);
        CheckAttendanceDBContext aDB= new CheckAttendanceDBContext();
        ArrayList<CheckAttendance> alist=aDB.getAttendancesOfStudentInGroup(s.getId(), g.getGroupID());
        request.setAttribute("status", alist);
        
        
      request.getRequestDispatcher("../view/student/reportAttendance.jsp").forward(request, response); */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User s = (User) request.getSession().getAttribute("user");
        SemesterDBContext seDB = new SemesterDBContext();
        ArrayList<Semester> semesters = seDB.all();

        request.setAttribute("semesters", semesters);

        int termID = Integer.parseInt(request.getParameter("term"));
        int groupID = Integer.parseInt(request.getParameter("group"));
        GroupDBContext gDB = new GroupDBContext();
        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> groups = gDB.getAllGroupInTerm(s.getId(), termID);
        request.setAttribute("groups", groups);
        request.setAttribute("selectedTerm", termID);
        request.setAttribute("selectedGroup", groupID);
        StudentDBContext stDB = new StudentDBContext();
        int num = stDB.getNumOfAbsent(s.getId(), groupID);
        request.setAttribute("numOfAbsent", num);
        int numOfSlot = groupDB.getNumOfSlot(groupID);

        request.setAttribute("numOfAbsent", num);
        request.setAttribute("numOfSlot", numOfSlot);
        
        
        int percent = Math.round(num*100/numOfSlot);

        
        request.setAttribute("percent", percent);
        CheckAttendanceDBContext aDB = new CheckAttendanceDBContext();
        ArrayList<CheckAttendance> alist = aDB.getAttendancesOfStudentInGroup(s.getId(), groupID);
        int x = alist.size();
        request.setAttribute("status", alist);
        request.setAttribute("x", x);
        request.getRequestDispatcher("../view/student/reportAttendance.jsp").forward(request, response);

        /*int groupID= Integer.parseInt(request.getParameter("group"));
       CheckAttendanceDBContext aDB= new CheckAttendanceDBContext();
       ArrayList<CheckAttendance> alist=aDB.getAttendancesOfStudentInGroup(termID, groupID);
       request.setAttribute("status", alist);
        
        
        
      request.getRequestDispatcher("../view/student/reportAttendance.jsp").forward(request, response); */
    }
    
}
