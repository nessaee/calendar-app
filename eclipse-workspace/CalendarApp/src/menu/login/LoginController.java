package menu.login;

import java.util.ArrayList;
import java.util.Scanner;

import db.setup.DB;

public class LoginController {
	private int userID;
	Scanner input;
	
	public LoginController(Scanner input) {
		this.input = input;
		this.userID = -1;
	}
	
	public int menu(DB db) {
		String select = "Continue";
		
		while(true) {		// Loops so long as the user has not entered exit
			System.out.println("Welcome to the Login menu, would you like to create a user, or login to an existing account");
			System.out.println("Enter 'Create' to create a new user, 'Login' to login to an existing account, or 'Exit' to exit the program");
			select = input.nextLine();
			
			if(select.equals("Create")) {	// If the Actor chooses to create a User, the createUser method is called
				createUser(db);
			}
			
			else if(select.equals("Login")) {	// If the Actor chooses to login to an existing account
				this.userID = loginUser(db);	// a Login object is created, then checked for validity
				if(this.userID != -1) {	// If the login is valid, then a user object is created and returned. Will break into another smaller checkLogin function
					System.out.println("Successfully logged in! Moving to the Main Menu");
					break;
				}
				else {	// If the login is invalid, then the Actor is returned to the login menu
					System.out.println("Invalid login, returning to the Login menu");
				}
			}
			else if(select.equals("Exit")) {	// If the user chooses to exit, the default User will be returned which will give an id of -1
				break;
			}
			else {
				System.out.println("Invalid input, please try again");
			}
		}	
		return this.userID;
	}
	
	private void createUser(DB db) {
		String info[] = inputInformation("Create");
		if(db.checkUser(info[0], info[1]) == -1) {	// If the User does not already exist, a new user will be created
			Login login = new Login(db, "Create", info[0], info[1]);	// through a login object and the helper method addUserToDatabase
			addUserToDatabase(login, db);
		}
		else {
			System.out.println("This user already exists, please try 'Login' instead of 'Create'. Returning to the Login menu");
		}
	}
	
	private int loginUser(DB db) {
		String info[] = inputInformation("Login");
		Login login = new Login(db, "Login", info[0], info[1]);
		
		return login.getUserID();
	}
	
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
			if(command.equals("Create")) {
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

	private void addUserToDatabase(Login login, DB db) {
		ArrayList<Object> rowData = new ArrayList<>();
		rowData.add(login.getUserID());
		rowData.add(login.getUsername());
		rowData.add(login.getPassword());
		
		db.saveRow("Users", rowData);
	}


}
