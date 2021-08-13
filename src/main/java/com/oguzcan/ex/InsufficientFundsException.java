package com.oguzcan.ex;

public class InsufficientFundsException extends Exception{
	private static final long serialVersionUID = 5654059834862554444L;

	public InsufficientFundsException(String message) {
		super(message);
	}
}
