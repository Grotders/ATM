package com.oguzcan.dao;

import com.oguzcan.controller.InputController;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;

public class DAOTest {
	InputController input = new InputController();
	AdminDAO dao = new AdminDAO();
	
	
	public static void main(String[] args) {
		DAOTest test = new DAOTest();
		
//		test.adminCreateTest();
//		test.adminDeleteTest();
		test.xString();
	}
	
	public void adminCreateTest() {
		while (true) {
			try {
				System.out.print("Kullanıcı: ");
				String username = input.nextString();
				System.out.print("Sifre: ");
				String pass = input.nextString();
				
				AdminDTO adminTest = new AdminDTO.Builder().username(username).password(pass).build();
			
				dao.create(adminTest);
				break;
			} catch (ClientAlreadyExistsException ex) {
				System.out.println(ex.getMessage());
				continue;
			}
		}
	}
	
	public void adminDeleteTest() {
		while (true) {
			try {
				System.out.print("Kullanıcı: ");
				String username = input.nextString();
				System.out.print("Sifre: ");
				String pass = input.nextString();
				
				AdminDTO adminTest = new AdminDTO.Builder().username(username).password(pass).build();
				dao.delete(adminTest);
				break;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				continue;
			}
		}
	}
	
	void xString() {
		String a = "BasicAccountDTO";
		String a2 = "BusinessAccountDTO";
		System.out.println(a.replace("AccountDTO", "").toLowerCase());
		System.out.println(a2.replace("AccountDTO", "").toLowerCase());
		
	}
}
	
	
	
	

