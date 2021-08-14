package com.oguzcan.ex;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 7458311364049666969L;

	public ValidationException(String message){
		super(message);
	}
}