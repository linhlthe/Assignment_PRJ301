/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.course;

import controller.authentication.BaseRequireAuthenticationController;
import dal.CourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.User;

/**
 *
 * @author DELL
 */
public class CourseDetail extends BaseRequireAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
       int courseID= Integer.parseInt(request.getParameter("course"));
        CourseDBContext cDB= new CourseDBContext();
        Course c= cDB.get(courseID);
        request.setAttribute("course", c);
         request.getRequestDispatcher("../view/course/courseDetail.jsp").forward(request, response);
    }

    

}
