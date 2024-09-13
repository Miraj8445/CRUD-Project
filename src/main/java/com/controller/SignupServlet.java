package com.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.models.User;


@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao;


    public SignupServlet() {
        super();
        dao = new UserDao();
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User(userId, name, address, email, password);
		try {
			dao.saveUser(user);
//			HttpSession session = req.getSession();
//			session.setAttribute("user", user);
			req.getRequestDispatcher("login.html").forward(req, res);

		}catch(SQLException e) {
			e.printStackTrace();
            RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req, res);
			
		}
		
	}

	
}