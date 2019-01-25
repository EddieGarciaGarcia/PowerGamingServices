package com.eddie.training.dao;

import com.eddie.training.model.Guia;

public interface GuiaDAO {

	public Guia findbyId(Integer id) throws Exception;
	
	public Guia create(Guia g) throws Exception;
	
	public boolean update(Guia g) throws Exception;
	
	public void delete(Guia g) throws Exception;
	
}
