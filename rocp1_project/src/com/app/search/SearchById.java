package com.app.search;

import com.app.exceptions.BusinessException;
import com.app.model.User;

public interface SearchById {
	
	public boolean userById(User user) throws BusinessException;

}
