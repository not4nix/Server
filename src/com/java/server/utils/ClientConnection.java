package com.java.server.utils;

import java.io.Serializable;
import java.net.Socket;
import java.util.regex.Pattern;

public class ClientConnection implements Serializable,Runnable{
	/*********************************************
	 * 
	 * Private
	 * 
	 * *******************************************
	 */
	private Socket socket;
	
	Pattern pattern = Pattern.compile("(GET /listen/[\\d]{1,6}(/HTTP/1.1)");
	public ClientConnection(Socket socket){
		this.socket = socket;
	}
}
