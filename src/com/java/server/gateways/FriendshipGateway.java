package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FriendshipGateway extends TableGateway{
	private String tableName = "friendship_tbl";
	public FriendshipGateway(Connection conn) {
		super(conn, "friendship_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllFriendsOfInitiator(String initiatorName){
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `initiatorName`='"+initiatorName+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String friendName = rs.getString("friendName");
			}
		}
		catch(SQLException ex){
			Logger.getLogger(FriendshipGateway.class.getName()).log(Level.SEVERE, "Exception occured", ex);
		}
		return null;
	}
	
	public synchronized void addFriend(String friendName, String initiatorName){
		try {
			Statement st = conn.createStatement();
			String sql = "INSERT INTO "+tableName+"(`friendName`,`initiatorName`) VALUES('"+friendName+"','"+initiatorName+"')";
			st.executeUpdate(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(FriendshipGateway.class.getName()).log(Level.SEVERE, "Exception occured", ex);
		}
	}
	
	public synchronized void deleteFriend(int friendId){
		try {
			Statement st = conn.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `friendId` = "+friendId;
			st.executeUpdate(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(FriendshipGateway.class.getName()).log(Level.SEVERE, "Exception occured", ex);
		}
	}
}
