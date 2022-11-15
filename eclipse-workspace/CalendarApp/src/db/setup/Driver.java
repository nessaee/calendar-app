package db.setup;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	public static void loadData(DB db, int startID, int numObjects, String tablename) {
		ArrayList<Object> rowData = new ArrayList<Object>();
		for(int i = startID; i < numObjects + startID; i++){
			switch(tablename) {
			case "Users":
				db.saveRow(tablename, userData(i,startID));
				break;
			case "Sets":
				db.saveRow(tablename, setData(i,startID));
				break;
			case "Categories":
				db.saveRow(tablename, categoryData(i,startID));
				break;
			}
		}
		db.viewTable(tablename);
	}
	public static ArrayList<Object> userData(int i, int startID) {
		ArrayList<Object> rowData = new ArrayList<Object>();
		int index = i - startID;
		rowData.add(i);
		rowData.add("user" + String.valueOf(index));
		rowData.add("password" + String.valueOf(index));
		return rowData;
	}
	public static ArrayList<Object> setData(int i, int startID) {
		int index = i - startID;
		ArrayList<Object> rowData = new ArrayList<Object>();
		int pID = (i%2==0) ? 1001 : 1002;
		rowData.add(pID); // parent ID
		rowData.add(i); // set ID
		rowData.add("set" + String.valueOf(index));
		return rowData;
	}
	public static ArrayList<Object> categoryData(int i, int startID) {
		int index = i - startID;
		ArrayList<Object> rowData = new ArrayList<Object>();
		int pID = (i%2==0) ? 1 : 2;
		rowData.add(pID); // parent ID
		rowData.add(i); // category ID
		rowData.add("category" + String.valueOf(index));
		return rowData;
	}
	

	
	public static void main(String[] args) {
		DB db = new DB("Data.db");
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

		
		System.out.println(db.loadSubset(1001, "Sets"));
		
		db.removeRow("Users", 1002);
		db.viewTable("Users");
		db.viewTable("Sets");
		
		db.close();
	
	}

}
