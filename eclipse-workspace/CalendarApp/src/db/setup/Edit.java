package db.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * This is an Edit class that contains methods to interact with the database. It is used as a utility bank for DB.java
 */
public class Edit {
	// Method to save a row into the database
	public static void saveRow(Connection conn, String tablename, ArrayList<Object> rowData) {  
		String sql;
		sql = "INSERT OR IGNORE INTO " + tablename;
		
		switch(tablename) {
			case "Users":
				sql += "(ID, username, password) VALUES(?,?,?)";
				break;
			case "Sets":
				sql += "(pID, ID, label) VALUES(?,?,?)";
				break;
			case "Categories":
				sql += "(pID, ID, label) VALUES(?,?,?)";
				break;
			case "Events":
				sql += "(pID, ID, label, description, urgency, date) VALUES(?,?,?,?,?,?)";
				break;
			case "IDs":
				sql += "(type, id) VALUES(?,?)";
				break;
		}
		// ask about templates 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			int i = 0; // list index 
			for(Object element: rowData) { 
				if(element instanceof String) {	// if typeof(element) is String
					pstmt.setString(i+1, (String) rowData.get(i));  
				} else if(element instanceof Integer) { // if typeof(element) is Integer 
					pstmt.setInt(i+1, (int) rowData.get(i));  
				}
				i++;
			}
			pstmt.executeUpdate(); 
		} 
		catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	// Method to load a row from the database
	public static ArrayList<Object> loadRow(Connection conn, String tablename, int ID){
		String sql = "SELECT * FROM " + tablename + "\n WHERE ID = " + String.valueOf(ID); 
		ResultSet rs = executeQuery(conn, sql);
		return getRowData(rs);
	}
	
	// Method to delete a row from the database
	public static void deleteRow(Connection conn, int ID) {  
		String sql = "DELETE FROM " + getTableName(ID) + "\n WHERE ID = " + String.valueOf(ID);  
		executeUpdate(conn, sql);
		deleteSubset(conn, getTableName(ID), ID);
	}
	
	// Method to delete a subset from the database
	public static void deleteSubset(Connection conn, String tablename, int ID) {
		String sql;
		if(getNext(tablename).equals("None")) {
			return; 
		}
		else {
			sql = "DELETE FROM " + getNext(tablename) + " WHERE pID = " + String.valueOf(ID);  
			executeUpdate(conn, sql);
			deleteSubset(conn, getNext(tablename), ID);
		}
	}
		
	// Method to check if a user exists in the database. If they do their userID is returned
	public static int checkUser(Connection conn, String username, String password) {
		username = username.replace(" ", "");
		password = password.replace(" ", "");
		String sql = "SELECT * FROM Users\n WHERE username = '" + username + "' AND password = '" + password + "'";
		ResultSet rs = executeQuery(conn, sql);
		try {
			if(rs == null) {
				return -1;
			}
			else {
				return rs.getInt("ID");
			}
		} 
		catch (SQLException e) {
			//e.printStackTrace();
			return -1;
		}
	}
	public static int checkID(Connection conn, int ID) {
		String table = getTableName(ID);
    	String sql = "SELECT * FROM " + table +"\n WHERE ID = " + String.valueOf(ID);
		ResultSet rs = executeQuery(conn, sql);
		try {
			if(rs == null) return -1;
			else return rs.getInt("ID");
		} 
		catch (SQLException e) {
			//e.printStackTrace();
			return -1;
		}
    }
    
	// Method to load a table from the database
	public static ArrayList<ArrayList<Object>> loadTable(Connection conn, String tablename) {
		 ArrayList<ArrayList<Object>> tableData = new ArrayList<>();
    	 String sql = "SELECT * FROM " + tablename;     
         ResultSet rs = executeQuery(conn, sql);
             
         try {
            // loop through table rows
            while(rs.next()) {
            	tableData.add(getRowData(rs));
            }
    	 } 
         catch (SQLException e) {
        	 e.printStackTrace();
    	 }  	
         return tableData;
	}
    
	// Method to view a table from the database
	public static void viewTable(Connection conn, String tablename){  
        String sql = "SELECT * FROM " + tablename;     
        ResultSet rs = executeQuery(conn, sql);
        String columnLabel;
         
        try {
        	ResultSetMetaData metadata = rs.getMetaData();
        	// print column names
        	for(int i = 1; i <= metadata.getColumnCount(); i++) {
        		System.out.print(metadata.getColumnLabel(i) +  "\t\t");
        	}
        	System.out.println("");
        	System.out.println("");
        	// loop through table rows
        	while(rs.next()) {
        		// loop through column data for each row
		        for(int i = 1; i <= metadata.getColumnCount(); i++) {
		        	// extract column label 
			   	    columnLabel = metadata.getColumnLabel(i);
			   	    // print (row, column) cell data 
			   	    System.out.print(rs.getObject(columnLabel) +  "\t\t"); 
			   	    
		        }  
		        System.out.println("");
        	}
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}  
    } 
    
