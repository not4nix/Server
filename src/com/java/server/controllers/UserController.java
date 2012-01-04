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
		
	}
	
	//HTTP POST
	public synchronized void register(HTTPRequest request, HTTPResponse response){
		
		
	}
	
	
}
