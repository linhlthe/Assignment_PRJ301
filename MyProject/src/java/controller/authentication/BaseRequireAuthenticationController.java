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

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthenticated(request)) {
            //do business
            doPost(request, response, (User) request.getSession().getAttribute("user"));
        } else {
            String inform = "Vui lòng đăng nhập trước!";
            request.setAttribute("error", inform);
            response.sendRedirect("../login");
        }
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthenticated(request)) {
            //do business
            doGet(request, response, (User) request.getSession().getAttribute("user"));
        } else {
            String inform = "Vui lòng đăng nhập trước!";
            request.setAttribute("error", inform);
            response.sendRedirect("../login");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

}
