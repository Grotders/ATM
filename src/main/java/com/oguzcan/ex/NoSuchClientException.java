package com.oguzcan.ex;



public class NoSuchClientException extends Exception{
	private static final long serialVersionUID = -566980330251694934L;

	public NoSuchClientException(String message) {
		super(message);
	}
}
