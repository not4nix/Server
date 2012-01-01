package com.java.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	
	private static volatile Database instance;
	//jdbc driver
	private String JdbcDriver = "com.mysql.jdbc.Driver";
	//database url
	private String Db = "jdbc:mysql://localhost:3306/chat";
	//database user name
	private String user = "yao";
	//database user password
	private String password = "261594873aa";
	private Connection con;
	
	
	private Database(){
		
	}
	
	public static Database getInstance(){
		if(instance == null){
			synchronized(Database.class){
				if(instance == null){
					instance = new Database();
				}
			}
		}
		return instance;
	}
	
	public synchronized Connection createConnection(){
		try {
			try {
				Class.forName(JdbcDriver);
			}
			catch(ClassNotFoundException ex){
				//TODO: Make own logger
				Logger.getLogger(Database.class.getName()).log(Level.FINEST, null, ex);
			}
			try {
				con = DriverManager.getConnection(Db, user, password);
			}
			catch(SQLException ex){
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
		catch(Exception ex){
			Logger.getLogger(Database.class.getName()).log(Level.WARNING,null,ex);
		}
		return con;
	}
	
//	private class ConnectionPool {
//		
//	}
}
