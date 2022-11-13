package datatype;

public class Event extends Node{
	private Category category;
	private Date date;
	private Integer urgency;
	
	public Event() {
		
	}
	public Event(Category C, Date D, Integer U) {
		
	}
	//TODO: finish this method, push to dev, think about ID assignment, 
	//ask about edit cal methods
	public String toString() {
		return this.getName();
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
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

}
