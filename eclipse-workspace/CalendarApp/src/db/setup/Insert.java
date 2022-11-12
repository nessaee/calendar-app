package db.setup;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;
import java.sql.Statement;  
   
public class Insert {  
   
    private Connection connect() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C://sqlite/SSSIT.db";  
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;  
    }  
   
  
    public void write(String name, double capacity) {  
        String sql = "INSERT INTO employees(name, capacity) VALUES(?,?)";  
   
        try{  
            Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, name);  
            pstmt.setDouble(2, capacity);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    public void read(String table){  
        String sql = "SELECT * FROM " + table;  
          
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("uId") +  "\t" +   
                                   rs.getString("username") + "\t" +  
                                   rs.getString("password"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
   
} 