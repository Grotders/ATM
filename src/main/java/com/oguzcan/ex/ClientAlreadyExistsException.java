package com.oguzcan.ex;

public class ClientAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 4199724519923860374L;

	public ClientAlreadyExistsException(String message) {
		super(message);
	}
}
