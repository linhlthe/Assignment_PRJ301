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
import model.Group;
import model.Semester;
import model.Student;

/**
 *
 * @author DELL
 */
public class CourseDBContext extends DBContext<Course> {

    public Course get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select c.courseID, c.code, c.courseName, d.departmentName, c.noCredit, c.timeAllocation, \n"
                + "                c.[decription], c.studentTask, c.tools, c.scoringScale, c.decisionNo, c.isApproved, \n"
                + "                c.note, c.minAvgMarkToPass, c.numSlot  from course c inner join department d ON c.departmentID=d.departmentID WHERE c.courseID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();

                c.setCourseID(rs.getInt("courseID"));
                c.setCourseCode(rs.getString("code"));
                c.setCourseName(rs.getString("courseName"));
                c.setNoCredit(rs.getInt("noCredit"));
                Department d = new Department();
                d.setDepartmentName(rs.getString("departmentName"));
                c.setDepartment(d);
                c.setTimeAllocation(rs.getString("timeAllocation"));
                c.setDecription(rs.getString("decription"));
                c.setStudentTask(rs.getString("studentTask"));
                c.setTool(rs.getString("tools"));
                c.setScoringScale(rs.getInt("scoringScale"));
                c.setDecisionNo(rs.getString("decisionNo"));
                c.setIsApproved(rs.getBoolean("isApproved"));
                c.setNote(rs.getString("note"));
                c.setMinAvgMarkToPass(rs.getInt("minAvgMarkToPass"));
                c.setNumOfSlot(rs.getInt("numSlot"));
                return c;

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
        return null;
    }

    public ArrayList<Course> getAllCourseInDepartment(int did) {

        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "SELECT c.code, c.courseName FROM department d inner Join Course c ON c.departmentID= d.departmentID\n"
                + "WHERE d.departmentID =?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, did);
            
            rs = stm.executeQuery();
            while (rs.next()) {
               Course c= new Course();
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
