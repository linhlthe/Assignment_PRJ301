package controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Instructor;
import model.Student;
import model.User;

/**
 *
 * @author DELL
 */
public abstract class BaseRequireAuthenticationController extends HttpServlet {

    private boolean isAuthenticatedStudent(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int role = user.getRole();
        if (user != null && role == 0) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isAuthenticatedInstructor(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int role = user.getRole();
        if (user != null && role == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticatedStudent(req)) {
            //do business
            doPost(req, resp, (User) req.getSession().getAttribute("user"));
        } else {
            resp.getWriter().println("access denied!");
        }
        if (isAuthenticatedInstructor(req)) {
            //do business
            doPost(req, resp, (User) req.getSession().getAttribute("user"));
        } else {
            resp.getWriter().println("access denied!");
        }
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticatedStudent(req)) {
            //do business
            doGet(req, resp, (User) req.getSession().getAttribute("user"));
        } else {
            resp.getWriter().println("access denied!");
        }
        if (isAuthenticatedInstructor(req)) {
            //do business
            doGet(req, resp, (User) req.getSession().getAttribute("user"));
        } else {
            resp.getWriter().println("access denied!");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;
  

}
