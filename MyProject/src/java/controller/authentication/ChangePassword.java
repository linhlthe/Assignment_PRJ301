/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import model.User;

/**
 *
 * @author DELL
 */
public class ChangePassword extends BaseRequireAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        User s = (User) request.getSession().getAttribute("user");
        String old_password = request.getParameter("oldpassword");
        String new_password = request.getParameter("newpassword");
        String re_enter_campus = request.getParameter("repassword");

        UserDBContext db = new UserDBContext();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(old_password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        User u = db.get(s.getUsername(), myHash, s.getCampus());
        if (u == null) {
            String inform = "Wrong Password! Try Again!";
            request.setAttribute("error", inform);
            request.getRequestDispatcher("view/authentication/changePass.jsp").forward(request, response);
        } else {
            if (!validatePass(new_password)) {
                String inform = "The new password is not valid! Try again!";
                request.setAttribute("error", inform);
                request.getRequestDispatcher("view/authentication/changePass.jsp").forward(request, response);
            } else {
                if (!new_password.equalsIgnoreCase(re_enter_campus)) {
                    String inform = "The re-enter password is not correct! Try again!";
                    request.setAttribute("error", inform);
                    request.getRequestDispatcher("view/authentication/changePass.jsp").forward(request, response);
                } else {
                    md.update(new_password.getBytes());
                    byte[] dig = md.digest();
                    String newPass = DatatypeConverter.printHexBinary(dig).toUpperCase();
                    UserDBContext userDB = new UserDBContext();
                    userDB.update(s, newPass);
                    String inform = "Change password successful";
                    request.setAttribute("error", inform);
                    request.getRequestDispatcher("view/authentication/changePass.jsp").forward(request, response);

                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
       request.getRequestDispatcher("view/authentication/changePass.jsp").forward(request, response);
    }

    public boolean validatePass(String raw_pass) {
        if ((raw_pass.length() >= 8) && (includeDigit(raw_pass)) && (includeLowerLetter(raw_pass)) && (includeUpperLetter(raw_pass)) && (includeSpecialCharacter(raw_pass))) {
            return true;
        }
        return false;
    }

    public boolean includeUpperLetter(String raw_pass) {
        for (int i = 0; i < raw_pass.length(); i++) {

            char c = raw_pass.charAt(i);
            if (((c >= 65) && (c <= 90))) {
                return true;
            }

        }
        return false;
    }

    public boolean includeLowerLetter(String raw_pass) {
        for (int i = 0; i < raw_pass.length(); i++) {

            char c = raw_pass.charAt(i);
            if ((((c >= 97) && (c <= 122)))) {
                return true;
            }

        }
        return false;
    }

    public boolean includeDigit(String raw_pass) {
        for (int i = 0; i < raw_pass.length(); i++) {

            char c = raw_pass.charAt(i);

            if ((c >= 48) && (c <= 57)) {
                return true;
            }
        }
        return false;
    }

    public boolean includeSpecialCharacter(String raw_pass) {

        String ss = "{`,!@#$%^&*()_-+={[}]|:;\"'<>.?/}";
        for (int i = 0; i < raw_pass.length(); i++) {
            char c = raw_pass.charAt(i);
            for (int x = 0; x < ss.length(); x++) {
                char ch = ss.charAt(x);
                if (c == ch) {
                    return true;
                }
            }
        }
        return false;
    }

}
