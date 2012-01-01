package com.java.server.models;

public class User {
	/**
	 * private fields
	 */
	private String _login;
	private String _password;
	private String _email;
	private int _userId;
	
	
	public User(String nickname, String password, String email, int userId){
		_login = nickname;
		_password = password;
		_email = email;
		_userId = userId;
	}
}
