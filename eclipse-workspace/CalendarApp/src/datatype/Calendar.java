package datatype;

import java.util.ArrayList;

public class Calendar {
	private ArrayList<Node> elementList;
	private ArrayList<Set> setList;
	private ArrayList<Event> eventList;
	private ArrayList<Category> categoryList;
	
	public Calendar() {
		elementList = new ArrayList<Node>();
		setList = new ArrayList<Set>();
		eventList = new ArrayList<Event>();
		categoryList = new ArrayList<Category>();
	}
	
//	public String toString() { //q: format for string?
//	
//	}
	public void setElementlist(ArrayList<Node> E) {
		this.elementList = E;
	}
	public ArrayList<Node> getElementList(){
		return this.elementList;
	}

	public ArrayList<Set> getSetList() {
		return setList;
	}

	public void setSetList(ArrayList<Set> setList) {
		this.setList = setList;
	}

	public ArrayList<Event> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}

	public void setElementList(ArrayList<Node> elementList) {
		this.elementList = elementList;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
