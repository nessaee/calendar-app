package menu.calender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import datatype.*;
import db.setup.DB;

public class CalendarController {
	private User U;
	private DB Data;
	public CalendarController(User u, DB db) {
		U = u;
		Data = db;
	}
	
	
	public void loadSet(Set s) { //loadrow(string tablename, id)
		Data.loadRow("Set", s.getID());
	}
	public void loadCategory(Category c) {
		Data.loadRow("Category", c.getID());
	}
	public void loadEvent(Event e) {
		Data.loadRow("Event", e.getID());
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
	
//		for(int i = 0; i < 30; i++) {
//			if(EventList.get(i).getDate() == i) {//ex 20221115
//				System.out.println(i + ". " + EventList.get(i).getName());
//			}
//		}
	}
}
