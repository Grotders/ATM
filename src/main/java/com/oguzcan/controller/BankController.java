package com.oguzcan.controller;


import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoProperUsernameException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.NoSuchUserException;
import com.oguzcan.ex.ValidationException;
import com.oguzcan.ex.WrongClientCredentialsException;
import com.oguzcan.factory.AccountFactory;
import com.oguzcan.factory.AccountFactoryImpl;
import com.oguzcan.factory.AdminFactory;
import com.oguzcan.factory.AdminFactoryImpl;
import com.oguzcan.factory.CustomerFactory;
import com.oguzcan.factory.CustomerFactoryImpl;
import com.oguzcan.service.AdminService;
import com.oguzcan.service.AdminServiceImpl;
import com.oguzcan.service.login.AdminLoginService;
import com.oguzcan.service.login.LoginService;
import com.oguzcan.view.BankView;

public class BankController {
	private final BankView view = new BankView();
	private final InputController input = new InputController();
	
	private final LoginService<AdminDTO> loginService = new AdminLoginService();
	private final AdminService adminService = new AdminServiceImpl();
	
	private final AccountFactory acFactory = new AccountFactoryImpl();
	private final AdminFactory aFactory = new AdminFactoryImpl();
	private final CustomerFactory cFactory = new CustomerFactoryImpl();
	
	// Logged in user 
	@SuppressWarnings("unused")
	private AdminDTO loggedInAdmin;

	private final int MENU1 = 0;
	private final int MENU2 = 2;
	private final int MENU3 = 4;
	private final int MENU4 = 8;

