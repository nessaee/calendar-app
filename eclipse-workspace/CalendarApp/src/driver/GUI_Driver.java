package driver;
import menu.main.*;
import db.setup.DB;
import menu.calendar.*;
import menu.login.LoginGUI;
public class GUI_Driver {

	public static void main(String[] args) {
		DB db = new DB("Data.db");
        db.setConnection(db.connect());
        LoginGUI g = new LoginGUI(db);
		
		
	}
}
