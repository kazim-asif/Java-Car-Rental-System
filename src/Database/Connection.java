package Database;

import java.sql.*;

public class Connection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/rentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    static java.sql.Connection connection;

    Connection(){}
    
    public static void connect() throws ClassNotFoundException {
    	 try {
    	        Class.forName("com.mysql.cj.jdbc.Driver");
    	        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	        System.out.println("Connected to the database.");
    	    } catch (ClassNotFoundException e) {
    	        System.out.println("Failed to load the JDBC driver.");
    	        e.printStackTrace();
    	    } catch (SQLException e) {
    	        System.out.println("Failed to connect to the database.");
    	        e.printStackTrace();
    	    }
    }
	
}
