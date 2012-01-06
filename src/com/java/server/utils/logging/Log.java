package com.java.server.utils.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.ocpsoft.pretty.time.PrettyTime;

public class Log {
	
	public Log(){
		
	}
	
	public synchronized static void writeLog(String string) throws SecurityException, IOException{
		Logger logger = Logger.getLogger("serverlog");
		FileHandler fh;
		fh = new FileHandler("D:\\Server\\serverlog.log", true);
		logger.addHandler(fh);
		logger.setLevel(Level.ALL);
		SimpleFormatter sf = new SimpleFormatter();
		logger.log(Level.ALL, "Hello");
	}
}
