package datatype;

import java.util.ArrayList;

import db.setup.DB;

/*
 * This class represents as Set that can contain Category objects and Event objects. It extends from the Node class.
 * It is used in the Calendar object.
 */
public class Set extends Node{
	
	// Declaration of fields
	private ArrayList<Category> categories = new ArrayList<Category>();
	private ArrayList<Event> events = new ArrayList<Event>();
	
	// Default Constructor
	public Set() {}
	
	// Constructor that takes in a parentID, personal ID, and name as parameters.
	public Set(Integer parentID, Integer ID, String name) {
		super(parentID, ID, name);
	}
	
	// Method to add a Category to this set. It adds the category to the Set's categories field, and the database.
	public void addCategory(DB db, Category c) {
		c.setParentID(this.getID());
		categories.add(c);
		db.saveRow("Categories", c.getRowData());
	}
	
	// Method to add an Event to this set. It adds the event to the Set's events field, and the database.
	public void addEvent(DB db, Event e) {
		e.setParentID(this.getID());
		events.add(e);
		db.saveRow("Events", e.getRowData());
	}
}
