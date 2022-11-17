package datatype;

import java.util.ArrayList;

import db.setup.DB;

public class Category extends Node{
	private ArrayList<Event> events = new ArrayList<Event>();

	public Category() {
	}
	
	public Category(Integer parentID, Integer ID, String name) {
		super(parentID, ID, name);
	}
	public void importExisting() { //q: existing eventlist? 
		
	}
	public void addEvent(DB db, Event e) {
		e.setParentID(this.getID());
		events.add(e);
		db.saveRow("Events", e.getRowData());
	}
	//Category: (uID, sID, cID, label)
}
