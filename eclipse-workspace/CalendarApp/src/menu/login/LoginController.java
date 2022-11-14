package menu.login;

import java.util.ArrayList;
import java.util.Scanner;

import db.setup.DB;

public class LoginController {
	//private Login login;
	
	public LoginController() {
		
	}
	
	// Method to create a new User and add them to the database
	public int createUser(DB db) {
		String info[] = inputInformation();		// Takes user information to create a username and password
		String username = info[0];
		String password = info[1];
		Integer userID = createID(username + password);	// Uses user information to create a unique id
		
		ArrayList<Object> rowData = new ArrayList<>();
		rowData.add(userID);
		rowData.add(username);
		rowData.add(password);
		
		db.saveRow("Users", rowData);		// Adds user information to the database
		System.out.println("User: " + username + ", Password: " + password + ", userID: " + userID + " has been created succesfully");
		return userID;
	}
	
	private String[] inputInformation() {
		Scanner input = new Scanner(System.in);
		String username, password, passwordConfirm;
		String[] returnInfo = new String[2];
		
		while(true) {
			System.out.print("Enter username: ");
			username = input.nextLine();
			if(!username.equals("")) {
				break;
			}
			else {
				System.out.println("Invalid username. Please try again");
			}
		}
		while(true) {
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
		int userID = 1;
		char[] test = convert.toCharArray();
		for(char c : test) {
			userID = userID * (int)c;
		}
		
		return Math.abs(userID);
	}
	
	public int checkLogin() {
		int userID = -1;
		Scanner input = new Scanner(System.in);
		String username, password;
		
		System.out.print("Enter Username: ");
		username = input.nextLine();
		System.out.print("Enter Password: ");
		password = input.nextLine();
		
		// FIXME Check db to see if username/password combinatione exists. IF it does, return the userID
		
		return userID;
	}
}
