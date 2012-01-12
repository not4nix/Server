package com.java.mysoft.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.mysoft.server.util.httputils.HTTPRequest;

public class Router {

	private String pattern;
	private String className;
	private String methodName;

	public Router(String pattern, String className, String methodName) {
		this.pattern = pattern;
		this.className = className;
		this.methodName = methodName;
	}

	public boolean match(HTTPRequest request) {
		Pattern patt = Pattern.compile(pattern);
		Matcher m = patt.matcher(request.getRequestString());
		return m.matches();
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
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
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
