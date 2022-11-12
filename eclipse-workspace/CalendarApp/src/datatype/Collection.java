package datatype;

import java.util.ArrayList;

public class Collection extends Node{
	private ArrayList<Event> events;
	
	public Collection() {
		this.events = new ArrayList<Event>();
	}
	
	
	public void importExisting() { //q: existing eventlist? 
		
	}
	public void addEvent(Event anEvent) {
		this.events.add(anEvent);
	}
	public String toString() {
		return this.getName();
	}
}
