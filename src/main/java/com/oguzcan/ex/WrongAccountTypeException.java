package com.oguzcan.ex;

public class WrongAccountTypeException extends Exception{
	public WrongAccountTypeException(String message){
		super(message);
	}
}
