package com.java.server.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatGateway;
import com.java.server.gateways.UserGateway;
import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
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
	private boolean _verify;
	private UserGateway gateway;
	private HashMap<String, String> _map = new HashMap<String, String>();
	
	public UserController(){
		
	}
	//HTTP POST
	/**
	 * Log On method of User Controller
	 * Method takes http request and http response
	 * If credentials valid user log in chat room
	 * @param request HTTP Request
	 * @param response HTTP Response
	 */
	public synchronized void logOn(HTTPRequest request, HTTPResponse response){
		try {
			con = Database.getInstance().connect();
			//get body of http request
			body = request.getBody();
			XMLWrapper.getInstance().parse(_map, body);
			//for starting connection need instantiate user gateway
			//and call method of this gateway
			gateway = new UserGateway(con);
			_verify = gateway.validateCredentials(_map.get("login"));
			if(_verify = true){
				//if login valid user connect into main chat room
				ChatGateway chat = new ChatGateway(con, answer);
				rs = gateway.select();
				answer = XMLWrapper.getInstance().createXDocument("Rooms", "room", rs);
				response.setResponseCode(ResponseCodes.UserLoggedOn.toString());
			}
			else {
				response.setResponseCode(ResponseCodes.Forbidden.toString());
			}
		}
		catch(UnsupportedEncodingException ex){
			//temporary before creating logger
			ex.printStackTrace();
		}
	}
	
	//HTTP POST
	public synchronized void register(HTTPRequest request, HTTPResponse response) throws UnsupportedEncodingException{
		con = Database.getInstance().connect();
		body = request.getBody();
		XMLWrapper.getInstance().parse(_map, body);
		gateway = new UserGateway(con);
		/****TODO some logic****/
	}
	
	//TODO: this
	public synchronized void logOff(){
		
	}
}
