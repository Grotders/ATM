package com.oguzcan.factory;

import com.oguzcan.dto.AdminDTO;

public class AdminFactoryImpl implements AdminFactory{
	private AdminDTO admin;
	
	@Override
	public AdminDTO create(String username, String password) {
		AdminDTO admin = new AdminDTO.Builder()
				.username(username).password(password).build();
		
		return admin;
	}

}
