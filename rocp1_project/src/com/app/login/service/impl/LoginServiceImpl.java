package com.app.login.service.impl;

import com.app.exceptions.BusinessException;
import com.app.login.dao.LoginDAO;
import com.app.login.dao.impl.LoginDAOImpl;
import com.app.login.service.*;
import com.app.model.User;

public class LoginServiceImpl implements LoginService{
	
	private LoginDAO dao = new LoginDAOImpl();

	@Override
	public boolean isValidUser(User user) throws BusinessException {
		boolean flag = false;
		
		if(user!=null && user.getUsername()!=null && user.getPassword()!=null){
			flag = dao.isValidUser(user);
		}else{
			throw new BusinessException("Invalid Login Credentials.");
		}
		
		return flag;
	}
	
	

}
