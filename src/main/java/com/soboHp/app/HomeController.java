package com.soboHp.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soboHp.bean.User;
import com.soboHp.dao.UserDao;
import com.soboHp.util.UserUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static final String USER = "user";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest  request) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		try {
			// if user already login, goto login page
			User user = UserUtil.getUserFromSession(request);
			if ( user != null) {
				model.addAttribute("user", user);
				return "home";
			}
			
			// else, goto home page, and display all users to login
			ArrayList<User> users = UserDao.getAllUsers();
			model.addAttribute("users", users );
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model, HttpServletRequest  request) {
		logger.info("Welcome Login! the client locale is "+ locale.toString());
		
		try {
			int userId = Integer.valueOf(request.getParameter("user_id"));
			String password = request.getParameter("password");
			HttpSession session = request.getSession();
			
			User user = UserDao.getUserById(userId);
			if (user.getPassword() != null && !user.getPassword().equals(password)) {
				model.addAttribute("title", "Login Fail!");
				model.addAttribute("msg", "Login Fail: Username and Password does't match!!!");
				return "loginFail";
			}
			
			model.addAttribute("user", user);
			session.setAttribute(USER, user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("title", "Login Fail!");
			model.addAttribute("msg", e.getMessage());
			return "loginFail";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpServletRequest  request) {
		logger.info("Welcome Logout! the client locale is "+ locale.toString());
		
		try {
			HttpSession session = request.getSession();			
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return home(locale, model, request);
	}
}
