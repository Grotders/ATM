package com.oguzcan.factory;

import com.oguzcan.dto.AdminDTO;

public class AdminFactoryImpl implements AdminFactory{
	
	@Override
	public AdminDTO create(String username, String password) {
		AdminDTO admin = new AdminDTO.Builder()
				.username(username)
				.password(password)
				.build();
		
		return admin;
	}

	@Override
	public AdminDTO copy(AdminDTO admin) {
		AdminDTO copyAdmin = new AdminDTO.Builder()
				.username(admin.getUsername())
				.password(admin.getPassword())
				.adminId(admin.getAdminId())
				.build();
		
		return copyAdmin;
	}

}
