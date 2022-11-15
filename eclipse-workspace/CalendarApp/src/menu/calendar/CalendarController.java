package menu.calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import datatype.*;
import db.setup.DB;

public class CalendarController {
	private User U;
	private DB Data;
	public CalendarController(User u, DB db) {
		U = u;
		Data = db;
	}
	
	
	public void loadSet(Set s) { 
		Data.loadRow("Set", s.getID());
	}
	public void loadCategory(Category c) {
		Data.loadRow("Category", c.getID());
	}
	public void loadEvent(Event e) {
		Data.loadRow("Event", e.getID());
	}
	public void printCalendar() {
		
	}
}
