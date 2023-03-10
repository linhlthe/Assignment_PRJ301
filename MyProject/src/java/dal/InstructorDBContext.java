/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String sql = "select u.surname, u.middlename, u.givenname, u.gender, u.campus,\n"
                + "u.email, d.departmentName,u.[address],u.dob,u.nationality, u.phoneNumber\n"
                + "from instructor i inner join [user] u on u.id=i.instructorID\n"
                + "inner join department d on d.departmentID=i.departmentID\n"
                + "where u.id=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Instructor i = new Instructor();
                i.setId(rs.getInt("id"));
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
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT i.instructorID,ses.sessionID, ses.[date] ,ses.taken, g.groupID, g.groupName, \n"
                    + "c.courseID, c.code, r.roomID, r.roomName, t.slotNum,t.startTime, t.endTime	\n"
                    + "FROM instructor i INNER JOIN [session]  ses ON i.instructorID = ses.instructorID\n"
                    + "INNER JOIN [group] g ON g.groupID = ses.groupID\n"
                    + "INNER JOIN [course] c ON g.courseID = c.courseID\n"
                    + "INNER JOIN [timeSlot] t ON t.slotID = ses.slotID\n"
                    + "INNER JOIN [room] r ON r.roomID = ses.roomID\n"
                    + "WHERE i.instructorID =? AND ses.[date] between ? AND ?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            Session ses = new Session();
            ses.setSessionID(-1);
            while (rs.next()) {
                if (instructor == null) {
                    instructor = new Instructor();
                    instructor.setId(rs.getInt("instructorID"));

                }
            int sesid = rs.getInt("sessionid");
            if (sesid != ses.getSessionID()) {
                ses = new Session();
                ses.setSessionID(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                ses.setTaken(rs.getBoolean("taken"));
                Group g = new Group();
                g.setGroupID(rs.getInt("groupID"));

                g.setGroupName(rs.getString("groupName"));
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseCode(rs.getString("code"));
                g.setCourse(c);
                ses.setGroup(g);
                Room r = new Room();
                r.setRoomiD(rs.getInt("rommID"));
                r.setRoomName(rs.getString("roomName"));
                ses.setRoom(r);

                Calendar cal = Calendar.getInstance();
                TimeSlot t = new TimeSlot();
                t.setSlotID(rs.getInt("slotID"));
                t.setSlotNum(rs.getInt(rs.getInt("slotNum")));
                t.setStartTime(rs.getTime("start", cal));
                t.setEndTime(rs.getTime("end", cal));
                ses.setSlot(t);

                
            }
            instructor.getSessions().add(ses);
            
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
        return instructor;
    }

}
