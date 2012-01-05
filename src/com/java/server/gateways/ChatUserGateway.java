package com.java.server.gateways;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatUserGateway extends TableGateway{
	private String tableName = "chatuser_tbl";
	public ChatUserGateway(Connection conn) {
		super(conn, "chatuser_tbl");
		// TODO Auto-generated constructor stub
	}

	public synchronized void kickOutTheRoom(int userId, int chatId){
		try {
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `userId`="+userId+" AND `chatRoomId`="+chatId+"";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
