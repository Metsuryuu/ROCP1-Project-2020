package com.app.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;
import com.app.search.SearchById;

public class SearchByIdImpl implements SearchById{

	@Override
	public boolean userById(User user) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "select username,firstName,lastName,email,roleName from banking.users u, banking.role r "
					+"where userId=? and u.roleId=r.roleId";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, user.getUserId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				
				flag = true;
				
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
				Role role = new Role(resultSet.getString("roleName"));
				user.setRole(role);
				
			}else{
				throw new BusinessException("Invalid login credentials.");
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}
		
		return flag;
	}

}
