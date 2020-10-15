package com.app.credentials.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.credentials.dao.CredentialsDAO;
import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public class CredentialsDAOImpl implements CredentialsDAO{

	@Override
	public boolean credentials(User user, Role role) throws BusinessException {	//meant to set the role to the user object.
		
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "select r.roleId, r.roleName from banking.users u, banking.role r where u.username = ? and u.password = ? and u.roleId = r.roleId;";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				
				flag = true;
				
				role.setRoleId(resultSet.getInt("roleId"));
				role.setRole(resultSet.getString("roleName"));
				
			}else{
				throw new BusinessException("No such account found, please register to gain access.");
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}
		
		return flag;
	}
	
	

}
