package com.eddie.ecommerce.service;

public interface MailService {
	
	void sendMail(String to, String subject, String message)throws Exception;
}
