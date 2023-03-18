/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Course {
    private int courseID;
    private String courseCode;
    private String courseName;
    private int noCredit;
    private String degreeLevel;
    private String timeAllocation;
    private String decription;
    private String studentTask;
    private String tool;
    private int scoringScale;
    private String decisionNo;
    private boolean isApproved;
    private String note;
    private int minAvgMarkToPass;
    private int numOfSlot;
    private Department department;
    private ArrayList<Course> preRequisite;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getNoCredit() {
        return noCredit;
    }

    public void setNoCredit(int noCredit) {
        this.noCredit = noCredit;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    public String getTimeAllocation() {
        return timeAllocation;
    }

    public void setTimeAllocation(String timeAllocation) {
        this.timeAllocation = timeAllocation;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(String studentTask) {
        this.studentTask = studentTask;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int getScoringScale() {
        return scoringScale;
    }

    public void setScoringScale(int scoringScale) {
        this.scoringScale = scoringScale;
    }

    public String getDecisionNo() {
        return decisionNo;
    }

    public void setDecisionNo(String decisionNo) {
        this.decisionNo = decisionNo;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMinAvgMarkToPass() {
        return minAvgMarkToPass;
    }

    public void setMinAvgMarkToPass(int minAvgMarkToPass) {
        this.minAvgMarkToPass = minAvgMarkToPass;
    }

    public int getNumOfSlot() {
        return numOfSlot;
    }

    public void setNumOfSlot(int numOfSlot) {
        this.numOfSlot = numOfSlot;
    }

    public ArrayList<Course> getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(ArrayList<Course> preRequisite) {
        this.preRequisite = preRequisite;
    }
    
    

}
