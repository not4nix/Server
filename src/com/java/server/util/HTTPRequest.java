package com.java.server.util;

public class HTTPRequest {
	
	protected String version = "HTTP/1.1";
	private String query;
	private String body;
	private String params = "Content-type: text/xml";
	private String requestString;
	private String method;
	private String host = "http://127.0.0.1:8080";
	
	public HTTPRequest(String method, String requestString, String params){
		this.method = method;
        this.requestString = requestString;
        this.params = params;
	}
	public HTTPRequest(String query){
		this.query = query;
	}
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
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
	 * @return the params
	 */
	public String getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
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
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
    public String toString(){
        return "Method: "+getMethod()+"\nQuery string: "+getRequestString()+"\nBody: "+getBody();
    }
}
