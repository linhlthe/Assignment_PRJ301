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
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author DELL
 */
public class GroupDBContext extends DBContext<Group> {

    public ArrayList<Group> all(int cid, int sid) {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select g.groupID, g.groupName from [group] g\n"
                + "inner join course c on g.courseID=c.courseID\n"
                + "inner join semester s on s.termID = g.termID\n"
                + "where g.termID=? AND g.courseID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setGroupID(rs.getInt("groupID"));
                g.setGroupName(rs.getString("groupName"));
                groups.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;

    }

    public ArrayList<Group> getAllGroupInTerm(int studentID, int termID) {

        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.courseID, c.code, c.courseName,g.groupID,g.groupName, c.numSlot,g.termID\n"
                    + "FROM student s LEFT JOIN studentJoin sj ON sj.studentID = s.studentID\n"
                    + "LEFT JOIN [Group] g ON g.groupID = sj.groupID\n"
                    + "LEFT JOIN Course c ON c.courseID=g.courseID\n"
                    + "LEFT JOIN [semester] semes ON semes.termID=g.termID\n"
                    + "WHERE s.studentID=? AND semes.termID=?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentID);
            stm.setInt(2, termID);

            rs = stm.executeQuery();

            while (rs.next()) {
                Group g = new Group();
                Semester s = new Semester();
                s.setTermID(rs.getInt("termID"));
                g.setSemester(s);
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setNumOfSlot(rs.getInt("numSlot"));
                c.setCourseCode(rs.getString("code"));
                c.setCourseName(rs.getString("courseName"));
                g.setGroupID(rs.getInt("groupID"));
                g.setGroupName(rs.getString("groupName"));
                g.setCourse(c);
                groups.add(g);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return groups;
    }

    public int getNumOfSlot(int gid) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int n = 1;

        String sql = "select c.numSlot from [group] g inner join course c on c.courseID=g.courseID\n"
                + "where g.groupID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);

            rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt("numSlot");

            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;

    }

    public Group getByID(int gid) {
        Group g = new Group();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "SELECT g.groupID,g.termID, c.departmentID, c.courseID\n"
                + "FROM [group] g inner join course c on g.courseID=c.courseID\n"
                + "inner join department d on d.departmentID =c.departmentID\n"
                + "Where g.groupID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);

            rs = stm.executeQuery();
            while (rs.next()) {
                Semester term = new Semester();
                term.setTermID(rs.getInt("termID"));
                g.setSemester(term);
                Department d = new Department();
                d.setDepartmentID(rs.getInt("departmentID"));
                Course c = new Course();
                c.setDepartment(d);
                c.setCourseID(rs.getInt("courseID"));
                g.setGroupID(rs.getInt("groupID"));
                g.setCourse(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return g;
    }

    public ArrayList<Group> getAllGroupInstructor(int instructorID, int termID) {

        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.courseID, c.code, c.courseName,g.groupID,g.groupName, c.numSlot,g.termID\n"
                    + "                    FROM instructor i LEFT JOIN [Group] g ON g.instructorID = i.instructorID\n"
                    + "                    LEFT JOIN Course c ON c.courseID=g.courseID\n"
                    + "                    LEFT JOIN [semester] se ON se.termID=g.termID\n"
                    + "                    WHERE i.instructorID=? AND se.termID=?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, instructorID);
            stm.setInt(2, termID);

            rs = stm.executeQuery();

            while (rs.next()) {
                Group g = new Group();
                Semester s = new Semester();
                s.setTermID(rs.getInt("termID"));
                g.setSemester(s);
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setNumOfSlot(rs.getInt("numSlot"));
                c.setCourseCode(rs.getString("code"));
                c.setCourseName(rs.getString("courseName"));
                g.setGroupID(rs.getInt("groupID"));
                g.setGroupName(rs.getString("groupName"));
                g.setCourse(c);
                groups.add(g);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return groups;
    }

    public static void main(String[] args) {
        GroupDBContext gDB = new GroupDBContext();
        System.out.println(gDB.getByID(1).getCourse().getDepartment().getDepartmentID());
    }
}
