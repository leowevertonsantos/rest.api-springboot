package com.restapi.commons;

import java.io.Serializable;

public class ErrorResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4521622549284365483L;
	
	private String userMessage;
	private String developerMessage;
	
	public ErrorResponse(String userMessage, String developerMessage) {
		super();
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
	}
	
	public ErrorResponse() {
		super();
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}


	@Override
	public String toString() {
		return "ErrorResponse [userMessage=" + userMessage + ", developerMessage=" + developerMessage + "]";
	}
	
	
	
}
