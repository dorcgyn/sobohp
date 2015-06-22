package com.soboHp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import com.soboHp.bean.Transaction;
import com.soboHp.service.UserService;

public class TranDao {
	
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_CONFIRMED = "Confirmed";
	public static final String STATUS_REFUSED = "Refused";
	
	public static final String TYPE_NORMAL = "Normal";
	public static final String TYPE_CLEAR = "Clear";
	
	private static Random rand = new Random();
	
	public static int createTransaction (Transaction tran) throws Exception {
		String sql = "insert into soboHp.transaction_t " 
				+ "(lender_id, borrow_id, tran_date, location, amount, status, rid, comment, generator_id, type, created_when)" 
				+ "	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW());";
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		statement.setInt(1, tran.getLender_id());
		statement.setInt(2, tran.getBorrow_id());
		statement.setDate(3, new java.sql.Date(tran.getTran_date().getTime()));
		
		statement.setString(4, tran.getLocation());
		statement.setFloat(5, tran.getAmount());
		statement.setString(6, STATUS_PENDING);
		
	    int randomNum = rand.nextInt(Integer.MAX_VALUE);

		statement.setInt(7, randomNum);
		statement.setString(8, tran.getComment());
		statement.setInt(9, tran.getGenerator_id());
		statement.setString(10, tran.getType());
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		if(rs.next())
	    {
	        return rs.getInt(1);
	    }
		return -1;
	}
	
	public static void changeStatusTransaction (int id, String status) throws Exception {
		String sql = "update soboHp.transaction_t set status=? where id = ?;";
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		statement.setString(1, status);
		statement.setInt(2, id);
		
		statement.executeUpdate();
	}
	
	public static ArrayList<Transaction> getAllTransactionByUser(int currentUserId) throws Exception {
		String sql = "select * from soboHp.transaction_t where lender_id = ? or borrow_id = ?;"; 
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		
		statement.setInt(1, currentUserId);
		statement.setInt(2, currentUserId);
		
		ResultSet rs = statement.executeQuery();
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		
		Map userMap = UserService.getUserMap();
		
        while (rs.next()) {
           Transaction tran = new Transaction(rs, userMap);
           trans.add(tran);
        }
        return trans;
	}
	
	public static ArrayList<Transaction> getAllTransactionByGenerator(int userId) throws Exception {
		String sql = "select * from soboHp.transaction_t where generator_id = ?;"; 
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		
		statement.setInt(1, userId);
		
		Map userMap = UserService.getUserMap();
		
		ResultSet rs = statement.executeQuery();
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
        while (rs.next()) {
           Transaction tran = new Transaction(rs, userMap);
           trans.add(tran);
        }
        return trans;
	}
	
	public static Transaction getTransactionById(int tranId) throws Exception {
		String sql = "select * from soboHp.transaction_t where id = ?;"; 
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		
		statement.setInt(1, tranId);
		
		Map userMap = UserService.getUserMap();
		
		ResultSet rs = statement.executeQuery();
        if (rs.next()) {
           Transaction tran = new Transaction(rs, userMap);
           return tran;
        }
        return null;
	}
}
