package menu.edit;

import java.util.ArrayList;

import datatype.*;
import db.setup.DB;

public class EditController {
	//take in user
	private User U;
	private DB Data;
	public EditController(User u, DB db) {
		U = u;
		Data = db;
	}
	
	public void addSet(Set s) {
		U.getCalendar().getElementList().add(s);
		U.getCalendar().getSetList().add(s);
		//Set: (uID, sID, label) 
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(U.getUserID());
		rowData.add(s.getID());
		rowData.add(s.getLabel());
		Data.saveRow("Set", rowData);
		
	}
	public void removeSet(Integer sID) {
		for(int i = 0; i < U.getCalendar().getElementList().size(); i++) {
			if (U.getCalendar().getElementList().get(i).getID() == sID) {
				U.getCalendar().getElementList().remove(i);
				Data.removeRow("Set", sID);
			}
		}
		
	}
	public void addCategory(Category c) {
		U.getCalendar().getElementList().add(c);
		U.getCalendar().getCategoryList().add(c);
		//Category: (uID, sID, cID, label)
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(U.getUserID());
		rowData.add(c.getParentID());
		rowData.add(c.getID());
		rowData.add(c.getLabel());
		Data.saveRow("Category", rowData);
		
	}
	public void removeCategory(Integer cID) {
		//q: will removing category also remove events?

		for(int i = 0; i < U.getCalendar().getElementList().size(); i++) {
			if (U.getCalendar().getElementList().get(i).getID() == cID) {
				U.getCalendar().getElementList().remove(i);
				Data.removeRow("Category", cID);
			}
		}
			
	}
	public void addEvent(Event e) {
		U.getCalendar().getElementList().add(e);
		U.getCalendar().getEventList().add(e);
		//Event: (uID, sID, cID, eID, label, description, urgency) 
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(U.getUserID());
		rowData.add(e.getsID());
		rowData.add(e.getParentID());
		rowData.add(e.getID());
		rowData.add(e.getLabel());
		rowData.add(e.getDescription());
		rowData.add(e.getUrgency());
		rowData.add(e.getDate().toInt());
		Data.saveRow("Event", rowData);
	}
	public void removeEvent(Integer eID) {
		for(int i = 0; i < U.getCalendar().getElementList().size(); i++) {
			if (U.getCalendar().getElementList().get(i).getID() == eID) {
				U.getCalendar().getElementList().remove(i);
				Data.removeRow("Event", eID);
			}
		}
	}
}
