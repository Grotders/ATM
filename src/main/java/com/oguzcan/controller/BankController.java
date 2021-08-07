package com.oguzcan.controller;


import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;
import com.oguzcan.service.AdminLoginService;
import com.oguzcan.service.LoginService;
import com.oguzcan.view.BankView;

public class BankController {
	private final BankView view = new BankView();
	private final LoginService loginService = new AdminLoginService();
	private final InputController input = new InputController();
	
	private AdminDTO admin;
//	enum Menu {CREATE, UPDATE, DELETE, FETCH;}
	
	public void init() {
		view.displayWelcome();
		login();
	}

	private void login() {
		try {
/* 
			System.out.print("kullanıcı adı: ");
			String username = input.nextString();
			System.out.print("sifre: ");
			String password = input.nextString();
			loginService.login(username, password);
 */			
			admin = (AdminDTO) loginService.login("oguzcan", "12345");

			
			System.out.println("Giriş başarılı! Yönlendiriliyorsunuz ...");
		} catch (WrongClientCredentialsException ex) {
			System.out.println(ex.getMessage());
		} catch (NoSuchClientException ex) {
			System.out.println(ex.getMessage());
			login();
		}
		
		redirecting();
		adminPanel();
	}
	
	private void redirecting() {
		try {
			Thread.sleep(3000);
			System.out.println("\n\n\n\n\n\n\n");
		} catch(InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}
	
	private void adminPanel() {
		view.displayAdminPanel();
		
		loop:
		while(true) {
			switch(input.nextInt()) {
				case 1: createPanel(); break loop;	// Enum yapısı eklenecek
				case 2: updatePanel(); break loop;
				case 3: updatePanel(); break loop;
				case 4: deletePanel(); break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz.");
			}
		}
	}

	
	private void createPanel() {
		view.displayAdminCreatePanel();
		loop:
		while(true) {
			switch(input.nextInt()) {
				case 1: createAdminPanel(); break loop;
				case 2: createCustomerPanel(); break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
			}
		}
	}
	private void createAdminPanel() {
		AdminDTO.Builder temp = new AdminDTO.Builder();
		
		view.displayAdminCreateAdminPanel();
		System.out.print("kullanıcı adı: ");
		String username = input.nextString();
		System.out.print("şifre: ");
		String password = input.nextString();
		
		
	}
	private void createCustomerPanel() {
		
	}
	
	private void updatePanel() {
			
		}
	private void deletePanel() {
		
	}
	private void fetchPanel() {
		
	}
}
