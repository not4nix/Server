package com.java.server.models;

public class ChatUser {
	private String _login;
	private String _role;
	private int _chatUserId;
	
	public ChatUser(String login, String role, int chatUserId){
		_login = login;
		_role = role;
		_chatUserId = chatUserId;
	}
}
