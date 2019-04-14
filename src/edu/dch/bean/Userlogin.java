package edu.dch.bean;

import org.springframework.stereotype.Component;

@Component("Userlogin")
public class Userlogin {
	String username;
	String userpassword;
	public Userlogin(String username, String userpassword) {
		super();
		this.username = username;
		this.userpassword = userpassword;
	}
	public Userlogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	@Override
	public String toString() {
		return "Userlogin [username=" + username + ", userpassword=" + userpassword + "]";
	}
	
}
