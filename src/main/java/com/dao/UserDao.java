package com.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.User;

public class UserDao {
	private static final String URL = "jdbc:mysql://localhost:3306/crud_app";
    private static final String USER = "root";
    private static final String PASSWORD = "Miraj@123";
    
    public UserDao()  {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection ()  throws SQLException{
    	return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public User saveUser(User user) throws SQLException{
    	String sql = "INSERT INTO users (userId, name ,address, email,password) VALUES (?, ?, ?, ?, ?)";   
    	
    	try {
    		Connection con = getConnection();
    		PreparedStatement st = con.prepareStatement(sql);
    		
    		st.setString(1, user.getUserId());
    		st.setString(2, user.getName());
    		st.setString(3, user.getAddress());
    		st.setString(4, user.getEmail());
    		st.setString(5, user.getPassword());
    		
    		st.executeUpdate();
    		return user;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    }
    
    public User checkUser(String userId , String pass) {
    	String sql = "SELECT * FROM users WHERE userId=? AND password=?";
        User user = null;
        String userID = null;
        String name = null;
        String address = null;
        String email = null;
        
        try {
        	Connection con = getConnection(); 
        	PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, userId);
            st.setString(2, pass);
            
            
         
            
            
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
            	userID =  rs.getString("userId");
            	name =  rs.getString("name");
            	address =  rs.getString("address");
            	email =  rs.getString("email");
            	
            	user =  new User(userID, name, address, email);
            }
            
            
        }catch (Exception e) {
			e.printStackTrace();
		}
		return user;
    	
    }
    
    public List<User> getUsers() throws SQLException{
    	String sql = "SELECT * FROM users";
    	List<User> users = new ArrayList<>();
    	try {
    		Connection con = getConnection();
    		PreparedStatement st = con.prepareStatement(sql);
    		ResultSet rs = st.executeQuery();
    		
    		
    		
    		 while (rs.next()) {
                 String userId = rs.getString("userId");
                 String name = rs.getString("name");
                 String address = rs.getString("address");
                 String email = rs.getString("email");
                 String password = rs.getString("password");

                 User user = new User(userId, name, address, email, password);
                 users.add(user);
             }
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return users;
    }
    
    public boolean deleteUserById(String userId) {
    	String sql = "DELETE FROM users WHERE userId = ?;";
    	
    	try {
    		Connection con = getConnection();
    		PreparedStatement st = con.prepareStatement(sql);
    		st.setString(1, userId);
    		int rawAffacted = st.executeUpdate();
    		return rawAffacted > 0;
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}    	
    }
    
    public User getUserById(String userId) {
    	String sql = "SELECT * FROM users WHERE userId = ?";
    	User user = null;
    	
    	try {
    		Connection con = getConnection();
    		PreparedStatement st = con.prepareStatement(sql);
    		st.setString(1, userId);
    		ResultSet rs = st.executeQuery();
    		while(rs.next()) {
    			new User(rs.getString("userId"), rs.getString("userId"), rs.getString("userId"), rs.getString("userId"));
    		}
    		return user;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}    	
    }
    
    public void updateUser(String userId, String name, String address, String email) throws Exception {
        // Example SQL for updating user information
        String sql = "UPDATE users SET name = ?, address = ?, email = ? WHERE userId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setString(4, userId);
            stmt.executeUpdate();
        }
        catch(Exception e) {
    		e.printStackTrace();
    	}
	}
    
}