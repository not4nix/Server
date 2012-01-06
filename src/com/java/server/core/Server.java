package com.java.server.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.controllers.ApplicationController;
import com.java.server.database.Database;
import com.java.server.utils.logging.Log;

public class Server{

	public static void main(String[] args) throws SecurityException, IOException{
		try {
			Logger logger = Logger.getLogger("serverlog");
			Database.getInstance().connect();
			ApplicationController app = new ApplicationController();
			try {
				Thread t = new Thread();
				t.start();
				ServerSocket sock = new ServerSocket(8080);
				logger.log(Level.ALL,"hello");
				while(true){
					Socket s = sock.accept();
					Thread tr = new Thread();
					tr.start();
				}
			}
			catch(Exception ex){
				Log.writeLog("OLOLO");
			}
		}
		catch(FileNotFoundException ex){
			Log.writeLog("OLOLO");
		}
	}
}
