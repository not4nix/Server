package com.java.server.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.UserGateway;
import com.java.server.models.User;
import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
import com.java.server.utils.ResponseCodes;
import com.java.server.utils.XMLWrapper;


public class UserController {
	protected User u = new User();
	private Connection conn;
	private String body;
	private String answer;
	private ResultSet rs;
	private UserGateway gateway;
	private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public UserController(){
		
	}
	public synchronized void register(HTTPRequest request, HTTPResponse response) throws UnsupportedEncodingException{
		try {
			conn = Database.getInstance().getConnection();
			body = request.getBody();
			XMLWrapper.getInstance().parse(map, body);
			UserGateway gateway = new UserGateway(conn);
			u = new User(map);
			gateway.insert(u.toHash());
			answer = gateway.findUser(map.get("email"), Integer.parseInt(map.get("id")));
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><email><id>"+answer+"</email></id>");
			response.setResponseCode(ResponseCodes.UserAdded.toString());
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized void logOn(HTTPRequest request, HTTPResponse response){
		try {
			conn = Database.getInstance().getConnection();
			body = request.getBody();
			XMLWrapper.getInstance().parse(map, body);
			UserGateway gateway = new UserGateway(conn);
			boolean validate = gateway.validateCredentials(map.get("login"), map.get("password"));
			if(validate = true){
				ChatGateway gate = new ChatGateway(conn);
				rs = gate.select();
				answer = XMLWrapper.getInstance().createXDocument("Room", "rooms", rs);
			}
			else {
				response.setResponseCode(ResponseCodes.Forbidden.toString());
			}
			response.setBody(answer);
			response.setResponseCode(ResponseCodes.UserLoggedOn.toString());
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void logOff(HTTPRequest request, HTTPResponse response){}
}
