package com.java.mysoft.server.dto;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.java.mysoft.server.interfaces.IToMap;
import com.ocpsoft.pretty.time.PrettyTime;

/**
 * Chat data transfer object entity
 * implements interface IToMap
 * I've decided user ConcurrentHashMap because it is synchronized and
 * put and get methods are constant-time
 * Pretty time is library that provides date time formatting in
 * human-readable format: e.g one day ago
 * @author not4nix
 * 
 */
public class ChatDTO implements IToMap{
	private String info;
	private String roomname;
	private String creatorName;
	private String creationDate;
	private int chatRoomId;
	private int userId;
	Map<String,String> map = new ConcurrentHashMap<String,String>();
	
	public ChatDTO(ConcurrentHashMap<String,String> map){
		this.info = map.get("info");
		this.roomname = map.get("roomname");
		this.creatorName = map.get("creatorName");
		String prettyTimeString = new PrettyTime(new Locale("")).format(new Date());
		this.creationDate = prettyTimeString;
		this.chatRoomId = Integer.parseInt(map.get("chatRoomId"));
		this.userId = Integer.parseInt(map.get("userId"));
	}

	/**
	 * Interface method
	 */
	@Override
	public Map<String, String> toMap() {
		map.put("info", this.info);
		map.put("roomname", this.roomname);
		map.put("creatorName", this.creatorName);
		map.put("creationDate", this.creationDate);
		map.put("chatRoomId", Integer.toString(this.chatRoomId));
		map.put("userId", Integer.toString(this.userId));
		return map;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the roomname
	 */
	public String getRoomname() {
		return roomname;
	}

	/**
	 * @param roomname the roomname to set
	 */
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	/**
	 * @return the creatorName
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * @param creatorName the creatorName to set
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}

