package com.oguzcan.controller;


import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoProperUsernameException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.ValidationException;
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
import com.oguzcan.util.validator.OnlyNumberValidator;
import com.oguzcan.util.validator.Validator;
import com.oguzcan.view.BankView;

public class BankController {
	private final BankView view = new BankView();
	private final LoginService<AdminDTO> loginService = new AdminLoginService();
	private final InputController input = new InputController();
	private final AccountFactory acFactory = new AccountFactoryImpl();
	private final AdminFactory aFactory = new AdminFactoryImpl();
	private final CustomerFactory cFactory = new CustomerFactoryImpl();
	private final AdminService adminService = new AdminServiceImpl();

	private Validator onlyNumberValidator = new OnlyNumberValidator();
	
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
	private final String YES = "y";
	
	
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
			view.displaySpace();
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}

	private void adminPanel() {
		top:
		while(true) {
		view.displayAdminMenu();

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
		view.displaySpace();
		top:
		while(true) {
			view.displayAdminCreateView();
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
		view.displayCreateAdminView();
		
		String username = input.nextString("Kullanıcı adı: ");
		String password = input.nextString("Şifre: ");
		
		AdminDTO admin = aFactory.create(username, password);
		try {
			view.displaySpace();
			adminService.createAdmin(admin);
			System.out.println("Admin başarıyla oluşturuldu.");
		} catch (NoProperPasswordException ex) {
			System.out.println(ex.getMessage());
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex.getMessage());
		} catch (ValidationException ex) {
			System.out.println(ex + "Beklenmedik bir hata meydana geldi.");
		}
	}
//  1-2
	private void createCustomerPanel() {
		view.displayCreateCustomerView();
		
		String username = input.nextString("Kullanıcı adı: ");
		String password = input.nextString("Şifre: ");
		String name = input.nextString("Müşteri adı: ");
		String lastname = input.nextString("Müşteri soyadı: ");
		String phoneNumber = input.nextString("Müşteri telefon numarası: ");
		int customerId = 0;
		
		CustomerDTO customer = cFactory.create(username, password, customerId, name, lastname, phoneNumber);
		
		try {
			view.displaySpace();
			adminService.createCustomer(customer);
			System.out.println("Müşteri başarıyla oluşturuldu.");
	 
		} catch (NoProperPasswordException ex) {
			System.out.println(ex.getMessage());
		} catch (ClientAlreadyExistsException ex) {
			System.out.println(ex.getMessage());
		} catch (ValidationException ex) {
			System.out.println(ex + "Beklenmedik bir hata meydana geldi.");
		}
	}

//2 ################################## FETCH ###################################
	private void fetchPanel() {
		view.displaySpace();
		top:
			while(true) {
				view.displayAdminFetchMenuView();
				loop:
					while(true) {
						switch(input.nextInt()) {
							case ADMIN: listAdminPanel(); break loop;
							case FETCH: listCustomerPanel(); break loop;
							case BACK: break top;
							default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
						}
					}
			}
	}

//  2-1
	private void listAdminPanel() {
		view.displaySpace();
		try {
			Set<AdminDTO> adminList = adminService.fetchAdminList();
			view.displayAdminListView(adminList);
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
			adminMenuPanel(fetchedAdmin);
		} catch (NoSuchClientException ex) {
			System.out.println(ex.getMessage() + " Menüye geri dönülüyor.");
		}
	}
	
//  2-1-1
	private void adminMenuPanel(AdminDTO fetchedAdmin) {
		view.displaySpace();
		view.displayFetchedAdminMenuView(fetchedAdmin);
		loop:
			while(true) {
				switch(input.nextInt()) {
				case UPDATE: updateAdminPanel(fetchedAdmin); break loop;
				case DELETE: deleteAdminPanel(fetchedAdmin); break loop;
				case BACK: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
	}
//  2-1-1-1
	private void updateAdminPanel(AdminDTO fetchedAdmin) {
		view.displaySpace();
		AdminDTO updatedAdmin;
		view.displayUpdateAdminView();
		loop:
		while(true) {
			updatedAdmin = aFactory.copy(fetchedAdmin);
			String username = input.nextString("Kullanıcı adı: ");
			String password = input.nextString("Şifre: ");
			
			if(username == "" && password == "") {
				System.out.println("Güncelleme işleminiz iptal edilmiştir."); break loop;
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
				break loop;
			} catch (NoProperUsernameException ex) {
				System.out.println(ex.getMessage());
			} catch (NoProperPasswordException ex) {
				System.out.println(ex.getMessage());
			} catch (ValidationException ex) {
				System.out.println("Beklenmedik bir hata oluştu.");
			}
		}
	}
// 2-1-1-2
	private void deleteAdminPanel(AdminDTO fetchedAdmin)  {
		view.displaySpace();
		view.displayDeleteAdminView(fetchedAdmin);
		
		if(input.nextString().equalsIgnoreCase("y")) {
			adminService.deleteAdmin(fetchedAdmin);
			view.displaySpace();
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
			customerMenuPanel(fetchedCustomer);
		} catch (NoSuchClientException ex) {
			System.out.println(ex.getMessage() + " Menüye geri dönülüyor.");
		}
	}

// 2-2-1
	private void customerMenuPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		top:
			while(true) {
				view.displayFetchedCustomerMenuView(fetchedCustomer);
				loop:
					while(true) {
						switch(input.nextInt()) {
						case UPDATE: updateCustomerPanel(fetchedCustomer); break loop;
						case DELETE: deleteCustomerPanel(fetchedCustomer); break top;
						case CREATE_ACCOUNT: createAccountPanel(fetchedCustomer); break loop;
						case FETCH_ACCOUNTS: listAccountPanel(fetchedCustomer); break loop;
						case BACK2: break top;
						default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
						}
					}
			}
	}

// 2-2-1-1
	private void updateCustomerPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		CustomerDTO updatedCustomer; 
		view.displayUpdateCustomerView();
		
		loop:
		while(true) {
			updatedCustomer = cFactory.copy(fetchedCustomer);
			System.out.println(fetchedCustomer);
			System.out.println(updatedCustomer);
			String username = input.nextString("Kullanıcı adı: ");
			String password = input.nextString("Şifre: ");
			String firstname = input.nextString("Adı: ");
			String lastname = input.nextString("Soyadı: ");
			String phoneNumber = input.nextString("Telefon numarası: ");
			
			if(username.equals("") && password.equals("") && firstname.equals("") 
					&& lastname.equals("") && phoneNumber.equals("") ) {
				
				System.out.println("Güncelleme işleminiz iptal edilmiştir."); break loop;
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
			System.out.println();
			
			try {
				
				adminService.updateCustomer(updatedCustomer);
				
				
			} catch (NoProperUsernameException ex) {
				System.out.println(ex.getMessage());
			} catch (NoProperPasswordException ex) {
				System.out.println(ex.getMessage());
			} catch (NoProperInfoException ex) {
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
			adminService.deleteCustomer(fetchedCustomer);
			view.displaySpace();
			System.out.println(fetchedCustomer.getUsername() + " kullanıcısı başarıyla silinmiştir.");
		} else {
			view.displaySpace();
			System.out.println("Silme işleminiz iptal edilmiştir.");
		}
	}
// 2-2-1-3
	private void createAccountPanel(CustomerDTO fetchedCustomer) {
		view.displaySpace();
		view.displayCreateAccountView();
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
	private void listAccountPanel(CustomerDTO fetchCustomer) {
		view.displaySpace();
		
		try {
			Set<AccountDTO> accountList = adminService.fetchAccountList(fetchCustomer.getCustomerId());
			fetchCustomer.setAccountList(accountList);
	
			view.displayAccountListView(accountList);
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
			accountMenuPanel(fetchedAccount);
		} catch(NoSuchClientException ex) {
			System.out.println(ex.getMessage() + " Müşteri menüsüne dönülüyor.");
		}
		
	}
// 2-2-1-4-1
	private void accountMenuPanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		view.displayFetchedAccountMenuView(fetchedAccount);
		
		loop:
			while(true) {
				switch(input.nextInt()) {
				case UPDATE: updateBalancePanel(fetchedAccount); break loop;
				case DELETE: deleteAccountPanel(fetchedAccount); break loop;
				case TYPE: changeAccountTypePanel(fetchedAccount); break loop;
				case TRANSACTION_HISTORY: accountHistoryPanel(fetchedAccount); break loop;
				case BACK2: break loop;
				default: System.out.println("Seçiminiz hatalı tekrar deneyiniz!");
				}
			}
	}
// 2-2-1-4-1-1
	private void updateBalancePanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		AccountDTO updatedAccount = acFactory.copy(fetchedAccount);
		view.displayUpdateBalanceView(fetchedAccount);
		String balance;
		
			
			while (true) {
				balance = input.nextString();
				try {
					onlyNumberValidator.validate(balance);
					break;
				} catch (NoProperInfoException ex) {
					System.out.print(ex.getMessage());
					;
				} catch (ValidationException ex) {
					System.out.println("Beklenmeyen bir hata gerçekleşti.");
				}
			}
			updatedAccount.setBalance(Double.parseDouble(balance));
			adminService.updateAccount(updatedAccount);
			System.out.println("Hesap bakiyesi " + balance + " olarak güncellenmiştir.");
			
		
		
		
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
	private void accountHistoryPanel(AccountDTO fetchedAccount) {
		view.displaySpace();
		Set<TransactionHistoryDTO> history = 
				adminService.fetchTransactionHistory (fetchedAccount.getAccNumber());
		view.displayTransactionHistoryView(history);
		input.nextString();
	}
	
// 3
	private void logout() {
		loggedInAdmin = null;
	}
}
