package driver;

import java.util.Scanner;

import db.setup.DB;
import menu.login.LoginController;
import menu.main.MainController;

public class Driver {

	public static void main(String[] args) {
		System.out.println("WELCOME TO THE ECE373 CALENDAR PROJECT");
		Scanner input = new Scanner(System.in);
	    DB db = new DB("Data.db");
		LoginController loginController = new LoginController(input);
	    MainController mainController = new MainController(input);
	    int userID;
	    db.setConnection(db.connect());
	    while(true) {
	      userID = loginController.menu(db);
	      if(userID == -1) break; // exit program 
	      mainController.menu(userID, db);  // userID passed to MainController
	    }
	    db.close();
	    System.out.println("Exiting program, Goodbye!");
	    input.close();
	}
}
