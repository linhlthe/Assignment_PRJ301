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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CheckAttendance;
import model.Student;
import java.sql.Timestamp;
import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author DELL
 */
public class CheckAttendanceDBContext extends DBContext<CheckAttendance> {

    public ArrayList<CheckAttendance> getAttendancesBySession(int sessionid) {
        String sql = "SELECT a.aid, u.id,s.studentCode, u.surname, u.middlename, u.givenname, s.imageURL, s.studentCode,a.[status], a.comment,a.recordedTime,a.sessionID\n"
                + "FROM [user] u inner join student s on u.id=s.studentID\n"
                + "LEFT JOIN studentJoin sj ON sj.studentID = s.studentID\n"
                + "LEFT JOIN [Group] g ON g.groupID = sj.groupID\n"
                + "LEFT JOIN [Session] ses ON ses.groupID = g.groupID\n"
                + "LEFT JOIN [checkAttendance] a ON ses.sessionID = a.sessionID AND s.studentID = a.studentID\n"
                + "WHERE ses.sessionID = ?";
        ArrayList<CheckAttendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            rs = stm.executeQuery();
            while (rs.next()) {
                CheckAttendance a = new CheckAttendance();
                a.setAid(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setComment(rs.getString("comment"));
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setStudentCode(rs.getString("studentCode"));
                s.setSurname(rs.getString("surname"));
                s.setMiddlename(rs.getString("middlename"));
                s.setGivenname(rs.getString("givenname"));
                s.setImage(rs.getString("imageURL"));
                a.setRecordedTime(rs.getTimestamp("recordedTime"));
                a.setStudent(s);
                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;

    }

    public void update(ArrayList<CheckAttendance> atts, int sessionid) {
        try {
            connection.setAutoCommit(false);
            String sql_update_session = "UPDATE [Session]\n"
                    + "   SET [taken] = 1\n"
                    + " WHERE [sessionID] = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            for (CheckAttendance a : atts) {
                if (a.getAid() == 0) {
                    //INSERT
                    String sql_insert_att = "Insert into checkAttendance(studentID,sessionID,[status],comment,recordedTime)\n"
                            + "VALUES (?,?,?,?,?)";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setInt(1, a.getStudent().getId());
                    stm_insert_att.setInt(2, a.getSession().getSessionID());
                    stm_insert_att.setBoolean(3, a.isStatus());
                    stm_insert_att.setString(4, a.getComment());
                    stm_insert_att.setTimestamp(5, a.getRecordedTime());
                    stm_insert_att.executeUpdate();
                } else {
                    //UPDATE
                    String sql_update_att = "Update checkAttendance\n"
                            + "set [status]=?, comment=?,recordedTime=?\n"
                            + "where aid=?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, a.isStatus());
                    stm_update_att.setString(2, a.getComment());
                    stm_update_att.setTimestamp(3, a.getRecordedTime());
                    stm_update_att.setInt(4, a.getAid());
                    stm_update_att.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(dal.StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(dal.CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<CheckAttendance> getAttendancesOfStudentInGroup(int studentID, int groupID) {
        String sql = "SELECT a.aid, a.[status], a.sessionID, ses.[date], ses.groupID,ses.slotID, i.instructorID, u.username,\n"
                + "ses.roomID, t.startTime, t.endTime, r.roomName,a.comment \n"
                + "FROM student  s LEFT JOIN studentJoin sj ON sj.studentID = s.studentID\n"
                + "LEFT JOIN [Group] g ON g.groupID = sj.groupID\n"
                + "LEFT JOIN [Session] ses ON ses.groupID = g.groupID\n"
                + "LEFT JOIN [instructor] i ON i.instructorID = ses.instructorID\n"
                + "LEFT JOIN [user] u ON i.instructorID = u.id\n"
                + "LEFT JOIN timeSlot t ON ses.slotID=t.slotID\n"
                + "LEFT JOIN room r ON r.roomID = ses.roomID\n"
                + "LEFT JOIN [checkAttendance] a ON ses.sessionID = a.sessionID AND s.studentID = a.studentID\n"
                + "WHERE s.studentID =? AND ses.groupID=?";
        ArrayList<CheckAttendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentID);
            stm.setInt(2, groupID);
            rs = stm.executeQuery();
            while (rs.next()) {
                CheckAttendance a = new CheckAttendance();
                a.setAid(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setComment(rs.getString("comment"));
                a.setRecordedTime(rs.getTimestamp("recordedTime"));
                Session ses = new Session();
                ses.setSessionID(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                TimeSlot t = new TimeSlot();
                t.setSlotID(rs.getInt("slotID"));
                t.setStartTime(rs.getTime("startTime"));
                t.setEndTime(rs.getTime("endTime"));
                ses.setSlot(t);
                Room r = new Room();
                Instructor i = new Instructor();
                i.setId(rs.getInt("id"));
                i.setUsername(rs.getString("username"));
                ses.setInstructor(i);
                r.setRoomID(rs.getInt("roomID"));
                r.setRoomName(rs.getString("roonName"));
                ses.setRoom(r);
                a.setSession(ses);
                

                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;

    }

    public ArrayList<CheckAttendance> getAttendancesOfStudentByDays(int studentID, Date from, Date to) {
        String sql = "SELECT a.aid, a.[status],a.comment, a.sessionID, ses.[date]\n"
                + "FROM student  s\n"
                + "LEFT JOIN studentJoin sj ON sj.studentID = s.studentID\n"
                + "LEFT JOIN [Group] g ON g.groupID = sj.groupID\n"
                + "LEFT JOIN [Session] ses ON ses.groupID = g.groupID\n"
                + "LEFT JOIN [checkAttendance] a ON ses.sessionID = a.sessionID AND s.studentID = a.studentID\n"
                + "WHERE s.studentID =? AND ses.[date] BETWEEN ? AND ?";
        ArrayList<CheckAttendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentID);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                CheckAttendance a = new CheckAttendance();
                a.setAid(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setComment(rs.getString("comment"));

                Session ses = new Session();
                ses.setSessionID(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                a.setSession(ses);

                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CheckAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;

    }

}
