package com.oguzcan.ex;

public class NoProperUsernameException extends ValidationException{
	private static final long serialVersionUID = 3078485731173808701L;

	public NoProperUsernameException(String message) {
		super(message);
	}
}
