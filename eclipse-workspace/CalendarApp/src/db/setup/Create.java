package db.setup;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  
   
public class Create {  
   
	public static void createTable(Connection conn, String tableName) {  
        // SQLite connection string  
    
        String sql = "";
        System.out.println("hello");
        if(tableName.equals("Users")) {  
        	// SQL statement for creating a new users table   
            sql = "CREATE TABLE IF NOT EXISTS Users (\n"  
           	    + " uID integer PRIMARY KEY,\n"
                + " username text NOT NULL,\n"  
                + " password text NOT NULL\n"
                + ");"; 
            System.out.println("hello");
        }
        else if(tableName.equals("Sets")) { 
        	// SQL statement for creating a new set table   
        	sql = "CREATE TABLE IF NOT EXISTS Sets (\n"  
        		+ " uID integer NOT NULL,\n"
                + " sID integer NOT NULL,\n"  
                + " label text NOT NULL,\n"   
                + ");";  
        }
        
        else if(tableName.equals("Collections")) {  
            // SQL statement for creating a new collections table   
            sql = "CREATE TABLE IF NOT EXISTS Collections (\n"  
           		+ " uID integer PRIMARY KEY,\n"
                + " sID integer NOT NULL,\n"  
                + " cID integer NOT NULL,\n"
                + " label text NOT NULL,\n"   
                + ");";  
        }
        
        else if(tableName.equals("Events")) {
            // SQL statement for creating a new events table   
            sql = "CREATE TABLE IF NOT EXISTS Events (\n"  
                + " uID integer PRIMARY KEY,\n"
                + " sID integer NOT NULL,\n"  
                + " cID integer NOT NULL,\n"
                + " eID integer NOT NULL,\n"
                + " label text NOT NULL,\n" 
                + " description text NOT NULL,\n"
                + " urgency int NOT NULL,\n"
                + ");";  
        }
        
       
       	
        try{  
            //conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement(); 
            stmt.execute(sql);  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
    public static void createDatabase(Connection conn) {  
   
        //String url = "jdbc:sqlite:src/db/files/" + fileName;  
   
        try {  
            //Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
   
}