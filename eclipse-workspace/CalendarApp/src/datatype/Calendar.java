package datatype;

import java.util.ArrayList;

public class Calendar {
	private ArrayList<Node> elementList;
	private ArrayList<Set> sets;
	private ArrayList<Event> events;
	private ArrayList<Category> categories;
	
	public Calendar() {
		elementList = new ArrayList<Node>();
		sets = new ArrayList<Set>();
		events = new ArrayList<Event>();
		categories = new ArrayList<Category>();
	}
	
//	public String toString() { //q: format for string?
//	
//	}
	
	public void addSet(Set s) {
		this.sets.add(s);
	}
	public void addCategory(Category c) {
		this.categories.add(c);
	}
	public void addEvent(Event e) {
		this.events.add(e);
	}
	public void setElementlist(ArrayList<Node> E) {
		this.elementList = E;
	}
	public ArrayList<Node> getElementList(){
		return this.elementList;
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

	public void setElementList(ArrayList<Node> elementList) {
		this.elementList = elementList;
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
