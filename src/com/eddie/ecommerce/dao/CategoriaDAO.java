package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Categoria;

import java.sql.Connection;
import java.util.List;

public interface CategoriaDAO {
	
	Categoria findById(Connection conexion, Integer id, String idioma) throws DataException;

	List<Categoria>  findAll(Connection conexion, String idioma) throws DataException;

	List<Categoria>  findByJuego(Connection conexion, Integer idJuego, String idioma) throws DataException;
}
