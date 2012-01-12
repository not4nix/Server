package com.java.mysoft.server.util;

import java.io.File;
import java.util.Scanner;

import com.java.mysoft.server.controllers.ApplicationController;
import com.java.mysoft.server.util.logging.Logger;

public class FileRouteReader extends Reader{

	private int[] spaces = new int[2];
	public FileRouteReader(String route) {
		super(route);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readRoute(ApplicationController ac) {
		try {
			String[] patt = {"(POST /user_tbl/doLogOn/ HTTP/1.1)","(POST /user_tbl/doRegister/ HTTP/1.1)"};
			File f = new File("routes.rtt");
			Scanner in = new Scanner(f);
			int i = 0;
			while(in.hasNextLine()){
				String line = in.nextLine();
				spaces[0] = line.indexOf("controllers.")+1;
				spaces[1] = line.indexOf(" ",spaces[0]+1);
				String pattern = line.substring(0, spaces[0]);
				String classname = line.substring(spaces[0]+1, spaces[1]);
				String methodname = line.substring(spaces[1]+1,line.length());
				ac.addRoute(patt[i], classname, methodname);
				i++;
			}
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
}
