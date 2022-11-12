package datatype;

import java.util.ArrayList;

public class Set extends Node{
	private ArrayList<Collection> collections;
	
	public Set() {
		collections = new ArrayList<Collection>();
	}
	
	public void importExisting() {
		
	}
	public void addCollection(Collection aCollection) {
		this.collections.add(aCollection);
	}
	public String toString() {
		return this.getName();
	}
}
