package datatype;

public class Date {
	private String month;
	private String day;
	private String year;
	
	public Date() {
		this.month = "unknown";
		this.day = "unknown";
		this.year = "unknown";
	}
	public Date(String month, String day, String year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
}
