package menu.login;

import java.util.ArrayList;
import java.util.Scanner;

import datatype.Login;
import datatype.User;
import db.setup.DB;

public class LoginController {
	//private Login login;
	
	public LoginController() {
	}
	
	// Method to create a new User and add them to the database
	public User createUser(DB db) {
		Login login = new Login(db, "Create");
		User user = new User(login.getUserID(), db);
		
		return user;
	}
	
	public User loginUser(DB db) {
		Login login = new Login(db, "Login");
		if(login.getUserID() == -11) {
			return new User();
		}
		
		User user = new User(login.getUserID(), db);
		return user;
	}

}
