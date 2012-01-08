package com.java.server.util.logging;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Log {
	
	private static int size = 1000000;
	public static synchronized void writeToFile(String path){
		try {
			File f = new File("D:\\Server\\serverlog.log");
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
			Log.writeToFile("Exception occured "+ex.toString());
		}
	}
}
