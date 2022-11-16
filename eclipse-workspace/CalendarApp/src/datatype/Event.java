package datatype;

import java.util.ArrayList;

public class Event extends Node{
	
	private Date date;
	private Integer urgency;
	private Integer sID;
	private String description;
	public Event(Integer parentID, Integer ID, String label, String description, Integer urgency, Integer date) {
		super(parentID, ID, label);
		this.description = description;
		this.urgency = urgency;
		this.date = new Date(date);
	}
	@Override
	public String toString() {
		return this.getLabel();
	}

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
	
	@Override 
	public ArrayList<Object> getRowData() {
		//Event: (uID, sID, cID, eID, label, description, urgency, date) 
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(this.getParentID());
		rowData.add(this.getID());
		rowData.add(this.getLabel());
		rowData.add(this.description);
		rowData.add(this.urgency);
		rowData.add(this.date.toInt());
		return rowData;	
	}

}
