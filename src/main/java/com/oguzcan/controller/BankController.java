package com.oguzcan.controller;


import com.oguzcan.service.LoginService;
import com.oguzcan.service.AdminLoginService;
import com.oguzcan.view.BankView;

public class BankController {
	private final BankView view = new BankView();
	private final LoginService loginService = new AdminLoginService();
	
	public void init() {
		login();
	}

	private void login() {
		view.displayWelcome();
		
		
	}
}
