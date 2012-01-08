package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import com.java.server.util.logging.Log;

public abstract class TableGateway {
	protected Connection con;
	private String tableName;
	private ResultSet rs;
	private Statement st;
	private String sql;
	public TableGateway(Connection conn, String table){
		this.con = conn;
		tableName = table;
	}
	
	public synchronized void delete(int id){
		/**
		 * sql template 
		 * DELETE FROM `users_tbl` WHERE `id`=2
		 */
		try {
			st = con.createStatement();
			st.executeUpdate("DELETE FROM "+tableName+"WHERE `id`="+id+"");
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized ResultSet select(){
		/**
		 * sql template
		 * SELECT * FROM `users_tbl`
		 */
		try {
			st = con.createStatement();
			st.executeQuery("SELECT * FROM "+tableName);
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
		return null;
	}
	
	public synchronized void insert(Map<String,String> map, String field, Object value){
		try {
			st = con.createStatement();
			//fields are fields in database table
			String fields = "(";
			//inserted value
			String values = " VALUE(";
			for(Map.Entry<String, String> thispairs : map.entrySet()){
				fields += "`" + thispairs.getKey() + "`,";
				values += "'" + thispairs.getValue() + "',";
			}
			fields = fields.substring(0,fields.length()-1);
			fields += ")";
			values = values.substring(0,values.length()-1);
			values += ")";
			st.executeUpdate("INSERT INTO "+tableName+""+field+value+"");
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void update(Map<String,String> map, String field, Object value){
		try {
			st = con.createStatement();
			for(Map.Entry<String, String> thispairs : map.entrySet()){
				sql = "`" + thispairs.getKey() + "`=";
				sql = "'" + thispairs.getValue() + "',";
			}
			st.executeUpdate("UPDATE "+tableName+ "SET " + "WHERE `"+field+"`='"+value+"'");
		}
		catch(SQLException ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
}
