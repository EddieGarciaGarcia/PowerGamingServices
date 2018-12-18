package com.eddie.training.dao;

import java.util.List;

import com.eddie.training.model.Pais;

public interface PaisDAO {
	
	public Pais findById(String id)throws Exception;
		
	public List<Pais> findAll()throws Exception;
}
