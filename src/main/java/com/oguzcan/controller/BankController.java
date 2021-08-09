package com.oguzcan.controller;


import static com.oguzcan.controller.Menu.CREATE;
import static com.oguzcan.controller.Menu.DELETE;

import java.util.Set;

import com.oguzcan.dao.GenericDAO;
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
	private GenericDAO factory;
	private AdminFactory aFactory = new AdminFactoryImpl();
	private CustomerFactory cFactory = new CustomerFactoryImpl();
	private AccountFactory acFactory = new AccountFactoryImpl();
	
	private AdminService adminService = new AdminServiceImpl();
	
	// Logged in user 
	private AdminDTO admin;

	
	public void init() {
		while(true) {
			view.displayWelcome();
			login();
			
		}
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
		System.out.println(CREATE);
		System.out.println();
		System.out.println(CREATE.ordinal()); // çalışıyor
		System.out.println(DELETE.getNum()); // çalışıyor
		loop:
		while(true) {
			switch(input.nextInt()) {
				case 1: createPanel(); break loop;	// Enum yapısı eklenecek
				case 2: fetchPanel(); break loop;
				case 3: logout(); break loop;

//				case CREATE: fetchPanel(); break loop;	 				// cannot convert from enum to int
//				case CREATE.ordinal(): createPanel(); break loop;		// must be constant
//				case FETCH.getNum(): fetchPanel(); break loop; 			// must be constant

				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz.");
			}
/*		
			switch(input.nextInt()) {				// java 14 özelliği eklenmiyor java 8'e
			case CREATE -> createPanel(); 	
			case UPDATE -> updatePanel(); 
			case DELETE -> deletePanel(); 
			case FETCH -> fetchPanel(); 
			default -> System.out.println("Seçiminiz hatalı tekrar deneyiniz.");
		}
*/
		}
	}

// ############################ CREATE ##########################################
	private void createPanel() {
		view.displayAdminCreatePanel();
		loop:
		while(true) {
			switch(input.nextInt()) {
				case 1: createAdminPanel(); break loop;
				case 2: createCustomerPanel(); break loop;
				case 3: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
			}
		}
	}
	
	private void createAdminPanel() {
		view.displayCreateAdminPanel();
		
//		System.out.print("Kullanıcı adı: ");
//		String username = input.nextString();
//		System.out.print("Şifre: ");
//		String password = input.nextString();

		// bu yapı kafa karıştırıcı mı?
		String username = input.nextString("Kullanıcı adı: ");
		String password = input.nextString("Şifre: ");
		
		AdminDTO admin = aFactory.create(username, password);
		try {
			adminService.createAdmin(admin);
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex);
		}
	}
	
	private void createCustomerPanel() {
		view.displayCreateCustomerPanel();
		
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
		
		try {
			adminService.createCustomer(customer);
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex);
		}
		// controller -> view -> controller -> factory -> controller -> service -> dao
		// temel motivasyon controller methodları hariç tüm methodlar sadece bir iş yaparak SRP ye uymalılar.
	}

// ############################## FETCH ########################################
	private void fetchPanel() {
		view.displayAdminFetchPanel();
		loop:
			while(true) {
				switch(input.nextInt()) {
					case 1: fetchAdminPanel(); break loop;
					case 2: fetchCustomerPanel(); break loop;
					default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
	}
	// geri gitme problemli
	private void fetchAdminPanel() {
		Set<AdminDTO> list = adminService.fetchAdminList();
		view.displayAdminListPanel(list);
		AdminDTO admin;
		loop: 
			while(true) {
				int id = input.nextInt();
				for(AdminDTO temp:list) {
					if(temp.getAdminId() == id) {
						admin = temp;
						break loop;
					} 
				}
			}
		fetchAdminMenuPanel();
	}
	private void fetchAdminMenuPanel() {
		view.displayFetchedAdminPanel(admin.getAdminId(), admin.getUsername());
		loop:
			while(true) {
				switch(input.nextInt()) {
				case 1: fetchAdminPanel(); break loop;
				case 2: fetchCustomerPanel(); break loop;
				case 3: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
		
	}
	
	private void updateAdmin() {
		view.displayAdminUpdatePanel();
	}
	
	private void deleteAdmin() {
		
	}
	
	private void fetchCustomerPanel() {
		view.displayCustomerListPanel();
	}
	
	
	
	
	
	
	
	
	private void logout() {
		admin = null;
	}
}
