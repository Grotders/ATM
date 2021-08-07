package com.oguzcan.dao;

import com.oguzcan.controller.InputController;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;

public class DAOTest {
	InputController input = new InputController();
	AdminDAO dao = new AdminDAO();
	
	
	public static void main(String[] args) {
		DAOTest test = new DAOTest();
		
//		test.adminCreateTest();
		test.adminDeleteTest();
		
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
			} catch (NoSuchClientException ex) {
				System.out.println(ex.getMessage());
				continue;
			}
		}
	}
}
	
	
	
	

