package com.java.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.controllers.ApplicationController;
import com.java.server.db.Database;
import com.java.server.gateways.ChatUserGateway;

public class Server implements Runnable{

	/**
	 * @param args
	 */
	private static OutputStream os;
	private InputStream is;
	private static int port = 8080;
	public static void writeResponse(String s) throws IOException{
		String response = "HTTP/1.1 200 OK\r\n" +
				"Server: Myserver/2011-10-11\r\n" +
				"Content-Type: text/html\r\n" +
				"Content-Length: " + s.length() + "\r\n" +
				"Connection: close\r\n\r\n";
		String result = response + s;
		os.write(result.getBytes());
		os.flush();
		System.out.println(response);
	}
	public static void main(String[] args) throws SQLException, IOException {
		Database.getInstance().connect();
		ApplicationController ac = new ApplicationController();
		ac.addRoute("POST", "/UserController", "logOn");
		ac.addRoute("POST", "/UserController", "register");
		ServerSocket sock = new ServerSocket(port);
		while(true){
			Socket s = sock.accept();
			Thread t = new Thread();
			t.start();
			System.out.println("Server has launched");
		}
	}
	
	private void readInputHeaders() throws Throwable {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while(true){
			String s = br.readLine();
			if(s == null || s.trim().length() == 0) {
				break;
			}
		}
	}
	
	@Override
	public synchronized void run(){
		try {
			readInputHeaders();
			writeResponse("<html><body><h1>Welcome</h1></body></html>");
		} 
		catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,e);
		} 
		catch (Throwable e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,e);
		}
	}
}
