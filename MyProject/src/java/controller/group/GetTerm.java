/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.group;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CourseDBContext;
import dal.DBContext;
import dal.DepartmentDBContext;
import dal.GroupDBContext;
import dal.SemesterDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Department;
import model.Group;
import model.Semester;
import model.Student;
import model.User;

/**
 *
 * @author DELL
 */
public class GetTerm extends BaseRequireAuthenticationController {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetTerm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetTerm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        int termID = Integer.parseInt((String) request.getParameter("term"));
        GroupDBContext gDB = new GroupDBContext();
        SemesterDBContext sesDB = new SemesterDBContext();
        ArrayList<Semester> terms = sesDB.all();
        request.setAttribute("semesters", terms);
        request.setAttribute("selectedTerm", termID);
        //request.setAttribute("selecteddept", deptID);
        //request.setAttribute("selectedcourse", courseID);
        DepartmentDBContext deptDB = new DepartmentDBContext();
        ArrayList<Department> depts = deptDB.all();
        request.setAttribute("depts", depts);
        Department d = depts.get(0);
        request.setAttribute("selecteddept", d.getDepartmentID());
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getAllCourseInDepartment(d.getDepartmentID());
        request.setAttribute("courses", courses);
        Course c = courses.get(0);
        request.setAttribute("selectedcourse", c.getCourseID());
        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> groups = groupDB.all(c.getCourseID(), termID);
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("../view/group/groupDetail.jsp").forward(request, response);

    }

    public static void main(String[] args) {
        DepartmentDBContext deptDB = new DepartmentDBContext();
        ArrayList<Department> depts = deptDB.all();

        Department d = depts.get(0);
        System.out.println(d.getDepartmentID());
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getAllCourseInDepartment(d.getDepartmentID());
        
        Course c = courses.get(0);
        System.out.println(c.getCourseName());
    }

}
