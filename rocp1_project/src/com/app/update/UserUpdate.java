package com.app.update;

import com.app.exceptions.BusinessException;
import com.app.model.User;

public interface UserUpdate {
	
	public boolean userUpdate(User user, String username, String flag) throws BusinessException;

}
