package com.oguzcan.ex;

public class WrongClientCredentialsException extends Exception {
	private static final long serialVersionUID = -7749765947964782310L;

	public WrongClientCredentialsException(String message){
		super(message);
	}
}

