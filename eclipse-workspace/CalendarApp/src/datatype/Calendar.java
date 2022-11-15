package datatype;

import java.util.ArrayList;

public class Calendar {
	private ArrayList<Node> elementList;
	
	public Calendar() {
		elementList = new ArrayList<Node>();
	}
	
//	public String toString() { //q: format for string?
//	
//	}
	public void setElementlist(ArrayList<Node> E) {
		this.elementList = E;
	}
	public ArrayList<Node> getElementList(){
		return this.elementList;
	}
}
