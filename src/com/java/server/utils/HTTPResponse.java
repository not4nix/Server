package com.java.server.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPResponse {
	protected String response;
	protected String queryString;
	protected String responseCode;
	private String body;
	
	public HTTPResponse(String response){
		this.response = response;
		this.queryString = "";
		this.responseCode = "";
		parse();
	}
	
	public String getResponseCode(){
        return responseCode;
    }
    
    public String getResponseBody(){
        return response;
    }
    
    public String getQueryString(){
        return queryString;
    }
    
    public void parse(){
        Pattern pattern = Pattern.compile("HTTP/1.1 [0-9]{3}\\s{1}([A-z]*)(\\s{1}[A-z]+)?");
        Matcher m = pattern.matcher(response);
        if(m.find()){
            int start = m.start();
            int end = m.end();
            queryString = response.substring(start, end);
        }
        pattern = Pattern.compile("Response-code:\\s[A-z]*");
        m = pattern.matcher(response);
        if(m.find()){
            int start = m.start();
            int end = m.end();
            responseCode = response.substring(start, end);
        }
        response = response.substring(response.indexOf("<?xml"));
    }
    public void setResponseCode(String responseCode){
    	this.responseCode = responseCode;
    }
   
    
    public void setBody(String body){
    	this.body = body;
    }
    
    @Override
    public String toString() {
	    return "Response code: "+responseCode.toString()+" Response body: "+response;
    }
}
