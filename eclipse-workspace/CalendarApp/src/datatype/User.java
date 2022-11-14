package datatype;

import db.setup.DB;

public class User {

	private int userID;
	private Calendar calendar;
	
	public User() {
		this.userID = -1;
		this.calendar = new Calendar();
	}
	
	public User(int id, DB db) {
		this.userID = id;
		this.calendar = new Calendar();
		populateCalendar(db);
	}
	
	public int getUserID() {
		return userID;
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

	// Method to use the the userID to populate the calendar with Sets, Categories, and Events from the database
	private void populateCalendar(DB db) {
		
	}
}
