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
import model.User;

/**
 *
 * @author DELL
 */
public class UserDBContext extends DBContext<User> {

    /*public void insert(User model) {

        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO [user](username, [password], [role], [address],phoneNumber,email,gender, surname,middlename,givenname,nationality, campus) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getUsername());
            stm.setString(2, model.getPassword());
            stm.setInt(3, model.getRole());
            stm.setString(4, model.getAddress());
            stm.setString(5, model.getPhoneNumber());
            stm.setString(6, model.getEmail());
            stm.setBoolean(7, model.isGender());
            stm.setString(8, model.getSurname());
            stm.setString(9, model.getMiddlename());
            stm.setString(10, model.getGivenname());
            stm.setString(11, model.getNationality());
            stm.setString(12, model.getCampus());
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
    public User get(String username, String password, String campus) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [username],[password], [role], campus, id FROM [User] WHERE [username] = ?  AND campus=?";
        try {
            stm = connection.prepareStatement(sql);

            stm.setString(1, username);
           // stm.setString(2, password);
            stm.setString(2, campus);
            rs = stm.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setRole(rs.getInt("role"));
                s.setCampus(rs.getString("campus"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }
    public static void main(String[] args) {
        UserDBContext uDB= new UserDBContext();
        User u= new User ();
        u=uDB.get("linhlthe171217","dv", "HL");
        System.out.println(u.getUsername());
    }
}
