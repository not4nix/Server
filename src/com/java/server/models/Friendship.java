package com.java.server.models;

import java.util.concurrent.ConcurrentHashMap;

public class Friendship {
	/**
	 * private fields
	 */
	private String friendName;
	private String initiatorName;
	private int friendshipId;
	
	public Friendship(){
		
	}
	
	public Friendship(ConcurrentHashMap<String,String> hash){
		this.friendName = hash.get(friendName);
		this.initiatorName = hash.get(initiatorName);
	}
	
	public ConcurrentHashMap<String,String> toHash(){
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("friendName", this.friendName);
		map.put("initiatorName", this.initiatorName);
		return map;
	}
	
	/**
	 * getters
	 */
	
	public String getFriendName(){
		return friendName;
	}
	
	public String getInitiatorName(){
		return initiatorName;
	}
	
	public int getFriendshipId(){
		return friendshipId;
	}
	
	/**
	 * Setters
	 */
	
	public void setFriendName(String friendName){
		this.friendName = friendName;
	}
	
	public void setInitiatorName(String initiatorName){
		this.initiatorName = initiatorName;
	}
	
	public void setFriendshipId(int friendshipId){
		this.friendshipId = friendshipId;
	}
}
