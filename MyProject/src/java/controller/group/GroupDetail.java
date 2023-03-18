/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.group;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CourseDBContext;
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
public class GroupDetail extends BaseRequireAuthenticationController {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        int groupID = Integer.parseInt(request.getParameter("group"));
        GroupDBContext gDB = new GroupDBContext();
        Group g = gDB.getByID(groupID);
        SemesterDBContext sesDB = new SemesterDBContext();
        ArrayList<Semester> terms = sesDB.all();
        request.setAttribute("semesters", terms);
        int termID = g.getSemester().getTermID();
        request.setAttribute("selectedTerm", termID);
        int deptID = g.getCourse().getDepartment().getDepartmentID();
        int courseID = g.getCourse().getCourseID();
        request.setAttribute("selecteddept", deptID);
        request.setAttribute("selectedcourse", courseID);
        DepartmentDBContext deptDB = new DepartmentDBContext();
        ArrayList<Department> depts = deptDB.all();
        request.setAttribute("depts", depts);
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courses = courseDB.getAllCourseInDepartment(deptID);
        request.setAttribute("courses", courses);
        Course c = g.getCourse();
        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> groups = groupDB.all(courseID, termID);
        request.setAttribute("groups", groups);
        StudentDBContext stuDB = new StudentDBContext();
        ArrayList<Student> students = stuDB.all(groupID);
        request.setAttribute("students", students);
        request.getRequestDispatcher("../view/group/groupDetail.jsp").forward(request, response);

    }

}
