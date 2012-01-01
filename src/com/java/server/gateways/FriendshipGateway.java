package com.java.server.gateways;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.database.Database;
import com.java.server.utils.ResponseCodes;

public class FriendshipGateway extends TableGateway{

	protected FriendshipGateway(String users, String friends, String messages,
			String chat, String membership, String chatUser) {
		super(users, friends, messages, chat, membership, chatUser);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllMembersOfInitiator(String initiatorName){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT * FROM "+getFriendTable()+"WHERE `initiatorName` ='"+initiatorName+"'");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(FriendshipGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String addFriend(String friendName, String initiatorName){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("INSERT INTO "+getFriendTable()+"(`friendName`,`initiatorName`) " +
					"VALUES('"+friendName+"','"+initiatorName+"')");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(FriendshipGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.FriendAdded.toString();
	}
	
	public synchronized String deleteFriend(int friendshipId){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("DELETE FROM "+getFriendTable()+"WHERE `id` ="+friendshipId);
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(FriendshipGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.FriendDeleted.toString();
	}
}
