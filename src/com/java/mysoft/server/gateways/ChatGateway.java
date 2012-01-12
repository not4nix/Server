package com.java.mysoft.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.util.logging.Logger;

public class ChatGateway extends TableGateway{
	String tableName = "chatroom_tbl";
	public ChatGateway(Connection conn) {
		super(conn, "chat_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findRoomCreator(String roomname, String creatorName){
		try{
			Connection conn = Db.getInstance().createConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT '"+creatorName+"' FROM "+tableName+" WHERE `roomname` ='"+roomname+"'";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized ResultSet findConcreteRoom(String roomname){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `roomname`='"+roomname+"'";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized ResultSet findRoomById(int roomId){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `roomId`="+roomId+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized ResultSet getAllChatUsers(int id){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT users_tbl.login, users_tbl.userId FROM chatroom_tbl,chatuser_tbl,users_tbl " +
			             "WHERE chatroom_tbl.chatRoomId = chatuser_tbl.chatRoomId " +
			             "AND chatuser_tbl.chatUserId = users_tbl.userId AND chatroom_tbl.chatRoomId = "+id+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized void deleteAllRooms(){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
}
