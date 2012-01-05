package com.java.server.models;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import com.ocpsoft.pretty.time.PrettyTime;


public class Message {
	/**
	 * private fields
	 */
	private String messageBody;
	private String messageType;
	private String recipient;
	private String author;
	private String postdate;
	private int messageId;
	private int chatRoomId;
	
	public Message(){
		
	}
	
	public Message(ConcurrentHashMap<String, String> hash){
		this.messageBody = hash.get("messageBody");
		this.messageType = hash.get("messageType");
		this.recipient = hash.get("recipient");
		this.author = hash.get("author");
		Calendar cal = Calendar.getInstance();
		PrettyTime pt = new PrettyTime();
		this.postdate = pt.format(cal.getTime());
	}
	
    public ConcurrentHashMap<String,String> toHash(){
		ConcurrentHashMap<String,String> hash = new ConcurrentHashMap<String,String>();
		hash.put("messageBody", this.messageBody);
		hash.put("messageType", this.messageType);
		hash.put("recipient", this.recipient);
		hash.put("author", this.author);
		hash.put("postdate", this.postdate);
		hash.put("messageId", Integer.toString(this.messageId));
		hash.put("chatRoomId", Integer.toString(this.chatRoomId));
		return hash;
	}
	
	/**
	 * Getters and setters
	 */
	public String getMessageBody(){
		return messageBody;
	}
	
	public String getMessageType(){
		return messageType;
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public String getPostdate(){
		return postdate;
	}
	
	public int getMessageId(){
		return messageId;
	}
	
	public int getChatRoomId(){
		return chatRoomId;
	}
	//Setters
	public void setMessageBody(String messageBody){
		this.messageBody = messageBody;
	}
	
	public void setMessageType(String messageType){
		this.messageType = messageType;
	}
	
	public void setRecipient(String recipient){
		this.recipient = recipient;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setPostdate(String postdate){
		this.postdate = postdate;
	}
	
	public void setMessageId(int messageId){
		this.messageId = messageId;
	}
	
	public void setRoomId(int chatRoomId){
		this.chatRoomId = chatRoomId;
	}
}
