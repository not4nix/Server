package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.server.util.logging.Log;

public class FriendshipGateway extends TableGateway{

	String tableName = "friendship_tbl";
	public FriendshipGateway(Connection conn) {
		super(conn, "friends_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findFriendById(int friendshipId){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `friendName` FROM "+tableName+" WHERE `friendshipId` ="+friendshipId+"");
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized void createFriend(String friendName, String initiatorName){
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO "+tableName+"(`friendName`,`initiatorName`) " +
					"VALUES('"+friendName+"','"+initiatorName+"'");
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	public synchronized ResultSet findAllFriendsOfInitiator(String initiatorName){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+"WHERE `initiatorName`='"+initiatorName+"'");
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
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
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void deleteFriend(int id){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `friendshipId`="+id+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
}
