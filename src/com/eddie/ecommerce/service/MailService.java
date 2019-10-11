package com.eddie.ecommerce.service;

public interface MailService {
	
	boolean sendMail(String to, String subject, String message)throws Exception;
}
