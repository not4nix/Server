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
		
	}
	
	public synchronized void update(MapWrapper map, String value, String field) throws SQLException{
		
	}
}
