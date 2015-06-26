package com.soboHp.service;

import com.soboHp.bean.Transaction;
import com.soboHp.bean.User;
import com.soboHp.util.EmailSender;

public class EmailService {
	public static void notifyTran(Transaction tran) {
		User targetUser = tran.getTargetUser();
		String targetEmailAddr = targetUser.getEmail();
		
		String subject = "You have a new Transaction!"; 
		
		String content = "Hi, " + targetUser.getUsername() + "\n\n" +
				tran.getGenerator().getUsername() + " has created a new transaction for you.\n"
				+ "Summary: " + tran.getLender().getUsername() + " lend " + tran.getAmount() + "$ to "
				+ tran.getBorrower().getUsername() + "\n" ;
		
		content += "\nDetails:" + tran.toEmail();
		
		EmailSender.send(targetEmailAddr, subject, content);
	}
	
	public static void notifyTempPwd(User user, String tempPwd) {
		String targetEmailAddr = user.getEmail();
		
		String subject = "You generate an Temp Password!";
		
		String content = "Hi, " + user.getUsername() + "\n\n" +
				"You try to find your password. Your temp Password is: " + tempPwd + "\n\n" +
				"Please click following url to ACTIVE your temp password:" + "\n" + 
				"http://sobohp.com/SoboHp/user/activeTempPwd?user_id=" + user.getId() + "&tempPwd=" +  tempPwd;
		
		EmailSender.send(targetEmailAddr, subject, content);
	}
}
