package com.oguzcan.factory;

import com.oguzcan.dto.CustomerDTO;

public interface CustomerFactory {

	public CustomerDTO create(String username, String password, int customerId, String name,
			String lastname, String phoneNumber);
	public CustomerDTO copy(CustomerDTO customer);
}
