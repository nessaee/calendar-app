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
				sql += "(uID, sID, cID, eID, label, description, urgency) VALUES(?,?,?,?,?,?,?)";
				break;
		}
		// ask about templates 
		/* "Users": // (uID, username, password) 
		 * "Sets":// (uID, sID, label)
		 *  case "Categories":// (uID, sID, cID, label)
		 *  case "Events":// (uID, sID, cID, eID, label, description, urgency)
		 */
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			int i = 0; // list index 
			// check datatype of current object 
			for(Object element: rowData) { 
				if(element instanceof String) {	// if typeof(element) is String
					pstmt.setString(i+1, (String) rowData.get(i));  
				} else if(element instanceof Integer) { // if typeof(element) is Integer 
					pstmt.setInt(i+1, (int) rowData.get(i));  
				}
				System.out.println("Done");
				i++;
			}
			pstmt.executeUpdate(); 
		} catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public static int checkUser(Connection conn, String username, String password) {
		String sql = "SELECT * FROM Users WHERE username = " + username + " AND password = " + password;
		ResultSet rs = executeQuery(conn, sql);
		try {
			return rs.getInt("uID");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static void deleteRow(Connection conn, String tablename, int ID) {  
		String sql = "DELETE FROM " + tablename + "\n WHERE " + getID(tablename) + "=" + String.valueOf(ID);  
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * "Users" -> "uID" 
	 * "Sets" -> "sID"
	 * "Categories" -> "cID"
	 * "Events" -> "eID"
	 */
	private static String getID(String tablename){
		return Character.toLowerCase(tablename.charAt(0)) + "ID";
	}
	
	// FIXME: add exception for invalid key
	public static ArrayList<Object> loadRow(Connection conn, String tablename, int ID){
		ArrayList<Object> rowData = new ArrayList<Object>();
		String sql = "SELECT * FROM " + tablename + "\n WHERE " + getID(tablename) + "=" + String.valueOf(ID); 
		ResultSet rs = executeQuery(conn, sql);
		try {   
			 ResultSetMetaData metadata = rs.getMetaData();
	         for(int i = 1; i <= metadata.getColumnCount(); i++) {
		   	     String columnLabel = metadata.getColumnLabel(i);
		   	     rowData.add(rs.getObject(columnLabel));	    
	         }      
		 } catch (SQLException e) {  
	         System.out.println(e.getMessage());  
	     }  
		return rowData;
	}
	
    public static void viewTable(Connection conn, String tablename){  
        String sql = "SELECT * FROM " + tablename;     
        ResultSet rs = executeQuery(conn, sql);
        // loop through the result set  
        try {
        	while (rs.next()) {  
        		System.out.println(rs.getInt("uId") +  "\t" +   
				                   rs.getString("username") + "\t" +  
				                   rs.getString("password"));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
     } 
    
    public static ResultSet executeQuery(Connection conn, String sql) {
    	Statement stmt;
    	ResultSet rs = null;
    	try {
	    	stmt  = conn.createStatement();  
	        rs    = stmt.executeQuery(sql);  
    	} catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
    	
    	return rs;
    
    }
	
}
