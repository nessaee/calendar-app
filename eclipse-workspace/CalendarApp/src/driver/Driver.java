package driver;

import java.util.Scanner;

import datatype.User;
import db.setup.DB;
import menu.login.LoginController;
import menu.main.MainController;

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
					user = loginController.loginUser(db);
					if(user.getUserID() == -1) {
						System.out.println("Invalid login, resetting to the main menu");	// If the login is invalid it will reset to the main 
					}
					else {
						System.out.println("You are successfully logged in!");
						break;
					}
				}
				
				else if(select.equals("Create")) {
					user = loginController.createUser(db);
					System.out.println("A new user has succesfully been created. Returning to the login menu");
				}
				
				else {
					System.out.println("Invalid input");
				}
			}
			
			while(controlKey == 'y') {
				user = new User(userID, db);
				MainController mainController = new MainController();
				
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
					System.out.println("You are now editing your Calendar, would you like to exit or add a Set, Category, or Event? Enter 'Exit', 'Set', 'Category', or 'Event': ");
					select = input.nextLine();
					if(select.equals("Exit")) {
						break;
					}
					else if(select.equals("Set")) {
						// FIXME Implement a CreateSetFromUserInput Method
						mainController.getEditController().addSet(set);
					}
					else if(select.equals("Category")) {
						// FIXME Implement a CreateCategoryFromUserInput Method
						mainController.getEditController().addCategory(category);
					}
					else if(select.equals("Event")) {
						//FIXME Implement a CreateEventFromUserInput Method
						mainController.getEditController().addEvent(event);
					}
				}
				
				else if(select.equals("Display")) {
					
				}
			}
		}
	}
}
