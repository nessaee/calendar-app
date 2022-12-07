package db.setup;
import db.setup.Create;
import db.setup.Edit;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * This class contains the database used to store all user information for the calendar. It creates the table formatting upon creation.
 */
public class DB {  
	// Declaration of fields
	private Connection conn = null;
	private String fileName;
	
	// Constructor that takes in a filename as a parameter and creates a database. It uses the Create class to properly
	// format itself for use by the calendar.
	public DB(String fileName) {
		this.fileName = fileName;
		Connection conn = connect();
		Create.createDatabase(conn);
		// Initialize tables: CREATE TABLE IF NOT EXISTS <name> 
		Create.createTable(conn, "Users"); 
		Create.createTable(conn, "Sets");
		Create.createTable(conn, "Categories");
		Create.createTable(conn, "Events");
		Create.createTable(conn, "IDs");
		close();
	}
	
	/* CONNECTION */
	// Method used to create a connection to the java SQLite databse.
	public Connection connect() {  
    	conn = null;
        try {  
            // db parameters  
            String url = "jdbc:sqlite:src/db/files/" + this.fileName;  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);    
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        return conn;
    }  
	
	// Method used to close the database.
	public void close() {
    	try {
    		if(conn != null) conn.close();    
    	}
        catch (SQLException ex) {
        	System.out.println(ex.getMessage());  
        }
    }
	
	/* OPERATIONS */
	// Method to save a row into the database
	public void saveRow(String tablename, ArrayList<Object> rowData) {
		Edit.saveRow(this.conn, tablename, rowData);
	}
	// Method to remove a row from the database
	public void removeRow(int ID) {
		Edit.deleteRow(this.conn,  ID);
	}
	// Method to load a row from the database
	public ArrayList<Object> loadRow(String tablename, int ID) {
		return Edit.loadRow(this.conn, tablename, ID);
	}
	// Method to load a subset from the database
	public ArrayList<ArrayList<Object>> loadSubset(int pID, String tablename){
		return Edit.loadSubset(this.conn, pID, tablename);
	}
	// Method to load all subsets from the database
	public ArrayList<ArrayList<ArrayList<Object>>> loadAllSubsets(int pID){
		return Edit.loadAllSubsets(this.conn, pID);
	}
	// Method to load a table from the database
	public ArrayList<ArrayList<Object>> loadTable(String tablename) {
		return Edit.loadTable(this.conn, tablename);
	}
	// Method to view a table from the database
	public void viewTable(String tablename) {
		Edit.viewTable(conn, tablename);
	}
	// Method to check if a user exists in the database
	public int checkUser(String username, String password) {
		return Edit.checkUser(this.conn, username, password);
	}
	// Method to establish a connection to the database
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	// Method to get a connection to the database
	public Connection getConnection() {
		return this.conn;
	}
	// Method to get the next ID in a table
	public int getNextID(String tablename) {
		return Edit.getNextID(this.conn, tablename);
	}
	// Method to get the parent name given ID
	public String getName(int ID) {
		return Edit.getName(this.conn, ID);
	}
	


	
	
	
   
}  