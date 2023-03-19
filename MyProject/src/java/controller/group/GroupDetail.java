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
import datetime.DateTimeHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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
        SemesterDBContext sesDB = new SemesterDBContext();
        ArrayList<Semester> terms = sesDB.all();
        request.setAttribute("semesters", terms);
        DepartmentDBContext deptDB = new DepartmentDBContext();
        ArrayList<Department> depts = deptDB.all();
        request.setAttribute("depts", depts);
        Date currentdate = new Date();
        Semester currentTerm = new Semester();
        for (Semester s : terms) {
            if ((currentdate.compareTo(DateTimeHelper.toUtilDate(s.getStartDate())) >= 0) && (currentdate.compareTo(DateTimeHelper.toUtilDate(s.getEndDate())) <= 0)) {
                currentTerm = s;
            }

        }

        Department firstDept = depts.get(0);

        Semester selectedSemester = new Semester();
        Department selectedDepartment = new Department();
        Course selectedCourse = new Course();
        Group selectedGroup = new Group();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        if (request.getParameter("group") != null) {
            int groupID = Integer.parseInt(request.getParameter("group"));
            GroupDBContext gDB = new GroupDBContext();
            selectedGroup = gDB.getByID(groupID);
            selectedSemester = selectedGroup.getSemester();
            selectedDepartment = selectedGroup.getCourse().getDepartment();
            selectedCourse = selectedGroup.getCourse();

            //request.setAttribute("selectedTerm", selectedSemester);
            //request.setAttribute("selecteddept", selectedDepartment);
            //request.setAttribute("selectedcourse", selectedCourse);
            CourseDBContext courseDB = new CourseDBContext();
            courses = courseDB.getAllCourseInDepartment(selectedDepartment.getDepartmentID());
            //request.setAttribute("courses", courses);

            GroupDBContext groupDB = new GroupDBContext();
            groups = groupDB.all(selectedCourse.getCourseID(), selectedSemester.getTermID());
            //request.setAttribute("groups", groups);
            StudentDBContext stuDB = new StudentDBContext();
            students = stuDB.all(groupID);
            request.setAttribute("students", students);
            request.setAttribute("selectedGroup", selectedGroup);

        } else {
            if (request.getParameter("term") == null) {
                selectedSemester = currentTerm;

            } else {
                int termID = Integer.parseInt(request.getParameter("term"));
                selectedSemester.setTermID(termID);
            }
            if (request.getParameter("dept") == null) {
                selectedDepartment = firstDept;
            } else {
                int deptID = Integer.parseInt(request.getParameter("dept"));
                selectedDepartment.setDepartmentID(deptID);
            }
            CourseDBContext courseDB = new CourseDBContext();
            courses = courseDB.getAllCourseInDepartment(selectedDepartment.getDepartmentID());
            request.setAttribute("courses", courses);

            if (request.getParameter("course") == null) {
                selectedCourse = courses.get(0);
            } else {
                int courseID = Integer.parseInt(request.getParameter("course"));
                selectedCourse.setCourseID(courseID);
            }
            GroupDBContext groupDB = new GroupDBContext();
            groups = groupDB.all(selectedCourse.getCourseID(), selectedSemester.getTermID());

        }
        request.setAttribute("groups", groups);
        request.setAttribute("courses", courses);
        request.setAttribute("selectedTerm", selectedSemester);
        request.setAttribute("selectedDept", selectedDepartment);
        request.setAttribute("selectedCourse", selectedCourse);
        request.setAttribute("selectedGroup", selectedGroup);
        request.getRequestDispatcher("../view/group/groupDetail.jsp").forward(request, response);
    }
}
