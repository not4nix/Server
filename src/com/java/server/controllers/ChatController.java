package com.java.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.TableGateway;
import com.java.server.models.ChatDTO;
import com.java.server.models.ChatUserDTO;
import com.java.server.util.HTTPRequest;
import com.java.server.util.HTTPResponse;
import com.java.server.util.ResponseCodes;
import com.java.server.util.XMLWrapper;
import com.java.server.util.logging.Log;

public class ChatController {
	
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public ChatController(){
		
	}
	
	public synchronized void getChatRoomCreator(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			ChatGateway gateway = new ChatGateway(conn);
			ResultSet rooms = gateway.findRoomCreator(map.get("roomname"), map.get("creatorName"));
			String answer = XMLWrapper.getInstance().createXDocument("roomname", "creatorName" , rooms);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><roomname><creatorName>"+rooms+"</creatorName></roomname>");
			response.setResponseCode(ResponseCodes.RoomCreatorFound.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured when getting chatroom creator " + ex.toString());
		}
	}
	
	public synchronized void createRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			ChatDTO chat = new ChatDTO(map);
			gateway.insert(chat.toMap());
			response.setResponseCode(ResponseCodes.RoomCreated.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured while creating the room " + ex.toString());
		}
	}
	
	public synchronized void deleteRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			gateway.delete(Integer.parseInt("id"));
			response.setResponseCode(ResponseCodes.RoomDeleted.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured while performing to delete the room " + ex.toString());
		}
	}
	
	public synchronized void addUserToRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			ChatUserDTO user = new ChatUserDTO(map);
			gateway.insert(user.toMap());
			response.setResponseCode(ResponseCodes.UserAdded.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured when adding user to chat " + ex.toString());
		}
	}
	
	public synchronized void kickUserFromRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new ChatGateway(conn);
			ChatUserDTO user = new ChatUserDTO(map);
			gateway.delete(Integer.parseInt("userId"));
			response.setResponseCode(ResponseCodes.UserKicked.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured when trying to delete user " + ex.toString());
		}
	}
	
	public synchronized void getAllOnlineUsers(HTTPRequest request, HTTPResponse response){}
}
