package com.oguzcan.service;

import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AdminDTO;

public class AdminLoginService implements LoginService{

	private GenericDAO<AdminDTO> adminDao;
	private PasswordValidatorService passwordValidator;
	
	@Override
	public void login(String username, String password) {
		AdminDTO admin = adminDao.retrieve(username);
		loginAdmin(admin, password);
		
	}
	
	public void loginAdmin(AdminDTO admin, String password) {
		passwordValidator.validate(password);
		checkAdminPassword(admin, password);
		adminDao.update(admin);
	}
	
	public void checkAdminPassword(AdminDTO admin, String password) {
		if(!admin.getPassword().equals(password)) {
			System.out.println("Parolan hatalı. Tekrar deneyiniz!");
		}
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
