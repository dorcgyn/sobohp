package com.soboHp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.soboHp.bean.User;
import com.soboHp.dao.UserDao;

public class UserService {
	public static ArrayList<User> getOtherUsers(User currentUser) throws Exception {
		ArrayList<User> allUsers = UserDao.getAllUsers();
		
		for (User user : allUsers) {
			if (user.getId() == currentUser.getId()) {
				allUsers.remove(user);
				break;
			}
		}
		return allUsers;
	}
	
	public static Map<Integer, User> getUserMap() throws Exception {
		ArrayList<User> allUsers = UserDao.getAllUsers();
		HashMap<Integer, User> userMap = new HashMap<Integer, User>();
		
		for (User user : allUsers) {
			userMap.put(user.getId(), user);
		}
		return userMap;
	}
}
