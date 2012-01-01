package com.java.server.gateways;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.database.Database;
import com.java.server.utils.ResponseCodes;

public class ChatUserGateway extends TableGateway{

	protected ChatUserGateway(String users, String friends, String messages,
			String chat, String membership, String chatUser) {
		super(users, friends, messages, chat, membership, chatUser);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet getAllChatUsers(){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT * FROM "+getChatUserTable());
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(ChatUserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String createChatUser(String login, String role){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("INSERT INTO "+getChatUserTable()+"(`login`,`role`) " +
					"VALUES('"+login+"','"+role+"')");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(ChatUserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.ChatUserAdded.toString();
	}
	
	public synchronized String deleteChatUser(int chatUserId){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("DELETE FROM "+getChatUserTable()+ "WHERE `chatUserId` ="+chatUserId);
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(ChatUserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.UserKickedOut.toString();
	}
}
