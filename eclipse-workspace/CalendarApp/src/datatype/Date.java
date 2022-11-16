package datatype;

public class Date {
	private Integer month;
	private Integer day;
	private Integer year;
	
	public Date() {
		this.month = 0;
		this.day = 0;
		this.year = 0;
	}
	
	public Date(int date) {
		this.fromInt(date);
	}
	public Date(Integer month, Integer day, Integer year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public void fromInt(int date) {
		this.year = date / 10000;
		this.month = (date % 10000) / 100;
		this.day = date % 100;
	}
	public Integer toInt() {
		return this.year * 10000 + this.month*100 + this.day;
	}
	
}
