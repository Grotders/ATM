package com.oguzcan.ex;

public class NoProperPasswordException extends ValidationException{
	private static final long serialVersionUID = -2514483671032803023L;

	public NoProperPasswordException(String message) {
		super(message);
	}
}
