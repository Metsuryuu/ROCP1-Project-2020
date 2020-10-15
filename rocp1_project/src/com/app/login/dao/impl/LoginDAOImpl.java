package com.app.login.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.login.dao.LoginDAO;
import com.app.model.User;

public class LoginDAOImpl implements LoginDAO{

	@Override
	public boolean isValidUser(User user) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "select username from banking.users where username=? and password=?";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				flag = true;
			}else{
				throw new BusinessException("Invalid login credentials.");
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}
		return flag;
	}
	
	

}
