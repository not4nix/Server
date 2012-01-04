package com.java.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.db.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.UserGateway;
import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
import com.java.server.utils.MapWrapper;
import com.java.server.utils.ResponseCodes;
import com.java.server.utils.XMLWrapper;

public class UserController {
	/////////////////////////////////////////
	/// PRIVATE ////////////////////////////
	///////////////////////////////////////
	private Connection con;
	private String body;
	private String answer;
	private ResultSet rs;
	
	public UserController(){
		
	}
	//HTTP POST
	public synchronized void logOn(HTTPRequest request, HTTPResponse response){
		try {
			//create connection to database
			con = Database.getInstance().connect();
			//getting response body
			body = response.getResponseBody();
			HashMap<String,String> map = new HashMap<String,String>();
			XMLWrapper.getInstance().parse(map, body);
			//instantantiate user gateway
			UserGateway gateway = new UserGateway(con);
			boolean checkAuthorization = gateway.validateCredentials(map.get("password"));
			if(checkAuthorization = true){
				ChatGateway gateways = new ChatGateway(con, answer);
				rs = gateways.select();
				answer = XMLWrapper.getInstance().createXDocument("Rooms", "room", rs);
				response.setResponseCode(ResponseCodes.UserLoggedOn.toString());
			}
			else {
				response.setResponseCode(ResponseCodes.BadRequest.toString());
			}
		}
		catch(Exception ex) {
			Logger.getLogger(UserController.class.toString()).log(Level.FINE,null,ex);
		}
	}
	
	//HTTP POST
	public synchronized void register(HTTPRequest request, HTTPResponse response){
		try {
			//establish connection
			con = Database.getInstance().connect();
			body = response.getResponseBody();
			HashMap<String,String> map = new HashMap<String,String>();
			XMLWrapper.getInstance().parse(map, body);
			MapWrapper m = new MapWrapper();
			m.add("login", map.get("login"));
			m.add("password", map.get("password"));
			m.add("email", map.get("email"));
			response.setResponseCode(ResponseCodes.UserAdded.toString());
		}
		catch(Exception ex){
			Logger.getLogger(UserController.class.toString()).log(Level.FINE,null,ex);
		}
		
	}
	
	
}
