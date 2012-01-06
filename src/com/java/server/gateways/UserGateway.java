package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserGateway extends TableGateway{

	String tableName = "user_tbl";
	public UserGateway(Connection conn) {
		super(conn, "user_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findUserById(int id){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM "+tableName+"WHERE `userId`="+id+"");
		}
		catch(SQLException ex){
			//TODO: Logging
		}
		return null;
	}
	
	public synchronized ResultSet findUserByLogin(String login){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM "+tableName+"WHERE `login`='"+login+"'");
		}
		catch(SQLException ex){
			//TODO: Logging
		}
		return null;
	}
	
	public synchronized void deleteUser(int id){
		try {
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM "+tableName+"WHERE `userId`="+id+"");
		}
		catch(SQLException ex){
			//TODO: Logging
		}
	}
	
	public synchronized void deleteAllUsers(){
		try {
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM "+tableName);
		}
		catch(SQLException ex){
			//TODO: Logging
		}
	}
	
	public synchronized boolean checkCredentials(String login, String password){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `login`,`password` FROM "+tableName+"WHERE `login`='"+login+"' " +
					"AND `password`='"+password+"'");
			if(rs.next()){
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex){
			//TODO:logging
		}
		return false;
	}
	
	public synchronized void changePassword(String login, String password){
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE "+tableName+"SET `password` ='"+password+"' WHERE `login`='"+login+"'";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			//TODO: Logging
		}
	}
	
	public synchronized void changeLogin(String login, int userId){
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE "+tableName+"SET `login` ='"+login+"' WHERE `userId` ="+userId+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
	}
}
