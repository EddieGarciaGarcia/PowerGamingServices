package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;

import java.sql.Connection;
import java.util.List;

public interface PaisDAO {
	
	Pais findById(Connection conexion, Integer id)throws DataException;
		
	List<Pais> findAll(Connection conexion)throws DataException;
}
