package driver;

import java.util.Scanner;

import datatype.User;
import db.setup.DB;
import menu.login.LoginController;

public class Driver {

	public static void main(String[] args) {
		System.out.println("WELCOME TO THE ECE373 CALENDAR PROJECT");
		
		char controlKey = 'y';
		Scanner input = new Scanner(System.in);
		DB db = new DB("Data.db");
		
		LoginController loginController = new LoginController();
		User user = new User();
		int userID = -1;
		
		// Controls User creation and logging in
		String select;
		while(controlKey == 'y') {
			while(controlKey == 'y') {
				// Selecting user input
				System.out.print("Would you like to login to an existing account, create a new one, or exit? Enter 'Login', 'Create', or 'Exit': ");
				select = input.nextLine();
				
				if(select.equals("Exit")) {		// If the user has chosen to exit, the program will end
					System.out.println("You have chosen to exit, hava a good one!");
					controlKey = 'n';
					break;
				}
				
				else if(select.equals("Login")) {	// If the user has chosen to login, the login will be validated
					userID = loginController.checkLogin();
					if(userID != -1) {				// If the login is valid, a User object will be created populated from the database and the program will continue
						break;
					}
					System.out.println("Invalid login, resetting to the main menu");	// If the login is invalid it will reset to the main menu
				}
				
				else if(select.equals("Create")) {
					loginController.createUser(db);
				}
				
				else {
					System.out.println("Invalid input");
				}
			}
			
			while(controlKey == 'y') {
				user = new User(userID, db);
				
				System.out.println("Would you like to Edit your calendar, display it, logout, or exit the program? Enter 'Edit', 'Display', 'Logout', or 'Exit': ");
				select = input.nextLine();
				
				if(select.equals("Exit")) {		// If the user has chosen to exit, the program will end
					System.out.println("You have chosen to exit, have a good one!");
					controlKey = 'n';
					break;
				}
				
				else if(select.equals("Logout")) {
					System.out.println("You have chosen to logout and will return to the main menu");
					break;
				}
				
				else if(select.equals("Edit")) {
					
				}
				
				else if(select.equals("Display")) {
					
				}
			}
		}
	}
}
