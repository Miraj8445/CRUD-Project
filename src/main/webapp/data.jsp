<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.models.User"%>
<%
    List<User> users = (ArrayList<User>) request.getAttribute("users");
User user2 = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Data File</title>
    <link href="CSS/index.css" rel="stylesheet" type="text/css">
    
</head>
<body>
    <h1>Data is Here <%= user2.getName() %></h1>
    <h1>
        Number of Users: <%= (users != null) ? users.size() : "No users found" %>
    </h1>
    
     <div class="data">
      <table>
        <tr id="h">
          <th>User ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>Email</th>
          <th>Update</th>
          <th>Delete</th>
        </tr>
       
        <% if (users != null) { %>
            <% for (User user : users) { %>
             <tr>
                <td><%= user.getUserId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getAddress() %></td>
                <td><%= user.getEmail() %></td>
                <td><a href="UpdateServlett?userId=<%= user.getUserId() %>">UPDATE</a></td>
                <td> <button onclick="deleteUser(<%= user.getUserId() %>);">DELETE</button></td>
              </tr>
            <% } %>
        <% } else { %>
            <li>No users to display.</li>
        <% } %>
      </table>
    </div>
    <script>
    async function deleteUser(userId) {
        try {
            const response = await fetch('DeleteServlet?userId=' + encodeURIComponent(userId), {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('User deleted successfully.');
                location.reload();  // Refresh the page to reflect the deletion
            } else {
                const message = await response.text();
                alert('Failed to delete user: ' + message);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred while deleting the user.');
        }
    }


    </script>
</body>
</html>