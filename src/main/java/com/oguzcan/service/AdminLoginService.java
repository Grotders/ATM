package com.oguzcan.service;

import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongCustomerCredentialsException;

public class AdminLoginService implements LoginService{

	private GenericDAO<AdminDTO> adminDao = new AdminDAO();
	private PasswordValidatorService passwordValidator;
	
	@Override
	public AdminDTO login(String username, String password) 
			throws WrongCustomerCredentialsException, NoSuchClientException {
		
		AdminDTO admin = adminDao.retrieve(username);
		System.out.println(admin);
		loginAdmin(admin, password);
		
		return admin;
	}
	
	private void loginAdmin(AdminDTO admin, String password) 
			throws WrongCustomerCredentialsException{
		
	//	passwordValidator.validate(password);
		checkAdminPassword(admin, password);
		adminDao.update(admin);
	}
	
	private void checkAdminPassword(AdminDTO admin, String password) 
			throws WrongCustomerCredentialsException {
		
		if(!admin.getPassword().equals(password)) {
			throw new WrongCustomerCredentialsException("Parola Hatalı");
		}
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
