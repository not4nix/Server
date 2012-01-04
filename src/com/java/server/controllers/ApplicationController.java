package com.java.server.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.server.utils.HTTPRequest;
import com.java.server.utils.HTTPResponse;
import com.java.server.utils.ResponseCodes;
import com.java.server.utils.Router;

public class ApplicationController {
	
	private ArrayList<Router> _rout = new ArrayList<Router>();
	
	/**
	 * Constuctor
	 */
	public ApplicationController(){}
	
	private Method _methodd;
	public void dispatchHTTPRequest(HTTPRequest request, HTTPResponse response){
		try {
			for(Router r : _rout){
				if(r.match(request)){
					//REFLECTION!!!! )
					//get instance of class
					Object cl = Class.forName(r.getClassName()).newInstance();
					//load class by classloader
					Class clas = Class.forName(r.getClassName());
					//create pair httprequest and httpresponse
					Object[] pairs = new Object[] {HTTPRequest.class, HTTPResponse.class};
					//get method
					_methodd = clas.getMethod(r.getMethod(),(Class[]) pairs);
					//set method to accessible
					_methodd.setAccessible(true);
					//invoke method
					_methodd.invoke(cl, pairs);
				}
				else{
					response.setResponseCode(ResponseCodes.BadRequest.toString());
				}
			}
		}
		catch(IllegalAccessException ex){
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		} 
		catch (IllegalArgumentException ex) {
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		} 
		catch (InvocationTargetException ex) {
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		} 
		catch (SecurityException ex) {
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		} 
		catch (NoSuchMethodException ex) {
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		} 
		catch (ClassNotFoundException ex) {
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		} 
		catch (InstantiationException ex) {
			Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,"Exception occured", ex);
		}
	}
	
	public void addRoute(String pattern, String className, String methodName){
		_rout.add(new Router(pattern, className, methodName));
	}
}
