package com.java.server.utils.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

import com.ocpsoft.pretty.time.PrettyTime;

public class Logger {
	
	public Logger(){
		
	}
	
	public synchronized static void writeLog(String string) throws FileNotFoundException{
		try {
			Calendar calendar = Calendar.getInstance();
			PrettyTime pt = new PrettyTime();
			String date = pt.format(calendar.getTime());
			File f = new File("D:\\Server\\serverlog.log");
			if(!f.exists()){
				f.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(f,"rw");
			raf.writeBytes(date + string);
		}
		catch(IOException ex){
			Logger.writeLog("Exception occured");
		}
	}
}
