package com.eddie.training.dao;

import com.eddie.training.model.Formato;

public interface FormatoDAO {

	public Formato findbyID(Integer id) throws Exception;
	
	public Formato create(Formato f) throws Exception;
	
	public boolean update(Formato f) throws Exception;
	
	public void delete(Formato f) throws Exception;
	
}
