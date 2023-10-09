package com.UserAccount.exception;

import org.springframework.http.HttpStatus;

public class UserException {

	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpstatus;
	
	public UserException(String message, Throwable throwable, HttpStatus httpstatus) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpstatus = httpstatus;
	}

	public String getMessage() {
		return message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}
	
	
	
}
