package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageGateway extends TableGateway{

	String tableName = "message_tbl";
	public MessageGateway(Connection conn) {
		super(conn, "message_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet findAllMessages(){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+"");
		}
		catch(SQLException ex){
			//TODO: logging
		}
		return null;
	}
	
	public synchronized ResultSet findAllMessagesByAuthor(String author){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM "+tableName+"WHERE `author`='"+author+"'";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			//TODO:logging
		}
		return null;
	}
	
	public synchronized ResultSet findAllRecentMessages(String postdate){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT `messageBody` FROM "+tableName+"WHERE `postdate='"+postdate+"'";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
		return null;
	}
	
	public synchronized ResultSet findAllRecipients(String recipient){
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT '"+recipient+"' FROM "+tableName+"";
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			//TODO: logging
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
			//TODO: logging
		}
	}
	
	public synchronized void deleteMessageByDate(String postdate){
		try {
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+tableName+"WHERE `postdate`='"+postdate+"'";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			//TODO: logging
		}
	}
}
