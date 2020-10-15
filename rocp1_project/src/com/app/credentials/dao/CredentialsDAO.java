package com.app.credentials.dao;

import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public interface CredentialsDAO {
	
	public boolean credentials(User user, Role role) throws BusinessException;

}