	public void init() {
		view.displayWelcome();
		login();
	}
//  0
	private void login() {
		try {
			System.out.print("Kullanıcı adı: ");
			String username = input.nextString();
			System.out.print("Şifre: ");
			String password = input.nextString();
			
			view.displaySpace();
			loginService.login(username, password);
			
//			loggedInAdmin = loginService.login("oguzcan", "12345");
			
			loginService.redirecting();
			adminPanel();
		} catch (WrongClientCredentialsException | NoSuchUserException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void adminPanel() {
		view.displayAdminMenu();
		loop:while(true) {
			
			switch(adminService.getEnum(input.nextString(), MENU1)) {
				case CREATE: createPanel(); break;
				case FETCH: fetchPanel(); break;
				case BACK: logout(); break loop;
				default: view.displayError();
			}	
			view.displayAdminMenu();
		} 
	}
	

//1 ############################ CREATE ########################################
	private void createPanel() {
		view.displaySpace();
		view.displayAdminCreateView();
		loop:while(true) {
			switch(adminService.getEnum(input.nextString(), MENU2)) {
				case ADMIN: createAdminPanel(); break loop;
				case CUSTOMER: createCustomerPanel(); break loop;
				case BACK: break loop;
				default: view.displayError();
			}
			view.displayAdminCreateView();
		}
	}
//  1-1
	private void createAdminPanel() {
		view.displayCreateAdminView();
		
		System.out.print("Kullanıcı adı: ");
		String username = input.nextString();
		System.out.print("Şifre: ");
		String password = input.nextString();
		
		AdminDTO admin = aFactory.create(username, password);
		
		try {
			view.displaySpace();
			adminService.createAdmin(admin);
			System.out.println("Admin başarıyla oluşturuldu.");
		} catch (NoProperPasswordException | ClientAlreadyExistsException | NoProperUsernameException ex) {
			System.out.println(ex.getMessage());
		} catch (ValidationException ex) {
			System.out.println("Admin oluştururken, beklenmedik bir hata meydana geldi");
		}
	}
//  1-2
	private void createCustomerPanel() {
		view.displayCreateCustomerView();
		
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
		
		int customerId = 0;
		CustomerDTO customer = cFactory.create(username, password, customerId, name, lastname, phoneNumber);
		
		try {
			view.displaySpace();
			adminService.createCustomer(customer);
			System.out.println("Müşteri başarıyla oluşturuldu."); 
		} catch (NoProperUsernameException | NoProperPasswordException ex) {
			System.out.println(ex.getMessage());
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex.getMessage());
		} catch (ValidationException ex) {
			System.out.println(ex.getMessage());
		}
	}

//2 ################################## FETCH ###################################
	private void fetchPanel() {
		view.displaySpace();
		view.displayAdminFetchMenuView();
		
		loop:while(true) {
			switch(adminService.getEnum(input.nextString(), MENU2)) {
				case ADMIN: listAdminPanel(); break;
				case CUSTOMER: listCustomerPanel(); break;
				case BACK: view.displaySpace(); break loop;
				default: view.displayError();
			}
			view.displayAdminFetchMenuView();
		}	
	}

//  2-1
	private void listAdminPanel() {
		view.displaySpace();
		try {
			Set<AdminDTO> adminList = adminService.fetchAdminList();
			view.displayAdminListView(adminList);
			AdminDTO fetchedAdmin = adminService.findAdmin(adminList, input.nextString());
				
			adminMenuPanel(fetchedAdmin);
			
		} catch (NoSuchUserException | NoProperNumberException ex) {
			view.displaySpace();
			System.out.println(ex.getMessage() + "Kullanıcı incele menüsüne geri dönülüyor.");
		} catch (ValidationException ex) {
			System.out.println("Listeleme admin beklenmedik bir hata.");
		}
		
	}
	
//  2-1-1
	private void adminMenuPanel(AdminDTO fetchedAdmin) {
		view.displaySpace();
		view.displayFetchedAdminMenuView(fetchedAdmin);
		loop:while(true) {
			switch(adminService.getEnum(input.nextString(), MENU4)) {
				case UPDATE: updateAdminPanel(fetchedAdmin); break loop;
				case DELETE: deleteAdminPanel(fetchedAdmin); break loop;
				case BACK: break loop;
				default: view.displayError();
			}	
			view.displayFetchedAdminMenuView(fetchedAdmin);
		}
	}
//  2-1-1-1 burada kaldım
	private void updateAdminPanel(AdminDTO fetchedAdmin) {
		view.displaySpace();
		view.displayUpdateAdminView();
		AdminDTO updatedAdmin = aFactory.copy(fetchedAdmin);
		
		System.out.print("Kullanıcı adı: ");
		String username = input.nextString();
		System.out.print("Şifre: ");
		String password = input.nextString();
		view.displaySpace();
		
		if(username == "" && password == "") {
			System.out.println("Güncelleme işleminiz iptal edilmiştir."); return;
		} else {
			if (!username.equals("")) {
				updatedAdmin.setUsername(username);
			}
			if(!password.equals("")) {
				updatedAdmin.setPassword(password);
			}
		}
			
		try {
			adminService.updateAdmin(updatedAdmin);
			view.displaySpace();
			System.out.println(updatedAdmin.getUsername() + " kullanıcısı başarıyla güncellenmiştir.");

		} catch (NoProperUsernameException | NoProperPasswordException ex) {
			System.out.println(ex.getMessage());
		} catch (ValidationException ex) {
			System.out.println("Beklenmedik bir hata oluştu.");
		}
		
	}
// 2-1-1-2
	private void deleteAdminPanel(AdminDTO fetchedAdmin)  {
		view.displaySpace();
		view.displayDeleteAdminView(fetchedAdmin);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			view.displaySpace();
			adminService.deleteAdmin(fetchedAdmin);
			System.out.println(fetchedAdmin.getUsername() + " kullanıcısı başarıyla silinmiştir.");
		} else {
			view.displaySpace();
			System.out.println("Silme işleminiz iptal edilmiştir.");
		}
	}

// 2-2
	private void listCustomerPanel() {
		view.displaySpace();
		try {
			Set<CustomerDTO> customerList = adminService.fetchCustomerList();
			view.displayCustomerListView(customerList);
			CustomerDTO fetchedCustomer = adminService.findCustomer(customerList, input.nextString());
			
			customerMenuPanel(fetchedCustomer);
			
		} catch (NoSuchUserException | NoProperNumberException ex) {
			view.displaySpace();
			System.out.println(ex.getMessage() + " Menüye geri dönülüyor.");
		} catch (ValidationException ex) {
			System.out.println("Müşteri listelemede beklenmedik bir hata gerçekleşti.");
		}
	}

// 2-2-1
	private void customerMenuPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		view.displayFetchedCustomerMenuView(fetchedCustomer);
		
		loop:while(true) {
						
			switch(adminService.getEnum(input.nextString(), MENU4)) {
				case UPDATE: updateCustomerPanel(fetchedCustomer); break;
				case DELETE: deleteCustomerPanel(fetchedCustomer); break loop;
				case CREATE_ACCOUNT: createAccountPanel(fetchedCustomer); break;
				case FETCH_ACCOUNTS: listAccountPanel(fetchedCustomer); break;
				case BACK: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
			}			
			view.displayFetchedCustomerMenuView(fetchedCustomer);
		}	
	}

// 2-2-1-1
	private void updateCustomerPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		view.displayUpdateCustomerView();
		
		CustomerDTO updatedCustomer = cFactory.copy(fetchedCustomer);
		
		System.out.print("Kullanıcı adı: ");
		String username = input.nextString();
		System.out.print("Şifre: ");
		String password = input.nextString();
		System.out.print("Adı: ");
		String firstname = input.nextString();
		System.out.print("Soyadı: ");
		String lastname = input.nextString();
		System.out.print("Telefon numarası: ");
		String phoneNumber = input.nextString();
		
		view.displaySpace();
		
		if(username.equals("") && password.equals("") && firstname.equals("") 
					&& lastname.equals("") && phoneNumber.equals("") ) {
				
			System.out.println("Güncelleme işleminiz iptal edilmiştir."); 
			return;
		
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
			
			try {
				adminService.updateCustomer(updatedCustomer);
			} catch (NoProperUsernameException | NoProperPasswordException | NoProperInfoException ex) {
				System.out.println(ex.getMessage());
			} catch (ValidationException ex) {
				System.out.println("Beklenmedik bir hata oluştu.");
			}
		}
	}
	
