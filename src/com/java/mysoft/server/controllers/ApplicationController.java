package com.java.mysoft.server.controllers;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.java.mysoft.server.util.Router;
import com.java.mysoft.server.util.httputils.HTTPRequest;
import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;

public class ApplicationController {
	
	private static ArrayList<Router> routes = new ArrayList<Router>();
	
	public ApplicationController(){
		
	}
	
	public void addRoute(String pattern, String className, String methodName){
		routes.add(new Router(pattern,className,methodName));
	}
	
	public static void dispatch(HTTPRequest request, HTTPResponse response){
		try{
			for(Router r : routes){
				if(r.match(request)){
					//call method through reflection
					//how call method through reflection see http://goo.gl/BMPbf
					try {
						Object[] input = {request,response};
						Class cl = Class.forName(r.getClassName());
						Class[] pairs = new Class[2];
						pairs[0] = HTTPRequest.class;
						pairs[1] = HTTPResponse.class;
						Method meth = cl.getMethod(r.getMethodName(), pairs);
						meth.invoke(cl, input);
					}
					catch(Exception ex){
						Logger.writeToFile("Exception occured  " + ex.toString());
					}
				}
			}
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured  " + ex.toString());
		}
	}
}
