/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TimeSlot;

/**
 *
 * @author DELL
 */
public class TimeSlotDBContext extends DBContext<TimeSlot> {
    public ArrayList<TimeSlot> all() {
        ArrayList<TimeSlot> timeSl = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select slotID, slotNum, startTime, endTime from timeSlot";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next())
            {
                TimeSlot t= new TimeSlot();
                Calendar cal = Calendar.getInstance();
                t.setSlotID(rs.getInt("slotID"));
                t.setSlotNum(rs.getInt("slotNum"));
                t.setStartTime(rs.getTime("startTime"));
                t.setEndTime(rs.getTime("endTime"));
               
                timeSl.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return timeSl;
    }
    
}
