package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatGateway extends TableGateway{
	private String tableName = "chat_tbl";
	private Statement stmt;
	public ChatGateway(Connection conn) {
		super(conn, "chat_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllUsersInRoom(int id){
		try {
			stmt = conn.createStatement();
			String sql = "SELECT users_tbl.id,users_tbl.login FROM chat_tbl,chatuser_tbl,users_tbl"+
			             "WHERE chat_tbl.id = chatuser_tbl.id AND" +
					     "chatuser_tbl.users_tbl.id = users_tbl.id AND "+
			             "chat_tbl.id = "+id+"";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
