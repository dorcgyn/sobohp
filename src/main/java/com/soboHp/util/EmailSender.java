package com.soboHp.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {
	public static final String EMAIL_PASSWORD = "hpsobopwd";
	
	public static final String FROM_ADDR = "hpsobo@gmail.com";
	
	public static void send(String targetEmailAddr, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(FROM_ADDR, EMAIL_PASSWORD);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_ADDR));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(targetEmailAddr));
			message.setSubject(subject);
			message.setText(content);
 
			Transport.send(message);

 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
