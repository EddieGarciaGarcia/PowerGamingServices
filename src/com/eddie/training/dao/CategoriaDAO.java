package com.eddie.training.dao;

import com.eddie.training.model.Categoria;

public interface CategoriaDAO {

	public Categoria create(Categoria c) throws Exception;
	
	public boolean update(Categoria c) throws Exception;
	
	public void delete(Categoria c) throws Exception;
}
