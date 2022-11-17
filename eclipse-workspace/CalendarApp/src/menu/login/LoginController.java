package menu.login;

import java.util.ArrayList;
import java.util.Scanner;

import db.setup.DB;

/*
 * This is a class that controls the user logging in. It uses user input to decide if a user is logging in, or being
 * created, and utilizes the Login class to accomplish these tasks.
 */
public class LoginController {
	// Declaration of fields
	private int userID;
	Scanner input;
	
	// Constructor that takes in a Scanner object
	public LoginController(Scanner input) {
		this.input = input;
		this.userID = -1;
	}
	
	// Method to create a menu, and take user input. It allows the user to select whether they would like to create a new User,
	// or login to an existing account. It then executes these commands.
	public int menu(DB db) {
		int option = -1;
		String buffer;
		while(true) {		// Loops so long as the user has not entered exit
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("Welcome to the Login menu!");
			System.out.println("(0) Exit Application\n(1) Login \n(2) Register\nEnter the option you would like to choose: ");
			option = input.nextInt();
			buffer = input.nextLine();
			
			switch(option) {
				case 0: // Exit 
					return -1;
					
				case 1: // Login
					this.userID = loginUser(db);
					if(this.userID != -1) {	// Valid Login 
						System.out.println("Successfully logged in!");
						return this.userID;
					}
					else {	// Invalid Login 
						System.out.println("Invalid login, returning to the Login menu");
					}
					break;
					
				case 2: // Register
					createUser(db);
					break;
					
				default:
					System.out.println("Invalid input, please try again");
					break;		
			}
		}
	}
	
	// Method to create a new User
	private void createUser(DB db) {
		String info[] = inputInformation("Register");
		if(db.checkUser(info[0], info[1]) == -1) {	// If the User does not already exist, a new user will be created
			Login login = new Login(db, "Create", info[0], info[1]);	// through a login object and the helper method addUserToDatabase
			addUserToDatabase(login, db);
		}
		else {
			System.out.println("This user already exists, please try 'Login' instead of 'Register'");
		}
	}
	
	// Method to login as an existing user
	private int loginUser(DB db) {
		String info[] = inputInformation("Login");
		Login login = new Login(db, "Login", info[0], info[1]);
		
		return login.getUserID();
	}
	
	// Method to input username and password information
	private String[] inputInformation(String command) {
		String username = "";
		String password = "";
		
		while(true) {	
			// Gets username input, and checks if it is valid
			System.out.print("Enter username: ");
			username = input.nextLine();
			if(username.equals("")) {
				System.out.println("Invalid username, it cannot be blank. Please try again");
				continue;
			}
			// Gets password input and checks if it is valid
			System.out.print("Enter password: ");
			password = input.nextLine();
			if(password.equals("")) {
				System.out.println("Invalid password, it cannot be blank. Please try again");
				continue;
			}
			// Gets password confirmation input and checks if it matches password
			if(command.equals("Register")) {
				String passwordConfirm = "";
				System.out.print("Confirm password: ");
				passwordConfirm = input.nextLine();
				if(!password.equals(passwordConfirm)) {
					System.out.println("Invalid password or passwords do not match. Please try again");
					continue;
				}
			}
			
			break;
		}
		String[] returnInfo = {username, password};
		return returnInfo;
	}

	// Method to add a User's information to the database
	private void addUserToDatabase(Login login, DB db) {
		ArrayList<Object> rowData = new ArrayList<>();
		rowData.add(login.getUserID());
		rowData.add(login.getUsername());
		rowData.add(login.getPassword());
		
		db.saveRow("Users", rowData);
	}


}
