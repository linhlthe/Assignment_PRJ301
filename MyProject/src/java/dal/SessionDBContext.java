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
import model.Course;
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
public class SessionDBContext extends DBContext<Session> {

    public Session getSessionByID(int id) {

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ses.sessionID, ses.[date],ses.taken, g.groupID, g.groupName, c.courseID, c.code, r.roomID, r.roomName, i.instructorID,t.slotID,  t.slotNum,t.startTime, t.endTime, u.username\n"
                    + "     FROM [session] ses INNER JOIN [group] g ON g.groupID = ses.groupID\n"
                    + "                    INNER JOIN [course] c ON g.courseID = c.courseID\n"
                    + "                    INNER JOIN [timeSlot] t ON t.slotID = ses.slotID\n"
                    + "                    INNER JOIN [room] r ON r.roomID = ses.roomID\n"
                    + "                    INNER JOIN [instructor] i ON i.instructorID = ses.instructorID\n"
                    + "		           INNER JOIN [user] u ON i.instructorID = u.id\n"
                    + "                    WHERE ses.sessionID=?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();
            if (rs.next()) {
                Session ses = new Session();
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

                ses.setSessionID(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                ses.setTaken(rs.getBoolean("taken"));
                ses.setGroup(g);

                Instructor i = new Instructor();
                i.setId(rs.getInt("instructorID"));
                i.setUsername(rs.getString("username"));
                ses.setInstructor(i);

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
                return ses;

            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
    public ArrayList<Session> getAllSessionInGroup(int gid){
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select ses.[date], ses.sessionID from [session] ses where ses.[groupID] =?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s= new Session();
                s.setSessionID(rs.getInt("sessionID"));
                s.setDate(rs.getDate("date"));
                sessions.add(s);
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sessions;

    }
    
    
}
