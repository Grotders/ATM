package com.oguzcan.controller;

public enum Menu {
	ZERO(0), CREATE(1), UPDATE(2), DELETE(3), FETCH(4);
	
	private int no;
	
	private Menu(int no) {
		this.no = no;
	}
	
	final int num() { return no; }
	
}
