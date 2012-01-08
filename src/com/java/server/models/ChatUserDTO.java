package com.java.server.models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.interfaces.IToMap;

public class ChatUserDTO implements IToMap{
	private int chatUserId;
	private int chatRoomId;
	
	private Map<String,String> map = new ConcurrentHashMap<String,String>();
	public ChatUserDTO(ConcurrentHashMap<String,String> map){
		this.chatUserId = Integer.parseInt(map.get("chatUserId"));
		this.chatRoomId = Integer.parseInt(map.get("chatRoomId"));
	}
	
	@Override
	public Map<String,String> toMap(){
		map.put("chatUserId", Integer.toString(this.chatUserId));
		map.put("chatRoomId", Integer.toString(this.chatRoomId));
		return map;
	}

	/**
	 * @return the chatUserId
	 */
	public int getChatUserId() {
		return chatUserId;
	}

	/**
	 * @param chatUserId the chatUserId to set
	 */
	public void setChatUserId(int chatUserId) {
		this.chatUserId = chatUserId;
	}

	/**
	 * @return the chatRoomId
	 */
	public int getChatRoomId() {
		return chatRoomId;
	}

	/**
	 * @param chatRoomId the chatRoomId to set
	 */
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
}
