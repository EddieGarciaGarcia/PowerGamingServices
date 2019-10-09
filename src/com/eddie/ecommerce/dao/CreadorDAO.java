package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Creador;

import java.sql.Connection;
import java.util.List;


public interface CreadorDAO {

	Creador findbyIdCreador(Connection conexion, Integer id) throws DataException;
	
	List<Creador> findAll(Connection conexion) throws DataException;
	
	
}
