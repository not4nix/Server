package com.java.server.models;

import java.util.concurrent.ConcurrentHashMap;

public class Chat {
	/**
	 * private fields
	 */
	private String info;
	private String roomname;
	private String creatorName;
	private int roomId;
	
	public Chat(){
		
	}
	
	public Chat(ConcurrentHashMap<String,String> hash){
		this.info = hash.get("info");
		this.roomname = hash.get("roomname");
		this.creatorName = hash.get("creatorName");
	}
	
	public ConcurrentHashMap<String,String> toHash(){
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("info", this.info);
		map.put("roomname", this.roomname);
		map.put("creatorName", this.creatorName);
		return map;
	}
	
	/**
	 * Getters
	 */
	public String getInfo(){
		return info;
	}
	
	public String getName(){
		return roomname;
	}
	
	public String getCreator(){
		return creatorName;
	}
	
	public int getRoomId(){
		return roomId;
	}
	
	/**
	 * Setters
	 */
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void setName(String roomname){
		this.roomname = roomname;
	}
	
	public void setCreator(String creatorName){
		this.creatorName = creatorName;
	}
	
	public void setRoomId(int roomId){
		this.roomId = roomId;
	}
}
