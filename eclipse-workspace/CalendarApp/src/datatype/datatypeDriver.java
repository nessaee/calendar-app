package datatype;

import db.setup.DB;
import menu.main.User;

public class datatypeDriver {

	public static void main(String[] args) {
		DB db = new DB("Data.db");
		User u1 = new User(1001, db);
	}
}
