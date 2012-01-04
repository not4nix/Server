package com.java.server.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapWrapper {
    private ArrayList<KeyValue> _kv = new ArrayList<KeyValue>();
	
	//put into map
	public void add(String key, String value){
		_kv.add(new KeyValue(key, value));
	}
	
	public void setType(Connection con, String tableName){
		try {
			/**
			 * Non java-doc
			 * @see DatabaseMetaData in JDK for details
			 */
			DatabaseMetaData dmd = con.getMetaData();
			/**
			 * Get columns
			 * 
			 * Example ResultSet result = dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern)
			 * 
			 * 
			 * So, we haven't column catalog, schema pattern and columnNamePattern
			 * We set this values as null. Only tableNamePattern will aquire value
			 */
			ResultSet result = dmd.getColumns(null, null, tableName, null);
			//Column name
			ArrayList<String> Column = new ArrayList<String>();
			ArrayList<String> Type = new ArrayList<String>();
			//while result set is next line. Get string
			while(result.next()){
				Column.add(result.getString("Column"));
				Type.add(result.getString("result"));
			}
			for(int i = 0; i < _kv.size(); i++){
				for (int a = 0; a < Column.size(); a++){
					//if key values equals column
					if(_kv.get(i).key.equals(Column.get(a))){
						_kv.get(i).setType(Type.get(a));
					}
				}
			}
		}
		catch(SQLException ex){
			Logger.getLogger(MapWrapper.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	/**
	 * Map length
	 * @return KeyValue size
	 */
	public int mapLength(){
		return _kv.size();
	}
	
	/**
	 * Key value get value
	 * @param index index of value
	 * @return keyvalue index
	 */
	public KeyValue getValue(int index){
		return _kv.get(index);
	}
}
