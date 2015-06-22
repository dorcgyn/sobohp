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
}
