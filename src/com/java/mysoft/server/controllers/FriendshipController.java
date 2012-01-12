package com.java.mysoft.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.dto.FriendshipDTO;
import com.java.mysoft.server.gateways.FriendshipGateway;
import com.java.mysoft.server.gateways.TableGateway;
import com.java.mysoft.server.util.ResponseCodes;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;
import com.java.mysoft.server.util.xmlutils.XMLWrapper;

public class FriendshipController {
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	public FriendshipController(){
		
	}
	//HTTP GET
	public synchronized void getFriendById(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Db.getInstance().getConnection();
			//get body of http request
			String httpRequest = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, httpRequest);
			//instantiate Friendship gateway
			FriendshipGateway gateway = new FriendshipGateway(con);
			ResultSet f = gateway.findFriendById(Integer.parseInt(map.get("friendshipId")));
			String answer = XMLWrapper.getInstance().createXMLDocument("Friends", "friend", f);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><friendName><initiatorName><friendshipId>" +
					""+f+"</friendshipId></initiatorName></friendName>");
			response.setCode(ResponseCodes.FriendFound);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void doCreateFriendship(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new FriendshipGateway(con);
			FriendshipDTO dto = new FriendshipDTO(map);
			gateway.insert(dto.toMap());
//			FriendshipGateway gateway = new FriendshipGateway(con);
//			gateway.createFriend(map.get("friendName"), map.get("initiatorName"));
			response.setCode(ResponseCodes.FriendshipCreated);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP DELETE
	public synchronized void doTerminateFriendship(HTTPRequest request, HTTPResponse response){
		try {
			Connection con = Db.getInstance().getConnection();
			String httpRequest = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequest);
			TableGateway gateway = new FriendshipGateway(con);
			gateway.delete(Integer.parseInt(map.get("friendshipId")));
			response.setCode(ResponseCodes.FriendshipTerminated);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
    public synchronized void doRefuseFriendship(HTTPRequest request, HTTPResponse response){}
}
