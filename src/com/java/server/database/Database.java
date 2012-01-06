package com.java.server.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.java.server.interfaces.IDbConnectionProps;

public class Database implements IDbConnectionProps{
	private Connection conn;
	private static volatile Database instance;
	
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
	
	public Connection getConnection(){
		return conn;
	}
	
	public synchronized Connection connect(){
		try {
			Class.forName(JdbcDriver);
			conn = DriverManager.getConnection(DB,User,Password);
		}
		catch(Exception ex){
			
		}
		return conn;
	}
}
