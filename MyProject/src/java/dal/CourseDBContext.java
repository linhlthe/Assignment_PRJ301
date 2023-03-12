/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Department;
import model.Semester;
import model.Student;

/**
 *
 * @author DELL
 */
public class CourseDBContext extends DBContext<Course> {

    public ArrayList<Course> allOfDepartment(Department d) {
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "Select c.code, c.courseID,c.courseName \n"
                + "From course c inner join department d\n"
                + "on c.departmentID=d.departmentID\n"
                + "where d.departmentID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, d.getDepartmentID());
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c= new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseCode(rs.getString("code"));
                c.setCourseName(rs.getString("courseName"));
                courses.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }

}
