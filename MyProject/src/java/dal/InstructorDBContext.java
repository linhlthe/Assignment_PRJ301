/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Department;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author DELL
 */
public class InstructorDBContext extends DBContext<Instructor> {

    public Instructor get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select i.instructorID, u.surname, u.middlename, u.givenname, u.gender, u.campus,\n"
                + "u.email, d.departmentName,u.[address],u.dob,u.nationality, u.phoneNumber\n"
                + "from [user] u inner join instructor i on u.id=i.instructorID\n"
                + "inner join department d on d.departmentID=i.departmentID\n"
                + "where u.id=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Instructor i = new Instructor();
                i.setId(rs.getInt("instructorID"));
                i.setSurname(rs.getString("surname"));
                i.setMiddlename(rs.getString("middlename"));
                i.setGivenname(rs.getString("givenname"));
                i.setEmail(rs.getString("email"));
                i.setCampus(rs.getString("campus"));
                i.setGender(rs.getBoolean("gender"));
                i.setPhoneNumber(rs.getString("phoneNumber"));
                i.setNationality(rs.getString("nationality"));
                i.setAddress(rs.getString("address"));
                i.setDob(rs.getDate("dob"));
                Department d = new Department();
                d.setDepartmentName(rs.getString("departmentName"));
                i.setDepartment(d);

                return i;
            }

        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Instructor getTimeTable(int id, Date from, Date to) {
        Instructor instructor = null;
        Session ses = new Session();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT i.instructorID,ses.sessionID, ses.[date] ,ses.taken, g.groupID, g.groupName, \n"
                    + "c.courseID, c.code, r.roomID, r.roomName,t.slotID, t.slotNum,t.startTime, t.endTime, c.courseName,g.edunextURL, g.meetURL \n"
                    + "FROM instructor i LEFT JOIN [session]  ses ON i.instructorID = ses.instructorID\n"
                    + "LEFT JOIN [group] g ON g.groupID = ses.groupID\n"
                    + "LEFT JOIN [course] c ON g.courseID = c.courseID\n"
                    + "LEFT JOIN [timeSlot] t ON t.slotID = ses.slotID\n"
                    + "LEFT JOIN [room] r ON r.roomID = ses.roomID\n"
                    + "WHERE i.instructorID =? AND ses.[date] between ? AND ?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();

            while (rs.next()) {
                if (instructor == null) {
                    instructor = new Instructor();
                    instructor.setId(rs.getInt("instructorID"));

                }

                ses = new Session();
                ses.setSessionID(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                ses.setTaken(rs.getBoolean("taken"));
                Group g = new Group();
                g.setGroupID(rs.getInt("groupID"));

                g.setGroupName(rs.getString("groupName"));
                g.setEduNextURL(rs.getString("edunextURL"));
                g.setMeetURL(rs.getString("meetURL"));
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseCode(rs.getString("code"));
                c.setCourseName(rs.getString("courseName"));
                g.setCourse(c);
                ses.setGroup(g);
                Room r = new Room();
                r.setRoomID(rs.getInt("roomID"));
                r.setRoomName(rs.getString("roomName"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setSlotID(rs.getInt("slotID"));
                t.setSlotNum(rs.getInt("slotNum"));
                t.setStartTime(rs.getTime("startTime"));
                t.setEndTime(rs.getTime("endTime"));
                ses.setSlot(t);
                instructor.getSessions().add(ses);

            }

           

        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instructor;
    }

}
