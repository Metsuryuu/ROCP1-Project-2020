package com.app.accounts;

import com.app.exceptions.BusinessException;
import com.app.model.Account;

public interface TransferService {
	
	public boolean transfer(Account a1, Account a2, double amount) throws BusinessException;

}
