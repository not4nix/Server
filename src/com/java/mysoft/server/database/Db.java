package com.java.mysoft.server.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.java.mysoft.server.interfaces.IDbConnProps;
import com.java.mysoft.server.util.logging.Logger;

public class Db implements IDbConnProps{
	private Connection conn;
	private static volatile Db instance;
	
	private Db(){
		
	}
	
	public static Db getInstance(){
		if(instance == null){
			synchronized(Db.class){
				if(instance == null){
					instance = new Db();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public synchronized Connection createConnection(){
		try {
			Class.forName(JdbcDriver);
			conn = DriverManager.getConnection(DB,User,Password);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return conn;
	}
}
