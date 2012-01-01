package com.java.server.gateways;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.database.Database;
import com.java.server.utils.ResponseCodes;

public class ChatGateway extends TableGateway{

	protected ChatGateway(String users, String friends, String messages,
			String chat, String membership, String chatUser) {
		super(users, friends, messages, chat, membership, chatUser);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllRooms(){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT * FROM "+getChatTable());
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(ChatGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized ResultSet findAllRoomsOfCreator(String creatorName){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT * FROM "+getChatTable()+"WHERE `creatorName` ='"+creatorName+"'");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(ChatGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String createRoom(String info, String roomname, String creatorName, Date creationDate){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("INSERT INTO "+getChatTable()+"(`info`,`roomname`,`creatorName`,`creationDate`) " +
					"VALUES('"+info+"','"+roomname+"','"+creatorName+"','"+creationDate+"')");
		}
		catch(SQLException ex){
			Logger.getLogger(ChatGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.RoomAdded.toString();
	}
	
	public synchronized String deleteRoom(int roomId){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("DELETE FROM "+getChatTable()+"WHERE `id` ="+roomId);
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(ChatGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.RoomDeleted.toString();
	}
}
