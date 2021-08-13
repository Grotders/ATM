package com.oguzcan.controller;

import java.util.Scanner;

public class InputController {
	Scanner keyboard = new Scanner(System.in); 	// String
	
	// keyboard.close() ekleyince çöküyor kod.
	public String nextString() {
		String input = keyboard.nextLine();
		return input.trim();
	}
}
