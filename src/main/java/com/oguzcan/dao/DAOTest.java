package com.oguzcan.dao;

import java.util.Set;

import com.oguzcan.controller.InputController;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.factory.AccountFactory;
import com.oguzcan.factory.AccountFactoryImpl;
import com.oguzcan.factory.AdminFactory;
import com.oguzcan.factory.AdminFactoryImpl;
import com.oguzcan.factory.CustomerFactory;
import com.oguzcan.factory.CustomerFactoryImpl;
import com.oguzcan.factory.TransactionHistoryFactory;
import com.oguzcan.factory.TransactionHistoryFactoryImpl;

public class DAOTest {
	InputController input = new InputController();
	AdminDAO aDao = new AdminDAO();
	AccountDAO acDao = new AccountDAO();
	CustomerDAO cDao = new CustomerDAO();
	
	AdminFactory aFactory = new AdminFactoryImpl();
	AccountFactory acFactory = new AccountFactoryImpl();
	CustomerFactory cFactory = new CustomerFactoryImpl();
	TransactionHistoryFactory tFactory = new TransactionHistoryFactoryImpl();
	
	public static void main(String[] args) {
		DAOTest test = new DAOTest();
		
		
// 	Transaction Tests
//		test.createTransactionTest();
//		test.transactionList();
	}
	
// ############################## ACCOUNTDAO TEST ##############################  
	public void createAccountTest() {
		AccountDTO account = acFactory.create(0, 10000, "basic", 1);
		acDao.create(account);
		acDao.createTransaction("hesap oluşturma", 21);
		
	}
	public void createTransactionTest() {
		acDao.createTransaction("test", 1);
	}
	
	public void transactionList() {
		Set<TransactionHistoryDTO> history = acDao.retrieveTransactionHistory(1);
		for(TransactionHistoryDTO temp:history) {
			System.out.println(temp);
		}
	}
	
}
	
	
	
	

