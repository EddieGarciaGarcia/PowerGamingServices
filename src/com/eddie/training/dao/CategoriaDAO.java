package com.eddie.training.dao;

import com.eddie.training.model.Categoria;

public interface CategoriaDAO {
	
	public Categoria findById(Integer id) throws Exception; 
	
}
