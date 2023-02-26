/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package GenModel;

import dal.StudentDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import model.User;
import myLibs.Rand;
import myLibs.VNCharacterUtils;

/**
 *
 * @author DELL
 */
public class Create extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenRanUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenRanUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
      
        request.getRequestDispatcher("view/create.jsp").forward(request, response);
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
      
        int num = Integer.parseInt(request.getParameter("num"));
        UserDBContext db = new UserDBContext();
        for (int i = 0; i < num; i++) {
            User s = new User();
            Rand r = new Rand();
            //String studentCode = (r.genStudentCode());
            s.setSurname("Ngô");
            s.setMiddlename("Tùng");
            s.setGivenname("Sơn");
            //String username = VNCharacterUtils.removeAccent(s.getGivenname().toLowerCase() + s.getSurname().toLowerCase().charAt(0) + s.getMiddlename().toLowerCase().charAt(0) + r.getRandomIntInRange(0, 20));
            String username = "sonnt5";
            String email = username + "@fpt.edu.vn";
            String password = username;
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            }
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            s.setPassword(myHash);
            s.setUsername(username);
            s.setEmail(email);
            s.setAddress(r.place());
            s.setPhoneNumber(r.phone());
            s.setNationality(r.nationality());
            s.setGender(r.getRandomIntInRange(0, 1) != 0);
            s.setRole(1);
            db.insert(s);
        }
        response.getWriter().print("jdfn");
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
