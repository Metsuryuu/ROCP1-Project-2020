package com.app.users.register.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;
import com.app.users.register.RegisterService;

public class RegisterServiceImpl implements RegisterService {

	@Override
	public boolean registerUser(User user, Role role) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection = MySQLConnection.getConnection()){
			
			String sql = "select roleId from banking.role where roleName=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, role.getRole());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				role.setRoleId(resultSet.getInt("roleId"));
				
			}
			
			sql = "insert into banking.users(username, password, firstName, lastName, email, roleId) values(?,?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setInt(6, role.getRoleId());
			
			int resultSet2 = preparedStatement.executeUpdate();

			if(resultSet2 != 0){
				flag = true;
			}else{
				throw new BusinessException("Registration failed.");
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Registration failed, please try again.");
		}
		
		return flag;
	}

}
