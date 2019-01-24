package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.Categoria;

public interface CategoriaDAO {
	
	public Categoria findById(Connection conexion,Integer id) throws Exception; 
	
	public List<Categoria>  findAll(Connection conexion, String idioma) throws Exception; 
	
}
