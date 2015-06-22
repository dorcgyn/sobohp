package com.soboHp.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soboHp.bean.Transaction;
import com.soboHp.bean.User;
import com.soboHp.dao.TranDao;
import com.soboHp.dao.UserDao;
import com.soboHp.service.EmailService;
import com.soboHp.service.TransactionService;
import com.soboHp.service.UserService;
import com.soboHp.util.TranUtil;
import com.soboHp.util.UserUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TransactionController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/tran/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model, HttpServletRequest  request) {
		logger.info("Welcome get create tran! the client locale is "+ locale.toString());
		
		try {
			User user = UserUtil.getUserFromSession(request);
			ArrayList<User> others = UserService.getOtherUsers(user);
			model.addAttribute("others", others );
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "tran/createTran";
	}
	
	@RequestMapping(value = "/tran/create", method = RequestMethod.POST)
	public String createSuccess(Locale locale, Model model, HttpServletRequest request) {		
		try {
			User user = UserUtil.getUserFromSession(request);
			
			Transaction tran = new Transaction();
			
			String type = request.getParameter("type");
			int targetId = Integer.valueOf(request.getParameter("target"));
			if (type.equalsIgnoreCase("lend")) { 
				tran.setBorrow_id(targetId);
				tran.setLender_id(user.getId());
			} else if (type.equalsIgnoreCase("borrow")) {
				tran.setLender_id(targetId);
				tran.setBorrow_id(user.getId());
			} else {
				// Exception handle
				return "home";
			}
			tran.setGenerator_id(user.getId());
			tran.setLocation(request.getParameter("location"));
			tran.setAmount(Float.valueOf(request.getParameter("amount")));
			tran.setComment(request.getParameter("comment"));
			tran.setType(TranDao.TYPE_NORMAL);
			
			String dateStr = request.getParameter("date");
			DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
			Date date = format.parse(dateStr);
			tran.setTran_date(date);
			
			Transaction newTran = TransactionService.createTransaction(tran);
			EmailService.notifyTran(newTran);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		model.addAttribute("msg", "Create transaction successsfully");
		return "success";
	}
	
	@RequestMapping(value = "/tran/history", method = RequestMethod.GET)
	public String history(Locale locale, Model model, HttpServletRequest request) {
		// logger.info("Welcome view tran! the client locale is "+ locale.toString());
		
		try {
			User user = UserUtil.getUserFromSession(request);
			
			ArrayList<Transaction> trans = TranDao.getAllTransactionByGenerator(user.getId());
			model.addAttribute("history", trans);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "tran/historyTran";
	}
	
	@RequestMapping(value = "/tran/pending", method = RequestMethod.GET)
	public String viewPending(Locale locale, Model model, HttpServletRequest request) {
		// logger.info("Welcome view tran! the client locale is "+ locale.toString());
		
		try {
			User user = UserUtil.getUserFromSession(request);
			ArrayList<Transaction> pending = TransactionService.getPendingTrans(user);
			model.addAttribute("pending", pending);
			ArrayList<Transaction> confirmedOrRefused = TransactionService.getConfirmedOrRefusedTrans(user);
			model.addAttribute("confirmedOrRefused", confirmedOrRefused);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "tran/pendingTran";
	}
	
	@RequestMapping(value = "/tran/confirm", method = RequestMethod.GET)
	public String confirm(Locale locale, Model model, HttpServletRequest request) {
		// logger.info("Welcome view tran! the client locale is "+ locale.toString());
		
		try {
			User user = UserUtil.getUserFromSession(request);
			int tranId = Integer.parseInt(request.getParameter("tran_id"));
		
			TransactionService.confirmTran(tranId, user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		model.addAttribute("msg", "Confirm Transaction successsfully");
		return "success";
	}
	
	@RequestMapping(value = "/tran/refuse", method = RequestMethod.GET)
	public String refuse(Locale locale, Model model, HttpServletRequest request) {
		// logger.info("Welcome view tran! the client locale is "+ locale.toString());
		
		try {
			User user = UserUtil.getUserFromSession(request);
			int tranId = Integer.parseInt(request.getParameter("tran_id"));
		
			TransactionService.refuseTran(tranId, user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		model.addAttribute("msg", "Refuse Transaction successsfully");
		return "success";
	}
	
	@RequestMapping(value = "/tran/report", method = RequestMethod.GET)
	public String report(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome view tran! the client locale is "+ locale.toString());
		
		try {
			User currentUser = UserUtil.getUserFromSession(request);
			
			ArrayList<User> others = UserService.getOtherUsers(currentUser);
			model.addAttribute("others", others);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "tran/reportTran";
	}
	
	@RequestMapping(value = "/tran/clear", method = RequestMethod.GET)
	public String clear(Locale locale, Model model, HttpServletRequest  request) {		
		try {
			User user = UserUtil.getUserFromSession(request);
			int targetId = Integer.valueOf(request.getParameter("target"));			
			float balance = Float.valueOf(request.getParameter("balance"));

			Transaction tran = new Transaction();
			if (balance < 0) { 
				tran.setBorrow_id(targetId);
				tran.setLender_id(user.getId());
			} else {
				tran.setLender_id(targetId);
				tran.setBorrow_id(user.getId());
			} 
			
			tran.setGenerator_id(user.getId());
			tran.setLocation("N/A");
			tran.setAmount(Math.abs(balance));
			tran.setComment("Clear balance!");
			tran.setType(TranDao.TYPE_CLEAR);
			tran.setTran_date(new Date());
			
			Transaction newTran = TransactionService.createTransaction(tran);
			EmailService.notifyTran(newTran);
			
			model.addAttribute("msg", "Clear your balance to " + newTran.getTargetUser().getUsername() + " successsfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
		
		return "success";
	}
}
