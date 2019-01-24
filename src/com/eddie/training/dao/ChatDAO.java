package com.eddie.training.dao;

import java.util.Date;

import com.eddie.training.model.Chat;
import com.eddie.training.model.Usuario;

public interface ChatDAO {
	public Chat findById(Integer id) throws Exception; 
	
	public Chat findByFecha(Date fecha)throws Exception;
	
	public Chat findByEmail(Usuario u) throws Exception;

	public Chat create(Chat c) throws Exception;
	
	public boolean update(Chat c) throws Exception;
	
	public void delete(Chat c) throws Exception;
}
