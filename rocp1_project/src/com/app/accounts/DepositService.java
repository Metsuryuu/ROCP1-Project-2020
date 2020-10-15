package com.app.accounts;

import com.app.exceptions.BusinessException;
import com.app.model.Account;

public interface DepositService{
	
	public boolean deposit(Account account, double amount) throws BusinessException;

}
