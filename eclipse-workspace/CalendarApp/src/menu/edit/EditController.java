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
			user.getCalendar().update(user.getUserID(), db);
			user.getCalendar().printCalendar();
			System.out.println("Please enter ID of object to remove: ");
			option = input.nextInt();
			buffer = input.nextLine();
			db.removeRow(option);
			break;
		default:
			System.out.println("Invalid input, please try again");
			break;
		}
	}
	public Set createSet(DB db, int parentID) {
		String name = inputName("set");
		int id = generateID(db, "set");
		Set set = new Set(parentID, id, name);
		return set;
	}
	
	public Category createCategory(DB db, int parentID) {
		String name = inputName("category");
		int id = generateID(db, "category");
		Category category = new Category(parentID, id, name);
		
		return category;
	}
	
	public Event createEvent(DB db, int parentID) {
		String name = inputName("event");
		int id = generateID(db, "event");
		String description = inputDescription();
		int urgency = inputUrgency();
		int date = inputDate();
		Event event = new Event(parentID, id, name, description, urgency, date);
		return event;
	}

	public int generateID(DB db, String idType) {
		return db.getNextID(idType);
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
		int urgency = this.input.nextInt();
		this.input.nextLine(); // clear \n buffer
		return urgency;
	}
	
	public int inputDate() {
		System.out.println("Please enter the event date:");
		int date = this.input.nextInt();
		this.input.nextLine(); // clear \n buffer
		return date;
	}
	
	/* ADD METHODS */
	public void addSet(Set s) {
		user.getCalendar().addSet(s);
		db.saveRow("Sets", s.getRowData());
	}

	public void addCategory(Category c) {
		user.getCalendar().addCategory(c);
		db.saveRow("Categories", c.getRowData());
	}
	
	public void addEvent(Event e) {
		user.getCalendar().addEvent(e);
		db.saveRow("Events", e.getRowData());
	}

	
}
