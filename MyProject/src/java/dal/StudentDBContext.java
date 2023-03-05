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
import model.Group;
import model.Student;
import model.User;

/**
 *
 * @author DELL
 */
public class StudentDBContext extends UserDBContext{
    @Override
    public void insert(User model) {
        
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<Student> all(Group g){
         ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
      
            String sql = "select s.studentID,s.studentCode,u.surname, u.middlename, u.givenname , s.imageURL, j.groupID from [user] u, student s, studentJoin j\n" +
"where s.studentID=u.id AND s.studentID=j.studentID AND j.groupID=?";
            try{
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
    public Student get(String studentCode){
        PreparedStatement stm = null;
        ResultSet rs = null;
      
            String sql = "select s.studentID, s.studentCode,u.username,u.email,u.surname,u.middlename,u.givenname,u.gender,u.dob,u.nationality,u.campus,u.[address],u.phoneNumber, s.idCard,s.placeOfBirth,s.major,s.curriculum,s.peoples,s.imageURL,s.fatherName,s.fatherJob,s.fatherPhoneNumber,s.motherName,s.motherJob,s.motherPhoneNumber from [user] u, student s where u.id=s.studentID AND s.studentCode=?";
            try{
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentCode);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("studentID"));
                s.setStudentCode(rs.getString("studentCode"));
                s.setSurname(rs.getString("surname"));
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
    
}
