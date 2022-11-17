package db.setup;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;  
import java.sql.Statement;  
  
/*
 * This class creates the databse upon initialization. It makes the database in the necessary format
 * to have it store the Calendar information.
 */
public class Create {  
   
	// Static method that takes in a Connection and String as parameters to create the database table formatting.
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
			case "IDs":
				// SQL statement for creating a new events table   
                sql = "CREATE TABLE IF NOT EXISTS IDs (\n"  
                    + " type text PRIMARY KEY,\n"
                    + " id integer NOT NULL\n"
                    + ");\n";  
                Edit.executeUpdate(conn, sql);
                sql =  " INSERT INTO IDs VALUES ('set', 1);\n";
                sql += " INSERT INTO IDs VALUES ('category', 101);\n";
                sql += " INSERT INTO IDs VALUES ('event', 501);\n";
                Edit.initializeIDs(conn, sql);
				return;
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
	
	// Static method that creates the databse from a Connection.
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