	// Method to execute a Query in the database
    public static ResultSet executeQuery(Connection conn, String sql) {
    	Statement stmt;
    	ResultSet rs = null;
    	try {
	    	stmt  = conn.createStatement();  
	        rs    = stmt.executeQuery(sql);  
    	} 
    	catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
    	return rs;
    }
    
    // Method to execute ID initialization inserts for empty IDs table
    public static void initializeIDs(Connection conn, String sql) {
    	PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    // Method to execute an update in the database
    public static void executeUpdate(Connection conn, String sql) {
    	PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
    }

    // Method to return row data from the database
    private static ArrayList<Object> getRowData(ResultSet rs) {
		ArrayList<Object> rowData = new ArrayList<Object>();
		String columnLabel;
		try {   
			 ResultSetMetaData metadata = rs.getMetaData();
			 // loop through column data for row
	         for(int i = 1; i <= metadata.getColumnCount(); i++) {
	        	 // extract column label 
		   	     columnLabel = metadata.getColumnLabel(i);
		   	     rowData.add(rs.getObject(columnLabel));	    
	         }      
		 } catch (SQLException e) {  
	         System.out.println(e.getMessage());  
	     }  
		
	     return rowData;
	}
    
    // Method to load a subset of data from the database
    public static ArrayList<ArrayList<Object>> loadSubset(Connection conn, int pID, String tablename){
    	ArrayList<ArrayList<Object>> subsetData = new ArrayList<>();
    	
   	 	String sql = "SELECT * FROM " + tablename + "\n WHERE pID = " + String.valueOf(pID);
        ResultSet rs = executeQuery(conn, sql);
            
        try {
           // loop through table rows
           if(rs != null) {
	           while(rs.next()) {
	           	subsetData.add(getRowData(rs));
	           }
           }
   	 	} catch (SQLException e) {
   		 	e.printStackTrace();
   	 	}  	
        return subsetData;
    }
    
    // Method to load all row data for subsets of an object 
    public static ArrayList<ArrayList<ArrayList<Object>>> loadAllSubsets(Connection conn, int pID){
    	ArrayList<ArrayList<ArrayList<Object>>> subsetData = new ArrayList<>();
    	String tablename = getNext(getTableName(pID));
    	while( !tablename.equals("None")) {
        	subsetData.add(loadSubset(conn, pID, tablename));
        	tablename = getNext(tablename);
        }
        return subsetData;
    }
    
    // Method to move to the next data table in the database
    private static String getNext(String tablename) {
    
    	switch(tablename) {
    	case "Users":
			return "Sets";
		case "Sets":
			return "Categories";
		case "Categories":
			return "Events";
		default: 
			return "None";
    	}
    }
    
    // Method to return a specific table from the database based on the id given
    public static String getTableName(int ID) {
    	if(ID <= 0) return "Invalid";
    	else if(ID <= 100) return "Sets";
    	else if(ID <= 500) return "Categories"; 
    	else if(ID <= 1000) return "Events";
    	else return "Users";
    }
    
    // Method to return a the next available ID for a type of object 
    // idType = "set", "category", or "event"
    public static int getNextID(Connection conn, String idType) {
    	
    	String sql = "SELECT * FROM IDs \n WHERE type = '" + idType + "'";
		ResultSet rs = executeQuery(conn, sql);
		int id = 0;
		try {
			id = rs.getInt("id");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "UPDATE IDs SET id = " + String.valueOf(id+1) + " WHERE type = '" + idType + "'";
		executeUpdate(conn, sql);
		
		return id;
    }
    
    public static String getName(Connection conn, int ID) {
    	String tableName = getTableName(ID);
    	String sql = "SELECT * FROM " + tableName + " \n WHERE ID = " + String.valueOf(ID);
		ResultSet rs = executeQuery(conn, sql);
		String name = "";
		String pName = "";
		int pID = 0;
		try {
			if(tableName.equals("Users")) {
				name = rs.getString("username") + "/";
			}
			else if (tableName.equals("Sets")) {
				pID = rs.getInt("pID");
				pName = getName(conn, pID);
				name = pName + rs.getString("label") + "/";
			}
			else if (tableName.equals("Categories")) {
				pID = rs.getInt("pID");
				pName = getName(conn, pID);
				name = pName + rs.getString("label") + "/";
			}
			else {
				name = rs.getString("label") + "/";
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		return name;
    }
    
}
