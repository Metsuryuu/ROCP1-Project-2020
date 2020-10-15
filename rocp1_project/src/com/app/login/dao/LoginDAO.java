package com.app.login.dao;

import com.app.exceptions.BusinessException;
import com.app.model.User;

public interface LoginDAO {
	
	public boolean isValidUser(User user) throws BusinessException;

}
