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
                    + " ID integer PRIMARY KEY,\n"
                    + " username text NOT NULL,\n"  
                    + " password text NOT NULL\n"
                    + ");"; 
				break;
			case "Sets":
                // SQL statement for creating a new set table   
                sql = "CREATE TABLE IF NOT EXISTS Sets (\n"  
                    + " pID integer NOT NULL,\n"
                    + " ID integer PRIMARY KEY,\n"  
                    + " label text NOT NULL\n"   
                    + ");";  
				break;
			case "Categories":
                // SQL statement for creating a new collections table   
                sql = "CREATE TABLE IF NOT EXISTS Categories (\n"  
                    + " pID integer NOT NULL,\n"
                    + " ID integer PRIMARY KEY,\n"
                    + " label text NOT NULL\n"   
                    + ");";  
				break;
			case "Events":
				// SQL statement for creating a new events table   
                sql = "CREATE TABLE IF NOT EXISTS Events (\n"  
                    + " pID integer NOT NULL,\n"
                    + " ID integer PRIMARY KEY,\n"
                    + " label text NOT NULL,\n" 
                    + " description text NOT NULL,\n"
                    + " urgency int NOT NULL\n,"
                    + " date int NOT NULL\n"
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
            //conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();   
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
   
}