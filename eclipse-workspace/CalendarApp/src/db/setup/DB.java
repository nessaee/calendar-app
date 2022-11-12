package db.setup;
import db.setup.Create;

import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
   
public class DB {  
	private Connection conn;
	private String fileName;
	public DB(String fileName) {
		this.fileName = fileName;
		this.conn = connect();
		//Create.createDatabase(conn);
		//Create.createTable(conn, "Users");
	}
	
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
	
	public void write(int uID, String username, String password) {  
		String sql = "INSERT INTO Users(uID, username, password) VALUES(?,?,?)";  
		   
        try{  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setInt(1, uID);  
            pstmt.setString(2, username);  
            pstmt.setString(3, password);  
            pstmt.executeUpdate();  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }   
    }  
    
    public void read(String table){  
        String sql = "SELECT * FROM " + table;  
          
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
	
    public void close() {
    	try {  
            if (conn != null) {  
                conn.close();  
            }  
        } 
    	catch (SQLException ex) {  
            System.out.println(ex.getMessage());  
        }  
    }
	public Connection getConnection() {
		return this.conn;
	}
	
	
	
	
   
}  