package com.controller;

import java.io.IOException;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.models.User;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao dao;

    public LoginServlet() {
        super();
        dao = new UserDao();
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String pass = request.getParameter("password");
		
		User user = dao.checkUser(userId, pass);
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("profile.jsp").forward(request, response);

		}else {
			RequestDispatcher rd  = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		
	}

}