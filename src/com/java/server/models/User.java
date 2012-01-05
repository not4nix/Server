package com.java.server.models;

import java.util.concurrent.ConcurrentHashMap;

public class User {
	/**
	 * private fields
	 */
	private String login;
	private String password;
	private String email;
	private int userId;
	
	public User(){
		
	}
	
	/**
	 * User Constructor
	 * @param hash
	 */
	public User(ConcurrentHashMap<String,String> hash){
		this.login = hash.get("login");
		this.password = hash.get("password");
		this.email = hash.get("email");
	}
	
	/**
	 * If we will use HashMap we need remember that HashMap not synchronized
	 * If during the HashMap iterator has been changed (without using their own methods for iterators), 
	 * then iterate through the result will be unpredictable.
	 * @return
	 */
	public ConcurrentHashMap<String, String> toHash(){
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("login", this.login);
		map.put("password", this.password);
		map.put("email", this.email);
		return map;
	}
	
	/**
	 * Getters
	 */
	public String getLogin(){
		return login;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getEmail(){
		return email;
	}
	
	public int getUserId(){
		return userId;
	}
	
	/**
	 * Setters
	 */
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
}
