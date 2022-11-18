package driver;

import java.util.ArrayList;
import java.util.List;

import db.setup.DB;

/*
 * This is a Driver class used to test the database functionality.
 */
public class DB_Driver {
	public static void loadData(DB db, int startID, int numObjects, String tablename) {
		ArrayList<Object> rowData = new ArrayList<Object>();
		int ID = 0;
		for(int i = startID; i < numObjects + startID; i++){
			switch(tablename) {
			case "Users":
				db.saveRow(tablename, userData(i,startID));
				break;
			case "Sets":
				ID = db.getNextID("set");
				db.saveRow(tablename, setData(i,ID,startID));
				break;
			case "Categories":
				ID = db.getNextID("category");
				db.saveRow(tablename, categoryData(i,ID,startID));
				break;
			case "Events":
				ID = db.getNextID("event");
				db.saveRow(tablename, eventData(i,ID,startID));
				break;
			}
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + tablename);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
		db.viewTable(tablename);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static ArrayList<Object> userData(int i, int startID) {
		ArrayList<Object> rowData = new ArrayList<Object>();
		int index = i - startID;
		rowData.add(i);
		rowData.add("user" + String.valueOf(index));
		rowData.add("password" + String.valueOf(index));
		return rowData;
	}
	
	public static ArrayList<Object> setData(int i, int id, int startID) {
		int index = i - startID;
		ArrayList<Object> rowData = new ArrayList<Object>();
		int pID = (i%2==0) ? 1001 : 1002;
		rowData.add(pID); // parent ID
		rowData.add(id); // set ID
		rowData.add("set" + String.valueOf(index));
		return rowData;
	}
	
	public static ArrayList<Object> categoryData(int i, int id, int startID) {
		int index = i - startID;
		ArrayList<Object> rowData = new ArrayList<Object>();
		int pID = (i%2==0) ? 1 : 2;
		rowData.add(pID); // parent ID
		rowData.add(id); // category ID
		rowData.add("category" + String.valueOf(index));
		return rowData;
	}
	
	public static ArrayList<Object> eventData(int i, int id, int startID) {
		int index = i - startID;
		ArrayList<Object> rowData = new ArrayList<Object>();
		int pID = (i%2==0) ? 1001 : 1002;
		rowData.add(pID); // parent ID
		rowData.add(id); // event ID
		rowData.add("event" + String.valueOf(index));
		rowData.add("event_description" + String.valueOf(index));
		rowData.add(10);
		rowData.add(20221117);
		return rowData;
	}
	
	public static void main(String[] args) {
		DB db = new DB("Data.db");
		db.setConnection(db.connect());
		int numObjects, startID;
		// Add numUsers user table 
		// rowData = {uID, username, password}
	
		
		numObjects = 10;
		startID = 1001;
		loadData(db, startID, numObjects, "Users");
		System.out.println("\n\n\n");
		startID = 1;
		loadData(db, startID, numObjects, "Sets");
		System.out.println("\n\n\n");
		startID = 101;
		loadData(db, startID, numObjects, "Categories");
		System.out.println("\n\n\n");
		startID = 101;
		loadData(db, startID, numObjects, "Events");
	
		
		
		db.close();
	
	}

}
