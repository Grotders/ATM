package com.oguzcan.controller;


import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;
import com.oguzcan.factory.AccountFactory;
import com.oguzcan.factory.AdminFactory;
import com.oguzcan.factory.CustomerFactory;
import com.oguzcan.service.AdminLoginService;
import com.oguzcan.service.LoginService;
import com.oguzcan.view.BankView;

public class BankController {
	private final BankView view = new BankView();
	private final LoginService<AdminDTO> loginService = new AdminLoginService();
	private final InputController input = new InputController();
	private AdminFactory aFactory;
	private CustomerFactory cFactory;
	private AccountFactory acFactory;
	
	
	// Logged in user 
	private AdminDTO admin;

	
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
			admin = loginService.login("oguzcan", "12345");

			
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
		
//		System.out.println(CREATE.ordinal()); // çalışıyor
//		System.out.println(DELETE.getNum()); // çalışıyor
		loop:
		while(true) {
			switch(input.nextInt()) {
				case 1: createPanel(); break loop;	// Enum yapısı eklenecek
				case 2: updatePanel(); break loop;
				case 3: updatePanel(); break loop;
				case 4: deletePanel(); break loop;
//				case DELETE.getNum(): deletePanel(); break loop; 		// must be constant
//				case DELETE: deletePanel(); break loop;	 				// cannot convert from enum to int
//				case CREATE.ordinal(): deletePanel(); break loop;		// must be constant
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz.");
			}
/*		
			switch(input.nextInt()) {				// java 14 özelliği eklenmiyor java 8'e
			case CREATE)-> createPanel(); 	
			case UPDATE-> updatePanel(); 
			case DELETE-> deletePanel(); 
			case FETCH-> fetchPanel(); 
			default-> System.out.println("Seçiminiz hatalı tekrar deneyiniz.");
		}
*/
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
		view.displayAdminCreateAdminPanel();
		System.out.print("Kullanıcı adı: ");
		String username = input.nextString();
		System.out.print("Şifre: ");
		String password = input.nextString();
		
		AdminDTO admin = aFactory.create(username, password);
		
		
	}
	private void createCustomerPanel() {
		view.displayAdminCreateCustomerPanel();
		System.out.print("Kullanıcı adı: ");
		String username = input.nextString();
		System.out.print("Şifre: ");
		String password = input.nextString();
		System.out.print("Müşteri adı: ");
		String name = input.nextString();
		System.out.print("Müşteri soyadı: ");
		String lastname = input.nextString();
		System.out.print("Müşteri telefon numarası: ");
		String phoneNumber = input.nextString();
		
		CustomerDTO customer = cFactory.create(username, password, name, lastname, phoneNumber);
		
		
	}
	
	
	private void updatePanel() {
			
		}
	private void deletePanel() {
		
	}
	private void fetchPanel() {
		
	}
}
