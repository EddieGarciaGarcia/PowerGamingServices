package com.eddie.ecommerce.dao;

import java.util.List;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface EdicionDAO {
	
	public List<Edicion> findByIdEdicion(Integer id) throws DataException;
	
	public Edicion create(Edicion e) throws DuplicateInstanceException, DataException;
	
	public boolean update(Edicion e) throws InstanceNotFoundException, DataException;
	
	public void delete(Edicion e) throws DataException;
		
	
}
