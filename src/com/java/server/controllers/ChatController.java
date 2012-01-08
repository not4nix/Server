package com.java.server.controllers;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.util.HTTPRequest;
import com.java.server.util.HTTPResponse;
import com.java.server.util.XMLWrapper;

public class ChatController {
	
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public ChatController(){
		
	}
	
	public synchronized void getChatRoomCreator(HTTPRequest request, HTTPResponse response){
//		try {
//			Connection con = Database.getInstance().getConnection();
//			String httpRequest = request.getBody();
//			XMLWrapper.getInstance().parse(map, httpRequest);
//		}
	}
	
	public synchronized void createRoom(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void deleteRoom(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void addUserToRoom(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void kickUserFromRoom(HTTPRequest request, HTTPResponse response){
		
	}
	
	public synchronized void getAllOnlineUsers(HTTPRequest request, HTTPResponse response){}
}
