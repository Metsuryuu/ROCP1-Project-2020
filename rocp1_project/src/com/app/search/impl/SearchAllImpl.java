package com.app.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;
import com.app.search.SearchAll;

public class SearchAllImpl implements SearchAll{

	@Override
	public boolean allUsers(List<User> ulist) throws BusinessException {
		
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "select userId,username,firstName,lastName,email,roleName from banking.users u, banking.role r "
					+"where u.roleId=r.roleId";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					
					flag = true;
				
					User user = new User();
					user.setUserId(resultSet.getInt("userId"));
					user.setUsername(resultSet.getString("username"));
					user.setFirstName(resultSet.getString("firstName"));
					user.setLastName(resultSet.getString("lastName"));
					user.setEmail(resultSet.getString("email"));
					Role role = new Role(resultSet.getString("roleName"));
					user.setRole(role);
					ulist.add(user);
				
				}
							
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}
		
		return flag;
	}

}
