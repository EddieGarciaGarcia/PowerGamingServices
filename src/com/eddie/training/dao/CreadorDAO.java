package com.eddie.training.dao;

import com.eddie.training.model.Creador;

public interface CreadorDAO {

	public Creador findbyIdFormato(Integer id) throws Exception;
	
	public boolean update(Creador c) throws Exception;
	
	public void delete(Creador c) throws Exception;
}
