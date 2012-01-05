package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class TableGateway {
	protected Connection con;
	private String _tableName;
	private ResultSet rs;
	private Statement st;
	private String sql;
	
	public TableGateway(Connection con, String tableName){
		this.con = con;
		_tableName = tableName;
	}
	
	public synchronized void delete(int id){
		//DELETE sample: DELETE FROM `users_tbl` WHERE `id` = 5;
		try {
			st = con.createStatement();
			st.executeUpdate("DELETE FROM "+_tableName+"WHERE `id` ="+id);
		}
		catch(SQLException ex){
			
		}
	}
	
	public synchronized ResultSet select(){
		//Sql sample: SELECT * FROM `users_tbl`
		try {
			st = con.createStatement();
			st.executeQuery("SELECT * FROM "+_tableName);
		}
		catch(SQLException ex){
			
		}
		return rs;
	}
	
	public synchronized void insert(Map<String,String> map) throws SQLException{
		//INSERT INTO `users_tbl`(`login`,`password`,`email`) VALUES('user','qwerty12345','user@user.com');
		st = con.createStatement();
		String field = "(";
		String value = " VALUES(";
		for(Map.Entry<String, String> thispair : map.entrySet()){
			//building sql
			field += "`" + thispair.getKey() + "`,";
			value += "'" + thispair.getValue() + "',";
		}
		field = field.substring(0,field.length()-1);
		field += ")";
		value = value.substring(0,value.length()-1);
		value += ")";
		String SQL = "INSERT INTO "+_tableName+""+field+value+"";
		st.executeUpdate(SQL);
	}
	public synchronized void update(Map<String,String> m, String field, Object value) throws SQLException{
		//UPDATE `users_tbl` SET `login`='nick', `password`='qwerty12345', `email`='user@mail.com' WHERE `userId`=2;
		st = con.createStatement();
		sql = "UPDATE "+_tableName+" SET ";
		for(Map.Entry<String, String> thispair : m.entrySet()){
			//building sql
			sql = "`" + thispair.getKey() + "`=";
			sql = "'" + thispair.getValue() + "',";
		}
		sql = "WHERE `"+field+"`='"+value+"'";
		st.executeUpdate(sql);
	}
}
