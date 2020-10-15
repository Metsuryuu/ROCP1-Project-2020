package com.app.users.register;

import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public interface RegisterService {
	
	public boolean registerUser(User user, Role role) throws BusinessException;
}
