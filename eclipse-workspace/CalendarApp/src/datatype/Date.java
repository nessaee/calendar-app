package datatype;

/*
 * This is a Date class that is used to represent a date in time. It stores fields for month, day, and year.
 * This is used to give points of occurence for Event objects.
 */
public class Date {
	// Declaration of fields.
	private Integer month;
	private Integer day;
	private Integer year;
	
	// Default Constructor
	public Date() {
		this.month = 0;
		this.day = 0;
		this.year = 0;
	}
	
	// Constructor that creates a Date object from a given integer. It parses the integer using a helper method fromInt.
	public Date(int date) {
		this.fromInt(date);
	}
	
	// Constructor that creates a Date object given 3 integers. It takes in a month, day, and year.
	// as parameters.
	public Date(Integer month, Integer day, Integer year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	// Helper method for a Constructor that parses an integer.
	public void fromInt(int date) {
		this.year = date / 10000;
		this.month = (date % 10000) / 100;
		this.day = date % 100;
	}
	
	// Method to return the date information in the form of a string
	public String toString() {;
		String year = String.valueOf(this.year);
		String month = String.valueOf(this.month);
		String day = String.valueOf(this.day);
		return month + "/" + day + "/" + year;
	}
	
	// Method that returns the year, month, and day as a single integer that represents all information.
	public Integer toInt() {
		return this.year * 10000 + this.month*100 + this.day;
	}
	
}
