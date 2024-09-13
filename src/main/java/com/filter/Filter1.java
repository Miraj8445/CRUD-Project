package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.models.User;


public class Filter1 implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User(userId, name , address, email, password);
		
		if(isValidate(user)) {
//			RequestDispatcher rd = req.getRequestDispatcher("login.html");
//			rd.forward(req, res);
			chain.doFilter(req, res);

		}else {
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
//			PrintWriter out = res.getWriter();
//			out.print("Password length should minimum 10.");
			rd.include(req, res);
		}

	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	public Boolean isValidate(User user) {
		 if (user.getPassword() != null && user.getPassword().length() >= 8) {
			 return true;
		 }else {
			 return false;
		 }
	}
}