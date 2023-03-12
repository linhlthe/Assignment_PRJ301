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
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;

/**
 *
 * @author DELL
 */
public class StudentDBContext extends UserDBContext {

    public ArrayList<Student> all(Group g) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select s.studentID,s.studentCode,u.surname, u.middlename, u.givenname , s.imageURL, g.groupID\n"
                + "                    FROM [user] u INNER JOIN student s ON u.id=s.studentID\n"
                + "					inner join [studentJoin] sj on sj.studentID=s.studentID\n"
                + "inner join [group] g on g.groupID=sj.groupID where g.groupID =?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, g.getGroupID());
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("studentID"));
                s.setStudentCode(rs.getString("studentCode"));
                s.setSurname(rs.getString("surname"));
                s.setMiddlename(rs.getString("middlename"));
                s.setGivenname(rs.getString("givenname"));
                s.setImage(rs.getString("imageURL"));
                students.add(s);
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
        return students;

    }

    public Student get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select s.studentID, s.studentCode,u.username,u.email,u.surname,u.middlename,u.givenname,u.gender,u.dob,u.nationality,u.campus,u.[address],u.phoneNumber, s.idCard,s.placeOfBirth,s.major,s.curriculum,s.peoples,s.imageURL,s.fatherName,s.fatherJob,s.fatherPhoneNumber,s.motherName,s.motherJob,s.motherPhoneNumber from [user] u, student s where u.id=s.studentID AND s.studentCode=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("studentID"));
                s.setStudentCode(rs.getString("studentCode"));
                s.setSurname(rs.getString("surname"));
                s.setMiddlename(rs.getString("middlename"));
                s.setGivenname(rs.getString("givenname"));
                s.setEmail(rs.getString("email"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setNationality("nationality");
                s.setCampus(rs.getString("campus"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getString("phoneNumber"));
                s.setIdCard("idCard");
                s.setCurriculum("curriculum");
                s.setPlaceOfBirth("placeOfBirth");
                s.setMajor("major");
                s.setPeoples("peoples");
                s.setImage("imageURL");
                s.setFatherName("fatherName");
                s.setFatherJob("fatherJob");
                s.setFatherPhone("fatherPhone");
                s.setMotherName("motherName");
                s.setMotherJob("motherJob");
                s.setMotherPhone("motherPhone");
                return s;
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
        return null;
    }

    public Student getTimeTable(int sid, Date from, Date to) {
        Student student = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.studentID,ses.sessionID, ses.[date] ,ses.taken, g.groupID, g.groupName, c.courseID, c.code, r.roomID, r.roomName, i.instructorID,t.slotID,  t.slotNum,t.startTime, t.endTime, u.username\n"
                    + "     FROM student s INNER JOIN [studentJoin]  sg ON s.studentID = sg.[studentID]\n"
                    + "                    INNER JOIN [group] g ON g.groupID = sg.groupID\n"
                    + "                    INNER JOIN [course] c ON g.courseID = c.courseID\n"
                    + "                    INNER JOIN [session] ses ON g.groupID = ses.groupID\n"
                    + "                    INNER JOIN [timeSlot] t ON t.slotID = ses.slotID\n"
                    + "                    INNER JOIN [room] r ON r.roomID = ses.roomID\n"
                    + "                    INNER JOIN [instructor] i ON i.instructorID = ses.instructorID\n"
                    + "		           INNER JOIN [user] u ON i.instructorID = u.id\n"
                    + "                    WHERE s.studentID =? AND ses.[date] between ? AND ?";
  
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            Group currentGroup = new Group();
            currentGroup.setGroupID(-1);
            while (rs.next()) {
                if (student == null) {
                    student = new Student();
                    student.setId(rs.getInt("studentID"));
                    
                }
                int gid = rs.getInt("groupID");
                if (gid != currentGroup.getGroupID()) {
                    currentGroup = new Group();
                    currentGroup.setGroupID(rs.getInt("groupID"));
                    currentGroup.setGroupName(rs.getString("groupName"));
                    Course c = new Course();
                    c.setCourseID(rs.getInt("courseID"));
                    c.setCourseCode(rs.getString("code"));
                    currentGroup.setCourse(c);
                    student.getGroups().add(currentGroup);
                }
                Session ses = new Session();
                ses.setSessionID(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                ses.setTaken(rs.getBoolean("taken"));
                ses.setGroup(currentGroup);

                Instructor i = new Instructor();
                i.setId(rs.getInt("instructorID"));
                i.setUsername(rs.getString("username"));
                ses.setInstructor(i);

                Room r = new Room();
                r.setRoomiD(rs.getInt("roomID"));
                r.setRoomName(rs.getString("roomName"));
                ses.setRoom(r);

                
                TimeSlot t = new TimeSlot();
                t.setSlotID(rs.getInt("slotID"));
                t.setSlotNum(rs.getInt(rs.getInt("slotNum")));
                t.setStartTime(rs.getTime("startTime"));
                t.setEndTime(rs.getTime("endTime"));
                ses.setSlot(t);

                currentGroup.getSessions().add(ses);

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
        return student;
    }
}
