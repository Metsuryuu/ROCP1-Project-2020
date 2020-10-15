package com.app.update.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.User;
import com.app.update.UserUpdate;

public class UserUpdateImpl implements UserUpdate{

	@Override
	public boolean userUpdate(User user, String username, String flag) throws BusinessException {
		
		boolean b = false;
		
		
		try(Connection connection = MySQLConnection.getConnection()){
			String sql = "Update user set ? = ? where username = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, flag);
			switch(flag){
				case "username":
					preparedStatement.setString(2, user.getUsername());
					break;
				case "firstName":
					preparedStatement.setString(2, user.getFirstName());
					break;	
				case "lastName":
					preparedStatement.setString(2, user.getLastName());
					break;
				case "email":
					preparedStatement.setString(2, user.getEmail());
					break;
				case "password":
					preparedStatement.setString(2, user.getPassword());
					break;
				case "role":
					preparedStatement.setString(2, user.getRole().getRole());
					break;
				default:
					throw new BusinessException("Update failed.");
			}
			preparedStatement.setString(3, username);
			
			int resultSet = preparedStatement.executeUpdate();
			
			if(resultSet != 0){
				b = true;
			}else{
				throw new BusinessException("Failed to update.");
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error.");
		}
				
		return b;
	}

}
