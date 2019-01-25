package com.eddie.ecommerce.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.eddie.ecommerce.service.MailService;

public class MailServiceImpl implements MailService {
	
	@Override
	public void sendMail(String to,String subject,String message) {
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("eddietuenti@gmail.com", PASSWORD));
		email.setSSLOnConnect(true);
		
		email.setSubject(subject);
		try {
			email.setFrom("eddie_taboada@hotmail.com");
			email.setMsg(message);
			email.addTo(to);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	public static final String PASSWORD="Hosner1994.";
	

	
	
}
