package driver;

import java.util.Scanner;

import db.setup.DB;
import menu.login.LoginController;
import menu.main.MainController;

/*
 * This class acts as the main driver for the program. It takes in user input, and creates a database to hold that user input in the
 * form of User information, Set information, Category information, and Event information. It accesses controllers to run the 
 * program.
 */
public class Main_Driver {

	public static void main(String[] args) {
		System.out.println("WELCOME TO THE ECE373 CALENDAR PROJECT");
		// Declaration of classes necessary to run the program
		Scanner input = new Scanner(System.in);
	    DB db = new DB("Data.db");
		LoginController loginController = new LoginController(input);
	    MainController mainController = new MainController(input);
	    int userID;
	    db.setConnection(db.connect());
	    // While the user has not entered "Exit" into the loginController, the program will continue giving the opportunity to login and logout
	    while(true) {	// of multiple user accounts
	      userID = loginController.menu(db);	// The LoginController controls logging in and out of user accounts
	      if(userID == -1) break; 				// Exit program if the loginController has given the signal
	      mainController.menu(userID, db);  	// The MainControlller controls editing and displaying of the user calendar
	    }
	    db.close();
	    System.out.println("Exiting program, Goodbye!");
	    input.close();
	}
}
