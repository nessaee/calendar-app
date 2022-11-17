package datatype;

import java.util.ArrayList;

import db.setup.DB;

/*
 * This is a Category class that extends from the Node class. It inherits label, currentId, and parentID fields.
 * It represents a category that the user is able to add events to and add to their calendar
 */
public class Category extends Node{
	// Declaration of fields
	private ArrayList<Event> events = new ArrayList<Event>();

	// Default Constructor
	public Category() {
	}
	
	// Constructor that takes in a parent ID, personal ID, and name as parameters.
	// It uses the Node constructor that the class extends from.
	public Category(Integer parentID, Integer ID, String name) {
		super(parentID, ID, name);
	}

	// Method used to an an event to the category. It takes in a database and Event object as
	// parameters and adds the event to the database.
	public void addEvent(DB db, Event e) {
		e.setParentID(this.getID());
		events.add(e);
		db.saveRow("Events", e.getRowData());
	}
}
