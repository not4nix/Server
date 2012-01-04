package com.java.server.utils;

public class HTTPRequest {
	protected String version = "HTTP/1.1";
    protected String query = "";
    protected String body = "";
    protected String params = "Content-type: text/xml";
    protected String requestString = "";
    protected String method = "";
    protected String host = "http://127.0.0.1:8080";
    
    public HTTPRequest(String method, String requestString, String params){
        this.method = method;
        this.requestString = requestString;
        this.params = params;
    }
    
    public HTTPRequest(String queryString){
        this.query = queryString;
    }
    
    public String getBody(){
        return body;
    }
    
    public void setBody(String body){
        this.body = body;
    }
    
    public String getParams(){
        return params;
    }
    
    public void setParams(String params){
        this.params = params;
    }
    
    public String getRequestString(){
        return requestString;
    }
    
    public void setRequestString(String requestString){
        this.requestString = requestString;
    }
    
    public String getMethod(){
        return method;
    }
    
    public void setMethod(String method){
        this.method = method;
    }
    
    public String getHost(){
        return host;
    }
    
    public String getQuery(){
        return query;
    }
    
    public void setQuery(String query){
        this.query = query;
    }
    
    @Override
    public String toString(){
        return "Method: "+getMethod()+"\nQuery string: "+getRequestString()+"\nBody: "+getBody();
    }
}
