package com.java.server.gateways;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.database.Database;
import com.java.server.utils.ResponseCodes;

public class UserGateway extends TableGateway{

	protected UserGateway(String users, String friends, String messages,
			String chat, String membership, String chatUser) {
		super(users, friends, messages, chat, membership, chatUser);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ResultSet getAllUsers(){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeQuery("SELECT * FROM "+getUserTable());
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public synchronized String createUser(String login, String email, String password){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("INSERT INTO "+getUserTable()+"(`login`,`password`,`email`) " +
					"VALUES("+login+"','"+email+"','"+password+")");
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.UserAdded.toString();
	}
	
	public synchronized String updateUser(String login, String email, String password, int userId){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("UPDATE "+getUserTable()+
					"SET `login` = '"+login+"'," +
				     "`password` = '"+password+"'," +
				     "`email` = '"+email+"'" +
				     "Where `id` = "+userId+""
				     );
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.UserUpdated.toString();
	}
	
	public synchronized String deleteUser(int userId){
		try {
			Statement stmt = Database.getInstance().createConnection().createStatement();
			stmt.executeUpdate("DELETE FROM "+getUserTable()+"WHERE `id` = "+userId);
			stmt.close();
		}
		catch(SQLException ex){
			Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return ResponseCodes.UserDeleted.toString();
	}
}
