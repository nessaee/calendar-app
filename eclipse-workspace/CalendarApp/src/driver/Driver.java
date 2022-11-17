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
	    MainController mainController;
	    int userID;
	    
	    while(true) {
	      userID = loginController.menu(db);
	      if(userID == -1) break; // exit program 
	      mainController = new MainController(userID, db, input);  // userID passed to MainController
	    }
	    input.close();
	    db.close();
	}
}
