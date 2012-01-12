package com.java.mysoft.server.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.java.mysoft.server.interfaces.IToMap;

/**
 * User data transfer object entity
 * @author not4nix
 *
 */
public class UserDTO implements IToMap{
	private String login;
	private String password;
	private String email;
	private int userId;
	Map<String,String> map = new ConcurrentHashMap<String,String>();
	
	public UserDTO(ConcurrentHashMap<String,String> map){
		this.login = map.get("login");
		this.password = map.get("password");
		this.email = map.get("email");
		this.userId = Integer.parseInt(map.get("userId"));
	}
	
	/**
	 * Interface method
	 */
	@Override
	public Map<String,String> toMap(){
		map.put("login", this.login);
		map.put("password", this.password);
		map.put("email", this.email);
		return map;
	}
	/* Getters and setters */
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
