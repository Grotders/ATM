package com.oguzcan.main;

import com.oguzcan.controller.BankController;

public class BankMain {

	public static void main(String[] args) {
		while(true) {
			BankController controller = new BankController();
			controller.init();
		}
	}
}
