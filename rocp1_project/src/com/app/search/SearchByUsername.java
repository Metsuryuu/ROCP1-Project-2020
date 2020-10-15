package com.app.search;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.User;

public interface SearchByUsername {
	
	public boolean userByUsername(User user) throws BusinessException; 

}
