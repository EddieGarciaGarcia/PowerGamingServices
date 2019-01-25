package com.eddie.ecommerce.dao;


import java.sql.Connection;
import java.util.List;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Provincia;

public interface ProvinciaDAO {
	
	public Provincia findById(Connection conexion,Integer id) throws InstanceNotFoundException, DataException; 
	
	public List<Provincia> findAllByIdPais(Connection conexion, Integer idPais)throws DataException;

	public List<Provincia> findAll(Connection conexion)throws DataException;
}
