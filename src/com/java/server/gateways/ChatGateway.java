package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatGateway extends TableGateway{

	String tableName = "chat_tbl";
	public ChatGateway(Connection conn) {
		super(conn, "chat_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllRooms(){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM "+tableName+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			//TODO: logging
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
			//TODO: logging
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
			//TODO: logging
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
			//TODO: logging
		}
	}
	
	public synchronized void deleteConcreteRoom(int roomId){
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM "+tableName+"WHERE `roomId`="+roomId+"");
		}
		catch(SQLException ex){
			//TODO: logging
		}
	}

}
