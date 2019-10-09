package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Chat;
import com.eddie.ecommerce.model.Usuario;

import java.util.Date;

public interface ChatDAO {
	public Chat findById(Integer id) throws InstanceNotFoundException, DataException;
	
	public Chat findByFecha(Date fecha)throws InstanceNotFoundException, DataException;
	
	public Chat findByEmail(Usuario u) throws InstanceNotFoundException, DataException;

	public Chat create(Chat c) throws DuplicateInstanceException, DataException;
	
	public boolean update(Chat c) throws InstanceNotFoundException, DataException;
	
	public void delete(Chat c) throws DataException;
}
