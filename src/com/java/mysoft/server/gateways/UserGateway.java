package com.java.mysoft.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.util.logging.Logger;

public class UserGateway extends TableGateway{
	
	String tableName = "user_tbl";
	public UserGateway(Connection conn){
		super(conn,"user_tbl");
	}
	
	//find single login from table by using userId
	//generally it looks like: SELECT `login` FROM user_tbl WHERE `userId` = 5;
	public synchronized ResultSet findById(int id){
		try {
			Connection conn = Db.getInstance().createConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT `login` FROM "+tableName+" WHERE `userId` ="+id+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	//change user login
	public synchronized void changeLogin(String login, int id){
		try {
			Connection conn = Db.getInstance().createConnection();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE "+tableName+"SET `login` ='"+login+"' WHERE `userId` ="+id+"";
			stmt.executeUpdate(sql);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//change user password
	public synchronized void changePassword(String password, int id){
		try {
			Connection conn = Db.getInstance().createConnection();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE "+tableName+"SET `password` ='"+password+"' WHERE `userId` ="+id+"";
			stmt.executeUpdate(sql);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//change user email
	public synchronized void changeUserEmail(String email, int id){
		try {
			Connection conn = Db.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE "+tableName+"SET `email` ='"+email+"' WHERE `userId` ="+id+"";
			stmt.executeUpdate(sql);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//check user credentials(e.g login and password)
	public synchronized boolean checkCredentials(String login, String password){
		try {
			Connection conn = Db.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT `login`,`password` FROM "+tableName+"WHERE `login` ='"+login+"' AND `password` ='"+password+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return false;
	}
}
