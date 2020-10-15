package com.app.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;
import com.app.search.SearchByUsername;

public class SearchByUsernameImpl implements SearchByUsername{

	@Override
	public boolean userByUsername(User user) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "select userId,firstName,lastName,email,roleName from banking.users u, banking.role r "
					+"where username=? and u.roleId=r.roleId";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUsername());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				
				flag = true;
				
				user.setUserId(resultSet.getInt("userId"));
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
