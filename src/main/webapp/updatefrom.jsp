<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Data Update Page</title>
</head>
<body>
	<form action="UpdateServlet" method="post">
	<h2>fill all fields if you don't want to change just put previous data</h2>
    	User ID: <input type="text" name="userId" value="<%= request.getParameter("userId") %>" readonly><br>
    	Name: <input type="text" name="name" placeholder="enter new value"><br>
    	Address: <input type="text" name="address" placeholder="enter new value"><br>
    	Email: <input type="text" name="email" placeholder="enter new value"><br>
    	<input type="submit" value="Submit"> 
	</form>
</body>
</html>