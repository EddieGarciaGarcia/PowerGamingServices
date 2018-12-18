package com.eddie.training.dao;

import com.eddie.training.model.Direccion;

public interface DireccionDAO {
	
	public Direccion create(Direccion d) throws Exception;
	
	public boolean update(Direccion d) throws Exception;
	
	public void delete(Direccion d) throws Exception;
}
