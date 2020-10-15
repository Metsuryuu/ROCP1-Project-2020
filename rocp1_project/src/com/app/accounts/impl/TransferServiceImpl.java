package com.app.accounts.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.accounts.TransferService;
import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Account;

public class TransferServiceImpl implements TransferService {

	@Override
	public boolean transfer(Account a1, Account a2, double amount) throws BusinessException {
		
		boolean flag = false;
		
		try(Connection connection = MySQLConnection.getConnection()){
			//set first account
			String sql = "Select balance from banking.accounts where accountId = ?";			
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, a1.getAccountId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				a1.setBalance(resultSet.getDouble("balance"));
				
			}
			
			//set the second account.
			sql = "Select balance from banking.accounts where accountId = ?";			
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, a2.getAccountId());
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				a2.setBalance(resultSet.getDouble("balance"));
				
			}
			
			a1.transfer(a2, amount);
			
			//update the accounts after the transfer.
			
			sql = "Update banking.accounts set balance = ? where accountId = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, a1.getBalance());
			preparedStatement.setInt(2, a1.getAccountId());
			
			int resultSet2 = preparedStatement.executeUpdate();
			
			if(resultSet2 != 0){
				flag = true;
			}else{
				throw new BusinessException("Deposit failed.");
			}
			
			sql = "Update banking.accounts set balance = ? where accountId = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, a2.getBalance());
			preparedStatement.setInt(2, a2.getAccountId());
			
			resultSet2 = preparedStatement.executeUpdate();
			
			if(resultSet2 != 0){
				flag = true;
			}else{
				throw new BusinessException("Transfer failed.");
			}
			
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Transfer failed."); 
		}
		
		return flag;
	}

}
