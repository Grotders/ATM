package com.oguzcan.controller;


import java.util.Set;

import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;
import com.oguzcan.factory.AccountFactory;
import com.oguzcan.factory.AccountFactoryImpl;
import com.oguzcan.factory.AdminFactory;
import com.oguzcan.factory.AdminFactoryImpl;
import com.oguzcan.factory.CustomerFactory;
import com.oguzcan.factory.CustomerFactoryImpl;
import com.oguzcan.service.AdminLoginService;
import com.oguzcan.service.AdminService;
import com.oguzcan.service.AdminServiceImpl;
import com.oguzcan.service.LoginService;
import com.oguzcan.view.BankView;

public class BankController {
	private final BankView view = new BankView();

	private final LoginService<AdminDTO> loginService = new AdminLoginService();
	private final InputController input = new InputController();
	private AdminFactory aFactory = new AdminFactoryImpl();
	private CustomerFactory cFactory = new CustomerFactoryImpl();
	private AccountFactory acFactory = new AccountFactoryImpl();
	
	private AdminService adminService = new AdminServiceImpl();

	// Logged in user 
	private AdminDTO loggedInAdmin;

	// RUN-TIME Constant Pool
	private final int CREATE = 1;
	private final int FETCH = 2;
	private final int ADMIN = 1;
	private final int CUSTOMER = 2;
	private final int BACK = 3;
	private final int UPDATE = 1;
	private final int DELETE = 2;
	
	
	public void init() {
		while(true) {
			view.displayWelcome();
			login();
		}
	}
//  0
	private void login() {
		try {
/* 			String username = input.nextString("Kullanıcı adı: ");
			String password = input.nextString("Şifre: ");
 			loginService.login(username, password);
*/			
			loggedInAdmin = loginService.login("oguzcan", "12345");

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
		top:
		while(true) {
		view.displayAdminPanel();
		
			loop:
			while(true) {
				switch(input.nextInt()) {
					case CREATE: createPanel(); break loop;	// Enum yapısı eklenecek
					case FETCH: fetchPanel(); break loop;
					case BACK: logout(); break top;
					default: System.out.println("Seçiminiz hatalı tekrar deneyiniz.");
				}		
			}
		}
	}

//1 ############################ CREATE ########################################
	private void createPanel() {
		top:
		while(true) {
			view.displayAdminCreatePanel();
			while(true) {
				switch(input.nextInt()) {
					case ADMIN: createAdminPanel(); break top;
					case CUSTOMER: createCustomerPanel(); break top;
					case BACK: break top;
					default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
		}
	}
//  1-1
	private void createAdminPanel() {
		view.displayCreateAdminPanel();
		
		String username = input.nextString("Kullanıcı adı: ");
		String password = input.nextString("Şifre: ");
		
		AdminDTO admin = aFactory.create(username, password);
		try {
			adminService.createAdmin(admin);
			
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex);
		}
	}
//  1-2
	private void createCustomerPanel() {
		view.displayCreateCustomerPanel();
		
		String username = input.nextString("Kullanıcı adı: ");
		String password = input.nextString("Şifre: ");
		String name = input.nextString("Müşteri adı: ");
		String lastname = input.nextString("Müşteri soyadı: ");
		String phoneNumber = input.nextString("Müşteri telefon numarası: ");
		
		CustomerDTO customer = cFactory.create(username, password, name, lastname, phoneNumber);
		
		try {
			adminService.createCustomer(customer);
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex);
		}
	}

//2 ################################## FETCH ###################################
	private void fetchPanel() {
		view.displayAdminFetchPanel();
		loop:
			while(true) {
				switch(input.nextInt()) {
					case ADMIN: fetchAdminPanel(); break loop;
					case FETCH: fetchCustomerPanel(); break loop;
					case BACK: break loop;
					default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
	}

//  2-1
	private void fetchAdminPanel() {
		Set<AdminDTO> adminList = adminService.fetchAdminList();
		view.displayAdminListPanel(adminList);
		AdminDTO fetchedAdmin;
		loop: 
			while(true) {
				int adminId = input.nextInt();
				for(AdminDTO temp:adminList) {
					if(temp.getAdminId() == adminId) {
						fetchedAdmin = temp;
						break loop;
					} 
				}
				System.out.print("Hatalı id girdiniz! Tekrar deneyiniz: ");
			}
		fetchAdminMenuPanel(fetchedAdmin);
	}
	
//  2-1-1
	private void fetchAdminMenuPanel(AdminDTO fetchedAdmin) {
		view.displayFetchedAdminPanel(fetchedAdmin);
		loop:
			while(true) {
				switch(input.nextInt()) {
				case UPDATE: updateAdmin(fetchedAdmin); break loop;
				case DELETE: deleteAdmin(fetchedAdmin); break loop;
				case BACK: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
	}
//  2-1-2
	private void updateAdmin(AdminDTO fetchedAdmin) {
		AdminDTO updatedAdmin = aFactory.copy(fetchedAdmin);
		view.displayAdminUpdatePanel();
		loop:
		while(true) {
			String username = input.nextString("Kullanıcı adı: ");
			String password = input.nextString("Şifre: ");
			
			if(username == "" && password == "") {
				System.out.println("İşleminiz iptal edilmiştir."); break loop;
			} else if (username != "") {
				updatedAdmin.setPassword(password);
			} else if(password == "") {
				updatedAdmin.setUsername(username);
			} else {
				updatedAdmin.setUsername(username);
				updatedAdmin.setPassword(password);
			}
			
		}
		
	}
	
	private void deleteAdmin(AdminDTO fetchedAdmin) {
		
	}
	
	private void fetchCustomerPanel() {
		view.displayCustomerListPanel();
	}
	
	
	
	
	
	
	
	
	private void logout() {
		loggedInAdmin = null;
	}
}
