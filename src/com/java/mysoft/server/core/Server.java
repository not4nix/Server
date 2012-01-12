package com.java.mysoft.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.GregorianCalendar;

import com.java.mysoft.server.controllers.ApplicationController;
import com.java.mysoft.server.database.Db;
import com.java.mysoft.server.util.EventThread;
import com.java.mysoft.server.util.FileRouteReader;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;

public class Server {
	private static int port = 8082;
	private static HTTPRequest request;
	private static HTTPResponse response;
	public static void main(String[] args) throws IOException{
		Connection conn = Db.getInstance().createConnection();
		Logger.writeToFile("Connecting to mysql database... ");
		ApplicationController ac = new ApplicationController();
		FileRouteReader frr = new FileRouteReader("routes.rtt");
		frr.readRoute(ac);
//		ac.addRoute("POST", "UserController", "doLogon");
//		ac.addRoute("POST", "UserController", "doRegister");
		Logger.writeToFile("Routes added...");
		try {
			EventThread event = new EventThread();
			Thread tr = new Thread(event);
			tr.start();
			ServerSocket sock = new ServerSocket(port);
			while(true){
				Socket s = sock.accept();
				Runnable run = new SocketData(s);
				Thread thr = new Thread(run);
				thr.start();
			}
		}
		catch(Exception ex){
			Logger.writeToFile("Exception in server main !!!! " + ex.toString());
		}
	}
}
		