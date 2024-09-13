<%@page import="com.models.User"%>
<%@page import="com.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>profile</title>
<link href="CSS/profile.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
		<h1>Name: <%= user.getName() %></h1>
		<h3>Contact Here: <%= user.getEmail() %></h3>
		<h3>Address:  <%= user.getAddress() %></h3>
		<h3><a href="LogoutServlet">Logout</a></h3>
	</div>
	
	<div>
		<p><a href="ReadServlet">Data</a></p>
	</div>
</body>
</html>