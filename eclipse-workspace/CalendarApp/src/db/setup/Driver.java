package db.setup;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		DB db = new DB("Data.db");
		
		// Add numUsers user table 
		// rowData = {uID, username, password}
		int numUsers = 10;
		List<Object> rowData = new ArrayList<Object>();
		List<Object> userRowData = new ArrayList<Object>();
		for(int i = 1; i < numUsers; i++){
			rowData = new ArrayList<Object>();
			rowData.add(i);
			rowData.add("user" + String.valueOf(i));
			rowData.add("password" + String.valueOf(i));
	        db.saveRow("Users", rowData);
	        userRowData = db.loadRow("Users", i);
	        System.out.println(userRowData);
		}
		
		// Add numSets belonging to user 1 to sets table  
		// rowData = {uID, sID, label}
		int numSets = 10;
		List<Object> setRowData = new ArrayList<Object>();
		for(int i = 1; i < numSets; i++){
			rowData = new ArrayList<Object>();
			rowData.add(1); // user ID
			rowData.add(i); // set ID
			rowData.add("set" + String.valueOf(i));
	        db.saveRow("Sets", rowData);
	        setRowData = db.loadRow("Sets", i);
	        System.out.println(setRowData);
		}
		
		// Add numCategories belonging to set 1 to categories table 
		// rowData = {uID, sID, cID, label}
		int numCategories = 10;
		List<Object> catRowData = new ArrayList<Object>();
		for(int i = 1; i < numCategories; i++){
			rowData = new ArrayList<Object>();
			rowData.add(1); // user ID
			rowData.add(1); // set ID
			rowData.add(i); // category ID
			rowData.add("category" + String.valueOf(i));
	        db.saveRow("Categories", rowData);
	        catRowData = db.loadRow("Categories", i);
	        System.out.println(catRowData);
		}
		
		
		

        db.close();
	}

}
