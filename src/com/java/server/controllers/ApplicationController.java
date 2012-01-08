package com.java.server.controllers;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.java.server.util.HTTPRequest;
import com.java.server.util.HTTPResponse;
import com.java.server.util.Router;
import com.java.server.util.logging.Log;

public class ApplicationController {
	
	private static ArrayList<Router> routes = new ArrayList<Router>();
	
	public ApplicationController(){}
	
	public void addRoute(String pattern, String className, String methodName){
		routes.add(new Router(pattern,className,methodName));
	}
	
	public void doDispatch(HTTPRequest request, HTTPResponse response){
		try {
			for(Router r : routes){
				if(r.match(request)){
					Class cl = Class.forName(r.getClassName());
					Object[] input = {
							request,
							response
					};
					Class[] pair = new Class[2];
					pair[0] = HTTPRequest.class;
					pair[1] = HTTPResponse.class;
					Method meth = cl.getMethod(r.getMethodName(),pair);
					meth.invoke(cl, input);
					}
				}
			}
		    catch(Exception ex){
		    	Log.writeToFile("Exception occured in app controller " + ex.toString());
	     }
	}
}
