package com.app.accounts.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.accounts.AccountSearch;
import com.app.dbutil.MySQLConnection;
import com.app.exceptions.BusinessException;
import com.app.model.Account;
import com.app.model.AccountStatus;
import com.app.model.AccountType;
import com.app.model.User;

public class AccountSearchImpl implements AccountSearch {

	@Override
	public boolean byUser(Account account, User user) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "SELECT accountId, statusId, typeId, balance FROM banking.accounts where userId=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, user.getUserId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					
					flag = true;
				
					AccountStatus status = new AccountStatus();
					AccountType type = new AccountType();
					account.setAccountId(resultSet.getInt("accountId"));
					status.setStatusId(resultSet.getInt("statusId"));
					account.setBalance(resultSet.getDouble("balance"));
					type.setTypeId(resultSet.getInt("typeId"));
					account.setStatus(status);
					account.setType(type);
				
				}
							
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}
		
		return flag;
	}

	@Override
	public boolean byId(Account account) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "SELECT statusId, typeId, balance FROM banking.accounts where accountId=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, account.getAccountId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					
					flag = true;
				
					AccountStatus status = new AccountStatus();
					AccountType type = new AccountType();
					status.setStatusId(resultSet.getInt("statusId"));
					account.setBalance(resultSet.getDouble("balance"));
					type.setTypeId(resultSet.getInt("typeId"));
					account.setStatus(status);
					account.setType(type);
				
				}
							
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}
		
		return flag;
	}

	@Override
	public boolean byStatus(Account account) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection=MySQLConnection.getConnection()){
			
			String sql = "SELECT accountId, typeId, balance FROM banking.accounts where statusId=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, account.getStatus().getStatusId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					
					flag = true;
				
					AccountType type = new AccountType();
					account.setAccountId(resultSet.getInt("accountId"));
					account.setBalance(resultSet.getDouble("balance"));
					type.setTypeId(resultSet.getInt("typeId"));
					account.setType(type);
				
				}
							
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error occurred. Please try again later.");
		}

		return flag;
	}

	@Override
	public boolean byAll(List<Account> alist) throws BusinessException {
		boolean flag = false;
		
		try(Connection connection = MySQLConnection.getConnection()){
			
			String sql = "SELECT accountId, statusId, typeId, balance FROM banking.accounts";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				flag = true;
				
				Account account = new Account();
				AccountStatus status = new AccountStatus();
				AccountType type = new AccountType();
				account.setAccountId(resultSet.getInt("accountId"));
				status.setStatusId(resultSet.getInt("statusId"));
				account.setBalance(resultSet.getDouble("balance"));
				type.setTypeId(resultSet.getInt("typeId"));
				account.setStatus(status);
				account.setType(type);
				alist.add(account);
				
				
			}
			
		}catch(ClassNotFoundException|SQLException e){
			throw new BusinessException("Internal error.");
		}
		return flag;
	}

}
