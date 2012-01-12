package com.java.mysoft.server.util.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Logger {
	private static int size = 1000000;
	public static synchronized void writeToFile(String path){
		try {
			File f = new File("serverlog.log");
			if(!f.exists()){
				f.createNewFile();
			}
			GregorianCalendar calendar = new GregorianCalendar();
			Date time = calendar.getTime();
			RandomAccessFile file = new RandomAccessFile(f, "rw");
			file.seek(file.length());
			if(f.length() > size){
				f.createNewFile();
			}
			file.writeBytes(time + ":" + path + System.getProperty("line.separator"));
			file.close();
		}
		catch(IOException ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized static String getHtml() throws FileNotFoundException{
		String html = "HTTP/1.1 200 OK\r\nServer: myServer\r\nConnection: close\r\nContent-Type: text/html; charset=UTF-8\r\n\r\n";
		html += "<html><head><title>Greetings</title></head><body bgcolor=\"white\" TEXT=\"black\"><center><h1>Hello</h1></center>";
		Scanner s = new Scanner(new File("D:\\java\\ServerComplete\\serverlog.log"));
    	while(s.hasNextLine())
    	{
    		html += "<p>" + s.nextLine() + "</p>";
    	}
    	html += "</body></html>";
		return html;
	}
}
