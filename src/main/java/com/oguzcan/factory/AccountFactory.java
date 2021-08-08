package com.oguzcan.factory;

import com.oguzcan.dto.AccountDTO;

public interface AccountFactory {

	public AccountDTO create(int accNumber, double balance, String accountType);
}
