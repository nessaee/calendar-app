package db.setup;
import db.setup.Create;
import db.setup.Edit;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;  
   
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
	
	public void addRow(String tablename, List<Object> rowData) {
		Edit.addRow(this.conn, tablename, rowData);
	}
	
	public Connection getConnection() {
		return this.conn;
	}


	
	
	
   
}  