package menu.login;

import java.util.ArrayList;
import java.util.Scanner;

import db.setup.DB;

/*
 * This class represents a Login containing a user ID, username, and password. It creates a unique userID, and either
 * returns that unique id, or the id of an existing user in the database.
 */
public class Login {
	// Declaration of fields
	private int userID;
	private String username;
	private String password;
	
	// Default Constructor
	public Login() {
		this.userID = -1;
		this.username = "";
		this.password = "";
	}
	
	// Constructor that takes in a database, command, username, and password to assign or create a user ID.
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
	
	// Method to create a unique id based on the username and password
	private int createID(String convert) {
		double u = 1;
		char[] test = convert.toCharArray();
		for(char c : test) {
			u = u * (int)c;
		}
		
		return (int)(Math.abs(u) % 99999);
	}
}
