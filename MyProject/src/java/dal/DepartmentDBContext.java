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

/**
 *
 * @author DELL
 */
public class DepartmentDBContext extends DBContext<Department> {
     public ArrayList<Department> all() {
        ArrayList<Department> depts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT departmentID, departmentName FROM department";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next())
            {
                Department d = new Department();
                d.setDepartmentID(rs.getInt("departmentID"));
                d.setDepartmentName(rs.getString("departmentName"));
                depts.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return depts;
    }
     
     
    
}
