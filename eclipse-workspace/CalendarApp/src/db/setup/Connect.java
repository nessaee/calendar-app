package db.setup;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
   
public class Connect {  
     /** 
     * Connect to a sample database 
     */  
	private Connection conn = null;  
    public Connect(String fileName) {  
    	conn = null;
        try {  
            // db parameters  
            String url = "jdbc:sqlite:src/db/files/" + fileName;  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        }  
    }  
    /** 
     * @param args the command line arguments 
     */  
}