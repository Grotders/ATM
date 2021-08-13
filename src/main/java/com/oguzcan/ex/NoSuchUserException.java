package com.oguzcan.ex;

public class NoSuchUserException extends Exception{
	private static final long serialVersionUID = -7914527853009988940L;

	public NoSuchUserException(String message) {
		super(message);
	}
}
