package com.java.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.MessageGateway;
import com.java.server.models.Message;
import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
import com.java.server.utils.ResponseCodes;
import com.java.server.utils.XMLWrapper;
import com.ocpsoft.pretty.time.PrettyTime;


public class MessageController {
	
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	private String body;
	private String answer;
	private String xml;
	
   
	public synchronized void retrieveMessageFromChat(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			MessageGateway gateway = new MessageGateway(conn);
			int startRequest = request.getRequestString().lastIndexOf("chatroom/")+5;
			int endRequest = request.getRequestString().indexOf("/messages");
			String getStartEnd = request.getRequestString().substring(startRequest,endRequest);
			ResultSet messages = gateway.getMessagesFromRoom(Integer.parseInt(getStartEnd));
			answer = XMLWrapper.getInstance().createXMLRoomMessages(messages);
			response.setBody(answer);
			response.setResponseCode(ResponseCodes.MessageRetrieved.toString());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
    
	public synchronized void sendPrivateMessage(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void sendChatMessage(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			body = request.getBody();
			XMLWrapper.getInstance().parse(map, body);
			MessageGateway gateway = new MessageGateway(conn);
			Message message = new Message(map);
			PrettyTime pt = new PrettyTime();
			Calendar calendar = Calendar.getInstance();
			String postdate = pt.format(calendar.getTime());
			message.setPostdate(postdate);
			gateway.insert(message.toHash());
			response.setBody(answer);
			response.setResponseCode(ResponseCodes.MessageSentIntoChat.toString());
			ChatGateway gate = new ChatGateway(conn);
			ResultSet onlineUsers = gate.findAllUsersInRoom(Integer.parseInt(map.get("chatRoomId")));
			xml = XMLWrapper.getInstance().createXMLRDocument(Integer.parseInt("chatRoomId"),map.get("message"), 
					Integer.parseInt("userId"));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
