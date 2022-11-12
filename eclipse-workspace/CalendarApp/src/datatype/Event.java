package datatype;

public class Event extends Node{
	private String category;
	private Date date;
	private String urgency;
	
	
	public String toString() {
		return this.getName();
	}

}
