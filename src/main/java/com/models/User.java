package com.models;

public class User {
	private String userId;
	private String name;
	private String address;
	private String email;
	private String password;
	
	public  User( String userId, String name, String address, String email, String password) {
		this.address = address;
		this.email = email;
		this.name = name;
		this.password = password;
		this.userId = userId;
	}
	
	public  User( String userId, String name, String address, String email) {
		this.address = address;
		this.email = email;
		this.name = name;
		this.userId = userId;
	}
	
	public  User( String userId, String password) {
		
		this.password = password;
		this.userId = userId;
	}
	
	public String getUserId() {
		
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}