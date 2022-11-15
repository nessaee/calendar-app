package datatype;

import java.util.ArrayList;

import db.setup.DB;

public class Category extends Node{
	private ArrayList<Event> events;

	public Category() {
		this.events = new ArrayList<Event>();
	}
	
	
	public void importExisting() { //q: existing eventlist? 
		
	}
	public void addEvent(DB db, Event e) {
		e.setParentID(this.getID());
		events.add(e);
		db.saveRow("Events", e.getRowData());
	}
	public String toString() {
		return this.getLabel();
	}
	
	//Category: (uID, sID, cID, label)
}
