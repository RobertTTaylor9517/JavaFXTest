package org.openjfx.hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
	
	public Connection Connect() {
		
		try {
			String url ="jdbc:postgresql://localhost:5432/fxdb";
			String username = "postgres";
			String password = "shoppass";
			
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
			
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
		
		
		
	}
}
