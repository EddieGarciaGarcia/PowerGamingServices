package com.eddie.training.dao;

import com.eddie.training.model.Idioma;

public interface IdiomaDAO {
	
	public Idioma findById(Integer id) throws Exception;

	public Idioma create(Idioma c) throws Exception;
	
	public boolean update(Idioma c) throws Exception;
	
	public void delete(Idioma c) throws Exception;
}
