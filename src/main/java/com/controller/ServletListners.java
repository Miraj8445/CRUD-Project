package com.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;




@WebListener("/servletListner")
public class ServletListners implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("started");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("ended:");
	}
}