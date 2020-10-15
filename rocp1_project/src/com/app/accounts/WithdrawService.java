package com.app.accounts;

import com.app.exceptions.BusinessException;
import com.app.model.Account;

public interface WithdrawService {

	public boolean withdraw(Account account, double amount) throws BusinessException;

}
