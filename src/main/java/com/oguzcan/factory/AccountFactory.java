package com.oguzcan.factory;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.ex.WrongAccountTypeException;

public interface AccountFactory {

	public AccountDTO create(int accNumber, double balance, String accountType)
			throws WrongAccountTypeException;
}
