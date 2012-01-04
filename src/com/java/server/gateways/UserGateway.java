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
	public UserGateway(Connection con) {
		super(con, "users_tbl");
		// TODO Auto-generated constructor stub
	}
	
	public synchronized boolean validateCredentials(String password){
		try {
			Statement stmt = con.createStatement();
			sql = "SELECT `login` FROM "+tableName+"WHERE `password` ='"+password+"'";
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
	
	public synchronized String createUser(String login, String password, String email){
		try {
			Statement stmt = con.createStatement();
			sql = "INSERT INTO"+tableName+"(`login`,`password`,`email`) VALUES('"+login+"','"+password+"','"+email+"')";
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.UserAdded.toString();
	}
	
	public synchronized ResultSet findAllUsers(){
		try {
			Statement stmt = con.createStatement();
			sql = "SELECT * FROM "+tableName;
			ResultSet rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String deleteUser(int userId){
		try {
			Statement stmt = con.createStatement();
			sql = "DELETE FROM "+tableName+"WHERE `userId`"+userId;
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.UserDeleted.toString();
	}
}
