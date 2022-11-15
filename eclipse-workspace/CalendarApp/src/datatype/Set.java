package datatype;

import java.util.ArrayList;

import db.setup.DB;

public class Set extends Node{
	private ArrayList<Category> categories;
	private ArrayList<Event> events;
	public Set() {
		categories = new ArrayList<Category>();
		events = new ArrayList<Event>();
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
	public String toString() {
		return this.getLabel();
	}
	
	//Set: (uID, sID, label) 
}
