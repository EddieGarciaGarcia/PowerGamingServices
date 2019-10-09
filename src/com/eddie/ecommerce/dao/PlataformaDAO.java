package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Plataforma;

import java.sql.Connection;
import java.util.List;


public interface PlataformaDAO {

	Plataforma findbyIdPlataforma(Connection conexion, Integer id) throws DataException;
	
	List<Plataforma>  findAll(Connection conexion) throws DataException;

	List<Plataforma>  findByJuego(Connection conexion, Integer idJuego)throws DataException;

}
