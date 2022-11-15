package db.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Edit {
	public static void saveRow(Connection conn, String tablename, List<Object> rowData) {  
		String sql;
		sql = "INSERT OR IGNORE INTO " + tablename;
		
		switch(tablename) {
			case "Users":
				sql += "(uID, username, password) VALUES(?,?,?)";
				break;
			case "Sets":
				sql += "(uID, sID, label) VALUES(?,?,?)";
				break;
			case "Categories":
				sql += "(uID, sID, cID, label) VALUES(?,?,?,?)";
				break;
			case "Events":
				sql += "(uID, sID, cID, eID, label, description, urgency, date) VALUES(?,?,?,?,?,?,?,?)";
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
	
	// FIXME: add exception for invalid key
	public static ArrayList<Object> loadRow(Connection conn, String tablename, int ID){
		String sql = "SELECT * FROM " + tablename + "\n WHERE " + getID(tablename) + "=" + String.valueOf(ID); 
		ResultSet rs = executeQuery(conn, sql);
		return getRowData(rs);
	}
	
	public static void deleteRow(Connection conn, String tablename, int ID) {  
		String sql = "DELETE FROM " + tablename + "\n WHERE " + getID(tablename) + "=" + String.valueOf(ID);  
		executeUpdate(conn, sql);
	}
	
	public static int checkUser(Connection conn, String username, String password) {
		String sql = "SELECT * FROM Users WHERE username = " + username + " AND password = " + password;
		ResultSet rs = executeQuery(conn, sql);
		try {
			return rs.getInt("uID");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}


	public static ArrayList<ArrayList<Object>> loadTable(Connection conn, String tablename) {
		 ArrayList<ArrayList<Object>> tableData = new ArrayList<>();
    	 String sql = "SELECT * FROM " + tablename;     
         ResultSet rs = executeQuery(conn, sql);
             
         try {
            // loop through table rows
            while(rs.next()) {
            	tableData.add(getRowData(rs));
            }
    	 } catch (SQLException e) {
    		 	e.printStackTrace();
    	 }  	
         return tableData;
	}
    
	
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
    
    private static String getID(String tablename){
		return Character.toLowerCase(tablename.charAt(0)) + "ID";
	}
	
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
	
}
