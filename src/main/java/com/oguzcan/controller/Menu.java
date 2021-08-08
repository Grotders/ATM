package com.oguzcan.controller;

public enum Menu {
	CREATE(1), UPDATE(2), DELETE(3), FETCH(4);
	
	private int num;
	
	private Menu(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
}
