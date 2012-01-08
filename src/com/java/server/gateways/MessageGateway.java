package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.server.util.logging.Log;

public class MessageGateway extends TableGateway{

	String tableName = "message_tbl";
	public MessageGateway(Connection conn) {
		super(conn, "message_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllRecentMessages(String postdate){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT `messageBody` FROM "+tableName+"WHERE `postdate='"+postdate+"'";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occurred " + ex.toString());
		}
		return null;
	}
	
	
	public synchronized void deleteAll(){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occurred " + ex.toString());
		}
	}
	
	public synchronized void deleteMessageByDate(String postdate){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `postdate`='"+postdate+"'";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occurred " + ex.toString());
		}
	}
	
	public synchronized void getMessageFromRoom(int id){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT FROM "+tableName+"WHERE `chatRoomId` ="+id+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
}
