package menu.calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import datatype.*;
import db.setup.DB;
import db.setup.Edit;

public class CalendarController {
	private User user;
	private DB db;
	public CalendarController(User u, DB db) {
		this.user = u;
		this.db = db;
		System.out.println("calendar retrieved user ID, now loading...");
		this.load(u.getUserID());
		this.user.getCalendar().parseNodes();
		System.out.println("calendar load complete!");
		this.printCalendar();
	}
	
	public void load(int ID) {
		int currentID;
		String tablename;
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		System.out.println("loading db data...");
		for(ArrayList<ArrayList<Object>> table : db.loadAllSubsets(ID)) {
			if(!table.isEmpty()) {
				tablename = Edit.getTableName( (int) table.get(0).get(1));
				for(ArrayList<Object> row : table) {
					if(!row.isEmpty()) {
						currentID = (int) row.get(1);
						switch(tablename) {
							case "Sets":
								user.getCalendar().addNode(loadSet(row.get(0), row.get(1), row.get(2)));
								break;
							case "Categories":
								user.getCalendar().addNode(loadCategory(row.get(0), row.get(1), row.get(2)));
								break;
							case "Events":
								user.getCalendar().addNode(loadEvent(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)));
								break;
							default: 
								break;
						}
					}
				}
			}
		}
	}
	
	private Set loadSet(Object pID, Object ID, Object label) {
		return new Set((int) pID, (int) ID, (String) label);
	}
	private Category loadCategory(Object pID, Object ID, Object label) {
		return new Category((int) pID, (int) ID, (String) label);
	}
	private Event loadEvent(Object pID, Object ID, Object label, Object description, Object urgency, Object date) {
		return new Event((int) pID, (int) ID, (String) label, (String) label, (int) urgency, (int) date);
	}
	
	public void printCalendar() {
		System.out.println("----------------------------SETS---------------------------");
		for(Set s : user.getCalendar().getSetList()) {
			System.out.println(s);
		}
		System.out.println("-------------------------CATEGORIES-----------------------");
		for(Category c : user.getCalendar().getCategoryList()) {
			System.out.println(c);
		}
		System.out.println("---------------------------EVENTS-------------------------");
		for(Event e : user.getCalendar().getEventList()) {
			System.out.println(e);
		}
	}
}
