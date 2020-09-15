package com.fc.commons.http;

public class HttpException extends RuntimeException {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -1274652490854626636L;

	public HttpException(String errorMessage, Throwable e) {
		super(errorMessage, e);
	}
	
	public HttpException(String errorMessage, String data, Throwable e) {
		super(errorMessage + "::" + data, e);
	}
}
