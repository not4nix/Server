package com.java.server.utils;

public class KeyValue {
	/**
	 * @see http://goo.gl/hA6UN for details
	 * modified
	 */
	public String key;
	public String value;
	public Object type;
	
	public KeyValue(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public void setType(Object type){
		this.type = type;
	}
}
