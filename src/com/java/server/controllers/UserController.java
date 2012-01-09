package com.java.server.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.UserGateway;
import com.java.server.models.UserDTO;
import com.java.server.util.HTTPRequest;
import com.java.server.util.HTTPResponse;
import com.java.server.util.ResponseCodes;
import com.java.server.util.XMLWrapper;
import com.java.server.util.logging.Log;

public class UserController {
	
	private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
	private boolean validate;
	private String answer;
	public UserController(){
		
	}
	
	public synchronized void getUserById(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			//get body of http request
			String requestBody = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requestBody);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			ResultSet users = gateway.findUserById(Integer.parseInt(map.get("userId")));
			String answer = XMLWrapper.getInstance().createXDocument("Users", "user", users);
			//form body of xml response
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><login><password><email><userId>" +
					""+users+"</userId></email></password></login>");
			response.setResponseCode(ResponseCodes.UserFound.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void getUserByLogin(HTTPRequest request, HTTPResponse response){
		try{
			Connection con = Database.getInstance().getConnection();
			//get body of http request
			String requestBody = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requestBody);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(con);
			//execute
			ResultSet u = gateway.findUserByLogin(map.get("login"));
			//create xml document
			String answer = XMLWrapper.getInstance().createXDocument("Users", "user", u);
			//form body of xml response
			response.setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><user><password><email><userId>" +
					""+u+"</userId></email></password></user>");
			response.setResponseCode(ResponseCodes.UserFound.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void deleteUser(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			//get body of http request
			String requestBody = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requestBody);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//perform deleting
			gateway.deleteUser(Integer.parseInt(map.get("userId")));
			//set response code
			response.setResponseCode(ResponseCodes.UserDeleted.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void deleteAllUsers(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			//get body of http request
			String requestBody = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requestBody);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//perform deleting
			gateway.deleteAllUsers();
			//set response code
			response.setResponseCode(ResponseCodes.UserDeleted.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void doLogOn(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
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
				ResultSet set = chat.findAllRooms();
				//write answer
				answer = XMLWrapper.getInstance().createXDocument("Rooms","room", set);
			}
			else{
				response.setResponseCode(ResponseCodes.BadRequest.toString());
			}
			//set body of response
			response.setBody(answer);
			//set response code
			response.setResponseCode(ResponseCodes.UserLoggedOn.toString());
			response.setResponseCode(ResponseCodes.UserOnline.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void doChangePassword(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			//get body of http request
			String requests = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requests);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//perform
			gateway.changePassword(map.get("login"), map.get("password"));
			//set response code
			response.setResponseCode(ResponseCodes.UserPasswordChanged.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void doChangeLogin(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
			//get body of http request
			String requests = request.getBody();
			//parse body of http request
			XMLWrapper.getInstance().parse(map, requests);
			//instantiate user gateway
			UserGateway gateway = new UserGateway(conn);
			//perform
			gateway.changeLogin(map.get("login"), Integer.parseInt(map.get("userId")));
			//set response code
			response.setResponseCode(ResponseCodes.UserLoginChanged.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void doRegister(HTTPRequest request, HTTPResponse response){
		try {
			Connection conn = Database.getInstance().getConnection();
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
			response.setResponseCode(ResponseCodes.UserAdded.toString());
		}
		catch(Exception ex){
			Log.writeToFile("Exception occured " + ex.toString());
		}
	}
}
