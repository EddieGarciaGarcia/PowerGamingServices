package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;


public interface CreadorDAO {

	public Creador findbyIdCreador(Connection conexion,Integer id) throws InstanceNotFoundException, DataException;
	
	public List<Creador> findAll(Connection conexion) throws DataException;
	
	
}
