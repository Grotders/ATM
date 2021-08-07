package com.oguzcan.ex;

public class WrongClientCredentialsException extends Exception {
	public WrongClientCredentialsException(String message){
		super(message);
	}
}