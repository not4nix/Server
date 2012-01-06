package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatUserGateway extends TableGateway{
	
    String tableName = "chatuser_tbl";
	public ChatUserGateway(Connection conn) {
		super(conn, "chatuser_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllUsersInChat(int userId, int chatRoomId){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `userId`="+userId+" AND `chatRoomId`="+chatRoomId+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
		return null;
	}
	
	public synchronized void kickUserFromRoom(int userId, int chatRoomId){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `userId`="+userId+" AND `chatRoomId`="+chatRoomId+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
	}
}
