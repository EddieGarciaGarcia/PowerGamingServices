package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface PaisDAO {
	
	public Pais findById(Connection conexion,Integer id)throws InstanceNotFoundException, DataException;
		
	public List<Pais> findAllBy(Connection conexion)throws DataException;
}
