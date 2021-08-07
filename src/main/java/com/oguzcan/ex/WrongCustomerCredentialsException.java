package com.oguzcan.ex;

public class WrongCustomerCredentialsException extends Exception {
	public WrongCustomerCredentialsException(String message){
		super(message);
	}
}