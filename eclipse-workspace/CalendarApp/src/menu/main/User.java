package menu.main;

import java.util.ArrayList;

import datatype.Calendar;
import db.setup.DB;
import menu.calendar.CalendarController;

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
	}
	
	public void updateCalendar(DB db) {
		this.calendar.load(this.userID, db);
	}

	public int getUserID() {
		return this.userID;
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
