package com.java.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {
	
	private String pattern;
	private String className;
	private String methodName;
	
	public Router(String pattern, String className, String methodName){
		this.pattern = pattern;
		this.setClassName(className);
		this.setMethodName(methodName);
	}
	
	public boolean match(HTTPRequest request){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(request.getRequestString());
		return m.matches();
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
}
