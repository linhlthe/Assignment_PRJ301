/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.CheckAttendanceDBContext;
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
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import model.CheckAttendance;
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
public class GetListWeekOfYear extends HttpServlet {

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
        int selectedyear = Integer.parseInt(request.getParameter("year"));
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
        w = weeks.get(0);
        ArrayList<Date> dates = DateTimeHelper.getListDate(w.getStart(), w.getEnd());
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.getTimeTable(s.getId(), w.getStart(), w.getEnd());
        CheckAttendanceDBContext db = new CheckAttendanceDBContext();
        ArrayList<CheckAttendance> atts = db.getAttendancesOfStudentByDays(s.getId(), w.getStart(), w.getEnd());
        

        ArrayList<Group> groups = new ArrayList<>();
        if(student!=null){

        groups = student.getGroups();
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
            if (k == w) {
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
                for (Group g : groups) {
                    ArrayList<Session> sessions = new ArrayList<>();
                    sessions = g.getSessions();
                    for (Session ses : sessions) {
                        TimeSlot t = new TimeSlot();
                        t = ses.getSlot();
                        int slotNum = t.getSlotNum();
                        Course c = new Course();
                        c = g.getCourse();
                        String code = c.getCourseCode();
                        Instructor ins = new Instructor();
                        ins = ses.getInstructor();
                        String iname = ins.getUsername();
                        Room r = new Room();
                        r = ses.getRoom();
                        String rname = r.getRoomName();

                        if (ses.getDate().compareTo(d) == 0 && slotNum == i) {
                            out.println(g.getGroupName() + " - " + code + "<br/>\n"
                                    + iname + " at " + rname + "<br/>\n"
                                    + t.getStartTime() + " - " + t.getEndTime() + " <br/>\n");
                            if (ses.isTaken()) {
                                for (CheckAttendance att: atts){
                                    if (att.getSession().getSessionID()==ses.getSessionID()){
                                        if (att.isStatus()){
                                            out.println("<font color=\"green\">attended</font>   \n");
                                        }
                                        else {
                                            out.println("<font color=\"red\">absent</font>   \n");
                                        }
                                    }
                                }
                                
                            } else {
                                out.println("<font color=\"red\">Not yet</font>   \n");
                            }

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
