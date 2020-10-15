package com.app.search;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.User;

public interface SearchAll {
	
	public boolean allUsers(List<User> ulist) throws BusinessException;

}
