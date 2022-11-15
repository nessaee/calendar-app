package menu.edit;

import java.util.ArrayList;

import datatype.*;
import db.setup.DB;

public class EditController {
	//take in user
	private User user;
	private DB db;
	
	public EditController(User u, DB db) {
		this.user = u;
		this.db = db;
	}
	
	public void addSet(Set s) {
		s.setParentID(user.getUserID());
		user.getCalendar().addSet(s);
		db.saveRow("Sets", s.getRowData());
	}

	public void addCategory(Category c) {
		c.setParentID(user.getUserID());
		user.getCalendar().addCategory(c);
		db.saveRow("Categories", c.getRowData());
	}
	
	public void addEvent(Event e) {
		e.setParentID(user.getUserID());
		user.getCalendar().addEvent(e);
		db.saveRow("Events", e.getRowData());
	}
	
	public void removeSet(Set s) {
		user.getCalendar().removeSet(s);
		db.removeRow("Sets", s.getID());
	}
	
	public void removeCategory(Category c) {
		//q: will removing category also remove events? now it will ;)
		user.getCalendar().removeCategory(c);
		db.removeRow("Categories", c.getID());		
	}
	
	public void removeEvent(Event e) {
		user.getCalendar().removeEvent(e);
		db.removeRow("Events", e.getID());
	}
	
}
