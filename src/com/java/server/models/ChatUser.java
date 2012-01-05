package com.java.server.models;

import java.util.concurrent.ConcurrentHashMap;

public class ChatUser {
	private String login;
	private String role;
	private int chatUserId;
	private int chatRoomId;
	
	public ChatUser(){
		
	}
	
	public ChatUser(ConcurrentHashMap<String,String> hash){
		this.login = hash.get("login");
		this.role = hash.get("role");
		this.chatUserId = Integer.parseInt(hash.get("chatUserId"));
		this.chatRoomId = Integer.parseInt(hash.get("chatRoomId"));
	}
	
	public ConcurrentHashMap<String,String> toHash(){
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("login", this.login);
		map.put("role", this.role);
		map.put("chatUserId", Integer.toString(this.chatUserId));
		map.put("chatRoomId", Integer.toString(this.chatRoomId));
		return map;
	}
	
	/**
	 * Getters
	 */
	
	public String getLogin(){
		return login;
	}
	
	public String getRole(){
		return role;
	}
	
	public int getChatUserId(){
		return chatUserId;
	}
	
	public int getRoomId(){
		return chatRoomId;
	}
	
	/**
	 * Setters
	 */
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public void setRole(String role){
		this.role = role;
	}
	
	public void setChatUserId(int chatUserId){
		this.chatUserId = chatUserId;
	}
	
	public void setRoomId(int chatRoomId){
		this.chatRoomId = chatRoomId;
	}
}
