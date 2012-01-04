package com.java.server.main;

import java.net.ServerSocket;
import java.net.Socket;

import com.java.server.controllers.ApplicationController;
import com.java.server.database.Database;

public class Server {
	public static void main(String[] args){
		Database.getInstance().connect();
		ApplicationController ap = new ApplicationController();
		try {
			ServerSocket s = new ServerSocket(8083);
			while(true){
				Socket socket = s.accept();
				Thread t = new Thread();
				t.start();
				System.out.println("SERVER STARTED...");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
