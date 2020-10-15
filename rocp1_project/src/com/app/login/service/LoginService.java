package com.app.login.service;

import com.app.exceptions.BusinessException;
import com.app.model.User;

public interface LoginService {
	
	public boolean isValidUser(User user) throws BusinessException;

}
