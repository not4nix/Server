package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageGateway extends TableGateway{
	private String tableName = "messages_tbl";
	public MessageGateway(Connection conn) {
		super(conn, "messages_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet getMessagesFromRoom(int id){
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `chatRoomId` ="+id+"";
			st.executeQuery(sql);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public synchronized ResultSet getRecentMessagesFromRoom(int id, String postdate){
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `chatRoomId` ="+id+" AND `postdate`='"+postdate+"'";
			st.executeQuery(sql);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
}
