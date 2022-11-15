package menu.edit;

import datatype.*;
import db.setup.DB;

public class EditController {
	//take in user
	private User U;
	public EditController(User u) {
		U = new User();
		U.setUserID(u.getUserID());
		U.setCalendar(u.getCalendar());
		DB db = new DB("Data.db");
	}
	
	public void addSet(Set s) {
		U.getCalendar().getElementList().add(s);
		
		
	}
	public void removeSet(Integer sID) {
		for(int i = 0; i < U.getCalendar().getElementList().size(); i++) {
			if (U.getCalendar().getElementList().get(i).getID() == sID) {
				U.getCalendar().getElementList().remove(i);
				//db
			}
		}
		
	}
	public void addCategory(Category c) {
		U.getCalendar().getElementList().add(c);
	}
	public void removeCategory(Integer sID) {
		//q: will removing category also remove events?

		for(int i = 0; i < U.getCalendar().getElementList().size(); i++) {
			if (U.getCalendar().getElementList().get(i).getID() == sID) {
				U.getCalendar().getElementList().remove(i);
				//db
			}
		}
			
	}
	public void addEvent(Event e) {
		U.getCalendar().getElementList().add(e);
	}
	public void removeEvent(Integer sID) {
		for(int i = 0; i < U.getCalendar().getElementList().size(); i++) {
			if (U.getCalendar().getElementList().get(i).getID() == sID) {
				U.getCalendar().getElementList().remove(i);
				//db
			}
		}
	}
}
