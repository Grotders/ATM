package com.oguzcan.ex;

public class WrongAccountTypeException extends Exception{
	private static final long serialVersionUID = 4552494781750395213L;

	public WrongAccountTypeException(String message){
		super(message);
	}
}
