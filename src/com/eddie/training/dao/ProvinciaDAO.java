package com.eddie.training.dao;


import java.sql.Connection;
import java.util.List;
import com.eddie.training.model.Provincia;

public interface ProvinciaDAO {
	
	public Provincia findById(Connection conexion,Integer id) throws Exception; 

	public List<Provincia> findAllById(Connection conexion)throws Exception;
}
