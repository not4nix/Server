package com.java.server.controllers;

import java.sql.Connection;
import java.util.HashMap;

import com.java.server.database.Database;
import com.java.server.gateways.ChatUserGateway;
import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
import com.java.server.utils.Map;
import com.java.server.utils.XMLWrapper;

public class ChatController {
	
//	public void addUserToChatRoom(HTTPRequest request, HTTPResponse response){
//		try {
//			Connection con = Database.getInstance().connect();
//			String body = request.getBody();
//			HashMap<String,String> map = new HashMap<String,String>();
//			try {
//				XMLWrapper.getInstance().parse(map, body);
//				ChatUserGateway gateway = new ChatUserGateway(con, body);
//				Map m = new Map();
//				m.add("userId", map.get("userId"));
//				m.add("chatId", map.get("chatId"));
//				gateway.insert(m);
//			}
//		}
//	}
}
