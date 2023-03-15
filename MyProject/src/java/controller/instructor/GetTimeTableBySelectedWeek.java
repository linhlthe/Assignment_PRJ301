/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import dal.InstructorDBContext;
import dal.StudentDBContext;
import datetime.DateTimeHelper;
import datetime.Week;
import datetime.Year_Cal;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
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
public class GetTimeTableBySelectedWeek extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        Date selectedweek =  java.sql.Date.valueOf(request.getParameter("fromTo")); 
        LocalDate localDate1 = selectedweek.toLocalDate();
        int selectedyear=localDate1.getYear();
        User s = (User) request.getSession().getAttribute("user");
        ArrayList<Integer> year = new ArrayList<>();
        LocalDateTime localDate = LocalDateTime.now();
        request.setAttribute("now", localDate);
        int y1 = localDate.getYear();
        year.add(y1 - 2);
        year.add(y1 - 1);
        year.add(y1);
        year.add(y1 + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        Year_Cal x = new Year_Cal();
        ArrayList<Week> weeks = new ArrayList<>();
        weeks = x.list(Year.of(selectedyear));
        Week w = new Week();
        w = w.getWeek(selectedweek);
        ArrayList<Date> dates = DateTimeHelper.getListDate(w.getStart(), w.getEnd());
        InstructorDBContext insDB = new InstructorDBContext();
        Instructor instructor = insDB.getTimeTable(s.getId(), w.getStart(), w.getEnd());

        ArrayList<Session> sessions = new ArrayList<>();
        if (instructor != null) {

            sessions = instructor.getSessions();
        }

        PrintWriter out = response.getWriter();
        out.println("<table border=\"1px\">\n"
                + "                <tr style=\"background-color:#00bebc\">\n"
                + "                    <td rowspan='2'><font color=\"white\">\n"
                + "                        <form action=\"timetable\" method=\"POST\"> \n"
                + "                            <div class=\"enter\">\n"
                + "                                <div id=\"selectedyear\"><!-- comment -->\n"
                + "                                    YEAR <select name=\"year\" onchange=\"showWeek(this.value)\" > \n");
        for (int y : year) {
            out.println("                                            <option value=\"" + y + "\"");
            if (y == selectedyear) {
                out.println("selected=\"selected\" ");
            }
            out.println(">" + y + "</option> \n");
        }
        out.println("                                    </select>\n"
                + "                                </div>\n"
                + "                                <div id=\"selectedweek\">\n"
                + "                                    From-To    <select name=\"fromTo\" onchange=\"showtimetable(this.value)\"> \n");
        for (Week k : weeks) {
            out.println(" <option value = \"" + k.getStart() + "\"");
            if (k.getStart().compareTo(selectedweek)==0) {
                out.println("selected=\"selected\" ");
            }
            out.println(">" + sdf.format(k.getStart()) + "-" + sdf.format(k.getEnd()) + "</option> \n");
        }
        out.println("                </select>"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </form><!-- comment -->\n"
                + "                    </td>\n"
                + "                    <td>MON</td>\n"
                + "                    <td>TUE</td>\n"
                + "                    <td>WED</td>\n"
                + "                    <td>THU</td>\n"
                + "                    <td>FRI</td>\n"
                + "                    <td>SAT</td>\n"
                + "                    <td>SUN</td>\n"
                + "                </tr>\n"
                + "                <tr style=\"background-color:#00bebc\">\n");
        for (Date d : dates) {
            out.println(" <td>" + sdf.format(d) + "</td>\n");
        }
        out.println("                </tr>\n");
        for (int i = 1; i <= 8; i++) {
            out.println("                        <tr>\n"
                    + "<td> Slot " + i + "</td>\n");
            for (Date d : dates) {
                out.println("<td>\n");
                
                    for (Session ses : sessions) {
                       
                        if (ses.getDate().compareTo(d) == 0 && ses.getSlot().getSlotNum() == i) {
                            out.println(ses.getGroup().getGroupName() + " - " + ses.getGroup().getCourse().getCourseCode() + "<br/>\n"
                                    + " at " + ses.getRoom().getRoomName() + "<br/>\n"
                                    + ses.getSlot().getStartTime() + " - " + ses.getSlot().getEndTime() + " <br/>\n");
                            if (ses.isTaken()) {
                                out.println("<font color=\"green\">attended</font>   \n");
                            } else {
                                out.println("<a href=\"checkAttendance?id="+ses.getSessionID()+"\"/>Take attendance");
                            }

                        }
                    }

                
                out.println("</td>");
            }
            out.println("</tr>");
        }
        

        out.println("            </table>");


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
