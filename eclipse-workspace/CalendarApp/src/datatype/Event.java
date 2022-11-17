package datatype;

import java.util.ArrayList;

/*
 * This is an Event class extends from Node, and represents events in real life. It contains fields for date, urgency, 
 * personal ID, and description for the event. It is used in the Calendar object, stored in the database, and can be edited.
 */
public class Event extends Node{
	// Declaration of fields
	private Date date;
	private Integer urgency;
	private Integer sID;
	private String description;
	
	// Constructor that takes in parent ID, personal ID, label, description, urgency, and date as parameters.
	// It then initializes the event using the Node constructor, and sets its personal fields.
	public Event(Integer parentID, Integer ID, String label, String description, Integer urgency, Integer date) {
		super(parentID, ID, label);
		this.description = description;
		this.urgency = urgency;
		this.date = new Date(date);
	}

	// GETTERS AND SETTERS
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getUrgency() {
		return urgency;
	}
	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
	}
	public Integer getsID() {
		return sID;
	}
	public void setsID(Integer sID) {
		this.sID = sID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Method to sort the Event information into an ArrayList<Object>, which allows it to be added to the database.
	@Override 
	public ArrayList<Object> getRowData() {
		//Event: (pID, ID, label, description, urgency, date) 
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(this.getParentID());
		rowData.add(this.getID());
		rowData.add(this.getLabel());
		rowData.add(this.description);
		rowData.add(this.urgency);
		rowData.add(this.date.toInt());
		return rowData;	
	}
	
	// Method to return the class information in the form of a String
	@Override 
	public String toString() {
		String id = String.format("%6s", String.valueOf(this.getID()) + "|");
		String label = String.format("%10s", this.getLabel() + "|");
		String description = String.format("%20s", this.description + "|");
		String urgency = String.format("%10s", String.valueOf(this.urgency) + "|");
		String date = String.format("%14s", this.date.toString() + "|");
		String text = "|" + id + label + description + urgency + date ;
		
		return text;
	}

}
