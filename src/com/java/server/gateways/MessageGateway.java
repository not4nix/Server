package com.java.server.gateways;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.database.Database;
import com.java.server.utils.ResponseCodes;

public class MessageGateway extends TableGateway{

	protected MessageGateway(String users, String friends, String messages,
			String chat, String membership, String chatUser) {
		super(users, friends, messages, chat, membership, chatUser);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllMessagesByAuthor(String author){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT `messageBody` FROM "+getMessageTable()+"WHERE `author` ='"+author+"'");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(MessageGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized ResultSet findMessagesByDate(Date postdate){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT `messageBody` FROM "+getMessageTable()+"WHERE `postdate` ='"+postdate+"'");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(MessageGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String addMessage(String messageBody, String messageType, Date postdate, 
			String recipient, String author){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("INSERT INTO "+getMessageTable()+"(`messageBody`,`messageType`,`postdate`,`recipient`,`author`) " +
					"VALUES("+messageBody+"','"+messageType+"','"+postdate+"','"+recipient+"','"+author+"')");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(MessageGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.MessageAdded.toString();
	}
	
	public synchronized String deleteMessage(int id){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("DELETE FROM "+getMessageTable()+"WHERE `id` ="+id);
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(MessageGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.MessageDeleted.toString();
	}
}
