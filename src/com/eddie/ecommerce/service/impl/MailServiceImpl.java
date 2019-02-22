package com.eddie.ecommerce.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.impl.CategoriaDAOImpl;
import com.eddie.ecommerce.service.MailService;

public class MailServiceImpl implements MailService {
	
	private static Logger logger=LogManager.getLogger(CategoriaDAOImpl.class);
	
	@Override
	public void sendMail(String to,String subject,String message) {
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("powergaming2019@gmail.com", PASSWORD));
		email.setSSLOnConnect(true);
		
		email.setSubject(subject);
		try {
			email.setMsg(message);
			email.addTo(to);
			email.send();
		} catch (EmailException e) {
			logger.error(e.getMessage(),e);
			
		}
		

	}
	public static final String PASSWORD="eddiegg1";
	

	
}
