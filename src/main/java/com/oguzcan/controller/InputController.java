package com.oguzcan.controller;

import java.util.Scanner;

public class InputController {
	
	// keyboard.close() ekleyince çöküyor kod.
	public String nextString() {
		
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		
		return input;
	}
	public String nextString(String text) {
		System.out.print(text);
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		
		return input;
	}
	
	// keyboard.close() ekleyince çöküyor kod.
	public int nextInt() {
		Scanner keyboard = new Scanner(System.in);
		int input = keyboard.nextInt();
		
		return input;
	}
}
