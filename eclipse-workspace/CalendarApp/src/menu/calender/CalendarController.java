package menu.calender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import datatype.*;
import db.setup.DB;

public class CalendarController {
	private User U;
	public CalendarController(User u) {
		U = new User();
		U.setUserID(u.getUserID());
		U.setCalendar(u.getCalendar());
		DB db = new DB("Data.db");
	}
	
	public void loadSet(Set s) {
		
	}
	public void loadCategory(Category c) {
		
	}
	public void loadEvent(Event e) {
		
	}
	public void printCalender() {
		ArrayList<Node> EventList = new ArrayList<Node>();
		// ^ an array list to hold only the event objects 
		for(int i = 0; i < U.getCalendar().getElementList().size();i++) {
			if (U.getCalendar().getElementList().get(i).getID() > 1000) {
				EventList.add(U.getCalendar().getElementList().get(i));
			}
		}
		//now to sort this array by sID
		Collections.sort(EventList, Comparator.comparing(Node::getID));
		
		for(int i = 0; i < 30; i++) {
			System.out.println(i + ". " + EventList.get(i).getName());
		}
	}
}
