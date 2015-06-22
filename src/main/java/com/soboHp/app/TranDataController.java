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

import com.soboHp.bean.Transaction;
import com.soboHp.bean.User;
import com.soboHp.dao.UserDao;
import com.soboHp.service.TransactionService;
import com.soboHp.util.TranUtil;
import com.soboHp.util.UserUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TranDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(TranDataController.class);
	
	public static final String USER = "user";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/data/tran", method = RequestMethod.GET)
	public String tranReport(Locale locale, Model model, HttpServletRequest  request) {
		logger.info("Welcome data tran! the client locale is "+ locale.toString());
		
		try {
			// if user already login, goto login page
			User current = UserUtil.getUserFromSession(request);
			String targetUserId = request.getParameter("target_id");
			
			ArrayList<Transaction> trans = null;
			if (targetUserId.equalsIgnoreCase("all")) {
				trans = TransactionService.getConfirmedTrans(current, null);
			}
			else {
				// display for target user
				User target = UserDao.getUserById(Integer.parseInt(targetUserId));
				trans = TransactionService.getConfirmedTrans(current, target);
				model.addAttribute("targetUser", target);
			}
			
			model.addAttribute("trans", trans);
			int balance = TranUtil.calculateBalance(trans, current);
			model.addAttribute("balance", balance);
			int size = trans.size();
			model.addAttribute("size", size);	
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "data/report";
	}
}
