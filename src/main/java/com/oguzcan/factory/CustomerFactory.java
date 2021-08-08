package com.oguzcan.factory;

import com.oguzcan.dto.CustomerDTO;

public interface CustomerFactory {

	public CustomerDTO create(String username, String password, String name,
			String lastname, String phoneNumber);
}
