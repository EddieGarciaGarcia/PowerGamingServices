package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface DireccionDAO {
	
	public Direccion findById(Integer id) throws InstanceNotFoundException, DataException;
	
	public Direccion create(Direccion d) throws DuplicateInstanceException, DataException;
	
	public boolean update(Direccion d) throws InstanceNotFoundException, DataException;
	
	public void delete(Direccion d) throws DataException;
}
