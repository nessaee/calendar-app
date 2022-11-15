package datatype;

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
	
	public Login(DB db, String command) {
		while(true) {
			String info[] = inputInformation();		// Takes user information to create a username and password
			if(command.equals("Create")) {	// If the user has chosen to create a new User, it takes this if statement
				if(db.checkUser(info[0], info[1]) == -1) {	// Checks if the user already exists
					this.username = info[0];
					this.password = info[1];
					this.userID = createID(username + password); // Uses user information to create a unique id
					addUserToDatabase(db);
					break;
				}
				System.out.println("This username and password already exist, please create a new one");
			}
			else if(command.equals("Login")) {		// If the user has chosen to login, it takes this if statement
				if(db.checkUser(info[0],  info[1]) == -1) {
					this.username = info[0];
					this.password = info[1];
					this.userID = db.checkUser(this.username, this.password);
					break;
				}
				System.out.println("The username or password is incorrect, please try again");
			}
		}
	}
	
	// Getters and Setters
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int id) {
		this.userID = id;
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
	
	// Helper methods to create a Login from input
	private String[] inputInformation() {
		Scanner input = new Scanner(System.in);
		String username, password, passwordConfirm;
		String[] returnInfo = new String[2];
		
		while(true) {	// Gets username input, and checks if it is valid
			System.out.print("Enter username: ");
			username = input.nextLine();
			if(!username.equals("")) {
				break;
			}
			else {
				System.out.println("Invalid username, it cannot be blank. Please try again");
			}
		}
		while(true) {	// Gets password input and checks if it is valid
			System.out.print("Enter password: ");
			password = input.nextLine();
			System.out.print("Confirm password: ");
			passwordConfirm = input.nextLine();
			
			if(!password.equals("") && password.equals(passwordConfirm)) {
				break;
			}
			else {
				System.out.println("Invalid password or passwords do not match. Please try again");
			}
		}
		
		
		returnInfo[0] = username;
		returnInfo[1] = password;
		
		return returnInfo;
	}
	
	private int createID(String convert) {
		double u = 1;
		char[] test = convert.toCharArray();
		for(char c : test) {
			u = u * (int)c;
		}
		
		return (int)(Math.abs(u) % 99999);
	}
	
	private void addUserToDatabase(DB db) {
		ArrayList<Object> rowData = new ArrayList<>();
		rowData.add(this.userID);
		rowData.add(this.username);
		rowData.add(this.password);
		
		db.saveRow("Users", rowData);		// Adds user information to the database
		System.out.println("User: " + this.username + ", Password: " + this.password + ", userID: " + this.userID + " has been created succesfully");
	}
}
