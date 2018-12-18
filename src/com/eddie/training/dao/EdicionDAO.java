package com.eddie.training.dao;

import java.util.List;

import com.eddie.training.model.Edicion;

public interface EdicionDAO {
	
	public List<Edicion> findByJuegoAll(Integer id) throws Exception;
	
	public Edicion create(Edicion e) throws Exception;
	
	public boolean update(Edicion e) throws Exception;
	
	public void delete(Edicion e) throws Exception;
		
	
}
