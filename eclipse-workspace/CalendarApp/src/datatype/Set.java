package datatype;

import java.util.ArrayList;

import db.setup.DB;

public class Set extends Node{
	private ArrayList<Category> categories = new ArrayList<Category>();
	private ArrayList<Event> events = new ArrayList<Event>();
	public Set() {
		
	}
	
	public Set(Integer parentID, Integer ID, String name) {
		super(parentID, ID, name);
	}
	
	public void importExisting() {
		
	}
	public void addCategory(DB db, Category c) {
		c.setParentID(this.getID());
		categories.add(c);
		db.saveRow("Categories", c.getRowData());
	}
	public void addEvent(DB db, Event e) {
		e.setParentID(this.getID());
		events.add(e);
		db.saveRow("Events", e.getRowData());
	}
	//Set: (uID, sID, label) 
}
