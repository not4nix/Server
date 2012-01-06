package com.java.server.models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.interfaces.IToMap;

public class FriendshipDTO implements IToMap{
	private String friendName;
	private String initiatorName;
	private int friendshipId;
	Map<String,String> map = new ConcurrentHashMap<String,String>();
	
	public FriendshipDTO(ConcurrentHashMap<String,String> map){
		this.friendName = map.get("friendName");
		this.initiatorName = map.get("initiatorName");
		this.friendshipId = Integer.parseInt(map.get("friendshipId"));
	}
	
	@Override
	public Map<String, String> toMap() {
		map.put("friendName", this.friendName);
		map.put("initiatorName", this.initiatorName);
		return map;
	}
	
	/* getters and setters */
	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public int getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(int friendshipId) {
		this.friendshipId = friendshipId;
	}
}
