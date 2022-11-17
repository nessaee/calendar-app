package datatype;

import java.util.ArrayList;

/*
 * Abstact class that has subclasses Set, Category, and Event. This class is used to help represent its subclasses and contains
 * fields for its label/name, personal ID, and parent ID.
 */
public abstract class Node {
	
	// Declaration of fields
	private String label;
	private Integer currentID;
	private Integer parentID;
	
	// Default Constructor
	public Node() {
		this.label = "unknown";
		this.currentID = 0;
		this.parentID = 0;
	}
	
	// Constructor that takes in a parent ID, personal ID, and name as parameters.
	public Node(Integer parentID, Integer ID, String name){
		this.parentID = parentID;
		this.currentID = ID;
		this.label = name;
	}
	
	// GETTERS AND SETTERS
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getID() {
		return currentID;
	}
	public void setID(Integer iD) {
		currentID = iD;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	
	// Method to return the class information in the form of a String
	public String toString() {
		String id = String.format("%6s", String.valueOf(this.getID()) + "|");
		String label = String.format("%10s", this.getLabel() + "|");
		String text = "|" + id + label;
		return text;
	}
	
	// Method to add fields to an ArrayList<Object> in order to add the Node data to the database.
	public ArrayList<Object> getRowData(){
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(this.getParentID());
		rowData.add(this.getID());
		rowData.add(this.getLabel());
		return rowData;
	}
	
}

