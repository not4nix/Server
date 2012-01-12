package com.java.mysoft.server.util.httputils;

import com.java.mysoft.server.util.ResponseCodes;

public class HTTPResponse {
	
	private String rcode = "HTTP/1.1 200 OK";
	private ResponseCodes code;
	private String body;
	private int mailto;
	
	public HTTPResponse(){}
	
	public HTTPResponse(ResponseCodes code, String body){
		this.setCode(code);
		this.setBody(body);
	}

	/**
	 * @return the code
	 */
	public ResponseCodes getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(ResponseCodes code) {
		this.code = code;
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
	
	@Override
	public String toString() {
		//HTTP/1.1 200OK UserAdded 
		return rcode + "code: " + code.toString() + "" + body;
	}

	public void setMailto(int mailto) {
		this.mailto = mailto;
		
	}

	public int getMailto() {
		return mailto;
	}
}
