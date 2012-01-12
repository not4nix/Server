package com.java.mysoft.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.dto.ChatDTO;
import com.java.mysoft.server.dto.ChatUserDTO;
import com.java.mysoft.server.gateways.ChatGateway;
import com.java.mysoft.server.gateways.TableGateway;
import com.java.mysoft.server.util.ResponseCodes;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;
import com.java.mysoft.server.util.xmlutils.XMLWrapper;

public class ChatController {
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public ChatController(){
		
	}
	
	//HTTP GET
	public synchronized void getChatRoomCreator(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			ChatGateway gateway = new ChatGateway(conn);
			ResultSet rooms = gateway.findRoomCreator(map.get("roomname"), map.get("creatorName"));
			String answer = XMLWrapper.getInstance().createXMLDocument("roomname", "creatorName" , rooms);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><roomname><creatorName>"+rooms+"</creatorName></roomname>");
			response.setCode(ResponseCodes.RoomCreatorFound);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured when getting chatroom creator " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void createRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			ChatDTO chat = new ChatDTO(map);
			gateway.insert(chat.toMap());
			response.setCode(ResponseCodes.RoomCreated);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured while creating the room " + ex.toString());
		}
	}
	
	//HTTP DELETE
	public synchronized void deleteRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			gateway.delete(Integer.parseInt("id"));
			response.setCode(ResponseCodes.RoomDeleted);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured while performing to delete the room " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void addUserToRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			ChatUserDTO user = new ChatUserDTO(map);
			gateway.insert(user.toMap());
		    response.setCode(ResponseCodes.UserAdded);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured when adding user to chat " + ex.toString());
		}
	}
	
	//HTTP DELETE
	public synchronized void kickUserFromRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			ChatUserDTO user = new ChatUserDTO(map);
			gateway.delete(Integer.parseInt("userId"));
			response.setCode(ResponseCodes.UserKicked);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured when trying to delete user " + ex.toString());
		}
	}
	
	public synchronized void getAllOnlineUsers(HTTPRequest request, HTTPResponse response){}
}
