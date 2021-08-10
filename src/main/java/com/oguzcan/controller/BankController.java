package com.oguzcan.controller;


import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
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
	private final int CREATE_ACCOUNT = 3;
	private final int FETCH_ACCOUNTS = 4;
	private final int BACK2 = 5;
	private final int BASIC = 1;
	private final int BUSINESS = 2;
	private final int TYPE = 3;
	private final int TRANSACTION_HISTORY = 4;
	
	
	
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
		int customerId = 0;
		
		CustomerDTO customer = cFactory.create(username, password, customerId, name, lastname, phoneNumber);
		
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
//  2-1-1-1
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
// 2-1-1-2
	private void deleteAdmin(AdminDTO fetchedAdmin)  {
		view.displayAdminDeletePanel(fetchedAdmin);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			adminService.deleteAdmin(fetchedAdmin);
		} 
	}

// 2-2
	private void fetchCustomerPanel() {
		Set<CustomerDTO> customerList = adminService.fetchCustomerList();
		view.displayCustomerListPanel(customerList);
		CustomerDTO fetchedCustomer;
		loop: 
			while(true) {
				int customerId = input.nextInt();
				for(CustomerDTO temp:customerList) {
					if(temp.getCustomerId() == customerId) {
						fetchedCustomer = temp;
						break loop;
					} 
				}
				System.out.print("Hatalı id girdiniz! Tekrar deneyiniz: ");
			}
		fetchCustomerMenuPanel(fetchedCustomer);
	}

// 2-2-1
	private void fetchCustomerMenuPanel(CustomerDTO fetchedCustomer) {
		top:
		while(true) {
		view.displayFetchedCustomerPanel(fetchedCustomer);
		loop:
			while(true) {
				switch(input.nextInt()) {
				case UPDATE: updateCustomer(fetchedCustomer); break loop;
				case DELETE: deleteCustomer(fetchedCustomer); break top;
				case CREATE_ACCOUNT: CreateAccount(fetchedCustomer); break loop;
				case FETCH_ACCOUNTS: fetchAccountPanel(fetchedCustomer); break loop;
				case BACK2: break top;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
		}
	}

// 2-2-1-1
	private void updateCustomer(CustomerDTO fetchedCustomer) {
		CustomerDTO updatedCustomer = cFactory.copy(fetchedCustomer);
		view.displayCustomerUpdatePanel();
		
		loop:
		while(true) {
			String username = input.nextString("Kullanıcı adı: ");
			String password = input.nextString("Şifre: ");
			String firstname = input.nextString("Adı: ");
			String lastname = input.nextString("Soyadı: ");
			String phoneNumber = input.nextString("Telefon numarası: ");
			
			if(username.equals("") && password.equals("") && firstname.equals("") 
					&& lastname.equals("") && phoneNumber.equals("") ) {
				
				System.out.println("İşleminiz iptal edilmiştir."); break loop;
			} else {
				if(!username.equals(""))
					updatedCustomer.setUsername(username);
				if(!password.equals(""))
					updatedCustomer.setPassword(password);
				if(!firstname.equals(""))
					updatedCustomer.getInfo().setName(firstname);
				if(!lastname.equals(""))
					updatedCustomer.getInfo().setLastname(lastname);
				if(!phoneNumber.equals(""))
					updatedCustomer.getInfo().setPhoneNumber(phoneNumber);
			}
		}
	}
	
// 2-2-1-2
	private void deleteCustomer(CustomerDTO fetchedCustomer) {
		view.displayCustomerDeletePanel(fetchedCustomer);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			adminService.deleteCustomer(fetchedCustomer);
		} 
	}
// 2-2-1-3
	private void CreateAccount(CustomerDTO fetchedCustomer) {
		view.displayCreateAccountPanel();
		String accountType;
		
		loop:
		while(true) {
			int option = input.nextInt();

			switch (option){
			case BASIC:  accountType = "basic"; break loop;
			case BUSINESS: accountType = "business"; break loop;
			default : System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
			}
		}
		AccountDTO account = acFactory.create(0, 0, accountType, fetchedCustomer.getCustomerId());
		adminService.createAccount(account);
		System.out.println("Hesap başarıyla oluşturuldu.");
	}
// 2-2-1-4
	private void fetchAccountPanel(CustomerDTO fetchCustomer) {
		Set<AccountDTO> accountList = adminService.fetchAccountList(fetchCustomer.getCustomerId());
		fetchCustomer.setAccountList(accountList);
	
		try {
			view.displayAccountListPanel(accountList);
			AccountDTO fetchedAccount;
			loop: 
				while(true) {
					int accountNo = input.nextInt();
					for(AccountDTO temp:accountList) {
						if(temp.getAccNumber() == accountNo) {
							fetchedAccount = temp;
							break loop;
						} 
					}
					System.out.print("Hatalı hesap numarası girdiniz! Tekrar deneyiniz: ");
				}
			fetchAccountMenuPanel(fetchedAccount);
		} catch(NullPointerException ex) {
			System.out.println("Müşterinin hiç hesabı bulunmamaktadır.");
		}
		
	}
// 2-2-1-4-1
	private void fetchAccountMenuPanel(AccountDTO fetchedAccount) {
		view.displayFetchedAccountPanel(fetchedAccount);
		
		loop:
			while(true) {
				switch(input.nextInt()) {
				case UPDATE: updateAccount(fetchedAccount); break loop;
				case DELETE: deleteAccount(fetchedAccount); break loop;
				case TYPE: changeTypeAccount(fetchedAccount); break loop;
				case TRANSACTION_HISTORY: accountHistory(fetchedAccount); break loop;
				case BACK2: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
	}
// 2-2-1-4-1-1
	private void updateAccount(AccountDTO fetchedAccount) {
		AccountDTO updatedAccount = acFactory.copy(fetchedAccount);
		view.displayAccountBalanceUpdatePanel(fetchedAccount);
		
		double balance = input.nextDouble();
		
		updatedAccount.setBalance(balance);
		adminService.updateAccount(updatedAccount);
		
	}
// 2-2-1-4-1-2
	private void deleteAccount(AccountDTO fetchedAccount) {
		view.displayAccountDeletePanel(fetchedAccount);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			adminService.deleteAccount(fetchedAccount);
		}
		
	}
// 2-2-1-4-1-3
	private void changeTypeAccount(AccountDTO fetchedAccount) {
		view.displayAccountTypeChangePanel(fetchedAccount);
		
		String currentType = fetchedAccount.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase();
		AccountDTO updatedAccount;
		
		String option = input.nextString();
		if(option.equalsIgnoreCase("y")) {
			if(currentType.equals("basic")) {
				updatedAccount = acFactory.create(fetchedAccount.getAccNumber(), 
						fetchedAccount.getBalance(), "business", fetchedAccount.getCustomerId());
			}else {
				updatedAccount = acFactory.create(fetchedAccount.getAccNumber(), 
						fetchedAccount.getBalance(), "basic", fetchedAccount.getCustomerId());
			}
			adminService.updateAccount(updatedAccount);
			
		} else {
			System.out.println("İşlem iptal edilmiştir.");
		}
	}
// 2-2-1-4-1-4
	private void accountHistory(AccountDTO fetchedAccount) {
		Set<TransactionHistoryDTO> history = 
				adminService.fetchTransactionHistory (fetchedAccount.getAccNumber());
		view.displayTransactionHistoryPanel(history);
		input.nextString();
	}
	
// 3
	private void logout() {
		loggedInAdmin = null;
	}
}
