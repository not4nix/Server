package com.java.mysoft.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.dto.UserDTO;
import com.java.mysoft.server.gateways.ChatGateway;
import com.java.mysoft.server.gateways.TableGateway;
import com.java.mysoft.server.gateways.UserGateway;
import com.java.mysoft.server.util.ResponseCodes;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;
import com.java.mysoft.server.util.xmlutils.XMLWrapper;

public class UserController {
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	private boolean validate;
	private String answer;
	
	public UserController() {}
	
	//HTTP GET
	public synchronized void getUserById(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			String httpRequestString = request.getBody();
			XMLWrapper.getInstance().parse(map, httpRequestString);
			UserGateway gateway = new UserGateway(conn);
			ResultSet users = gateway.findById(Integer.parseInt("userId"));
			String answer = XMLWrapper.getInstance().createXMLDocument("Users", "login", users);
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><login><password><email><userId>" +
					""+users+"</userId></email></password></login>");
			response.setCode(ResponseCodes.UserFound);
		}
		catch(Exception ex){
			Logger.writeToFile("Something happened " + ex.toString());
		}
	}
	
	
	//HTTP DELETE
	public synchronized void deleteUser(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			//get body of http request
			String requestBody = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requestBody);
			TableGateway gateway = new UserGateway(conn);
			gateway.delete(Integer.parseInt("userId"));
//			UserGateway gateway = new UserGateway(conn);
//			gateway.deleteUser(Integer.parseInt(map.get("userId")));
			//set response code
			response.setCode(ResponseCodes.UserDeleted);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void doLogOn(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			//get body of http request
			String requestBody = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requestBody);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			validate = gateway.checkCredentials(map.get("login"), map.get("password"));
			//if validation successful user joins into the chat
			if(validate = true){
				//instantiate chat gateway
				ChatGateway chat = new ChatGateway(conn);
				//user need to obtain all chatrooms in chat
				ResultSet set = chat.select();
				//write answer
				answer = XMLWrapper.getInstance().createXMLDocument("Rooms","room", set);
			}
			else{
				response.setCode(ResponseCodes.BadRequest);
			}
			//set body of response
			response.setBody(answer);
			//set response code
			response.setCode(ResponseCodes.UserLoggedOn);
			response.setCode(ResponseCodes.UserOnline);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void doChangePassword(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			//get body of http request
			String requests = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requests);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//perform
			gateway.changePassword(map.get("login"), Integer.parseInt(map.get("password")));
			//set response code
			response.setCode(ResponseCodes.UserPasswordChanged);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void doChangeLogin(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			//get body of http request
			String requests = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requests);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//perform
			gateway.changeLogin(map.get("login"), Integer.parseInt(map.get("userId")));
			//set response code
			response.setCode(ResponseCodes.UserLoginChanged);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//HTTP POST
	public synchronized void doRegister(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Db.getInstance().getConnection();
			//get body of http request
			String requests = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requests);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//instantiate userTDO
			UserDTO user = new UserDTO(map);
			//perform
			gateway.insert(user.toMap());
			//set response codes
			response.setCode(ResponseCodes.UserAdded);
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
}
