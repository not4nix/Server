package com.java.server.util;

import java.util.ArrayList;

import com.java.server.controllers.ApplicationController;

public abstract class Reader {
	
	ArrayList<Router> route = new ArrayList<Router>();
	public Reader(String route){
		
	}
	public abstract void readRoute(ApplicationController ac);
}