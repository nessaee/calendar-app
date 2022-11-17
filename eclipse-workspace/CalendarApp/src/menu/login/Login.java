package menu.login;

import java.util.ArrayList;
import java.util.Scanner;

import db.setup.DB;

public class Login {
	private int userID;
	private String username;
	private String password;
	
	public Login() {
		this.userID = -1;
		this.username = "";
		this.password = "";
	}
	public Login(DB db, String command, String username, String password) {
		this.username = username;
		this.password = password;
		if(command.equals("Login")) {
			
			this.userID = db.checkUser(username, password);
		}
		else if(command.equals("Create")) {
			this.userID = createID(username + password);
		}
	}
	
	
	// Getters and Setters
	public int getUserID() {
		return this.userID;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String u) {
		this.username = u;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String p) {
		this.password = p;
	}
	
	private int createID(String convert) {
		double u = 1;
		char[] test = convert.toCharArray();
		for(char c : test) {
			u = u * (int)c;
		}
		
		return (int)(Math.abs(u) % 99999);
	}
}
