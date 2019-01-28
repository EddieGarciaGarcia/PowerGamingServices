package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Plataforma;


public interface PlataformaDAO {

	public Plataforma findbyIdPlataforma(Connection conexion,Integer id) throws InstanceNotFoundException, DataException;
	
	public List<Plataforma>  findAll(Connection conexion) throws DataException; 
	
	public List<Plataforma>  findByJuego(Connection conexion,Integer idJuego) throws DataException;
}
