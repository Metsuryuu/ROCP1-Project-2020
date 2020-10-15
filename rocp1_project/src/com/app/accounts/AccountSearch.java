package com.app.accounts;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Account;
import com.app.model.User;

public interface AccountSearch {
	
	public boolean byUser(Account account, User user) throws BusinessException;
	public boolean byId(Account account) throws BusinessException;
	public boolean byStatus(Account account) throws BusinessException;
	public boolean byAll(List<Account> alist) throws BusinessException;

}
