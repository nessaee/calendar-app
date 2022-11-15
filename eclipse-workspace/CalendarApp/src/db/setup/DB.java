package db.setup;
import db.setup.Create;
import db.setup.Edit;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

   
public class DB {  
	private Connection conn;
	private String fileName;
	
	public DB(String fileName) {
		this.fileName = fileName;
		this.conn = connect();
		Create.createDatabase(conn);
		// Initialize tables: CREATE TABLE IF NOT EXISTS <name> 
		Create.createTable(conn, "Users"); 
		Create.createTable(conn, "Sets");
		Create.createTable(conn, "Categories");
		Create.createTable(conn, "Events");
	}
	
	/* CONNECTION */
	public Connection connect() {  
    	conn = null;
        try {  
            // db parameters  
            String url = "jdbc:sqlite:src/db/files/" + fileName;  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);    
            System.out.println("Connection to SQLite has been established.");  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        return conn;
    }  
	
	public void close() {
    	try {
    		if(conn != null) conn.close();    
    	}
        catch (SQLException ex) {
        	System.out.println(ex.getMessage());  
        }
    }
	
	/* OPERATIONS */
	public void saveRow(String tablename, List<Object> rowData) {
		Edit.saveRow(this.conn, tablename, rowData);
	}
	public void removeRow(String tablename, int ID) {
		Edit.deleteRow(this.conn, tablename,  ID);
	}
	public List<Object> loadRow(String tablename, int ID) {
		return Edit.loadRow(this.conn, tablename, ID);
	}
	public ArrayList<ArrayList<Object>> loadTable(String tablename) {
		return Edit.loadTable(this.conn, tablename);
	}
	public int checkUser(String username, String password) {
		return Edit.checkUser(this.conn, username, password);
	}
	public void viewTable(String tablename) {
		Edit.viewTable(conn, tablename);
	}
	public Connection getConnection() {
		return this.conn;
	}


	
	
	
   
}  