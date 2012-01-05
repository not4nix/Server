package com.java.server.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	
	private static volatile Database instance;
	private String JdbcDriver = "com.mysql.jdbc.Driver";
	//database url
	private String Db = "jdbc:mysql://localhost:3306/chat";
	//database user name
	private String user = "yao";
	//database user password
	private String password = "261594873aa";
	private Connection conn;
	
	
	
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
//	public void auth() throws IOException{
//		try {
//			File f = new File("D:\\Server\\Database.txt");
//			if(!f.exists()){
//				f.createNewFile();
//			}
//			RandomAccessFile raf = new RandomAccessFile(f, "r");
//			try {
//				Db = raf.readLine();
//				username = raf.readLine();
//				password = raf.readLine();
//			}
//			catch(IOException ex){
//				ex.printStackTrace();
//			}
//		}
//		catch(FileNotFoundException ex){
//			ex.printStackTrace();
//		}
//	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public synchronized Connection connect(){
		try {
			try {
				Class.forName("JdbcDriver");
			}
			catch(ClassNotFoundException ex){
				//TODO: Make own logger
				Logger.getLogger(Database.class.getName()).log(Level.FINEST, null, ex);
			}
			try {
//				auth();
				conn = DriverManager.getConnection(Db, user, password);
			}
			catch(SQLException ex){
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
		catch(Exception ex){
			Logger.getLogger(Database.class.getName()).log(Level.WARNING,null,ex);
		}
		return conn;
	}
	
//	private class ConnectionPool {
//		
//	}
}
