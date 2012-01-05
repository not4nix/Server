package com.java.server.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.ChatUserGateway;
import com.java.server.gateways.MessageGateway;
import com.java.server.models.ChatUser;
import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
import com.java.server.utils.ResponseCodes;
import com.java.server.utils.XMLWrapper;

public class ChatController {
	
	private String body;
	public synchronized void deleteChatRoom(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void kickUserFromRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			ChatUserGateway gateway = new ChatUserGateway(conn);
			int startRoomRequest = request.getRequestString().indexOf("room/")+4;
			int endRoomRequest = request.getRequestString().indexOf("/main");
			int startUserRequest = request.getRequestString().indexOf("err/")+2;
			int endUserRequest = request.getRequestString().indexOf("/HTTP");
			String user = request.getRequestString().substring(startUserRequest,endUserRequest);
			String room = request.getRequestString().substring(startRoomRequest,endRoomRequest);
			gateway.kickOutTheRoom(Integer.parseInt(user), Integer.parseInt(room));
			response.setBody(body);
			response.setResponseCode(ResponseCodes.UserKickedOut.toString());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized void joinIntoChatRoom(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void addUserToChatRoom(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			body = request.getBody();
			ConcurrentHashMap<String, String> hash = new ConcurrentHashMap<String, String>();
			XMLWrapper.getInstance().parse(hash, body);
			ChatUserGateway cug = new ChatUserGateway(conn);
			ChatUser cuonline = new ChatUser(hash);
			cug.insert(cuonline.toHash());
			ChatGateway chag = new ChatGateway(conn);
			ResultSet u = chag.findAllUsersInRoom(Integer.parseInt(hash.get("chatRoomId")));
			MessageGateway mesg = new MessageGateway(conn);
			ResultSet messages = mesg.getMessagesFromRoom(Integer.parseInt(hash.get("chatRoomId")));
			response.setBody(XMLWrapper.getInstance().createXMLDocument(u, messages));
			response.setResponseCode(ResponseCodes.UserJoined.toString());
			ChatGateway cg = new ChatGateway(conn);
			ResultSet usersOnline = cg.findAllUsersInRoom(Integer.parseInt(hash.get("chatRoomId")));
			String xml = XMLWrapper.getInstance().createXMLUserJoined(usersOnline);
			if(usersOnline.next()){
				response.setResponseCode(ResponseCodes.UserOnline.toString());
			}
			else {
				response.setResponseCode(ResponseCodes.UserLoggedOut.toString());
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void createChatRoom(HTTPRequest request, HTTPResponse response){
		
	}
}
