package menu.main;
import menu.edit.EditController;
import menu.calendar.CalendarController;
import datatype.User;
import db.setup.DB;

public class MainController {
	private EditController editController;
	private CalendarController calendarController;
	
	public MainController() {
		
	}
	
	public MainController(User u, DB db) {
		this.editController = new EditController(u, db);
		this.calendarController = new CalendarController(u, db);
	}
}
