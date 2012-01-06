package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendshipGateway extends TableGateway{

	String tableName = "friends_tbl";
	public FriendshipGateway(Connection conn) {
		super(conn, "friends_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAll(){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
		}
		catch(SQLException ex){
			//TODO: logging
		}
		return null;
	}
	
	public synchronized ResultSet findAllFriendsOfInitiator(String initiatorName){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+"WHERE `initiatorName`='"+initiatorName+"'");
		}
		catch(SQLException ex){
			//TODO: logging
		}
		return null;
	}
	
	public synchronized void deleteAll(){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName;
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
	}
	
	public synchronized void deleteFriend(int id){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `friendshipId`="+id+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
	}
}
