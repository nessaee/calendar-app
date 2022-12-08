package menu.edit;

import java.util.ArrayList;
import java.util.Scanner;

import datatype.*;
import db.setup.DB;

/*
 * Class that controls editing of the User's calendar. It adds and removes Sets, Categories, and Events from both the
 * User and the database. It also controls user input in selecting what actions to take.
 */
public class EditController {
	
	// Declaration of fields
	private User user;
	private DB db;
	private Scanner input;
	

	// Constructor that takes in a user, database, and scanner
	public EditController(User u, DB db, Scanner input) {
		this.user = u;
		this.db = db;
		this.input = input;
	}
	
	// Constructor that takes in a user, database, and scanner
	public EditController(User u, DB db) {
		this.user = u;
		this.db = db;
	}
	
	// Method that displays the menu. It takes in user input and calls methods based on it. All edit functionality
	// is implemented through this controller.
	public void menu() {
		int option = -1;
		String buffer;
		System.out.println("-------------------------------------------------------------\n");
		System.out.println("Welcome to the Calendar Editor!" );
		while(true) {
			
			System.out.println("(0) Exit\n(1) Add data\n(2) Remove data\nEnter the option you would like to choose: ");
			
			option = input.nextInt();
			buffer = input.nextLine();
			switch(option) {
				case 0: 
					return;
				case 1:
					addMenu();
					break;
				case 2:
					removeMenu();
					break;
				default:
					System.out.println("Invalid input, please try again");
					break;
			}
		}
	}
	
	// Method to get user input and decide if a Set, Category, or Event is added
	public void addMenu() {
		int option = -1;
		String buffer;
		System.out.println("(0) Exit\n(1) Add Event\n(2) Add Category\n(3) Add Set\nEnter option: ");
		option = input.nextInt();
		buffer = input.nextLine();
		switch(option) {
			case 0:
				return;
			case 1:
				// FIXME: Currently assuming parentID is userID
				addEvent(createEvent(db, user.getUserID()));
				System.out.println("Event successfully added!");
				break;
			case 2:
				// FIXME: Currently assuming parentID is userID
				addCategory(createCategory(db, user.getUserID()));
				System.out.println("Category successfully added!");
				break;
			case 3:
				// FIXME: Currently assuming parentID is userID
				addSet(createSet(db, user.getUserID()));
				System.out.println("Set successfully added!");
				break;
			default:
				System.out.println("Invalid input, please try again");
				break;
				
		}
	}
	
	public void addMenu(int option) {
		switch(option) {

			case 0:
				// FIXME: Currently assuming parentID is userID
				addEvent(createEvent(db, user.getUserID()));
				System.out.println("Event successfully added!");
				break;
			case 1:
				// FIXME: Currently assuming parentID is userID
				addCategory(createCategory(db, user.getUserID()));
				System.out.println("Category successfully added!");
				break;
			case 3:
				// FIXME: Currently assuming parentID is userID
				addSet(createSet(db, user.getUserID()));
				System.out.println("Set successfully added!");
				break;
			default:
				System.out.println("Invalid input, please try again");
				break;
				
		}
	}
	
	
	// Method to get user input and decide if a Set, Category, or Event is removed
	public void removeMenu() {
		int option = -1;
		String buffer;
		System.out.println("(0) Exit\n(1) Remove Object\nEnter option: ");
		option = input.nextInt();
		buffer = input.nextLine();
		switch(option) {
		case 0:
			return;
		case 1:
			this.user.getCalendar().update(user.getUserID(), db);
			this.user.getCalendar().printCalendar();
			System.out.println("Please enter ID of object to remove: ");
			option = input.nextInt();
			buffer = input.nextLine();
			this.db.removeRow(option);
			break;
		default:
			System.out.println("Invalid input, please try again");
			break;
		}
	}
	
	// Method to create a Set given a parent ID
	public Set createSet(DB db, int parentID) {
		String name = inputName("set");
		int id = generateID(db, "set");
		Set set = new Set(parentID, id, name);
		return set;
	}
	public Set createSet(String name) {
		int id = generateID(this.db, "set");
		Set set = new Set(this.user.getUserID(), id, name);
		return set;
	}

	
	// Method to create a Category given a parent ID
	public Category createCategory(DB db, int parentID) {
		String name = inputName("category");
		int id = generateID(db, "category");
		Category category = new Category(parentID, id, name);
		
		return category;
	}
	public Category createCategory(String name, int parentID) {
		int id = generateID(this.db, "category");
		Category category = new Category(parentID, id, name);
		return category;
	}

	// Method to create an Event given a parent ID
	public Event createEvent(DB db, int parentID) {
		String name = inputName("event");
		int id = generateID(db, "event");
		String description = inputDescription();
		int urgency = inputUrgency();
		int date = inputDate();
		Event event = new Event(parentID, id, name, description, urgency, date);
		return event;
	}
    public Event createEvent(int parentID, String name, String description, int urgency, int date) {
    	int id = generateID(this.db, "event");
    	Event event = new Event(parentID, id, name, description, urgency, date);
    	return event;
    }	

	// Method to generate a unique ID
	public int generateID(DB db, String idType) {
		return db.getNextID(idType);
	}
	
	/* INPUT METHODS */
	
	// Method to input a name
	public String inputName(String type) {
		System.out.println("Please enter the name of your " + type + ":");
		return this.input.nextLine();
	}
	// Method to input a description
	public String inputDescription() {
		System.out.println("Please enter the event description:");
		return this.input.nextLine();
	}
	// Method to input an urgency level
	public int inputUrgency() {
		System.out.println("Please enter the event urgency (1-10):");
		int urgency = this.input.nextInt();
		this.input.nextLine(); // clear \n buffer
		return urgency;
	}
	// Method to input a Date
	public int inputDate() {
		System.out.println("Please enter the event date:");
		int date = this.input.nextInt();
		this.input.nextLine(); // clear \n buffer
		return date;
	}
	
	/* ADD METHODS */
	
	// Method to add a Set to the User calendar and database
	public void addSet(Set s) {
		this.user.getCalendar().addSet(s);
		this.db.saveRow("Sets", s.getRowData());
	}
	// Method to add a Category to the User calendar and database
	public void addCategory(Category c) {
		this.user.getCalendar().addCategory(c);
		this.db.saveRow("Categories", c.getRowData());
	}
	// Method to add an event to the User calendar and database
	public void addEvent(Event e) {
		this.user.getCalendar().addEvent(e);
		this.db.saveRow("Events", e.getRowData());
	}
	
	
	/* SETTERS AND GETTERS */
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DB getDB() {
		return db;
	}

	public void setDB(DB db) {
		this.db = db;
	}


	
}
