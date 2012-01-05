package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.utils.ResponseCodes;

public class UserGateway extends TableGateway{
	private String tableName = "users_tbl";
	private String sql;
	private ResultSet rs;
	public UserGateway(Connection conn) {
		super(conn, "users_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized boolean validateCredentials(String login, String password){
		try {
			Statement stmt = conn.createStatement();
			sql = "SELECT `login` FROM "+tableName+"WHERE `login` ='"+login+"' AND `password` ='"+password+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				return true;
			}
			if(!rs.next()){
				return false;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public synchronized ResultSet findAllUsers(){
		try {
			Statement stmt = conn.createStatement();
			sql = "SELECT * FROM "+tableName;
			rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String findUser(String email, int id){
		try {
			Statement stmt = conn.createStatement();
			sql = "SELECT `login` FROM "+tableName+"WHERE `email` ='"+email+"' AND `id` ="+id+"";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String login = rs.getString("login");
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public synchronized int deleteUser(int userId){
		try {
			Statement stmt = conn.createStatement();
			sql = "DELETE FROM "+tableName+"WHERE `userId`"+userId;
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return userId;
	}
}
