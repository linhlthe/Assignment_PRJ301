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

    public ArrayList<Group> all(Course c, Semester s) {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "select g.groupID, g.groupName from [group] g\n"
                + "right join course c on g.courseID=c.courseID\n"
                + "right join semester s on s.termID = g.termID\n"
                + "where g.termID=? AND g.courseID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getTermID());
            stm.setInt(2, c.getCourseID());
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

}
