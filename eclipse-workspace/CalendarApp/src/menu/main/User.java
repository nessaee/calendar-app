package menu.main;

import java.util.ArrayList;

import datatype.Calendar;
import datatype.Category;
import datatype.Set;
import db.setup.DB;
import menu.calendar.CalendarController;

/*
 * This class represents a User, and contains their id and calendar. their calendar will be populated upon
 * creation with a user ID.
 */
public class User {
	// Declaration of fields
	private String username;
	private int userID;
	private Calendar calendar;
	
	// Default Constructor
	public User() {
		this.userID = -1;
		this.calendar = new Calendar();
	}
	
	// Constructor that takes in a user ID, and database. It uses the given user ID to populate the calendar field with the database
	public User(int id, DB db) {
		this.userID = id;
		this.calendar = new Calendar();
	}
		
	// Constructor that takes in a user ID, and database. It uses the given user ID to populate the calendar field with the database
	public User(int id, String username, DB db) {
		this.username = username;
		this.userID = id;
		this.calendar = new Calendar();
	}
	
	// Method to update the calendar from the database
	public void updateCalendar(DB db) {
		this.calendar.update(this.userID, db);
	}

	// Getters and Setters
	public int getUserID() {
		return this.userID;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUserID(int id) {
		this.userID = id;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar c) {
		this.calendar = c;
	}
}
