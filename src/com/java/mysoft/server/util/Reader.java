package com.java.mysoft.server.util;

import java.util.ArrayList;

import com.java.mysoft.server.controllers.ApplicationController;

public abstract class Reader {
	
	ArrayList<Router> r = new ArrayList<Router>();
	
	public Reader(String route){
		
	}
	
	public abstract void readRoute(ApplicationController ac);
}
