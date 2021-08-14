package com.oguzcan.ex;

public class NoSuchAccountException extends Exception{
	private static final long serialVersionUID = -7914527853009988940L;

	public NoSuchAccountException(String message) {
		super(message);
	}
}
