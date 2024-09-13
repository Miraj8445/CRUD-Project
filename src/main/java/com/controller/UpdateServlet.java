package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.models.User;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao;

    public UpdateServlet() {
        super();
        dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 HttpSession session = req.getSession(false); 
	        if (session == null || session.getAttribute("user") == null) {
	        	resp.sendRedirect("login.html");
	            return;
	        }
    	String userId = req.getParameter("userId");
    	
    	if(userId != null && !userId.isEmpty()) {
    		try {
    			User user = dao.getUserById(userId);
        		req.setAttribute("user", user);
        		RequestDispatcher rd = req.getRequestDispatcher("updateform.jsp");
        		rd.forward(req, resp);
    		}catch(Exception e) {
    			e.printStackTrace();
    			resp.getWriter().write("Failed to load user.");
    		}
    		
    		
    		
    	}
    	 else {
 			RequestDispatcher rd = req.getRequestDispatcher("index.html");
 			rd.forward(req, resp);
 		}
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        if (userId != null && name != null && address != null && email != null) {
            try {
                dao.updateUser(userId, name, address, email);
                RequestDispatcher rd = request.getRequestDispatcher("ReadServlet");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Failed to update user.");
            }
        } else {
            response.getWriter().write("Missing parameters.");
        }
    }

}

