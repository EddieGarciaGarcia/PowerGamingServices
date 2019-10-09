package com.eddie.ecommerce.dao;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Provincia;

import java.sql.Connection;
import java.util.List;

public interface ProvinciaDAO {
	
	Provincia findById(Connection conexion, Integer id) throws DataException;
	
	List<Provincia> findAllByIdPais(Connection conexion, Integer idPais)throws DataException;

	List<Provincia> findAll(Connection conexion)throws DataException;
}
