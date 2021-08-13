package com.oguzcan.controller;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.BackPreviousMenuException;
import com.oguzcan.ex.InsufficientFundsException;
import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.ValidationException;
import com.oguzcan.ex.WrongClientCredentialsException;
import com.oguzcan.service.CustomerServiceImpl;
import com.oguzcan.service.login.CustomerLoginService;
import com.oguzcan.service.login.LoginService;
import com.oguzcan.view.CustomerView;

public class CustomerController {

	private final CustomerView view = new CustomerView();
	private final LoginService<CustomerDTO> loginService = new CustomerLoginService();
	private final CustomerServiceImpl customerService = new CustomerServiceImpl();
	private InputController input = new InputController();

	private CustomerDTO loggedInCustomer;
	private AccountDTO currentAccount;
	
	public void init() {
		while (true) {
			login();
		}
	}

	public void login() {
		try {
			// view.displayLogin2Menu("oguzcan");
			loggedInCustomer = loginService.login("oguzcan", "12345");
		} catch (WrongClientCredentialsException ex) {
			System.out.println(ex.getMessage());
		} catch (NoSuchClientException ex) {
			System.out.println(ex.getMessage());
			login();
		}

//		loginService.redirecting();
		customerPanel();
//		testView();
	}

	private void customerPanel() {
		top: while (true) {
			view.displayCustomerMenu();

			switch (input.nextString()) {
			case "1": depositPanel(); break;
			case "2": withdrawPanel();break;
			case "3": transferPanel();break;
			case "4": balanceInquiryPanel();break;
			case "5": transactionHistoryPanel();break;
			case "6": applicationPanel();break;
			case "7": accountSettingsPanel();break;
			case "0": loginService.logout();break top;
			default:  System.out.println("geçersiz işlem");
			}
		}
	}
	private void selectAccountPanel(String message) throws BackPreviousMenuException{
		view.displayFetchAccountView(loggedInCustomer.getAccountList(), message);
		Set<AccountDTO> accountList = loggedInCustomer.getAccountList();
		int accountId;
		
		while (true) {
			try {
				accountId = customerService.checkInput(input.nextString());
				currentAccount = customerService.findAccount(accountList, accountId);
				break;
			} catch (NoSuchAccountException | NoProperInfoException ex) {
				view.displaySpace();
				view.displayFetchAccountView(loggedInCustomer.getAccountList(), message);
				System.out.println(ex.getMessage());
				System.out.print("---> ");
			} catch (ValidationException ex) {
				view.displaySpace();
				view.displayFetchAccountView(loggedInCustomer.getAccountList(), message);
				System.out.println("Beklenmedik bir hata oluştur!");
				System.out.print("---> ");
			}
		}
	}

	private void depositPanel() {
		try {
			selectAccountPanel("Para Yatırma");
		} catch (BackPreviousMenuException ex) {
			return;
		} 
		
		view.displaySpace();
		view.displayDepositView();
		
		while (true) {
			try {
				customerService.deposit(currentAccount, input.nextString());
				view.displaySpace();
				view.displaySuccessView();
				customerService.redirecting();
				view.displaySpace();
				break;
			} catch (NoProperNumberException ex) {
				view.displaySpace();
				view.displayDepositView();
				System.out.println(ex.getMessage());
				System.out.println("-->");
			} catch (ValidationException ex) {
				view.displaySpace();
				view.displayDepositView();
				System.out.println("Beklenmedik bir hatayla karşılaşıldı.");
				System.out.println("-->");
			}
		}
		
	}

	private void withdrawPanel() {
		try {
			selectAccountPanel("Para Çekme");
		} catch (BackPreviousMenuException ex) {
			return;
		} 
		
		view.displaySpace();
		view.displayWithdrawView();
		
		while (true) {
			try {
				customerService.withdraw(currentAccount, input.nextString());
				view.displaySuccessView();
				customerService.redirecting();
				view.displaySpace();
				break;
			} catch (NoProperNumberException | InsufficientFundsException ex) {
				view.displaySpace();
				view.displayDepositView();
				System.out.println(ex.getMessage());
				System.out.println("-->");
			} catch (ValidationException ex) {
				view.displaySpace();
				view.displayDepositView();
				System.out.println("Beklenmedik bir hatayla karşılaşıldı.");
				System.out.println("-->");
			}
		}
	}

	private void transferPanel() {
		try {
			selectAccountPanel("EFT");
		} catch (BackPreviousMenuException ex) {
			return;
		} 
		
		view.displaySpace();
		view.displayEFT1View();
		
		while (true) {
			try {
				String target = input.nextString();
				customerService.verifyAccountNumber(target);
				view.displayEFT2View();
				String amount = input.nextString();
				customerService.eft(currentAccount, target, amount);
				view.displaySuccessView();
				customerService.redirecting();
				view.displaySpace();
				break;
			} catch (NoProperNumberException | InsufficientFundsException | NoSuchClientException ex) {
				view.displaySpace();
				view.displayDepositView();
				System.out.println(ex.getMessage());
				System.out.println("-->");
			} catch (ValidationException ex) {
				view.displaySpace();
				view.displayDepositView();
				System.out.println("Beklenmedik bir hatayla karşılaşıldı.");
				System.out.println("-->");
			}
		}
	}

	private void balanceInquiryPanel() {
		
	}

	private void transactionHistoryPanel() {
		try {
			selectAccountPanel("EFT");
		} catch (BackPreviousMenuException ex) {
			return;
		} 
		
		Set<TransactionHistoryDTO> history = customerService.findHistory(currentAccount);
		view.displaySpace();
		view.displayFetchAccountHistoryView(history);
		input.nextString();
	}

	private void applicationPanel() {
		view.displayApplicationView();
		input.nextString();
		view.displaySuccessView();
		customerService.redirecting();
	}

	private void accountSettingsPanel() {
		view.displayAccountSettingsView();
		switch (input.nextString()) {
		case "1": passwordChangePanel();
		default: System.out.println("Hatalı seçim yapıldı. Ana menüye dönülüyor.");
		}
	}
	
	private void passwordChangePanel() {
		view.displayChangePasswordMenu();
		
		while(true) {
			try {
				customerService.changePassword(loggedInCustomer, input.nextString());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
