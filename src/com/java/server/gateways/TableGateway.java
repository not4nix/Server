package com.java.server.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.utils.MapWrapper;

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
		try {
			st = con.createStatement();
			st.executeUpdate("DELETE FROM "+_tableName+"WHERE `id` ="+id);
		}
		catch(SQLException ex){
			Logger.getLogger(TableGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public synchronized ResultSet select(){
		try {
			st = con.createStatement();
			st.executeQuery("SELECT * FROM "+_tableName);
		}
		catch(SQLException ex){
			Logger.getLogger(TableGateway.class.getName()).log(Level.SEVERE,null,ex);
		}
		return rs;
	}
	
	public synchronized void insert(MapWrapper map) throws SQLException{
		map.setType(con, _tableName);
		String getFields = "(";
		String getValues = " VALUES (";

		for(int i = 0;i < map.mapLength();i++)
		{
			getFields += "`" + map.getValue(i).key + "`,";
			getValues += "'" + map.getValue(i).value + "',";
		}

		getFields = getFields.substring(0, getFields.length()-1);
		getFields += ")";

		getValues = getValues.substring(0, getValues.length()-1);
		getValues += ")";

		Statement stat = con.createStatement();
		sql = "INSERT INTO " + _tableName +" "+ getFields+getValues+"";
		stat.executeUpdate(sql);
	}
	
	public synchronized void update(MapWrapper map, String value, String field) throws SQLException{
		st = con.createStatement();
		map.setType(con, _tableName);
		sql = "UPDATE "+_tableName+" SET ";
		sql = sql.substring(0, sql.length()-1);
		st.executeQuery("UPDATE "+_tableName+ "SET " + " WHERE `" + field + "` = '"+value+"'");
	}
}
