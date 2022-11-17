package datatype;

import java.util.ArrayList;

import db.setup.DB;
import db.setup.Edit;

public class Calendar {
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Set> sets = new ArrayList<Set>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	
	public Calendar() {
		nodes = new ArrayList<Node>();
		sets = new ArrayList<Set>();
		events = new ArrayList<Event>();
		categories = new ArrayList<Category>();
	}
	
	public Calendar(int userID, DB db) {
		System.out.println("Calendar now loading...");
		load(userID, db);
		this.parseNodes();
		System.out.println("Load complete!");
	}
	
//	public String toString() { //q: format for string?
//	
//	}
	public void load(int ID, DB db) {
		int currentID;
		String tablename;
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		for(ArrayList<ArrayList<Object>> table : db.loadAllSubsets(ID)) {
			if(!table.isEmpty()) {
				tablename = Edit.getTableName( (int) table.get(0).get(1));
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
	}
	
	private Set loadSet(Object pID, Object ID, Object label) {
		return new Set((int) pID, (int) ID, (String) label);
	}
	private Category loadCategory(Object pID, Object ID, Object label) {
		return new Category((int) pID, (int) ID, (String) label);
	}
	private Event loadEvent(Object pID, Object ID, Object label, Object description, Object urgency, Object date) {
		return new Event((int) pID, (int) ID, (String) label, (String) label, (int) urgency, (int) date);
	}
	public void addSet(Set s) {
		this.sets.add(s);
	}
	public void addCategory(Category c) {
		this.categories.add(c);
	}
	public void addEvent(Event e) {
		this.events.add(e);
	}

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

	public void removeSet(Set s) {
		this.sets.remove(s);
	}

	public void removeCategory(Category c) {
		this.categories.remove(c);
	}

	public void removeEvent(Event e) {
		this.events.remove(e);
	}
}
