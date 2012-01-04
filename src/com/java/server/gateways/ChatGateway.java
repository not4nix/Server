package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatGateway extends TableGateway{
	private String tableName = "chat_tbl";
	
	public ChatGateway(Connection con, String tableName) {
		super(con, "chat_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllUsersInRoom(int id){
		try {
			Statement st = con.createStatement();
			String sql = "SELECT user_tbl.id,user_tbl.login FROM chat_tbl,chatUser_tbl,user_tbl" +
			      "WHERE chat.id = chatUser.chatId AND" +
				  "chatUser.user_tbl_id = user_tbl.id AND" +
			      "chat_tbl.id = "+id+"";
			st.executeQuery(sql);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public synchronized int findRoomId(String roomname, String userId){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT `id` FROM "+tableName+"WHERE `roomname`='"+roomname+"' AND `userId` ="+userId+"";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
			}
		}
		catch(SQLException ex){
			Logger.getLogger(ChatGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return 0;
	}
}
