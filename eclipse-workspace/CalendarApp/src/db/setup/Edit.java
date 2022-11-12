package db.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Edit {
	public static void addRow(Connection conn, String tablename, List<Object> rowData) {  
		String sql;
		sql = "INSERT INTO " + tablename;
		switch(tablename) {
			case "Users":
				sql += "(uID, username, password) VALUES(?,?,?)";
				break;
			case "Sets":
				sql += "(uID, sID, label) VALUES(?,?,?)";
				break;
			case "Collections":
				sql += "(uID, sID, cID, label) VALUES(?,?,?,?)";
				break;
			case "Events":
				sql += "(uID, sID, cID, eID, label, description, urgency) VALUES(?,?,?,?,?,?,?)";
				break;
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			switch(tablename) {
				// (uID, username, password) 
				case "Users":
					pstmt.setInt(1, (int) rowData.get(0));  
		            pstmt.setString(2, (String) rowData.get(1));  
		            pstmt.setString(3, (String) rowData.get(2));  
		            break;
		        // (uID, sID, label)
				case "Sets":
					pstmt.setInt(1, (int) rowData.get(0));  
		            pstmt.setInt(2, (int) rowData.get(1));  
		            pstmt.setString(3, (String) rowData.get(2));  
		            break;
		        // (uID, sID, cID, label)
				case "Collections":
					pstmt.setInt(1, (int) rowData.get(0));  
					pstmt.setInt(2, (int) rowData.get(1));  
					pstmt.setInt(3, (int) rowData.get(2));  
		            pstmt.setString(4, (String) rowData.get(3));  
		            break;
		        // (uID, sID, cID, eID, label, description, urgency)
				case "Events":
					pstmt.setInt(1, (int) rowData.get(0));  
					pstmt.setInt(2, (int) rowData.get(1));  
					pstmt.setInt(3, (int) rowData.get(2));  
					pstmt.setInt(4, (int) rowData.get(3));  
		            pstmt.setString(5, (String) rowData.get(4));  
		            pstmt.setString(6, (String) rowData.get(5));  
		            pstmt.setString(7, (String) rowData.get(6));  
		            break;
			}
			pstmt.executeUpdate(); 
		}
		catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    public static void viewTable(Connection conn, String tablename){  
        String sql = "SELECT * FROM " + tablename;  
          
        try {   
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("uId") +  "\t" +   
                                   rs.getString("username") + "\t" +  
                                   rs.getString("password"));  
            }  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
}
