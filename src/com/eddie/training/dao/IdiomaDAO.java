package com.eddie.training.dao;

import com.eddie.training.model.Idioma;

public interface IdiomaDAO {
	
	public Idioma findById(Integer id) throws Exception;
	
	public void delete(Idioma c) throws Exception;
}
