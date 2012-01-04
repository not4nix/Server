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

/*
 * ��������, ���������
 */
public class ApplicationController {
	
	private ArrayList<Router> _rout = new ArrayList<Router>();
	
	/**
	 * Constuctor
	 */
	public ApplicationController(){}
	
	private Method _methodd;
	public void dispatchHTTPRequest(HTTPRequest request, HTTPResponse response){
		
	}
	
	public void addRoute(String pattern, String className, String methodName){
		_rout.add(new Router(pattern, className, methodName));
	}
}
