package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageGateway extends TableGateway{
	private String tableName = "messages_tbl";
	public MessageGateway(Connection con, String tableName) {
		super(con, "messages_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet getMessagesFromRoom(int id){
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `chatId` ="+id+"";
			st.executeQuery(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(MessageGateway.class.getName()).log(Level.SEVERE,"Exception occured",ex);
		}
		return null;
	}
}
