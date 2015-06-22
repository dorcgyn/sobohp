package com.soboHp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.soboHp.app.HomeController;
import com.soboHp.bean.User;
import com.soboHp.dao.UserDao;

public class UserUtil {
	public static User getUserFromSession (HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(HomeController.USER);
		return user;
	}
}
