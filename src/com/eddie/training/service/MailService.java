package com.eddie.training.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailService {

	
	public MailService() {
		
	}
	
	public boolean sendMail(String message, String... to) {
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("eddietuenti@gmail.com", PASSWORD));
		email.setSSLOnConnect(true);
		
		email.setSubject("Primer mensage");
		try {
			email.setFrom("eddie_taboada@hotmail.com");
			email.setMsg(message);
			email.addTo(to);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return true;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	public static final String PASSWORD="Hosner1994.";
}
