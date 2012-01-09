package com.java.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.MessageGateway;
import com.java.server.models.MessageDTO;
import com.java.server.util.HTTPRequest;
import com.java.server.util.HTTPResponse;
import com.java.server.util.ResponseCodes;
import com.java.server.util.XMLWrapper;
import com.ocpsoft.pretty.time.PrettyTime;

public class MessageController {
	
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public MessageController(){
		
	}
	
	public synchronized void getMessagesByDate(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			MessageGateway gateway = new MessageGateway(con);
			ResultSet messages = gateway.findAllRecentMessages(map.get("postdate"));
			String answer = XMLWrapper.getInstance().createXDocument("Messages", "message", messages);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><messageBody><messageType>" +
					"<postdate><authorName><recipient><messageId><chatRoomId>"+messages+"</messageBody></messageType>" +
					"</postdate></authorName></recipient></messageId></chatRoomId>");
			response.setResponseCode(ResponseCodes.MessageFound.toString());
		}
		catch(Exception ex){
			//TODO:logging
		}
	}
	
	
    public synchronized void deleteOldMessages(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			MessageGateway gateway = new MessageGateway(con);
			gateway.deleteMessageByDate(map.get("postdate"));
			response.setResponseCode(ResponseCodes.MessageDeleted.toString());
		}
		catch(Exception ex){
			//TODO: logging
		}
	}
    
	public synchronized void doSentMessageToChat(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			MessageGateway gateway = new MessageGateway(con);
			MessageDTO message = new MessageDTO(map);
			String prettyTimeString = new PrettyTime(new Locale("")).format(new Date());
			String postdate = prettyTimeString;
			message.setPostdate(postdate);
			gateway.insert(message.toMap(), null, null);
			response.setResponseCode(ResponseCodes.MessageSent.toString());
		}
		catch(Exception ex){
			//TODO: logging
		}
	}
	public synchronized void doSentPrivateMessage(HTTPRequest request, HTTPResponse response){}
}
