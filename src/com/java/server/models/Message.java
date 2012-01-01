package com.java.server.models;

import java.util.Date;

public class Message {
	/**
	 * private fields
	 */
	private String _messageBody;
	private String _messageType;
	private Date _postDate;
	private String _recipient;
	private String _author;
	
	public Message(String body, String type, Date date, String recipient, String author){
		_messageBody = body;
		_messageType = type;
		_postDate = date;
		_recipient = recipient;
		_author = author;
	}
}
