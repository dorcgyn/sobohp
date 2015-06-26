package com.soboHp.app;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soboHp.bean.User;
import com.soboHp.dao.UserDao;
import com.soboHp.service.EmailService;
import com.soboHp.util.UserUtil;


@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/user/changepwd", method = RequestMethod.GET)
	public String changePwd(Locale locale, Model model, HttpServletRequest  request) {
		return "pwd/changePwd";
	}
	
	@RequestMapping(value = "/user/changepwd", method = RequestMethod.POST)
	public String changePwdSucc(Locale locale, Model model, HttpServletRequest  request) {
		
		try {
			User user = UserUtil.getUserFromSession(request);
			
			String oldPwd = request.getParameter("oldPwd");
			oldPwd = oldPwd.trim();
			String newPwd = request.getParameter("newPwd");
			newPwd = newPwd.trim();
			String confirmPwd = request.getParameter("confirmPwd");
			confirmPwd = confirmPwd.trim();
			
			if (user.getPassword() == null || oldPwd.equals(user.getPassword())) {
				if (newPwd == null || newPwd.equals("")) {
					throw new Exception("Error: New Password could't be null!");
				}
				if (newPwd.equals(confirmPwd)) {
					UserDao.updateUserPwd(user.getId(), newPwd);
				} else {
					throw new Exception("Error: New Passord is not the same as Confirm Password!");
				}
			}
			else {
				throw new Exception("Error: Old Password does't match!!!");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		model.addAttribute("msg", "Change Password Successsfully");
		return "success";
	}
	
	// send email to user with temp password && active temp password url 
	@RequestMapping(value = "/user/forgetpwd", method = RequestMethod.GET)
	public String forget(Locale locale, Model model, HttpServletRequest  request) {		
		try {
			int userId = Integer.valueOf(request.getParameter("user_id"));
			
			Random random = new Random();
			int tempPwd = random.nextInt(Integer.MAX_VALUE - 1);
			String tempPwdStr = String.valueOf(tempPwd);
			
			UserDao.updateTempPwd(userId, tempPwdStr);
			User user = UserDao.getUserById(userId);
			
			EmailService.notifyTempPwd(user, tempPwdStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("title", "Error");
			model.addAttribute("msg", "Find Pwd Back Error: " + e.getMessage());
			return "loginFail";
		}
		model.addAttribute("title", "Success");
		model.addAttribute("msg", "Find Pwd Success!<br/> Have sent Email to you. Please click the link in Email to active your temp password!");
		return "loginFail";
	}
	
	// active temp password as password
	@RequestMapping(value = "/user/activeTempPwd", method = RequestMethod.GET)
	public String activeTempPwd(Locale locale, Model model, HttpServletRequest  request) {		
		try {
			String tempPwd = request.getParameter("tempPwd");	
			int userId = Integer.valueOf(request.getParameter("user_id"));
			User user = UserDao.getUserById(userId);
			
			if (user.getTempPwd() != null 
					&& user.getTempPwd().equals(tempPwd)) {
				UserDao.updateUserPwd(userId, tempPwd);
				model.addAttribute("title", "Success");
				model.addAttribute("msg", "Active Temp Password Success!<br/> Now you can login with your temp Password!");
			} else {
				model.addAttribute("title", "Error");
				model.addAttribute("msg", "Your temp password is not correct. Please try to find Password again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("title", "Error");
			model.addAttribute("msg", "Active Temp Password Error: " + e.getMessage());
			return "loginFail";
		}
		
		return "loginFail";
	}
}
