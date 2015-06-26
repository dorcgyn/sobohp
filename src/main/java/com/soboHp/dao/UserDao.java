package com.soboHp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.soboHp.bean.User;

public class UserDao {
	public static User getUserById(int id) throws Exception {
		ArrayList<User> users = getAllUsers();
		for (User user: users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public static ArrayList<User> getAllUsers () throws Exception {
		String sql = "select * from soboHp.user_t;"; 
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		ArrayList<User> users = new ArrayList<User>();
		
		while (rs.next()) {
           User user = new User();
           user.setId(rs.getInt("id"));
           user.setUsername(rs.getString("username"));
           user.setPassword(rs.getString("password"));
           user.setEmail(rs.getString("email"));
           user.setComments(rs.getString("comments"));
           user.setTempPwd(rs.getString("tempPwd"));
           users.add(user);
        }
		return users;
	}
	
	public static void updateUserPwd(int id, String newPwd) throws Exception  {
		String sql = "update soboHp.user_t set password = ? where id = ?;"; 
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		
		statement.setString(1, newPwd);
		statement.setInt(2, id);
		statement.execute();
	}
	
	public static void updateTempPwd(int userId, String tempPwd) throws Exception  {
		String sql = "update soboHp.user_t set tempPwd = ? where id = ?;"; 
		PreparedStatement  statement = 
				ConnectionManager.getConnection().prepareStatement(sql);
		
		statement.setString(1, tempPwd);
		statement.setInt(2, userId);
		statement.execute();
	}
	
}
