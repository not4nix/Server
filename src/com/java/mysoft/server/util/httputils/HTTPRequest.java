package com.java.mysoft.server.util.httputils;

import java.util.ArrayList;

public class HTTPRequest {
	private String body;
	private String requestString;
	private ArrayList<String> params = new ArrayList<String>();
	
	public HTTPRequest(){}
	
	public HTTPRequest(String body, String requestString, ArrayList<String> params){
		this.setBody(body);
		this.setRequestString(requestString);
		this.setParams(params);
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the requestString
	 */
	public String getRequestString() {
		return requestString;
	}

	/**
	 * @param requestString the requestString to set
	 */
	public void setRequestString(String requestString) {
		this.requestString = requestString;
	}

	/**
	 * @return request string
	 */
	public String getParams() {
		return requestString;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(ArrayList<String> params) {
		this.params = params;
	}
}
