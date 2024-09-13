package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dao.UserDao;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao;

    public DeleteServlet() {
        super();
        dao = new UserDao();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String userId = request.getParameter("userId");
        
        if (userId != null && !userId.isEmpty()) {
            try {
                boolean success = dao.deleteUserById(userId);
                
                if (success) {
                    response.setStatus(HttpServletResponse.SC_OK);  // 200 OK
                    response.getWriter().write("User deleted successfully.");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404 Not Found
                    response.getWriter().write("User not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();  // Log the exception
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
                response.getWriter().write("An error occurred while deleting the user.");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400 Bad Request
            response.getWriter().write("Invalid user ID.");
        }
    }
}
