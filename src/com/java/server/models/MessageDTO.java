package com.java.server.models;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.interfaces.IToMap;
import com.ocpsoft.pretty.time.PrettyTime;

public class MessageDTO implements IToMap{
	private String messageBody;
	private String postdate;
	private String authorName;
	private String recipient;
	private int messageId;
	private int chatRoomId;
	Map<String,String> map = new ConcurrentHashMap<String,String>();
	
	public MessageDTO(ConcurrentHashMap<String, String> map){
		this.messageBody = map.get("messageBody");
		String prettyTimeString = new PrettyTime(new Locale("")).format(new Date());
		this.postdate = prettyTimeString;
		this.authorName = map.get("authorName");
		this.recipient = map.get("recipient");
		this.messageId = Integer.parseInt(map.get("messageId"));
		this.chatRoomId = Integer.parseInt(map.get("chatRoomId"));
	}
	
	@Override
	public Map<String, String> toMap() {
		map.put("messageBody", this.messageBody);
		map.put("postdate", this.postdate);
		map.put("authorName", this.authorName);
		map.put("recipient", this.recipient);
		map.put("messageId", Integer.toString(this.messageId));
		map.put("chatRoomId", Integer.toString(this.chatRoomId));
		return map;
	}
	
	public String getMessageBody() {
		return messageBody;
	}
	
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	public String getPostdate() {
		return postdate;
	}
	
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public int getMessageId() {
		return messageId;
	}
	
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	public int getChatRoomId() {
		return chatRoomId;
	}
	
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
}