// 2-2-1-2
	private void deleteCustomerPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		view.displayDeleteCustomerView(fetchedCustomer);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			view.displaySpace();
			adminService.deleteCustomer(fetchedCustomer);
			view.displaySpace();
			System.out.println(fetchedCustomer.getUsername() + " kullanıcısı başarıyla silinmiştir.");
		} else {
			System.out.println("Silme işleminiz iptal edilmiştir.");
		}
	}
// 2-2-1-3
	private void createAccountPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		view.displayCreateAccountView();
		String accountType;
		
		String option = input.nextString();
		view.displaySpace();
		
		switch (adminService.getEnum(option, MENU3)){
			case BASIC:  accountType = "basic"; break;
			case BUSINESS: accountType = "business"; break;
			default : System.out.println("Seçiminiz hatalı! Müşteri paneline geri dönülüyor.");
				return;
		}
		int accountNo = 0;
		int balance = 0;
		AccountDTO account = acFactory.create(accountNo, balance, accountType, fetchedCustomer.getCustomerId());
		adminService.createAccount(account);
		System.out.println("Hesap başarıyla oluşturuldu.");
	}
// 2-2-1-4
	private void listAccountPanel(CustomerDTO fetchCustomer) {
		view.displaySpace();
		
		try {
			Set<AccountDTO> accountList = adminService.fetchAccountList(fetchCustomer.getCustomerId());
			fetchCustomer.setAccountList(accountList);
	
			view.displayAccountListView(accountList);
			
			AccountDTO fetchedAccount = adminService.findAccount(accountList, input.nextString());
			
			accountMenuPanel(fetchedAccount);
		} catch(NoSuchAccountException |NoProperNumberException | NoSuchUserException ex) {
			System.out.println(ex.getMessage() + " Müşteri menüsüne dönülüyor.");
		} catch (ValidationException ex) {
			System.out.println("Hesap listelemede beklenmeyen hata gerçekleşti.");
		}
		
	}
// 2-2-1-4-1
	private void accountMenuPanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		view.displayFetchedAccountMenuView(fetchedAccount);
		
		while(true) {
			switch(adminService.getEnum(input.nextString(), MENU4)) {
				case UPDATE: updateBalancePanel(fetchedAccount); return;
				case DELETE: deleteAccountPanel(fetchedAccount); return;
				case TYPE: changeAccountTypePanel(fetchedAccount); return;
				case TRANSACTION_HISTORY: accountHistoryPanel(fetchedAccount); return;
				case BACK: return;
				default: view.displayError();
			}		
		}
	}
// 2-2-1-4-1-1
	private void updateBalancePanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		view.displayUpdateBalanceView(fetchedAccount);
		AccountDTO updatedAccount = acFactory.copy(fetchedAccount);
		

		while (true) {
			String amount = input.nextString();
			view.displaySpace();
			
			try {
				double balance = adminService.convertProperDouble(amount);
				updatedAccount.setBalance(balance);
				adminService.updateAccount(updatedAccount);
				System.out.println("Hesap bakiyesi " + balance + " olarak güncellenmiştir.");
				
			} catch (NoProperInfoException | NoProperNumberException ex) {
				System.out.print(ex.getMessage());	
			} catch (ValidationException ex) {
				System.out.println("Beklenmeyen bir hata gerçekleşti.");
			}
		}
	}
// 2-2-1-4-1-2
	private void deleteAccountPanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		view.displayDeleteAccountView(fetchedAccount);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			adminService.deleteAccount(fetchedAccount);
			view.displaySpace();
			System.out.println(fetchedAccount.getAccNumber() + " numaralı hesap başarıyla silinmiştir.");
		} else {
			view.displaySpace();
			System.out.println("Silme işleminiz iptal edilmiştir.");
		}
	}
// 2-2-1-4-1-3
	private void changeAccountTypePanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		view.displayChangeAccountTypeView(fetchedAccount);
		
		String currentType = fetchedAccount.getAccountType();
		AccountDTO updatedAccount;
		
		String option = input.nextString();
		view.displaySpace();
		
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
	private void accountHistoryPanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		Set<TransactionHistoryDTO> history = 
				adminService.fetchTransactionHistory (fetchedAccount.getAccNumber());
		view.displayTransactionHistoryView(history);
		input.nextString();
		view.displaySpace();
	}
	
// 3
	private void logout() {
		loggedInAdmin = null;
	}
}
