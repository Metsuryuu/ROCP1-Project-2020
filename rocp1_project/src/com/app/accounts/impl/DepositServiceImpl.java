package com.app.accounts.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.accounts.DepositService;
import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Account;

public class DepositServiceImpl implements DepositService{

	@Override
	public boolean deposit(Account account, double amount) throws BusinessException {
		
		boolean flag = false;
		
		try(Connection connection = MySQLConnection.getConnection()){
			String sql = "Select balance from banking.accounts where accountId = ?";			
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, account.getAccountId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				account.setBalance(resultSet.getDouble("balance"));
				
			}
			
			account.deposit(amount);
			
			sql = "Update banking.accounts set balance = ? where accountId = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, account.getBalance());
			preparedStatement.setInt(2, account.getAccountId());
			
			int resultSet2 = preparedStatement.executeUpdate();
			
			if(resultSet2 != 0){
				flag = true;
			}else{
				throw new BusinessException("Deposit failed.");
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Deposit failed."); 
		}
		
		return flag;
	}

}
