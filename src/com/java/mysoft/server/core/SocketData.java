package com.java.mysoft.server.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.mysoft.server.controllers.ApplicationController;
import com.java.mysoft.server.util.EventThread;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;

public class SocketData implements Runnable{
	private GregorianCalendar gc = new GregorianCalendar();
	private Socket s;
	private InputStream is;
	private OutputStream os;
	
	SocketData(Socket s) throws IOException{
		this.s = s;
		this.is = s.getInputStream();
		this.os = s.getOutputStream();
	}
	
	@Override
	public void run() {
		try {
			//if we typing in browser http://127.0.0.1:8080 we'll got this response
			readInputHeaders();
			writeResponse("<html><body><h1>Welcome to server, man</h1><h2>This is server for chatting in varous chat rooms</h2>" +
				"<h3>Enjoy</h3></body></html>");
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occurred in method run() " + ex.toString());
		}
		finally {
			try {
				s.close();
			}
			catch(Exception ex){
				Logger.writeToFile("Exception occured while trying to close socket " + ex.toString());
			}
		}
	}
	
	private void readInputHeaders(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while(true){
				String s = br.readLine();
				if(s == null || s.trim().length() == 0) {
					break;
				}
			}
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured in method readInputHeaders() " + ex.toString());
		}
	}
	
	private void writeResponse(String s){
	try {
		String response = "HTTP/1.1 200 OK\r\n" +
				"Server: Myserver, "+gc.getTime()+"\r\n" +
				"Content-Type: text/html\r\n" +
				"Content-Length: " + s.length() + "\r\n" +
				"Connection: close\r\n\r\n";
		String result = response + s;
		os.write(result.getBytes());
		os.flush();
		Logger.writeToFile(response);
	}
	catch(Exception ex){
		Logger.writeToFile("Exception occured in method writeResponse() " + ex.toString());
	}
}
}