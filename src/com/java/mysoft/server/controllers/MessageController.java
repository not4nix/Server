package com.java.mysoft.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.dto.MessageDTO;
import com.java.mysoft.server.gateways.MessageGateway;
import com.java.mysoft.server.util.ResponseCodes;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;
import com.java.mysoft.server.util.xmlutils.XMLWrapper;
import com.ocpsoft.pretty.time.PrettyTime;

public class MessageController {
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public MessageController(){
		
	}
	
	//HTTP GET
	public synchronized void getMessagesByDate(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			MessageGateway gateway = new MessageGateway(con);
			ResultSet messages = gateway.findMessagesByDate(map.get("postdate"));
			String answer = XMLWrapper.getInstance().createXMLDocument("Messages", "message", messages);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><messageBody>" +
					"<postdate><authorName><recipient><messageId><chatRoomId>"+messages+"</chatRoomId></messageId>" +
					"</recipient></authorName></postdate></messageBody>");
			response.setCode(ResponseCodes.MessageFound);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP DELETE
    public synchronized void deleteOldMessages(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			MessageGateway gateway = new MessageGateway(con);
			gateway.deleteMessageByDate(map.get("postdate"));
			response.setCode(ResponseCodes.MessageDeleted);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
    
	//HTTP POST
    public synchronized void doSentMessageToChat(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			MessageGateway gateway = new MessageGateway(con);
			MessageDTO message = new MessageDTO(map);
			String prettyTimeString = new PrettyTime(new Locale("")).format(new Date());
			String postdate = prettyTimeString;
			message.setPostdate(postdate);
			gateway.insert(message.toMap());
			response.setCode(ResponseCodes.MessageSent);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	public synchronized void doSentPrivateMessage(HTTPRequest request, HTTPResponse response){}
}
