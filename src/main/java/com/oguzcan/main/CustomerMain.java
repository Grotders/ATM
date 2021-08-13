package com.oguzcan.main;

import com.oguzcan.controller.CustomerController;

public class CustomerMain {

	public static void main(String[] args) {
		while(true) {
			CustomerController controller = new CustomerController();
			controller.init();
		}
	}
}
