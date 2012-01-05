package com.java.server.core;

import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

import com.java.server.controllers.ApplicationController;
import com.java.server.database.Database;
import com.java.server.utils.logging.Logger;

public class Server{

	public static void main(String[] args) throws FileNotFoundException{
		try {
			Database.getInstance().connect();
			ApplicationController app = new ApplicationController();
			Logger.writeLog("\tApplication controller launched\t");
			try {
				Thread t = new Thread();
				t.start();
				ServerSocket sock = new ServerSocket(8080);
				Logger.writeLog("\tSocket opened\t");
				while(true){
					Socket s = sock.accept();
					Thread tr = new Thread();
					tr.start();
				}
			}
			catch(Exception ex){
				Logger.writeLog("...Exception occured...");
			}
		}
		catch(FileNotFoundException ex){
			Logger.writeLog("....Exception occured because file not found....");
		}
	}
}
