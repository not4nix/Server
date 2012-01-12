package com.java.mysoft.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.mysoft.server.util.logging.Logger;

public class FriendshipGateway extends TableGateway{
	String tableName = "friendship_tbl";
	
	public FriendshipGateway(Connection conn) {
		super(conn, "friendship_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findFriendById(int friendshipId){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `friendName` FROM "+tableName+" WHERE `friendshipId` ="+friendshipId+"");
		}
		catch(SQLException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized ResultSet findAllFriendsOfInitiator(String initiatorName){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+"WHERE `initiatorName`='"+initiatorName+"'");
		}
		catch(SQLException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
}
