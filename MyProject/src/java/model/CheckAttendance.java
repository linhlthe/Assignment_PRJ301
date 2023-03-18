/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author DELL
 */
public class CheckAttendance {
    private int aid;
    private Student student;
    private Session session;
    private boolean status;
    private String comment;
    private Instructor taker;
    private String recordedTime;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
    

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(String recordedTime) {
        this.recordedTime = recordedTime;
    }

    

    public Instructor getTaker() {
        return taker;
    }

    public void setTaker(Instructor taker) {
        this.taker = taker;
    }
    

    
    
}
