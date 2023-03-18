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

import model.Semester;
import model.Student;

/**
 *
 * @author DELL
 */
public class SemesterDBContext extends DBContext<Semester> {

    public ArrayList<Semester> all() {
        ArrayList<Semester> term = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT termID, termName, startDate, endDate FROM semester";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Semester s = new Semester();
                s.setTermID(rs.getInt("termID"));
                s.setTermName(rs.getString("termName"));
                s.setStartDate(rs.getDate("startDate"));
                s.setEndDate(rs.getDate("endDate"));
                term.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return term;
    }

    public ArrayList<Semester> allOfStudent(int studentID) {
        ArrayList<Semester> term = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sql = "Select COUNT(sj.groupID) num,sj.studentID, s.termID , s.termName\n"
                + "from [group] g inner join semester s on s.termID=g.termID\n"
                + "inner join studentJoin sj on sj.groupID = g.groupID \n"
                + "where sj.studentID=?\n"
                + "group by s.termID, sj.studentID,s.termName";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Semester ses = new Semester();
                ses.setTermID(rs.getInt("termID"));
                ses.setTermName(rs.getString("termName"));
                term.add(ses);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return term;
    }
    public static void main(String[] args) {
        SemesterDBContext seDB= new SemesterDBContext();
        ArrayList<Semester> term =seDB.allOfStudent(17);
        for(Semester s: term){
            System.out.println(s.getTermName());
        }
        
    }
}
