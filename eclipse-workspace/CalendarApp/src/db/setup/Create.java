package db.setup;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;  
import java.sql.Statement;  
   
public class Create {  
   
	public static void createTable(Connection conn, String tablename) {  
        // SQLite connection string  
    
        String sql = "CREATE TABLE IF NOT EXISTS " + tablename;
        switch(tablename) {
			case "Users":
				// SQL statement for creating a new users table   
                sql = "CREATE TABLE IF NOT EXISTS Users (\n"  
                    + " uID integer PRIMARY KEY,\n"
                    + " username text NOT NULL,\n"  
                    + " password text NOT NULL\n"
                    + ");"; 
				break;
			case "Sets":
                // SQL statement for creating a new set table   
                sql = "CREATE TABLE IF NOT EXISTS Sets (\n"  
                    + " uID integer NOT NULL,\n"
                    + " sID integer PRIMARY KEY,\n"  
                    + " label text NOT NULL\n"   
                    + ");";  
				break;
			case "Categories":
                // SQL statement for creating a new collections table   
                sql = "CREATE TABLE IF NOT EXISTS Categories (\n"  
                    + " uID integer NOT NULL,\n"
                    + " sID integer NOT NULL,\n"  
                    + " cID integer PRIMARY KEY,\n"
                    + " label text NOT NULL\n"   
                    + ");";  
				break;
			case "Events":
				// SQL statement for creating a new events table   
                sql = "CREATE TABLE IF NOT EXISTS Events (\n"  
                    + " uID integer NOT NULL,\n"
                    + " sID integer NOT NULL,\n"  
                    + " cID integer NOT NULL,\n"
                    + " eID integer PRIMARY KEY,\n"
                    + " label text NOT NULL,\n" 
                    + " description text NOT NULL,\n"
                    + " urgency int NOT NULL\n"
                    + ");";  
				break;
            default:
                sql = "";
                break;
		}
       
       	
        try {  
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(sql);  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
    public static void createDatabase(Connection conn) {  
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