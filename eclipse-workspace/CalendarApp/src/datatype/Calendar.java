package datatype;

import java.util.ArrayList;
import db.setup.DB;
import db.setup.Edit;

/* This is a Calendar class used to hold User's sets, categories, and events. It populates with values from the database
 * upon construction, and is used to display the calendar to the User. Any time a set, category, or event is added or removed
 * it is updated both in the database, and the Calendar.
 */
public class Calendar {
	private ArrayList<Node> nodes;
	private ArrayList<Set> sets;
	private ArrayList<Event> events;
	private ArrayList<Category> categories;
	ArrayList<Node> nodes2 = new ArrayList<Node>();
	
	/* This is a Calendar class used to hold User's sets, categories, and events. It populates with values from the database
	 * upon construction, and is used to display the calendar to the User. Any time a set, category, or event is added or removed
	 * it is updated both in the database, and the Calendar.
	 */
	public Calendar() {
		nodes = new ArrayList<Node>();
		sets = new ArrayList<Set>();
		events = new ArrayList<Event>();
		categories = new ArrayList<Category>();
	}

	// Method used to populate the calendar with values from the database
	public void update(int ID, DB db) {
		this.clear();
        this.load(ID, db);
        for(Set s : sets) {
            this.load(s.getID(), db);
        }
        for(Category c : categories) {
            this.load(c.getID(), db);
        }
	}
	
	// Helper method for the update method. This takes in an ID and database as parameters, and
	// adds all associated Sets, Events, and Categories from the database to the Calendar
	public void load(int ID, DB db) {
		int currentID = 0;
		String tablename;
		this.nodes.clear();
		for(ArrayList<ArrayList<Object>> table : db.loadAllSubsets(ID)) {
			if(!table.isEmpty()) {
				//System.out.println(table);
				tablename = Edit.getTableName((int) table.get(0).get(1));
				for(ArrayList<Object> row : table) {
					if(!row.isEmpty()) {
						currentID = (int) row.get(1);
						switch(tablename) {
							case "Sets":
								this.nodes.add(loadSet(row.get(0), row.get(1), row.get(2)));
								break;
							case "Categories":
								this.nodes.add(loadCategory(row.get(0), row.get(1), row.get(2)));
								break;
							case "Events":
								this.nodes.add(loadEvent(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)));
								break;
							default: 
								break;
						}
					}
				}
			}
		}
		this.parseNodes();

	}
	
	// Method to print all Sets, Categories, and Events in the User's calendar
	public void printCalendar() {
		String id = String.format("%6s", "ID|");
		String label = String.format("%10s", "Label|");
		String description = String.format("%20s", "Description|");
		String urgency = String.format("%10s", "Urgency|");
		String date = String.format("%14s", "Date|");
		
		/* PRINT SETS */
		System.out.println("-----------------");
		System.out.println("|-----SETS------|");
		System.out.println("-----------------");
		System.out.println("|" + id + label);
		System.out.println("-----------------");
		for(Set s : sets) {
			System.out.println(s);
		}
		System.out.println("-----------------\n\n");
		
		
		/* PRINT CATEGORIES */
		System.out.println("-----------------");
		System.out.println("|--CATEGORIES---|");
		System.out.println("-----------------");
		System.out.println("|" + id + label);
		System.out.println("-----------------");
		for(Category c : categories) {
			System.out.println(c);
		}
		System.out.println("-----------------\n\n");
		
		/* PRINT EVENTS */
		System.out.println("-------------------------------------------------------------");
		System.out.println("|---------------------------EVENTS--------------------------|");
		System.out.println("-------------------------------------------------------------");
		System.out.println("|" + id + label + description + urgency + date);
		System.out.println("-------------------------------------------------------------");
		for(Event e : events) {
			System.out.println(e);
		}
		System.out.println("-------------------------------------------------------------\n\n");
	}
	
	// Method used to create a Set object from a given parent id, personal id, and label
	private Set loadSet(Object pID, Object ID, Object label) {
		return new Set((int) pID, (int) ID, (String) label);
	}
	// Method used to create a Category object from a given parent id, personal id, and label
	private Category loadCategory(Object pID, Object ID, Object label) {
		return new Category((int) pID, (int) ID, (String) label);
	}
	// Method used to create an Event object from a given parent id, personal id, label, description, urgency, and date
	private Event loadEvent(Object pID, Object ID, Object label, Object description, Object urgency, Object date) {
		return new Event((int) pID, (int) ID, (String) label, (String) description, (int) urgency, (int) date);
	}
	// Method used to add a Set object to the sets field of the calendar
	public void addSet(Set s) {
		this.sets.add(s);
	}
	// Method used to add a Category object to the categories field of the calendar
	public void addCategory(Category c) {
		this.categories.add(c);
	}
	// Method used to add an Event object to the events field of the calendar
	public void addEvent(Event e) {
		this.events.add(e);
	}
	
	// Helper method used to transfer all nodes in the nodes field of the calendar to their respective sets, events, or categories
	// field in the calendar
	public void parseNodes() {
		for(Node n : nodes) {
			if (n instanceof Event) {
				this.events.add((Event) n);
			}
			else if (n instanceof Category) {
				this.categories.add((Category) n);
			}
			else if (n instanceof Set) {
				this.sets.add((Set) n);
			}
		}
	}
	
	// Methodd used to clear all fields of the calendar
	public void clear() {
		this.nodes.clear();
		this.sets.clear();
		this.categories.clear();
		this.events.clear();
	}
	
	// GETTERS AND SETTERS
	public ArrayList<Set> getSetList() {
		return sets;
	}
	public void setSetList(ArrayList<Set> setList) {
		this.sets = setList;
	}
	public ArrayList<Event> getEventList() {
		return events;
	}
	public void setEventList(ArrayList<Event> eventList) {
		this.events = eventList;
	}
	public ArrayList<Category> getCategoryList() {
		return categories;
	}
	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categories = categoryList;
	}
	
	// Method used to remove a Set object from the sets field of the calendar
	public void removeSet(Set s) {
		this.sets.remove(s);
	}
	public void removeSet(int ID) {
		for(Set s : sets) {
			if(s.getID() == ID){
				this.sets.remove(s);
			}
		}
	}
	// Method used to remove a Category object from the categories field of the calendar
	public void removeCategory(Category c) {
		this.categories.remove(c);
	}
	public void removeCategory(int ID) {
		for(Category c : categories) {
			if(c.getID() == ID){
				this.categories.remove(c);
			}
		}
	}
	// Method used to remove an Event object from the events field of the calendar
	public void removeEvent(Event e) {
		this.events.remove(e);
	}
	public void removeEvent(int ID) {
		for(Event e : events) {
			if(e.getID() == ID){
				this.events.remove(e);
			}
		}
	}
}
