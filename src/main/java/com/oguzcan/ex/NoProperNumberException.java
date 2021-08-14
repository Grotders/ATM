package com.oguzcan.ex;

public class NoProperNumberException extends ValidationException{
	private static final long serialVersionUID = -2622891925455661499L;

	public NoProperNumberException(String message) {
		super(message);
	}
}
