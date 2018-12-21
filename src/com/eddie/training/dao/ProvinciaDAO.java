package com.eddie.training.dao;


import com.eddie.training.model.Provincia;

public interface ProvinciaDAO {
	
	public Provincia findById(Integer id) throws Exception; 

	public ProvinciaDAO create(ProvinciaDAO pro) throws Exception;
	
	public boolean update(ProvinciaDAO pro) throws Exception;
	
	public void delete(ProvinciaDAO pro) throws Exception;
}
