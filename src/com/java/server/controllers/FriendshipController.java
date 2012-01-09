package com.java.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.FriendshipGateway;
import com.java.server.util.HTTPRequest;
import com.java.server.util.HTTPResponse;
import com.java.server.util.ResponseCodes;
import com.java.server.util.XMLWrapper;
import com.java.server.util.logging.Log;

public class FriendshipController {
	
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public FriendshipController(){
		
	}
	public synchronized void getFriendById(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Database.getInstance().getConnection();
			//get body of http request
			String httpRequest = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, httpRequest);
			//instantiate Friendship gateway
			FriendshipGateway gateway = new FriendshipGateway(con);
			ResultSet f = gateway.findFriendById(Integer.parseInt(map.get("friendshipId")));
			String answer = XMLWrapper.getInstance().createXDocument("Friends", "friend", f);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><friendName><initiatorName><friendshipId>" +
					""+f+"</friendshipId></initiatorName></friendName>");
			response.setResponseCode(ResponseCodes.FriendFound.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void doCreateFriendship(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			FriendshipGateway gateway = new FriendshipGateway(con);
			gateway.createFriend(map.get("friendName"), map.get("initiatorName"));
			response.setResponseCode(ResponseCodes.FriendshipCreated.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void doTerminateFriendship(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Database.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			FriendshipGateway gateway = new FriendshipGateway(con);
			gateway.deleteFriend(Integer.parseInt(map.get("friendshipId")));
			response.setResponseCode(ResponseCodes.FriendshipTerminated.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
    public synchronized void doRefuseFriendship(HTTPRequest request, HTTPResponse response){}
}
