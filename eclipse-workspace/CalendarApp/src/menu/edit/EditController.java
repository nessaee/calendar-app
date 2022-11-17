package menu.edit;

import java.util.ArrayList;
import java.util.Scanner;

import datatype.*;
import db.setup.DB;
import menu.main.User;

public class EditController {
	//take in user
	private User user;
	private DB db;
	private Scanner input;
	
	public EditController(User u, DB db, Scanner input) {
		this.user = u;
		this.db = db;
		this.input = input;
	}
	
	public void menu() {
		int parentID;
		System.out.println("Welcome to the Calendar Editor!");
		while(true) {
			System.out.println("Would you like to exit or add a Set, Category, or Event? Enter 'Exit', 'Set', 'Category', or 'Event': ");
			String option = input.nextLine();
			if(option.equals("Exit")) {
				break;
			}
			else if(option.equals("Set")) {
				parentID = user.getUserID();
				addSet(createSet(parentID));
				System.out.println("Set successfully added!");
			}
			else if(option.equals("Category")) {
				// FIXME: Currently assuming parentID is userID
				parentID = user.getUserID();
				addCategory(createCategory(parentID));
				System.out.println("Category successfully added!");
			}
			else if(option.equals("Event")) {
				// FIXME: Currently assuming parentID is userID
				parentID = user.getUserID();
				addEvent(createEvent(parentID));
				System.out.println("Event successfully added!");
			}
			else {
				System.out.println("Invalid input, please try again");
			}
		}
	}
	
	public Set createSet(int parentID) {
		String name = inputName("set");
		int id = generateID();
		Set set = new Set(parentID, id, name);
		return set;
	}
	
	public Category createCategory(int parentID) {
		String name = inputName("category");
		int id = generateID();
		Category category = new Category(parentID, id, name);
		
		return category;
	}
	
	public Event createEvent(int parentID) {
		String name = inputName("event");
		int id = generateID();
		String description = inputDescription();
		int urgency = inputUrgency();
		int date = inputDate();
		Event event = new Event(parentID, id, name, description, urgency, date);
		return event;
	}

	public int generateID() {
		return 0;
	}
	
	/* INPUT METHODS */
	public String inputName(String type) {
		System.out.println("Please enter the name of your " + type + ":");
		return this.input.nextLine();
	}
	public String inputDescription() {
		System.out.println("Please enter the event description:");
		return this.input.nextLine();
	}
	
	public int inputUrgency() {
		System.out.println("Please enter the event urgency (1-10):");
		return this.input.nextInt();
	}
	
	public int inputDate() {
		System.out.println("Please enter the event date:");
		return this.input.nextInt();
	}
	
	/* ADD METHODS */
	public void addSet(Set s) {
		s.setParentID(user.getUserID());
		user.getCalendar().addSet(s);
		db.saveRow("Sets", s.getRowData());
	}

	public void addCategory(Category c) {
		c.setParentID(user.getUserID());
		user.getCalendar().addCategory(c);
		db.saveRow("Categories", c.getRowData());
	}
	
	public void addEvent(Event e) {
		e.setParentID(user.getUserID());
		user.getCalendar().addEvent(e);
		db.saveRow("Events", e.getRowData());
	}
	
	/* REMOVE METHODS */
	public void removeSet(Set s) {
		user.getCalendar().removeSet(s);
		db.removeRow("Sets", s.getID());
	}
	
	public void removeCategory(Category c) {
		//q: will removing category also remove events? now it will ;)
		user.getCalendar().removeCategory(c);
		db.removeRow("Categories", c.getID());		
	}
	
	public void removeEvent(Event e) {
		user.getCalendar().removeEvent(e);
		db.removeRow("Events", e.getID());
	}
	
}
