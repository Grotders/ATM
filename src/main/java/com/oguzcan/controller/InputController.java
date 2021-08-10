package com.oguzcan.controller;

import java.util.Scanner;

public class InputController {
	Scanner keyboard = new Scanner(System.in); 	// String
	Scanner keyboard2 = new Scanner(System.in); // int
	
	// keyboard.close() ekleyince çöküyor kod.
	public String nextString() {
		
		String input = keyboard.nextLine();
		return input;
	}
	public String nextString(String text) {
		System.out.print(text);
		
		String input = keyboard.nextLine();
		return input;
	}
	
//  ################################### INT ####################################
	public int nextInt() {
		
		int input = keyboard2.nextInt();
		return input;
	}
	
	public int nextInt(String text) {
		System.out.print(text);
		
		int input = keyboard2.nextInt();
		return input;
	}
	
//  ################################# DOUBLE ##################################
	public double nextDouble() {
		double input = keyboard2.nextDouble();
		return input;
	}
	
	
	
}
