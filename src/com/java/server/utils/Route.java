package com.java.server.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {
	private String pattern;
	private String className;
	private String methodName;
	
	public Route(String pattern, String className, String methodName){
		this.pattern = pattern;
		this.className = className;
		this.methodName = methodName;
	}
	
	public boolean match(HTTPRequest request){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(request.getRequestString());
		return m.matches();
	}
	
	public String getClassName()
	{
		return className;
	}
	public String getMethod()
	{
		return methodName;
	}
}
